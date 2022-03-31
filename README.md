# TechG Remittance Application 
### Object-Oriented-Programming-G2Project

This Digital Remittance Portal (DRP.) has a network of Digital Funds Transfer Payout API that supports digital cross-border funds transfer: FinanceNow, EverywhereRemit
and PaymentGo.

---

### Table of Content
* [Description](/README.md#description)
* [Tech Stack](/README.md#tech-stack)
* [Code Navigation](/README.md#code-navigation)
* [Steps to Run Application](/README.md#steps-to-run-application)
* [Contributors](/README.md#contributors)

---

### Description
A visual representation of the workflow is as follows:
![image](https://user-images.githubusercontent.com/78516806/161027333-95c9164c-3df4-4644-a111-905d295bb621.png)

---

### Tech Stack
#### Database
* MySQL

#### Front End
* HTML
* CSS
* JavaScript
* Vue.js

#### Back End
* Java
* Spring
* Springboot

---

### Code Navigation 
1. Front End codes in this [folder](https://github.com/shaohong-g/Object-Orientated-Programming-G2Project/tree/main/frontend)
2. Back End codes in this [folder](https://github.com/shaohong-g/Object-Orientated-Programming-G2Project/tree/main/src)
3. SQL codes in this [script](https://github.com/shaohong-g/Object-Orientated-Programming-G2Project/blob/main/remittance.sql)

---

### Steps to Run Application
* Run remittance.sql script in SQL engine (MySQL or PHPAdmin) 

#### (For Windows)
1. Load WAMP
2. Ensure db access password is according to your OS System i.e, "" 
3. Check your SQL port number is correct (port 3306)
4. mvnw dependency:purge-local-repository
        - Remove the project dependencies from the local repository, and optionally re-resolve them.
5. mvnw spring-boot:run
        - Triggers the download of Apache Tomcat and initializes the startup of Tomcat
        
#### (for Mac)
1. Load MAMP
2. Ensure db access password is according to your OS System i.e, "root"
3. Check your SQL port number is correct (port 8889)
4. mvn dependency:purge-local-repository
        - Remove the project dependencies from the local repository, and optionally re-resolve them.
5. mvn spring-boot:run
        - Triggers the download of Apache Tomcat and initializes the startup of Tomcat

---

### Contributors
1. Yeo Yu Quan
2. Wong Wei Kit, Leonard
3. David Lim Yock Jun
4. Cheow Tian Le
5. Gan Shao Hong
6. Wang Shurui 
