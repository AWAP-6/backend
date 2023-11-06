package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class testDB {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "postgres";

        // Query to fetch the latest data or changes, adjust as needed
        String query = "SELECT * FROM \"lockers_schema\".\"lockers\"";

        try {
            while (true) {
                try (Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(query)) {

                    while (rs.next()) {
                        int lockNum = rs.getInt("lock_num");
                        String openCode = rs.getString("open_code");
                        boolean isEmpty = rs.getBoolean("is_empty");
                        int location_id = rs.getInt("location_id");

                        System.out.println("LockNum: " + lockNum + ", OpenCode: " + openCode + ", IsEmpty: " + isEmpty + ", Location_ID: " + location_id);
                    }

                    // Sleep for a set interval before polling again, e.g., 5 seconds
                    Thread.sleep(5000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
