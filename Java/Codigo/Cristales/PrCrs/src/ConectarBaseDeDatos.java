import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConectarBaseDeDatos {
    
    public ArrayList<Jugador> obtenerJugadores() {
        
        ArrayList<Jugador> jugadores = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Paso 1: Establecer la conexión con la base de datos
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cristales", "Usuario", "Contraseña");

            // Paso 2: Crear el objeto Statement para ejecutar la consulta SQL
            stmt = conn.createStatement();

            // Paso 3: Ejecutar la consulta SQL y obtener el resultado en un ResultSet
            rs = stmt.executeQuery("SELECT id, nombre, nivel, experiencia FROM jugador");

            // Paso 4: Recorrer el ResultSet y agregar los datos al ArrayList
            while (rs.next()) {
                // Obtener datos del ResultSet
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int nivel = rs.getInt("nivel");
                int experiencia = rs.getInt("experiencia");

                // Crear objeto Jugador y agregarlo a la lista
                
                Jugador jugador = new Jugador(id, nombre, nivel, experiencia);
                jugadores.add(jugador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Paso 5: Cerrar las conexiones y liberar los recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return jugadores;
    }
}
