# Springboot Json Web Token (JWT) Secure API project
This is the secure API project developed in Java using the [Springboot](https://spring.io/projects/spring-boot) framework. This project will demostrate Json Web Token (JWT) in action including some additional implementation that categorized users roles based on the JWT provided. All these security feature were possible with the help of the [Spring Security](https://spring.io/projects/spring-security) framework which is a powerful and highly customizable authentication and access-control framework.

In this demo REST api project users can publish posts by providing a title, description and categories. Each user will be assign to a specific role which is categorized in three level, namely:
```bash
* Root
 * Admin
  * User
```
**Root**: user which has the authority to create and remove post items, admins and users.<br/>
**Admin**: Admin is a bit restricted when it comes to performing tasks. For instance an admin can remove post items and users, but cannot remove other admin users nor viewing the list of admins.<br/>
**User**: the user is the normal user which is more limited to resources and only able to perform normal tasks such as publish posts and view profile of users.<br/>
All API's are documented using [Swagger UI](https://swagger.io/tools/swagger-ui/).<br/><br/>

Note: The front-end of this project is still in development and will be soon available.


## Installation

Download or clone project and open it in your Java IDE. Recommended IDE for this project is [Intelli J](https://www.jetbrains.com/idea/) by JetBrains.<br/>
Make sure you have atleast [Java 1.8 SDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) installed.

## Usage

Run this Spring boot project and in case you encounter issue with the certification just create your own self-signed certification, add it to the project and configure it in the _ _application.properties_ _ file.

## Testing

The project includes some unit test cases and to conduct the integration test, please download and run [Secure API Integration Test](https://github.com/mel182/secure_api_integration_test) 

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.
