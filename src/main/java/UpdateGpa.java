import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateGpa {
    private static final String URL = "jdbc:h2:./data/studentdb;MODE=MySQL;DATABASE_TO_LOWER=TRUE";
    private static final String USER = "sa";
    private static final String PASSWORD = "secret";
    private static final String SQL = "UPDATE student SET gpa = ? WHERE id = ?";

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));

        if (args.length < 2) {
            System.err.println("Usage: java UpdateGpa <student-id> <new-gpa>");
            return;
        }

        String studentIdString = args[0];
        String gpaString = args[1];

        int studentId;
        try {
            studentId = Integer.parseInt(studentIdString);
            if (studentId <= 0) {
                System.err.println("Invalid student ID: must be a positive integer.");
                return;
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid student ID: must be an integer.");
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
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setDouble(1, gpa);
            pstmt.setInt(2, studentId);
            pstmt.setQueryTimeout(10);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("Updated student #" + studentId + " - GPA set to " + gpa + " - 1 row changed");
            } else if (rowsAffected == 0) {
                System.out.println("No update - no student with id " + studentId + " - 0 rows changed");
            }
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
    }
}
