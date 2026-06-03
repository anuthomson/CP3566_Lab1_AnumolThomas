import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.sql.DriverManager;

public class SearchSafe {
    private static final String URL = "jdbc:h2:./data/studentdb;MODE=MySQL;DATABASE_TO_LOWER=TRUE";
    private static final String USER = "sa";
    private static final String PASSWORD = "secret";
    private static final String SQL = "SELECT id, name, program, gpa FROM student WHERE name LIKE ?";

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));

        if (args.length < 1) {
            System.err.println("Usage: java SearchSafe <search-value>");
            return;
        }

        String searchValue = args[0];
        DriverManager.setLoginTimeout(5);
    }
}
