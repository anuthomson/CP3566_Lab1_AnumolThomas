import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListStudents {
    private static final String URL = "jdbc:h2:./data/studentdb;MODE=MySQL;DATABASE_TO_LOWER=TRUE";
    private static final String USER = "sa";
    private static final String PASSWORD  = "secret";
    private static final String SQL = "SELECT id, name, program, gpa FROM student ORDER BY id";

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));
        DriverManager.setLoginTimeout(5);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
     Statement stmt = conn.createStatement()) {

    stmt.setQueryTimeout(10);

    try (ResultSet rs = stmt.executeQuery(SQL)) {

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String program = rs.getString("program");
            double gpa = rs.getDouble("gpa");

            System.out.println(id + " | " + name + " | " + program + " | " + gpa);
        }

    }

    } catch (SQLException e) {
    System.err.println("Connection failed: " + e.getMessage());
    }
    }
}
