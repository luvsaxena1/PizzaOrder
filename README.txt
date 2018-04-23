# Pizza Order Project
Please follow the following steps to run the application :
1. Open the command prompt and go to the root folder of the project
2. As this is Mavenized  project run command "mvn clean install". This will also run all the test cases.
3. The executable jar with name PizzaOrder will be created in target folder
4. Run the Application by executing jar with the below command
	java -jar PizzaOrder.jar --file.source=C://Users//windows//Desktop//Interview//Aquent//sample_data_ordered.txt --file.destination=C://Users//windows//Desktop//Interview//Aquent//sample_data_sorted.txt

NOTE: --file.source and file.destination path will be your local system path.

The above command deploy the Spring boot application on in build tomcat server on port 8080

5. Convert the source file to human readable file by using the below Rest API call

	http://localhost:8080/v1/order/convert

6. Application Swagger Documentation is on : http:\\localhost:8080\swagger-ui.html