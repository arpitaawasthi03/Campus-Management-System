# Campus Management System

A full-stack Java web application built on **Jakarta Servlets 6.1**, **Hibernate ORM 7.4.9**, **Thymeleaf 3.1**, and **MySQL**, modeled following the architectural principles of the reference Employee Management repository.

---

## 🏛️ Project Overview

The Campus Management System provides complete academic and campus administration, including:
- **Campus Dashboard**: Real-time statistics across all campus academic and administrative modules.
- **Student Management**: Student directory, enrollment, record updates, semester tracking, and GPA calculations.
- **Course Catalog**: Course offerings, credit units, curriculum outlines, and assigned faculty instructors.
- **Academic Departments**: Department registry, HOD assignments, contact information, and building locations.
- **Faculty Directory**: Academic staffing, designations, room assignments, and departmental affiliations.

---

## 🛠️ Technology Stack & Dependencies

| Component | Technology | Version | Purpose |
|---|---|---|---|
| **Language** | Java | 17+ (Tested on Java 21) | Application runtime |
| **Build & Dependency Tool** | Apache Maven | 3.9+ | Compilation, packaging, testing |
| **Web Tier** | Jakarta Servlet API | 6.1.0 | HTTP request routing and servlet controllers |
| **Template Engine** | Thymeleaf (`thymeleaf`) | 3.1.3.RELEASE | Server-side template rendering with Jakarta Servlet integration |
| **Persistence / ORM** | Hibernate ORM (`hibernate-core`) | 7.4.9.Final | JPA implementation and Object-Relational Mapping |
| **JPA Specification** | Jakarta Persistence API (`jakarta.persistence-api`) | 3.2.0 | Standard persistence annotations (`@Entity`, `@Id`, `@Table`, etc.) |
| **Database Driver** | MySQL Connector/J (`mysql-connector-j`) | 9.4.0 | JDBC database driver for production MySQL persistence |
| **Embedded Database** | H2 Database (`h2`) | 2.3.232 | In-memory database for automated testing and standalone verification |
| **Testing** | JUnit Jupiter (`junit-jupiter`) | 5.11.0 | Automated unit and integration test suite (replaces legacy JUnit 3) |

---

## 📁 Project Architecture & Folder Organization

```text
campus management system/
├── pom.xml                                   # Maven POM build and dependency configuration
├── README.md                                 # Documentation and execution instructions
├── .gitignore                                # Clean repository ignore rules
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/campus/
│   │   │       ├── TestCampusApp.java        # Standalone verification runner with main()
│   │   │       ├── model/                    # JPA Entity Models
│   │   │       │   ├── Student.java
│   │   │       │   ├── Course.java
│   │   │       │   ├── Department.java
│   │   │       │   └── Faculty.java
│   │   │       ├── dao/                      # Data Access Objects (CRUD with Hibernate)
│   │   │       │   ├── StudentDAO.java
│   │   │       │   ├── CourseDAO.java
│   │   │       │   ├── DepartmentDAO.java
│   │   │       │   └── FacultyDAO.java
│   │   │       ├── servlet/                  # Jakarta HTTP Servlets (Controllers)
│   │   │       │   ├── DashboardServlet.java
│   │   │       │   ├── StudentServlet.java
│   │   │       │   ├── AddStudentServlet.java
│   │   │       │   ├── EditStudentServlet.java
│   │   │       │   ├── SaveStudentServlet.java
│   │   │       │   ├── DeleteStudentServlet.java
│   │   │       │   ├── CourseServlet.java
│   │   │       │   ├── AddCourseServlet.java
│   │   │       │   ├── EditCourseServlet.java
│   │   │       │   ├── SaveCourseServlet.java
│   │   │       │   ├── DeleteCourseServlet.java
│   │   │       │   ├── DepartmentServlet.java
│   │   │       │   ├── AddDepartmentServlet.java
│   │   │       │   ├── EditDepartmentServlet.java
│   │   │       │   ├── SaveDepartmentServlet.java
│   │   │       │   ├── DeleteDepartmentServlet.java
│   │   │       │   ├── FacultyServlet.java
│   │   │       │   ├── AddFacultyServlet.java
│   │   │       │   ├── EditFacultyServlet.java
│   │   │       │   ├── SaveFacultyServlet.java
│   │   │       │   └── DeleteFacultyServlet.java
│   │   │       └── util/                     # Utilities & Singletons
│   │   │           ├── HibernateUtil.java
│   │   │           └── ThymeleafConfig.java
│   │   ├── resources/
│   │   │   ├── hibernate.cfg.xml             # Production Hibernate config (MySQL)
│   │   │   └── templates/                    # Thymeleaf HTML5 Templates
│   │   │       ├── dashboard.html
│   │   │       ├── students.html
│   │   │       ├── student-form.html
│   │   │       ├── courses.html
│   │   │       ├── course-form.html
│   │   │       ├── departments.html
│   │   │       ├── department-form.html
│   │   │       ├── faculty.html
│   │   │       └── faculty-form.html
│   │   └── webapp/
│   │       ├── css/
│   │       │   └── style.css                 # Modern responsive styling
│   │       ├── WEB-INF/
│   │       │   └── web.xml                   # Jakarta EE 10 / Servlet 6.0 deployment descriptor
│   │       └── index.jsp                     # Root entry point redirecting to /dashboard
│   └── test/
│       ├── java/
│       │   └── com/campus/dao/
│       │       └── CampusDAOTest.java        # JUnit 5 integration test suite
│       └── resources/
│           └── hibernate.cfg.xml             # Test Hibernate config (H2 in-memory)
```

---

## ⚙️ Database Configuration

### 1. Default MySQL Setup
By default, `src/main/resources/hibernate.cfg.xml` is configured for local MySQL:
- **URL**: `jdbc:mysql://localhost:3306/campus_db?createDatabaseIfNotExist=true`
- **Username**: `root`
- **Password**: `password`
- **DDL Auto**: `update` (automatically creates tables on first run)

### 2. Environment Variable Overrides
You can easily override credentials without editing files by setting environment variables:
```bash
export CAMPUS_DB_URL="jdbc:mysql://localhost:3306/my_database"
export CAMPUS_DB_USER="my_user"
export CAMPUS_DB_PASS="my_secret_password"
```

---

## 🚀 Execution & Verification Commands

### 1. Build and Run Automated Tests
```bash
mvn clean test
```
Executes all JUnit 5 test cases covering CRUD operations across Students, Courses, Departments, and Faculty against an isolated in-memory H2 database.

### 2. Package the Application WAR
```bash
mvn clean package
```
Generates `target/campus-management-system.war`.

### 3. Run Standalone CLI Verification
```bash
mvn exec:java -Dexec.mainClass="com.campus.TestCampusApp"
```
Runs the standalone test application that performs end-to-end CRUD on all campus entities and prints structured outputs.

### 4. Deploy and Run in Apache Tomcat
1. Install **Apache Tomcat 10.1+** (supports Jakarta EE 10 / Servlet 6.0).
2. Copy `target/campus-management-system.war` into `$CATALINA_BASE/webapps/`.
3. Start Tomcat:
   ```bash
   $CATALINA_HOME/bin/startup.sh
   ```
4. Access the application in your browser:
   ```text
   http://localhost:8080/campus-management-system/
   ```
