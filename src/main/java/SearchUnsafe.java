import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SearchUnsafe {
    private static final String URL = "jdbc:h2:./data/studentdb;MODE=MySQL;DATABASE_TO_LOWER=TRUE";
    private static final String USER = "sa";
    private static final String PASSWORD = "secret";
    private static final String SQL = "SELECT id, name, program, gpa FROM student WHERE name LIKE '%SEARCH%'";

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));

        if (args.length < 1) {
            System.err.println("Usage: java SearchUnsafe <search-value>");
            return;
        }

        String searchValue = args[0];
        DriverManager.setLoginTimeout(5);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.setQueryTimeout(10);

            String finalSql = SQL.replace("SEARCH", searchValue);
            System.out.println("Final SQL: " + finalSql);

            try (ResultSet rs = stmt.executeQuery(finalSql)) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String program = rs.getString("program");
                    double gpa = rs.getDouble("gpa");

                    System.out.println(id + " | " + name + " | " + program + " | " + gpa);
                }
            }

            // Statement ready for unsafe execution (query executed)
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
    }
}
