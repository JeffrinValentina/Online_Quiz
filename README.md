Online Quiz / Exam System (Minimal)

How to run:
1. Install MySQL and create database: CREATE DATABASE quizdb;
2. Update src/main/resources/application.properties with your MySQL username/password.
3. Build WAR: mvn clean package
4. Deploy the generated online-quiz-system.war to Apache Tomcat 10.1 (drop into webapps).
5. Default users (HTTP Basic):
   - admin / adminpass  (ROLE_ADMIN)  -> can create quizzes via /api/admin/quizzes
   - student / studentpass (ROLE_STUDENT) -> can list and submit quizzes via /api/student/**

Simple API examples:
- Create quiz (admin):
  POST /api/admin/quizzes
  Body: JSON for Quiz with questions and choices. Example structure in below example.json

- List quizzes (student):
  GET /api/student/quizzes

- Submit answers (student):
  POST /api/student/quizzes/{id}/submit
  Body: JSON object mapping questionId -> selectedChoiceIndex (0-based)

Notes:
- This is a minimal example intended to run as a demo. For production you should:
  * Use proper password encoding (BCrypt)
  * Add DTOs and validation
  * Add service layer and transactions
  * Add tests and better error handling
