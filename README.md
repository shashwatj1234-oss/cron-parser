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

## Features
- Supports:
    - Wildcards (`*`)
    - Ranges (`a-b`)
    - Steps (`*/n`, `a-b/n`, `a/n`)
    - Lists (`1,5,10-12`)
- Field bounds:
    - Minute: `0–59`
    - Hour: `0–23`
    - Day of Month: `1–31`
    - Month: `1–12`
    - Day of Week: `0–6` (Sunday = 0)
- Throws clear exceptions for invalid or out-of-range values.
- Modular design with per-field parsers for easy extension.

---

## Possible Extensions
- Month and day-of-week names (`JAN..DEC`, `MON..SUN`)
- Special macros (`@yearly`, `@monthly`, etc.`)
- Next-run calculator given a base timestamp
- JSON or other structured output

---

## Author
**Shashwat Jatav**
