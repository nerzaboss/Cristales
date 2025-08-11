public class Item {
    private String nombre;
    private String descripcion;
    private int stat;

    // Constructor
    public Item(String nombre, String descripcion, int stat) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stat = stat;
    }

    // Métodos getters para acceder a las propiedades
    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getStat() {
        return stat;
    }
}
