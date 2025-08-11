import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JugadorModel {
    private List<Observador> observadores = new ArrayList<>();
    private ArrayList<Jugador> jugadores;
    private int indiceActual;
    private String url; // Debes definir la URL de la base de datos aquí
    private String usuario; // Debes definir el nombre de usuario de la base de datos aquí
    private String contraseña; // Debes definir la contraseña de la base de datos aquí

    public JugadorModel(String url, String usuario, String contraseña) {
        this.url = url;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    // Agrega un observador a la lista de observadores
    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    // Notifica a todos los observadores cuando se agrega un jugador
    private void notificarObservadores(Jugador jugador) {
        for (Observador observador : observadores) {
            observador.actualizar(jugador);
        }
    }

    public boolean agregarJugador(String nombre, int nivel, int experiencia) {
        // Define la consulta SQL de inserción
        String sql = "INSERT INTO jugador (nombre, nivel, experiencia) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Configura los valores de los parámetros de la consulta
            stmt.setString(1, nombre);
            stmt.setInt(2, nivel);
            stmt.setInt(3, experiencia);

            // Ejecuta la consulta de inserción
            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                // Notifica a los observadores cuando se agrega un jugador exitosamente
                notificarObservadores(new Jugador(filasAfectadas, nombre, nivel, experiencia));
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public int getIndiceActual() {
		return indiceActual;
	}

	public void setIndiceActual(int indiceActual) {
		this.indiceActual = indiceActual;
	}
}
