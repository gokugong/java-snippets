package jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTestBed {
  public static void main(String[] args) {
    Connection conn = null;

    try {
      //DriverManager.registerDriver(new SQLServerDriver());
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

      String dbURL = "jdbc:sqlserver://10.238.21.39\\PPSDEV:1433;forwardReadOnlyMethod=serverCursor;databaseName=RentPayment_YS";
      String user = "pret_webuser"; //""RP-CS-webuser";
      String pass = "4hzPCV#JyYDvkMX5^iQ2k3Nx"; //""RP-CS-webuser";
      conn = DriverManager.getConnection(dbURL, user, pass);
      if (conn != null) {
        DatabaseMetaData dm = conn.getMetaData();
        System.out.println("Driver name: " + dm.getDriverName());
        System.out.println("Driver version: " + dm.getDriverVersion());
        System.out.println("Product name: " + dm.getDatabaseProductName());
        System.out.println("Product version: " + dm.getDatabaseProductVersion());

        Statement stmt = conn.createStatement();
        String SQL = "SELECT TOP 10 * FROM rp.Account";
        ResultSet rs = stmt.executeQuery(SQL);

        // Iterate through the data in the result set and display it.
        while (rs.next()) {
          System.out.println(rs.getLong("id") + " " + rs.getString("alias"));
        }
      } else {
        System.out.println("No connection");
      }
    } catch (SQLException | ClassNotFoundException ex) {
      ex.printStackTrace();
    } finally {
      try {
        if (conn != null && !conn.isClosed()) {
          conn.close();
        }
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
/*
    // Create a variable for the connection string.
    String connectionUrl = "jdbc:sqlserver://10.238.21.39\\PPSDEV:1433;forwardReadOnlyMethod=serverCursor;databaseName=RentPayment_YS;applicationName=DBBlockingQueue;user=RP-CS-webuser;password=RP-CS-webuser";

    try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
      String SQL = "SELECT TOP 10 * FROM rp.Account";
      ResultSet rs = stmt.executeQuery(SQL);

      // Iterate through the data in the result set and display it.
      while (rs.next()) {
        System.out.println(rs.getLong("id") + " " + rs.getString("alias"));
      }
    }
    // Handle any errors that may have occurred.
    catch (SQLException e) {
      e.printStackTrace();
    }
*/
  }
}
