# Snotel Pipeline README #
This repository includes a dockerized data pipeline which scrapes snowpack & streamflow data from the USDA and pushes to a DynamoDB table.
I provide implementaitons of equivalent APIs using spring boot, flask & a serverless API using AWS SAM


The range of dates which the scraper runs can be changed in the scrape_snowpack_data() function, at the bottom of the 
snotel.py file.  


### Snotel Pipeline, Query API & FrontEnd ###

* Data Pipeline
    - Containerized data pipeline which scrapes USDA website with the BeatifulSoup python library
    - Push data items to DynamoDB
* Query API
    - Implmentations of a query APIs in Spring Boot, Flask & a serverless API using the AWS SAM
* Front End Website for querying the data & data visualization using D3 JS
    - Current implementation 


### Reproduction steps ###

* Run pipeline
    - docker-compose -f snotel-dynamo-compose.yaml up --build
* Run tests


### In Development ###
* Currently item is being pushed to DynamoDB indivually when I should be performing batch operations
* Add the Vue front end 
* Create a React front end
