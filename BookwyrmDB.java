package main.Database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class BookwyrmDB {
    public Connection conn;

    public BookwyrmDB() {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("resources/db.properties"));
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.username");
            String password = props.getProperty("db.password");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BookwyrmDB db = new BookwyrmDB();
        db.migrateDown();
        db.migrateUp();
        db.seedData();
    }

    public void migrateDown() {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("SET FOREIGN_KEY_CHECKS = 0");
            stmt.execute("DROP TABLE IF EXISTS coins_transaction, inventory, battles, leaderboards, questions, letter_grid, sphinx, users, items");
            stmt.execute("SET FOREIGN_KEY_CHECKS = 1");
            System.out.println("Tables dropped.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void migrateUp() {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("""
                CREATE TABLE users (
                    user_id INT AUTO_INCREMENT PRIMARY KEY,
                    username VARCHAR(50) NOT NULL UNIQUE,
                    password VARCHAR(100) NOT NULL,
                    email VARCHAR(100) NOT NULL UNIQUE,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
            """);

            stmt.execute("""
                CREATE TABLE items (
                    item_id INT AUTO_INCREMENT PRIMARY KEY,
                    item_name VARCHAR(100) NOT NULL,
                    description TEXT,
                    rarity VARCHAR(50),
                    value INT NOT NULL
                )
            """);

            stmt.execute("""
                CREATE TABLE inventory (
                    inventory_id INT AUTO_INCREMENT PRIMARY KEY,
                    user_id INT NOT NULL,
                    item_id INT NOT NULL,
                    quantity INT NOT NULL CHECK (quantity >= 0),
                    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
                    FOREIGN KEY (item_id) REFERENCES items(item_id) ON DELETE CASCADE,
                    UNIQUE (user_id, item_id)
                )
            """);

            stmt.execute("""
                CREATE TABLE coins_transaction (
                    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
                    user_id INT NOT NULL,
                    item_id INT,
                    action ENUM('buy', 'sell') NOT NULL,
                    quantity INT NOT NULL,
                    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (user_id) REFERENCES users(user_id),
                    FOREIGN KEY (item_id) REFERENCES items(item_id)
                )
            """);

            stmt.execute("""
                CREATE TABLE leaderboards (
                    leaderboard_id INT AUTO_INCREMENT PRIMARY KEY,
                    user_id INT NOT NULL,
                    score INT NOT NULL,
                    playtime_minutes INT DEFAULT 0,
                    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
                )
            """);

            stmt.execute("""
                CREATE TABLE battles (
                    battle_id INT AUTO_INCREMENT PRIMARY KEY,
                    user_id INT,
                    result ENUM('win', 'lose'),
                    duration_minutes INT,
                    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (user_id) REFERENCES users(user_id)
                )
            """);

            System.out.println("Tables created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void seedData() {
        try (PreparedStatement stmt = conn.prepareStatement("""
            INSERT INTO items (item_name, value) VALUES
            ('Health Regen', 100),
            ('Increase Damage', 120),
            ('Word Correction', 150)
        """)) {
            stmt.executeUpdate();
            System.out.println("Seed data inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------- Login ----------
    public boolean login(String username, String password) {
        String sql = "SELECT password FROM users WHERE username = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return storedPassword.equals(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateUserEmail(int userId, String newEmail) {
        String sql = "UPDATE users SET email = ? WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newEmail);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
            System.out.println("User email updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
            System.out.println("User deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getUserById(int userId) {
        String sql = "SELECT username, email FROM users WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.printf("Username: %s, Email: %s%n", rs.getString("username"), rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------- CRUD: ITEMS ----------
    public void createItem(String name, String desc, String rarity, int value) {
        String sql = "INSERT INTO items (item_name, value) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, desc);
            stmt.setString(3, rarity);
            stmt.setInt(4, value);
            stmt.executeUpdate();
            System.out.println("Item created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateItemValue(int itemId, int newValue) {
        String sql = "UPDATE items SET value = ? WHERE item_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newValue);
            stmt.setInt(2, itemId);
            stmt.executeUpdate();
            System.out.println("Item value updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteItem(int itemId) {
        String sql = "DELETE FROM items WHERE item_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, itemId);
            stmt.executeUpdate();
            System.out.println("Item deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listAllItems() {
        String sql = "SELECT item_id, value FROM items";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s, Value: %d%n",
                        rs.getInt("item_id"), rs.getString("item_name"),
                        rs.getInt("value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------- ANALYTICS ----------
    public void printTopUsersByScore() {
        String sql = "SELECT u.username, l.score FROM leaderboards l JOIN users u ON l.user_id = u.user_id ORDER BY l.score DESC LIMIT 5";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Top 5 Users by Score:");
            while (rs.next()) {
                System.out.printf("Username: %s, Score: %d%n", rs.getString("username"), rs.getInt("score"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAveragePlaytime() {
        String sql = "SELECT AVG(playtime_minutes) AS avg_playtime FROM leaderboards";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                System.out.printf("Average Playtime: %.2f minutes%n", rs.getDouble("avg_playtime"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printTotalBattles() {
        String sql = "SELECT COUNT(*) AS total_battles FROM battles";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                System.out.printf("Total Battles Fought: %d%n", rs.getInt("total_battles"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printMostOwnedItem() {
        String sql = """
            SELECT i.item_name, SUM(inv.quantity) AS total_quantity
            FROM inventory inv
            JOIN items i ON inv.item_id = i.item_id
            GROUP BY inv.item_id
            ORDER BY total_quantity DESC
            LIMIT 1
        """;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                System.out.printf("Most Owned Item: %s (Total Owned: %d)%n", rs.getString("item_name"), rs.getInt("total_quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}