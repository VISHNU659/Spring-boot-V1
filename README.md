
---

# Spring Boot Oracle Demo

This project demonstrates a Spring Boot application connected to an **Oracle Database**.

---

## 🚀 Setup Instructions

### 1. Configure Application Properties

Create a folder and file under this path:

```
src/main/resources/application.properties
```

Add the following configuration:

```properties
spring.application.name=demo1

spring.datasource.url=jdbc:oracle:thin:@0.0.0.0:1521:FREE
spring.datasource.username=C##spring
spring.datasource.password=spring
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.hibernate.ddl-auto=update

# ⚠️ Use this ONLY once if you want to reset the DB structure.
# This will drop & recreate all tables from scratch.
#spring.jpa.hibernate.ddl-auto=create

spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
```

---

### 2. Create Oracle User

Login to your Oracle sql developer  and run:

```sql
CREATE USER C##spring IDENTIFIED BY spring;
GRANT CONNECT, RESOURCE TO C##spring;
ALTER USER C##spring QUOTA UNLIMITED ON USERS;
```

---

### 3. Clone the Repository

```bash
git clone https://github.com/VISHNU659/Spring-boot-V1.git
cd Spring-boot-V1
```

---

### 4. Create Your Own Branch

```bash
git checkout -b yourname
```

---

### 5. Make Changes & Push

```bash
git add .
git commit -m "Your commit message"
git push origin yourname
```

---

### 6. Pull Latest Changes

* To pull updates from **main**:

  ```bash
  git pull origin main
  ```

* To pull updates from **your branch**:

  ```bash
  git pull origin yourname
  ```

---

✅ You’re now ready to work on your own branch without affecting the main codebase.

---

Do you also want me to add **how to run the Spring Boot application** (Maven/Gradle command) into this README?
