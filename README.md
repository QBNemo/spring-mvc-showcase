Spring MVC Showcase
-------------------
Demonstrates the capabilities of the Spring MVC web framework through small, simple examples.
After reviewing this showcase, you should have a good understanding of what Spring MVC can do and get a feel for how easy it is to use.
Includes project code along with a supporting slideshow and screen cast.

In this showcase you'll see the following in action:

* The simplest possible @Controller
* Mapping Requests
* Obtaining Request Data
* Generating Responses
* Message Converters
* Rendering Views
* Type Conversion
* Validation
* Forms
* File Upload
* Exception Handling

To get the code:
-------------------
Clone the repository:

    $ git clone git://github.com/SpringSource/spring-mvc-showcase.git

If this is your first time using Github, review http://help.github.com to learn the basics.

To run the application:
-------------------	
From the command line with Maven:

    $ cd spring-mvc-showcase
    $ mvn tomcat7:run [-Dmaven.tomcat.port=<port no.>] (In case 8080 is busy] 

or

In your preferred IDE such as SpringSource Tool Suite (STS) or IDEA:

* Import spring-mvc-showcase as a Maven Project
* Drag-n-drop the project onto the "SpringSource tc Server Developer Edition" or another Servlet 2.5 or > Server to run, such as Tomcat.

Access the deployed web application at: http://localhost:8080/spring-mvc-showcase/

Note:
-------------------

This showcase originated from a [blog post](http://blog.springsource.com/2010/07/22/spring-mvc-3-showcase/) and was adapted into a SpringOne presentation called [Mastering MVC 3](http://www.infoq.com/presentations/Mastering-Spring-MVC-3).

A screen cast showing the showcase in action is [available in QuickTime format](http://s3.springsource.org/MVC/mvc-showcase-screencast.mov).

1.spring-mvc源码编译 三个测试注释掉
2.


[INFO] --- maven-dependency-plugin:2.8:sources (install) @ spring-mvc-showcase ---
[INFO] 
[INFO] The following files have been resolved:
[INFO]    org.apache.tiles:tiles-request-velocity:jar:sources:1.0.6:compile
[INFO]    org.apache.tiles:tiles-autotag-core-runtime:jar:sources:1.1.0:compile
[INFO]    stax:stax:jar:sources:1.2.0:compile
[INFO]    org.apache.tiles:tiles-api:jar:sources:3.0.5:compile
[INFO]    org.glassfish.web:jstl-impl:jar:sources:1.2:compile
[INFO]    commons-lang:commons-lang:jar:sources:2.6:compile
[INFO]    net.minidev:json-smart:jar:sources:1.1.1:test
[INFO]    com.fasterxml.jackson.core:jackson-annotations:jar:sources:2.6.0:compile
[INFO]    net.sf.jasperreports:jasperreports:jar:sources:6.1.1:compile
[INFO]    org.apache.tomcat:tomcat-servlet-api:jar:sources:7.0.30:provided
[INFO]    junit:junit:jar:sources:4.11:test
[INFO]    org.bouncycastle:bcmail-jdk14:jar:sources:1.38:compile
[INFO]    org.apache.tiles:tiles-ognl:jar:sources:3.0.5:compile
[INFO]    com.google.zxing:core:jar:sources:2.3.0:compile
[INFO]    org.apache.velocity:velocity:jar:sources:1.7:compile
[INFO]    com.fasterxml.jackson.core:jackson-databind:jar:sources:2.6.3:compile
[INFO]    org.webjars.npm:validate.js:jar:sources:0.8.0:compile
[INFO]    org.codehaus.groovy:groovy-all:jar:sources:2.4.5:compile
[INFO]    javax.inject:javax.inject:jar:sources:1:compile
[INFO]    javax.xml.stream:stax-api:jar:sources:1.0-2:compile
[INFO]    org.springframework:spring-aop:jar:sources:4.2.2.RELEASE:compile
[INFO]    org.apache.commons:commons-compress:jar:sources:1.9:compile
[INFO]    aopalliance:aopalliance:jar:sources:1.0:compile
[INFO]    com.fasterxml.jackson.module:jackson-module-jaxb-annotations:jar:sources:2.6.3:compile
[INFO]    javax.validation:validation-api:jar:sources:1.0.0.GA:compile
[INFO]    org.apache.tiles:tiles-velocity:jar:sources:3.0.5:compile
[INFO]    org.apache.tiles:tiles-template:jar:sources:3.0.5:compile
[INFO]    org.bouncycastle:bcprov-jdk14:jar:sources:1.38:compile
[INFO]    org.slf4j:slf4j-api:jar:sources:1.7.12:compile
[INFO]    org.springframework:spring-expression:jar:sources:4.2.2.RELEASE:compile
[INFO]    ognl:ognl:jar:sources:2.7.3:compile
[INFO]    org.apache.tiles:tiles-request-servlet-wildcard:jar:sources:1.0.6:compile
[INFO]    org.slf4j:jcl-over-slf4j:jar:sources:1.7.12:runtime
[INFO]    com.rometools:rome-utils:jar:sources:1.5.1:compile
[INFO]    org.apache.commons:commons-lang3:jar:sources:3.4:compile
[INFO]    org.codehaus.castor:castor-core:jar:sources:1.3.3:compile
[INFO]    commons-codec:commons-codec:jar:sources:1.9:compile
[INFO]    org.freemarker:freemarker:jar:sources:2.3.23:compile
[INFO]    org.apache.lucene:lucene-sandbox:jar:sources:4.5.1:compile
[INFO]    org.springframework:spring-web:jar:sources:4.2.2.RELEASE:compile
[INFO]    org.jfree:jcommon:jar:sources:1.0.23:compile
[INFO]    org.apache.tiles:tiles-el:jar:sources:3.0.5:compile
[INFO]    net.sourceforge.jexcelapi:jxl:jar:sources:2.6.12:compile
[INFO]    org.apache.tiles:tiles-compat:jar:sources:3.0.5:compile
[INFO]    commons-io:commons-io:jar:sources:2.0.1:compile
[INFO]    com.rometools:rome:jar:sources:1.5.1:compile
[INFO]    com.fasterxml.jackson.dataformat:jackson-dataformat-xml:jar:sources:2.6.3:compile
[INFO]    commons-collections:commons-collections:jar:sources:3.2.1:compile
[INFO]    org.jfree:jfreechart:jar:sources:1.0.19:compile
[INFO]    org.slf4j:slf4j-log4j12:jar:sources:1.7.12:runtime
[INFO]    commons-fileupload:commons-fileupload:jar:sources:1.2.2:compile
[INFO]    org.springframework:spring-context-support:jar:sources:4.2.2.RELEASE:compile
[INFO]    org.springframework:spring-context:jar:sources:4.2.2.RELEASE:compile
[INFO]    org.bouncycastle:bctsp-jdk14:jar:sources:1.38:compile
[INFO]    org.apache.tiles:tiles-request-servlet:jar:sources:1.0.6:compile
[INFO]    org.apache.tiles:tiles-mvel:jar:sources:3.0.5:compile
[INFO]    javax.servlet.jsp.jstl:jstl-api:jar:sources:1.2:compile
[INFO]    com.jayway.jsonpath:json-path:jar:sources:0.8.1:test
[INFO]    commons-logging:commons-logging:jar:sources:1.1.1:compile
[INFO]    org.springframework.security:spring-security-web:jar:sources:4.0.1.RELEASE:compile
[INFO]    log4j:log4j:jar:sources:1.2.16:runtime
[INFO]    org.apache.tiles:tiles-jsp:jar:sources:3.0.5:compile
[INFO]    org.apache.lucene:lucene-queryparser:jar:sources:4.5.1:compile
[INFO]    org.apache.lucene:lucene-queries:jar:sources:4.5.1:compile
[INFO]    org.webjars:webjars-locator:jar:sources:0.28:compile
[INFO]    org.apache.tiles:tiles-core:jar:sources:3.0.5:compile
[INFO]    com.fasterxml.jackson.core:jackson-core:jar:sources:2.6.3:compile
[INFO]    org.apache.tiles:tiles-request-api:jar:sources:1.0.6:compile
[INFO]    com.github.spullara.mustache.java:compiler:jar:sources:0.8.4:compile
[INFO]    org.springframework:spring-beans:jar:sources:4.2.2.RELEASE:compile
[INFO]    joda-time:joda-time:jar:sources:2.3:compile
[INFO]    org.eclipse.jdt.core.compiler:ecj:jar:sources:4.3.1:compile
[INFO]    org.webjars:webjars-locator-core:jar:sources:0.27:compile
[INFO]    org.apache.poi:poi-ooxml:jar:sources:3.13:compile
[INFO]    commons-beanutils:commons-beanutils:jar:sources:1.9.0:compile
[INFO]    org.apache.tiles:tiles-request-freemarker:jar:sources:1.0.6:compile
[INFO]    org.codehaus.woodstox:stax2-api:jar:sources:3.1.4:compile
[INFO]    org.mvel:mvel2:jar:sources:2.0.11:compile
[INFO]    org.apache.tiles:tiles-request-jsp:jar:sources:1.0.6:compile
[INFO]    org.jdom:jdom:jar:sources:2.0.2:compile
[INFO]    org.springframework.security:spring-security-core:jar:sources:4.0.1.RELEASE:compile
[INFO]    commons-digester:commons-digester:jar:sources:2.1:compile
[INFO]    org.hibernate:hibernate-validator:jar:sources:4.1.0.Final:compile
[INFO]    org.apache.lucene:lucene-analyzers-common:jar:sources:4.5.1:compile
[INFO]    org.apache.poi:poi:jar:sources:3.13:compile
[INFO]    javax.servlet.jsp:jsp-api:jar:sources:2.1:provided
[INFO]    org.apache.tiles:tiles-request-mustache:jar:sources:1.0.6:compile
[INFO]    org.springframework:spring-oxm:jar:sources:4.2.2.RELEASE:compile
[INFO]    org.apache.tiles:tiles-freemarker:jar:sources:3.0.5:compile
[INFO]    org.springframework:spring-test:jar:sources:4.2.2.RELEASE:test
[INFO]    org.apache.lucene:lucene-core:jar:sources:4.5.1:compile
[INFO]    org.hamcrest:hamcrest-library:jar:sources:1.3:test
[INFO]    org.springframework:spring-core:jar:sources:4.2.2.RELEASE:compile
[INFO]    org.apache.tiles:tiles-servlet:jar:sources:3.0.5:compile
[INFO]    jboss:javassist:jar:sources:3.7.ga:compile
[INFO]    org.aspectj:aspectjrt:jar:sources:1.8.1:compile
[INFO]    com.lowagie:itext:jar:sources:2.1.7:compile
[INFO]    org.apache.tiles:tiles-extras:jar:sources:3.0.5:compile
[INFO]    org.codehaus.castor:castor-xml:jar:sources:1.3.3:compile
[INFO]    org.hamcrest:hamcrest-core:jar:sources:1.3:test
[INFO] 
[INFO] The following files have NOT been resolved:
[INFO]    stax:stax-api:jar:sources:1.0.1:compile
[INFO]    bouncycastle:bcprov-jdk14:jar:sources:138:compile
[INFO]    xml-apis:xml-apis:jar:sources:1.3.02:compile
[INFO]    velocity-tools:velocity-tools-view:jar:sources:1.4:compile
[INFO]    org.apache.xmlbeans:xmlbeans:jar:sources:2.6.0:compile
[INFO]    org.apache.poi:poi-ooxml-schemas:jar:sources:3.13:compile
[INFO]    bouncycastle:bcmail-jdk14:jar:sources:138:compile
[INFO]    jakarta-regexp:jakarta-regexp:jar:sources:1.4:compile
[INFO]    xmlunit:xmlunit:jar:sources:1.2:test
[INFO]    org.olap4j:olap4j:jar:sources:0.9.7.309-JS-3:compile
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------