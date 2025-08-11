import java.sql.*;

public class RPGInventory {
    private static final String DATABASE_URL = "rpg_inventory.db";

    public static void main(String[] args) {
        createTable();

        addItem("Espada", "Arma afilada");
        addItem("Poción de curación", "Restaura 50 puntos de vida");

        displayInventory();
    }

    private static void createTable() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {

            String createTableSQL = "CREATE TABLE IF NOT EXISTS inventory (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "description TEXT);";

            statement.execute(createTableSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addItem(String name, String description) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO inventory (name, description) VALUES (?, ?)")) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.executeUpdate();

            System.out.println("Se ha añadido '" + name + "' al inventario.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void displayInventory() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM inventory")) {

            System.out.println("Inventario:");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");

                System.out.println("ID: " + id + ", Nombre: " + name + ", Descripción: " + description);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
