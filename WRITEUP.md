# WRITEUP

## Question 1

In SearchUnsafe, the user's input was added directly into the SQL query using string concatenation. This approach is unsafe because user input becomes part of the SQL statement instead of being treated as data. During my testing, the malicious input did not return all rows because it was interpreted as part of the LIKE pattern. However, building SQL statements directly from user input is still considered a security risk and can lead to SQL injection vulnerabilities.

## Question 2

Checking `rowsAffected` is important because it tells us whether the UPDATE statement actually changed a record. If a student ID does not exist, the database returns 0 rows affected. Without checking that value, the program could display a success message even though nothing was updated, which could hide errors and make troubleshooting more difficult.

## Question 3

The database generated the new student ID automatically when the INSERT statement was executed. My Java program requested the generated key by using `Statement.RETURN_GENERATED_KEYS` when creating the PreparedStatement. After the insert completed, I called `getGeneratedKeys()` and read the ID from the returned ResultSet so it could be displayed to the user.

## Question 4

One AI suggestion that looked correct at first was adding `Class.forName("org.h2.Driver")` before opening the database connection. The code would still run, so it seemed reasonable. However, the lab instructions specifically state that the H2 driver automatically registers itself and that using `Class.forName()` would result in a deduction. After reviewing the instructions, I removed that suggestion and used the modern JDBC approach instead.
