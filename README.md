# Wolf of Wright Street - API Group Repository
- This is the API repository for the Transaction Tracker project. Our main repository can be found [here](https://github.com/WSU-kduncan/cs4900-wolf-of-wright-street).
- Our group's UI work is in a separate repository, linked [here](https://github.com/WSU-kduncan/cs4900-ui-wolf-of-wright-street).

### Group Members
- Kyle Cox
- Daniel Cronauer
- Samuel Kondall
- Cyrus Straley

## API definitions

- Our API Route definitions can be found here
  
  [API Routes](bruno/api-routes-plan.md)

## API Instructions

### Recommended Tools
- For Windows users, it's recommended to have a version of ubuntu installed via WSL2.

### Required Tools
- Ensure that you have java 17 installed in the environment that you will run the program from. 
- Docker desktop is also required to be installed on your local machine. 
- Ensure you have Bruno installed on your local machine to test API routes.
- Ensure that VSCode is installed in the environment you will run the program from. 
- In VSCode, search for and install the following extensions to remote:
  - Gradle for Java
  - Language support for Java
  - Project Manager for Java 
  - Extension Pack for Java 


### How to run API
1. Have docker desktop running on local machine.
2. Navigate to the DATABASE folder inside our main project repo, then into DBeaver_Docker_Integration folder. Run the command `docker compose up` from your terminal.
3. Now that the DB is running, navigate to the cs4900-api-wolf-of-wright-street repo, and run the command `code .` to open VSCode from this directory. Ensure that you are on the main `develop` branch.
4. Once inside VSCode, navigate to the `WolfOfWrightStreetApplication.java` file located under `src/main/java/com/wolf/budgetapp`, right click on the file and select Run Java. 
5. Upon successful start of the service, your console should print a message resembling this: `Started WolfOfWrightStreetApplication in 15.779 seconds (process running for 16.434)`

### How to test API
1. While the service is running, open Bruno, select Collection -> open collection in the top left corner. Navigate to wherever you installed the `cs4900-api-wolf-of-wright-street` repo, click inside the repo, click inside the bruno folder, and finally select the `Wolf_of_Wright_Street_Service` folder.
2. In bruno, select the Wolf_of_Wright_Street_Service drop down on the left panel. From here, you should see get, put, post, and delete requests for each of our endpoints.
3. To test an endpoint, click on a request, ensure in the top right corner of bruno that your environment is set to local, then click on the arrow at the very end of the request url to run the request.
4. If the request ran successfully, you will see a `200 OK` green response in the top right corner of bruno, as well as a JSON response body.
5. For example, testing the `get-all-users` request will return the response: `[
  {
    "emailAddress": "user@wolf.com",
    "firstName": "Joe",
    "lastName": "Schmoe"
  }
]`