public class Jugador {
	private int id;             // id del jugador
    private String nombre;      // Nombre del jugador
    private int nivel;          // Nivel actual del jugador
    private int experiencia;    // Puntos de experiencia del jugador
    // Constructor
    public Jugador(int id, String nombre, int nivel, int experiencia) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.experiencia = experiencia;
    }

    // Métodos getter y setter para acceder y modificar las propiedades del jugador
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
