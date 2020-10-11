# clone-try1
The project implements simplified twitter clone for:
- User registration
- Log in/Log out
- Create a post of 140 characters
- Search for users
- Follow an user
- Like a post
- List all posts for an use
- Show feeds of users who you follow and posts that you like.

The project was built and run in Windows environment in eclipse and Windows Command Prompt. The testing was done using Postman. The poject is a java project that uses PostgreSQL as database, dropwizard framework, hibernate and maven. The application launches on port 8080.

## Prerequisites
- Windows Environment
- Apache Maven 3.6.3
- Maven path setup in windows environment variable
- Stable internet connection (for maven to download dependency jars)
- PostgreSQL 4.26

## Setup
- clone the poject
- create a database called 'clone' using the "CREATE DATABASE" commands in the file setup\createTables.txt. The expected port, username, database name and password are available in the yaml file.
- navigate to clone-try1.yaml under the project and update the fields (if needed):
  - user
  - password
  - database name in url (jdbc:postgresql://localhost/<put the database name here>)
  - port

## Build and Start
- Open powershell and navigate to the directory where the project is cloned and run:
```
mvn clean install
```
It should return "BUILD SUCCESS".
  - run:
```
java -jar target/clone-try1-0.0.1-SNAPSHOT.jar server clone-try1.yml
```
It should return:
```
usage: java -jar clone-try1-0.0.1-SNAPSHOT.jar
       [-h] [-v] {server,check} ...

positional arguments:
  {server,check}         available commands

named arguments:
  -h, --help             show this help message and exit
  -v, --version          show the application version and exit
```
  - run:
```
java -jar target/clone-try1-0.0.1-SNAPSHOT.jar server clone-try1.yml
```
The server should start with the last line of the message similar to:
```
INFO  [2020-10-11 18:46:40,492] org.eclipse.jetty.server.Server: Started @5208ms
```

## Test
Use the file setup\twitterclone.postman_collection. Import the file in Postman.

- User registration
  - POST. URL: http://localhost:8080/user/signup
  - The body expects data of format:
```
{
    "userName": "user",
    "password":"password"
}
```
- Log in/Log out
  - It returns an auth code. The auth code is necessary to maintain a session and all actions from on here will need the generated auth code to proceed. If the auth code has any spaces, they need to be removed. The auth code is passed in the header with key "token".
  - POST. URL: http://localhost:8080/user/signin/user
  - The body expects data for format:
```
{
    "userName": "user",
    "password":"password"
}
```
- Create a post of 140 characters
  - POST. URL: http://localhost:8080/post/create
  - The body expects data of format:
```
{
    "postData": "this is 3rd post",
    "createTime":"11-10-2020 00:09"
}
```
- Search for users
  - GET. UTL: http://localhost:8080/user/find?user=userName
  - The data is passed in params in key "user" and a value of a user in value.
- Follow an user
  - GET. URL: http://localhost:8080/user/follow/user?user=ketan
  - The data is passed in params in key "user" and a value of a user in value.
- Like a post
  - The data for keys "user" and "postId" comes from the Post table in the database.
  - POST. URL: http://localhost:8080/post/like/current
  - The body expects data of format:
```
{
    "postData": "this is 1st post",
    "user":"794dc6d8-eb2a-4b58-a8ec-0728a1d440e2",
    "createTime": "11-10-2020 00:09",
    "postId":"acb15cc4-8588-465d-8fd5-373f42b110cb"
}
```
- Like all posts for an use
  - GET. URL: http://localhost:8080/post/like/all?user=ketan
  - The data is passed in params in key "user" and a value of a user in value.
- Show feeds of users who you follow and posts that you like
  - GET. URL: http://localhost:8080/post/view/user/all
  - No data is passed to the request except the auth token.
- Logout
  - DELETE. URL: http://localhost:8080/user/signout
  - No data is passed to the request except the auth token.
