import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

public class AddStudent {
    private static final String URL = "jdbc:h2:./data/studentdb;MODE=MySQL;DATABASE_TO_LOWER=TRUE";
    private static final String USER = "sa";
    private static final String PASSWORD = "secret";
    private static final String SQL = "INSERT INTO student (name, program, gpa) VALUES (?, ?, ?)";

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));

        if (args.length < 3) {
            System.err.println("Usage: java AddStudent <name> <program> <gpa>");
            return;
        }

        String name = args[0].trim();
        String program = args[1];
        String gpaString = args[2];

        if (name.isEmpty()) {
            System.err.println("Invalid name: value is required and cannot be empty.");
            return;
        }

        if (name.length() > 80) {
            System.err.println("Invalid name: must be 80 characters or fewer.");
            return;
        }

        if (!Pattern.matches("^[A-Z0-9]{2,12}$", program)) {
            System.err.println("Invalid program: must be 2-12 uppercase letters or digits.");
            return;
        }

        double gpa;
        try {
            gpa = Double.parseDouble(gpaString);
        } catch (NumberFormatException e) {
            System.err.println("Invalid GPA: must be a numeric value.");
            return;
        }

        if (gpa < 0.00 || gpa > 4.00) {
            System.err.println("Invalid GPA: must be between 0.00 and 4.00.");
            return;
        }

        DriverManager.setLoginTimeout(5);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, name);
            pstmt.setString(2, program);
            pstmt.setDouble(3, gpa);
            pstmt.setQueryTimeout(10);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

            try (var keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    int studentId = keys.getInt(1);
                    System.out.println("New student ID: " + studentId);
                }
            }
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
    }
}
