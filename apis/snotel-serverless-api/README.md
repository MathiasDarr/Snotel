### Serverless Snotel API ###
SAM application that allows querying of the Snotel dynamoDB table

### Application dependencies ###
    - SAM (AWS serverless application model)
    - Data pipeline must have been run

### Deploying the application to the cloud ###
* bash deploy.sh

### Testing the application locally ###
* sam local start-api (This is currently broken because the sam local sends the query params differently, better to make the request from the front end, 
or to deploy the applicaiton to the cloud )
* Make request to server with the following URL in browser or postman
    - http://127.0.0.1:3000/snotel?location=Trinity&sdate=20140101&edate=20140201
    
## TODO  ##
* set output of cloudformation template to a URL that can be queried