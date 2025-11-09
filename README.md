Online Quiz / Exam System (Minimal)

How to run:
1. Install MySQL and create database: CREATE DATABASE quizdb;
2. Update src/main/resources/application.properties with your MySQL username/password.
3. Build WAR: mvn clean package
4. Deploy the generated online-quiz-system.war to Apache Tomcat 10.1 (drop into webapps).
5. Default users (HTTP Basic):
   - admin / adminpass  (ROLE_ADMIN)  -> can create quizzes
   - student / studentpass (ROLE_STUDENT) -> can list and submit quizzes 


