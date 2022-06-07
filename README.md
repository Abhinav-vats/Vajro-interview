### Vajro-Interview-Application

Following are steps to run application through IDE
- Import the project into the IDE for example IntelliJ or Eclipse
- Let the IDE first build the project.
- Once the project is build you will be able to see Maven Dependencies file in the Package explorer of the IDE
- Now go to VajroInterviewApplication.java file.
- Right click any where on the screen click on Run as -> Java application
- Once application is started one can use the application endpoint localhost port 8080.

Following are the steps to generate deployable jar file

- Go to the folder where you have extracted or cloned the repository on command prompt.
- Run the command: mvn clean package
- This will create jar in the target folder of the repository
- this .jar file can be deployed any where


### URL Endpoint of the application:

URL: https/http://hostname/product/all

Method: GET

Header: Authorization (value will be the access token provided in the Case study)

Query parameters: All query parameters are optional:

- sortBy: This parameter is used to sort product list as per the given value. There are 4 values 1. title 2. price 3.date else it will automatically sort according to product id 
- page: This is for pagination, and values vary from 0 to 4 having every page 10 product
- isReverse: With sortBy it will reverse the order of the product list. 0 for Ascending and 1 for descending.
- inStock: This parameter is used for filtering only those product which are in stock. Passing value as 1 will return all product in stock.
- outOfStock: This parameter is used for filtering only those product which are out of stock. Passing value as 1 will return all product out of stock.
- priceLessThan: This parameter is used for filtering only those product whose price is less then the given value of the parameter.
- priceGreaterThan: This parameter is used for filtering only those product whose price is greater then the given value of the parameter.


### Deployed application on Heroku cloud platform.

URL: https://vajro-interview.herokuapp.com/product/all

Method: GET

Header: Authorization (value will be the access token provided in the Case study)

Query parameters: All query parameters are optional:

- sortBy: This parameter is used to sort product list as per the given value. There are 4 values 1. title 2. price 3.date else it will automatically sort according to product id 
- page: This is for pagination, and values vary from 0 to 4 having every page 10 product
- isReverse: With sortBy it will reverse the order of the product list. 0 for Ascending and 1 for descending.
- inStock: This parameter is used for filtering only those product which are in stock. Passing value as 1 will return all product in stock.
- outOfStock: This parameter is used for filtering only those product which are out of stock. Passing value as 1 will return all product out of stock.
- priceLessThan: This parameter is used for filtering only those product whose price is less then the given value of the parameter.
- priceGreaterThan: This parameter is used for filtering only those product whose price is greater then the given value of the parameter.
