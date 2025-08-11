import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Item> items;

    // Constructor
    public Inventario() {
        items = new ArrayList<>();
    }

    // Método para agregar un objeto al Inventario
    public void addItem(Item item) {
        items.add(item);
    }

    // Método para usar un objeto del Inventario por índice
    public void useItem(int index) {
        if (index >= 0 && index < items.size()) {
            Item item = items.get(index);
            System.out.println("Usaste el objeto: " + item.getNombre());
            items.remove(index);
        }
    }
}
