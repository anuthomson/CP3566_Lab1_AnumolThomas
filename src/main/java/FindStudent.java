import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FindStudent {
    private static final String URL = "jdbc:h2:./data/studentdb;MODE=MySQL;DATABASE_TO_LOWER=TRUE";
    private static final String USER = "sa";
    private static final String PASSWORD = "secret";
    private static final String SQL = "SELECT id, name, program, gpa FROM student WHERE id = ?";

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));
        DriverManager.setLoginTimeout(5);

        if (args.length < 1) {
            System.err.println("Usage: java FindStudent <student-id>");
            return;
        }

        String studentId = args[0];
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            int id = Integer.parseInt(studentId);
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("Query executed; ResultSet captured for studentId=" + studentId);
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid student ID (must be an integer): " + studentId);
        }
    }
}
