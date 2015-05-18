AHP Dashboard Service
=====================

[![Join the chat at https://gitter.im/ak-hacks/ahp-dashboard-service](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/ak-hacks/ahp-dashboard-service?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

A Spring MVC based RESTful dashboard using Anthill Pro APIs

How to Build
============

Add AHP Jars to your local Maven Repo
-------------------------------------
    cd {PROJECT_ROOT}/libs
    mvn install:install-file  -Dfile=anthill3-client.jar -DgroupId=local.ahp -DartifactId=anthill3-client -Dversion=1.0 -Dpackaging=jar -DlocalRepositoryPath={PATH_OF_YOUR_LOCAL_m2_REPO}/.m2/repository
    mvn install:install-file  -Dfile=anthill3-drivers.jar -DgroupId=local.ahp -DartifactId=anthill3-drivers -Dversion=1.0 -Dpackaging=jar -DlocalRepositoryPath={PATH_OF_YOUR_LOCAL_m2_REPO}/.m2/repository
    mvn install:install-file  -Dfile=bsh-2.0b4-uc1.jar -DgroupId=local.ahp -DartifactId=bsh-2.0b4-uc1 -Dversion=1.0 -Dpackaging=jar -DlocalRepositoryPath={PATH_OF_YOUR_LOCAL_m2_REPO}/.m2/repository
    mvn install:install-file  -Dfile=codestation2.jar -DgroupId=local.ahp -DartifactId=codestation2 -Dversion=1.0 -Dpackaging=jar -DlocalRepositoryPath={PATH_OF_YOUR_LOCAL_m2_REPO}/.m2/repository
    mvn install:install-file  -Dfile=Commons-Locking.jar -DgroupId=local.ahp -DartifactId=Commons-Locking -Dversion=1.0 -Dpackaging=jar -DlocalRepositoryPath={PATH_OF_YOUR_LOCAL_m2_REPO}/.m2/repository
    mvn install:install-file  -Dfile=CommonsNet -DgroupId=local.ahp -DartifactId=CommonsNet -Dversion=1.0 -Dpackaging=jar -DlocalRepositoryPath={PATH_OF_YOUR_LOCAL_m2_REPO}/.m2/repository
    mvn install:install-file  -Dfile=CommonsUtil.jar -DgroupId=local.ahp -DartifactId=CommonsUtil -Dversion=1.0 -Dpackaging=jar -DlocalRepositoryPath={PATH_OF_YOUR_LOCAL_m2_REPO}/.m2/repository
    mvn install:install-file  -Dfile=devilfish.jar -DgroupId=local.ahp -DartifactId=devilfish -Dversion=1.0 -Dpackaging=jar -DlocalRepositoryPath={PATH_OF_YOUR_LOCAL_m2_REPO}/.m2/repository

Maven build
-----------
    mvn clean install

Additional Documentation
========================

Refer: [My Blog](http://techbytes.anuragkapur.com/2012/10/restful-anthill-pro-reporting-service.html)

Tested Tech Stack Versions
==========================
* Java 1.6.0_33
* Tomcat 7

Service Configuration
=====================

For the service to work correctly, you will have to update the file src/ahpconfigs.properties with details like your Anthill Pro Server hostname, port, user credentials, Lifecycle status stamps etc.

RESTful Endpoints
=================

* Consolidated projects service endpoint
Gives the summary status of all projects configured under the Programme/Category/Grouping via the ahpconfigs.properties file     
Example: http://localhost:8080/ahpsvc/rest/programme/<your_programme_name_configured_in_ahpconfigs_properties>.json

* Individual Project service endpoint
Gives a list of all buildlives including data about each buildlife like date it was deployed to various environments, repository change logs etc     
Example: http://localhost:808/ahpsvc/rest/project/<any_ahp_project_name>.json

Views
=====

There is a customised view implementation using JSPs which consume the model objects returned by the RESTful web service. If you wish to create your own views you can use JSON responses.

Preconfigured Views can be accessed via endpoints described above by not including the .json at the end of the URL.     
Example: http://localhost:8080/ahpsvc/rest/programme/${your_programme_name_configured_in_ahpconfigs_properties}     
OR     
http://localhost:808/ahpsvc/rest/project/${any_ahp_project_name}

Note(s)
=======
There is probably an overkill of jar files used in the webapp. I will try and mavenise the project soon so that the jar management is a lot more cleaner.     
Update on 16 Aug 2013: Mavenisation Complete.

