import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CristalesGUI implements Observador { 
    private ArrayList<Jugador> jugadores;
    private int indiceActual;
    private JTextField nombreCampodetexto;
    private JTextField nivelCampodetexto;
    private JTextField experienciaCampodetexto;
    private JugadorModel jugadorModel; // Agrega una referencia al modelo

    // Constructor de la clase
    public CristalesGUI(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.indiceActual = 0;

        // Crear una ventana Swing
        JFrame venmain = new JFrame("Lista de Jugadores");
        venmain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        venmain.setSize(400, 200);

        // Crear un panel para la interfaz de usuario (Patrón Composite)
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        // Crear etiquetas y campos de texto
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreCampodetexto = new JTextField(20);
        JLabel nivelLabel = new JLabel("Nivel:");
        nivelCampodetexto = new JTextField(10);
        JLabel experienciaLabel = new JLabel("Experiencia:");
        experienciaCampodetexto = new JTextField(10);

        // Crear botones de navegación (Patrón Command)
        JButton btnAnterior = new JButton("Anterior");
        JButton btnSiguiente = new JButton("Siguiente");

        // Configurar ActionListener para los botones (Patrón Observer)
        btnAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarJugadorAnterior();
            }
        });

        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarSiguienteJugador();
            }
        });

        // Agregar componentes al panel
        panel.add(nombreLabel);
        panel.add(nombreCampodetexto);
        panel.add(nivelLabel);
        panel.add(nivelCampodetexto);
        panel.add(experienciaLabel);
        panel.add(experienciaCampodetexto);
        panel.add(btnAnterior);
        panel.add(btnSiguiente);

        // Agregar el panel al marco
        venmain.add(panel);

        // Hacer visible la ventana
        venmain.setVisible(true);

        // Mostrar el jugador actual
        mostrarJugadorActual();

        // Crear una instancia del modelo (Patrón Modelo-Vista-Controlador)
        jugadorModel = new JugadorModel("jdbc:mysql://localhost:3306/cristales", "Manolo", "123456");

        // Registrar esta GUI como observador después de crear el modelo (Patrón Observer)
        jugadorModel.agregarObservador(this);
    }

    // Método para mostrar los datos del jugador actual en las cajas de texto
    private void mostrarJugadorActual() {
        if (indiceActual >= 0 && indiceActual < jugadores.size()) {
            Jugador jugador = jugadores.get(indiceActual);
            nombreCampodetexto.setText(jugador.getNombre());
            nivelCampodetexto.setText(String.valueOf(jugador.getNivel()));
            experienciaCampodetexto.setText(String.valueOf(jugador.getExperiencia()));
        }   
    }

    // Método para mostrar el jugador anterior
    private void mostrarJugadorAnterior() {
        if (indiceActual > 0) {
            indiceActual--;
            mostrarJugadorActual();
        }
    }

    // Método para mostrar el siguiente jugador
    private void mostrarSiguienteJugador() {
        if (indiceActual < jugadores.size() - 1) {
            indiceActual++;
            mostrarJugadorActual();
        }
    }

    @Override
    public void actualizar(Jugador jugador) {
        // Actualiza la GUI con los datos del jugador proporcionado
        nombreCampodetexto.setText(jugador.getNombre());
        nivelCampodetexto.setText(String.valueOf(jugador.getNivel()));
        experienciaCampodetexto.setText(String.valueOf(jugador.getExperiencia()));
    }

    // Método principal para iniciar la aplicación
    public static void main(String[] args) {
        // Conectar a la base de datos y obtener la lista de jugadores
        ConectarBaseDeDatos dbConnection = new ConectarBaseDeDatos();
        ArrayList<Jugador> jugadores = dbConnection.obtenerJugadores();

        // Crear la interfaz gráfica Swing en un hilo de eventos (Patrón Singleton)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CristalesGUI(jugadores);
            }
        });
    }
}
