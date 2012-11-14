spring-social-sparklr
=====================

Spring Social binding and connect support for the Sparklr sample application from spring-security-oauth

Requires a customised version of the Sparklr application to be running on port 8080.

The customised Sparklr simply adds an endpoint to the base Sparklr project which returns the currently authenticated user details to support spring social - the code is found in the spring-social-support branch of the following project:

https://github.com/michaellavelle/spring-security-oauth

Running the customised Sparklr demo application
-----------------------------------------------

git clone https://github.com/michaellavelle/spring-security-oauth.git

cd spring-security-oauth

git checkout -b spring-social-support origin/spring-social-support

mvn install -P bootstrap

cd samples/oauth2/sparklr

mvn tomcat:run

Using spring-social-sparklr to connect to Sparklr
-------------------------------------------------

An demo of the use of spring-social-sparklr to connect to the customised Sparklr application can be found here:

https://github.com/michaellavelle/spring-social-sparklr-demo

The SparklrConnnectionFactory must be configured and registered with Spring Social using the following properties:

```
# spring-social-sparklr properties
sparklr.clientId=tonr
sparklr.clientSecret=secret
sparklr.tokenUrl=http://localhost:8080/sparklr2/oauth/token
sparklr.authorizeUrl=http://localhost:8080/sparklr2/oauth/authorize
sparklr.apiBaseUrl=http://localhost:8080/sparklr2
```

