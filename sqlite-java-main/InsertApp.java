package net.sqlitetutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sqlitetutorial.net
 */
public class InsertApp {

    /**
     * Connect to the test.db database
     *
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/test.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Insert a new row into the movies table
     *
     * @param movie
     * @param actor
     * @param actress
     * @param year
     * @param director
     */
    public void insert(String movie, String actor, String actress, int year, String director) {
        String sql = "INSERT INTO movies(movie, actor, actress, year, director) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, movie);
            pstmt.setString(2, actor);
            pstmt.setString(3, actress);
            pstmt.setInt(4, year);
            pstmt.setString(5, director);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        InsertApp app = new InsertApp();
        // insert three new rows
        app.insert('mov1', 'act1', 'femact1', 2019, 'D1');
        app.insert('mov2', 'act1', 'femact2', 2020, 'D2');
        app.insert('mov3', 'act3', 'femact3', 2021, 'D3');
        app.insert('mov4', 'act4', 'femact4', 2022, 'D4');
    }

}
