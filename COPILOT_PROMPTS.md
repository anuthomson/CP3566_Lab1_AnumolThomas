# COPILOT_PROMPTS.md

## Prompt 1

I am starting Task 1 for my JDBC lab. I created a new file called ListStudents.java.

I want to do this slowly, one small step at a time, because I am still learning JDBC.

For now, please help me write only the starting setup for this file.

Step 1: create the public class ListStudents.
Step 2: add constants for the database URL, username, and password.
Step 3: set UTF-8 for System.out and System.err inside main.
Step 4: set DriverManager.setLoginTimeout(5).

Please do not write the SELECT query yet.
Please do not use Class.forName().
Please keep the code simple and explain each part in beginner-friendly words.

WHEN:
Starting Task 1.

ACCEPTED:
Partially

VERDICT:
Added class structure, constants, UTF-8 setup, and login timeout.

---

## Prompt 2

Help me add only the database connection using try-with-resources and DriverManager.getConnection.

For now, I just want to confirm that the connection opens successfully and print a simple message.

Please do not add any SQL code yet.
Explain the code briefly.

WHEN:
Task 1 - Database connection.

ACCEPTED:
Yes

VERDICT:
Used try-with-resources and successfully opened the database connection.

---

## Prompt 3

The database connection is working and I can connect to the database.

Please help me create a Statement inside the try-with-resources block and set a query timeout of 10 seconds.

Do not add the SQL query yet.
Do not add a ResultSet yet.

Keep the changes small and explain briefly why setQueryTimeout(10) is useful.

WHEN:
Task 1 - Statement setup.

ACCEPTED:
Yes

VERDICT:
Added a Statement object and configured the required query timeout using setQueryTimeout(10).
## Prompt 4

The Statement and query timeout are working.

Please help me add the SQL query as a constant at the top of the class.

SQL:
SELECT id, name, program, gpa FROM student ORDER BY id

Do not execute the query yet.
Do not add a ResultSet yet.

Keep the changes small and explain why using a constant for SQL is useful.

WHEN:
Task 1 - SQL constant.

ACCEPTED:
Yes

VERDICT:
Added the SQL query as a class constant and kept it separate from the execution code.
## Prompt 5

The query is executing successfully and I now have a ResultSet.

Please help me read the rows using a while loop.

Show how to retrieve:

* id
* name
* program
* gpa

Do not worry about the final output formatting yet.

Keep the changes small and explain briefly what rs.next(), getInt(), getString(), and getDouble() do.

WHEN:
Task 1 - Reading rows from ResultSet.

ACCEPTED:
Yes

VERDICT:
Added a while loop and retrieved id, name, program, and gpa from the ResultSet.

## Prompt 6

I am starting FindStudent.java.

Please help me create the basic setup with:

* class structure
* database constants
* UTF-8 output
* login timeout

No SQL yet.

WHEN:
Starting FindStudent.java

ACCEPTED:
Yes

VERDICT:
Created the basic setup successfully.
check that a student ID was provided as a command-line argument.

If no ID is provided, display a usage message and exit.

No database code yet.

WHEN:
Argument validation

ACCEPTED:
Yes

VERDICT:
Added command-line argument validation and usage message.


## Prompt 8

To add the SQL query for finding a student by ID.

Use a parameter placeholder (?) because I will use a PreparedStatement.

WHEN:
SQL query setup

ACCEPTED:
Yes

VERDICT:
Added SQL constant with a parameter placeholder for PreparedStatement.

## Prompt 9

To add the database connection using try-with-resources and DriverManager.getConnection.

WHEN:
Database connection

ACCEPTED:
Yes

VERDICT:
Added database connection using try-with-resources and DriverManager.getConnection.
## Prompt 10

Create a PreparedStatement using the existing SQL constant.

Set the student ID parameter using the command-line argument.

WHEN:
PreparedStatement setup

ACCEPTED:
Pending

VERDICT:
Waiting for Copilot response.
