# deliveroo-cron-parser

### Main Class : `deliveroo.Parser`

---

## Requirements
- Java 21+
- Maven 3.9+

---

## How to Build
Use Maven to compile and package the project:

```bash
mvn clean package
```

This will create the JAR in the `target/` directory:

```
target/cron-parser-1.0-SNAPSHOT.jar
```

---

## How to Run
Pass the cron expression as a **single argument** (wrap in quotes):

```bash
java -jar target/cron-parser-1.0-SNAPSHOT.jar "<CRON EXPRESSION>"
```

---

## Example

### Command
```bash
java -jar target/cron-parser-1.0-SNAPSHOT.jar "*/15 0 1,15 * 1-5 /usr/bin/find"
```

### Output
```
minute          0 15 30 45
hour            0
day of month    1 15
month           1 2 3 4 5 6 7 8 9 10 11 12
day of week     1 2 3 4 5
command         /usr/bin/find
```

---

## How to Run Tests
Run the test suite with:

```bash
mvn test
```

---

## Author
**Shashwat Jatav**
