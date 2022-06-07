package com.myshopify.Vajrointerview.serivice.Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.myshopify.Vajrointerview.Entity.ProductEntity;
import com.myshopify.Vajrointerview.Entity.Variant;

import com.myshopify.Vajrointerview.ValueObject.ImageSrcVO;
import com.myshopify.Vajrointerview.ValueObject.VariantVO;
import com.myshopify.Vajrointerview.dto.Filter;
import com.myshopify.Vajrointerview.ValueObject.ProductVO;
import com.myshopify.Vajrointerview.repository.VajroInterviewProductRepository;
import com.myshopify.Vajrointerview.Entity.FinalResponseDTO;
import com.myshopify.Vajrointerview.Entity.ImageSrc;
import com.myshopify.Vajrointerview.Entity.ProductResponseDTO;
import com.myshopify.Vajrointerview.Entity.ShopInfo;
import com.myshopify.Vajrointerview.Entity.ShopInfoEntity;
import com.myshopify.Vajrointerview.serivice.VajroInterviewProductService;

@SuppressWarnings("unchecked")
@Service
public class VajroInterviewProductServiceImpl implements VajroInterviewProductService {

	private final static Logger logger = LogManager.getLogger(VajroInterviewProductServiceImpl.class);

	@Autowired
	VajroInterviewProductRepository vajroInterviewProductRepository;

	private final static RestTemplate restTemplate = new RestTemplate();

	@SuppressWarnings("rawtypes")
	private static ProductResponseDTO getAllProduct(final String authorization) {

		logger.info("Entered: getAllProduct");

		final HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		header.add("X-Shopify-Access-Token", authorization);

		final HttpEntity entity = new HttpEntity(header);

		final ResponseEntity<ProductResponseDTO> response = restTemplate.exchange(
				"https://vajro-interview.myshopify.com/admin/api/2022-04/products.json", HttpMethod.GET, entity,
				ProductResponseDTO.class);

		logger.info("Exited: getAllProduct");
		return response.getBody();
	}

	private List<ProductVO> copyDataToVOClass(final List<ProductEntity> productEntites) {
		logger.info("Entered: copyDataToVOClass");

		final List<ProductVO> productVOs = new ArrayList<ProductVO>();

		productEntites.forEach(productEntity -> {
			ProductVO productVO = new ProductVO();

			productVO.setId(productEntity.getId());
			productVO.setCreated_at(productEntity.getCreated_at());
			productVO.setStatus(productEntity.getStatus());
			productVO.setTitle(productEntity.getTitle());

			final List<ImageSrcVO> imageSrcVOs = new ArrayList<>();
			final List<VariantVO> variantVOs = new ArrayList<>();

			productEntity.getVariants().forEach(variant -> {
				final VariantVO variantVO = new VariantVO();

				variantVO.setCompare_at_price(variant.getCompare_at_price());
				variantVO.setId(variant.getId());
				variantVO.setInventory_item_id(variant.getInventory_item_id());
				variantVO.setInventory_quantity(variant.getInventory_quantity());
				variantVO.setPrice(String.valueOf(variant.getPrice()));

				variantVO.setRequires_shipping(variant.getRequires_shipping());
				variantVO.setTitle(variant.getTitle());

				variantVOs.add(variantVO);
			});

			productEntity.getImages().forEach(img -> {
				final ImageSrcVO imageSrcVO = new ImageSrcVO();
				BeanUtils.copyProperties(img, imageSrcVO);
				imageSrcVOs.add(imageSrcVO);
			});

			productVO.setVariants(variantVOs);
			productVO.setImages(imageSrcVOs);
			productVOs.add(productVO);

		});

		logger.info("Exited: copyDataToVOClass");
		return productVOs;
	}

	private static List<ProductEntity> formatData(final ProductResponseDTO productResponseDTO) {
		logger.info("Entered: formatData");

		List<ProductEntity> productEntities = new ArrayList<ProductEntity>();

		productResponseDTO.getProducts().forEach(product -> {

			final List<Variant> variants = new ArrayList<Variant>();
			final List<ImageSrc> imageSrcs = new ArrayList<>();
			product.getVariants().forEach(varient -> {
				final Variant variant = new Variant();

				variant.setId(varient.getId());
				variant.setCompare_at_price(varient.getCompare_at_price());
				variant.setInventory_item_id(varient.getInventory_item_id());
				variant.setInventory_quantity(varient.getInventory_quantity());
				variant.setPrice(Double.parseDouble(varient.getPrice()));
				variant.setRequires_shipping(varient.getRequires_shipping());
				variant.setTitle(varient.getTitle());

				variants.add(variant);
			});

			product.getImages().forEach(img -> {
				final ImageSrc imageSrc = new ImageSrc();

				imageSrc.setSrc(img.getSrc());

				imageSrcs.add(imageSrc);
			});

			final ProductEntity productEntity = new ProductEntity(product.getId(), product.getTitle(),
					product.getCreated_at(), product.getStatus(), variants, imageSrcs);
			productEntities.add(productEntity);
		});

		logger.info("Exited: formatData");
		return productEntities;
	}

	@Override
	public FinalResponseDTO getAllDataPage(String sortBy, Integer page, String isReverse, Filter filter,
			final String authorization) throws HttpClientErrorException{
		logger.info("Entered: getAllDataPage");
		FinalResponseDTO finalResponseDTO = null;
		try {
			final ProductResponseDTO productResponseDTO = getAllProduct(authorization);
			final List<ProductEntity> productEntities = formatData(productResponseDTO);

			final Pageable pageRequest = PageRequest.of(page, 10);

			final ShopInfoEntity shopInfoEntity = vajroInterviewProductRepository.getShopInfoData();

			final ShopInfo shop_info = new ShopInfo();
			shop_info.setName(shopInfoEntity.getName());
			shop_info.setDomain(shopInfoEntity.getDomain());

			final List<ProductVO> productVOs = filterAndSortVolist(copyDataToVOClass(productEntities), sortBy, filter);
			System.out.println(productVOs.size());

			if (isReverse.equalsIgnoreCase("1")) {
				Collections.reverse(productVOs);
			}

			final int start = (int) pageRequest.getOffset();
			final int end = Math.min((start + pageRequest.getPageSize()), productVOs.size());

			final Page<ProductVO> pageProductEntity = new PageImpl<ProductVO>(productVOs.subList(start, end), pageRequest,
					productEntities.size());
			
			if(productEntities.size()!=50) {
				vajroInterviewProductRepository.updateCountValue(shopInfoEntity.getName(), (long) productEntities.size());
			}

			finalResponseDTO = new FinalResponseDTO(pageProductEntity.toList(),
					vajroInterviewProductRepository.getCount(shopInfoEntity.getName()), shop_info);
		}catch (HttpClientErrorException e) {
			throw e;
		}catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
		}

		logger.info("Exited: getAllDataPage");
		return finalResponseDTO;
	}

	private List<ProductVO> filterAndSortVolist(final List<ProductVO> list, final String sortBy, final Filter filter) {
		logger.info("Entered: filterAndSortVolist");
		final List<ProductVO> productVOs = filterProductVOs(list, filter);
		final List<ProductVO> productVOsSorted = sortProductVos(productVOs, sortBy);

		logger.info("Exited: filterAndSortVolist");
		return productVOsSorted;
	}

	private List<ProductVO> sortProductVos(final List<ProductVO> productVOs, final String sortBy) {

		logger.info("Entered: sortProductVos");

		switch (sortBy) {
		case "title":
			logger.info("Exited: sortProductVos");
			return productVOs.stream().sorted((p1, p2) -> p1.getTitle().compareTo(p2.getTitle()))
					.collect(Collectors.toList());
		case "price":
			logger.info("Exited: sortProductVos");
			return productVOs.stream()
					.sorted((p1, p2) -> Double.compare(Double.parseDouble(p1.getVariants().get(0).getPrice()),
							Double.parseDouble(p2.getVariants().get(0).getPrice())))
					.collect(Collectors.toList());
		case "date":
			logger.info("Exited: sortProductVos");
			return productVOs.stream().sorted((p1, p2) -> p1.getCreated_at().compareTo(p2.getCreated_at()))
					.collect(Collectors.toList());
		default:
			logger.info("Exited: sortProductVos");
			return productVOs.stream().sorted((p1, p2) -> p1.getId().compareTo(p2.getId()))
					.collect(Collectors.toList());

		}
	}

	private List<ProductVO> filterProductVOs(final List<ProductVO> productVOs, final Filter filter) {
		logger.info("Entered: filterProductVOs");

		List<ProductVO> productVOsList = new ArrayList<ProductVO>();

		if (!filter.getInStock().equals(filter.getOutOfStock())) {
			if (filter.getInStock().equals("1")) {
				for (ProductVO productVO : productVOs) {
					for (VariantVO variantVO : productVO.getVariants()) {
						if (variantVO.getInventory_quantity() > 0) {
							productVOsList.add(productVO);
							break;
						}
					}
				}

			}

			if (filter.getOutOfStock().equals("1")) {
				for (ProductVO productVO : productVOs) {
					for (VariantVO variantVO : productVO.getVariants()) {
						if (variantVO.getInventory_quantity() == 0) {
							productVOsList.add(productVO);
							break;
						}
					}
				}
			}

		} else {
			productVOsList = productVOs;
		}

		final List<ProductVO> productVOs2 = new ArrayList<ProductVO>();

		for (ProductVO productVO : productVOsList) {
			for (VariantVO variantVO : productVO.getVariants()) {
				if (Double.parseDouble(variantVO.getPrice()) >= Double.parseDouble(filter.getPriceGreaterThan())
						&& Double.parseDouble(variantVO.getPrice()) <= Double.parseDouble(filter.getPriceLessThan())) {
					productVOs2.add(productVO);
					break;
				}
			}
		}

		logger.info("Exited: filterProductVOs");
		return productVOs2;
	}

}
