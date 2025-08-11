import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Patrón de Diseño Modelo-Vista-Controlador (MVC) aplicado en esta aplicación:
//- El JFrame y los componentes de Swing forman la "Vista" (View), que representa la interfaz de usuario.
//- La clase JugadorModel representa el "Modelo" (Model), que maneja la lógica de negocio y la interacción con la base de datos.
//- La clase AgregarJugadorGUI actúa como el "Controlador" (Controller), que conecta la Vista y el Modelo, maneja las acciones del usuario y
//comunica las solicitudes del usuario al Modelo para procesarlas.
//Este enfoque separa claramente las responsabilidades de cada componente, lo que facilita la modificación y el mantenimiento del código.

public class AgregarJugadorGUI {
    private JFrame venjugador;
    private JTextField nombreCampodetexto;
    private JTextField nivelCampodetexto;
    private JTextField experienciaCampodetexto;
    private JugadorModel jugadorModel;

    // Constructor de la clase
    public AgregarJugadorGUI(JugadorModel model) {
        // Configura la ventana principal
    	venjugador = new JFrame("Agregar Jugador");
    	venjugador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	venjugador.setSize(300, 200);
    	venjugador.setLayout(new GridLayout(4, 4));

        // Crea etiquetas y campos de entrada
        JLabel nombreLabel = new JLabel("Nombre del jugador:");
        nombreCampodetexto = new JTextField();
        JLabel nivelLabel = new JLabel("Nivel del jugador:");
        nivelCampodetexto = new JTextField();
        JLabel experienciaLabel = new JLabel("Experiencia del jugador:");
        experienciaCampodetexto = new JTextField();

        // Crea un botón para agregar jugadores
        JButton agregarButton = new JButton("Agregar Jugador");
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarJugador(); // Invoca el método cuando se hace clic en el botón
            }
        });

        // Agrega componentes a la ventana
        venjugador.add(nombreLabel);
        venjugador.add(nombreCampodetexto);
        venjugador.add(nivelLabel);
        venjugador.add(nivelCampodetexto);
        venjugador.add(experienciaLabel);
        venjugador.add(experienciaCampodetexto);
        venjugador.add(new JLabel()); // Espacio en blanco
        venjugador.add(agregarButton);

        // Asigna el modelo del jugador
        this.jugadorModel = model;
        venjugador.setVisible(true); // Hace visible la ventana
    }

    // Método para agregar un jugador
    private void agregarJugador() {
        String nombre = nombreCampodetexto.getText();
        int nivel = Integer.parseInt(nivelCampodetexto.getText());
        int experiencia = Integer.parseInt(experienciaCampodetexto.getText());

        // Llama al método en el modelo para agregar el jugador
        if (jugadorModel.agregarJugador(nombre, nivel, experiencia)) {
            JOptionPane.showMessageDialog(venjugador, "Jugador agregado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(venjugador, "No se pudo agregar el jugador.");
        }
    }

    // Método principal para iniciar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Crea una instancia del modelo y la pasa al constructor de la interfaz
                JugadorModel model = new JugadorModel("jdbc:mysql://localhost:3306/cristales", "Manolo", "123456");
                new AgregarJugadorGUI(model); // Crea la interfaz gráfica
            }
        });
    }
}
