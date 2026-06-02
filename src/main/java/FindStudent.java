import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.sql.DriverManager;

public class FindStudent {
    private static final String URL = "jdbc:h2:./data/studentdb;MODE=MySQL;DATABASE_TO_LOWER=TRUE";
    private static final String USER = "sa";
    private static final String PASSWORD = "secret";

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));
        DriverManager.setLoginTimeout(5);

        if (args.length < 1) {
            System.err.println("Usage: java FindStudent <student-id>");
            return;
        }

        String studentId = args[0];
        System.out.println("FindStudent ready. studentId=" + studentId);
    }
}
