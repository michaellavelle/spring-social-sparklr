spring-social-sparklr
=====================

Spring Social binding and connect support for the Sparklr sample application from spring-security-oauth

Requires version 1.0.1-SNAPSHOT or later of the Sparklr application to be running on port 8080.

Running the Sparklr application
-------------------------------

git clone https://github.com/SpringSource/spring-security-oauth.git

cd spring-security-oauth

mvn install -P bootstrap

cd samples/oauth2/sparklr

mvn tomcat:run

Using spring-social-sparklr to connect to Sparklr
-------------------------------------------------

An demo of the use of spring-social-sparklr to connect to the Sparklr application can be found here:

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

