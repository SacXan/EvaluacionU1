package pe.edu.upeu.producto.modelo;

public class Producto extends Item {
    private double precio;
    private int stock;

    public Producto(int id, String nombre, String descripcion, double precio, int stock) {
        super(id, nombre, descripcion); // hereda de Item
        this.precio = precio;
        this.stock = stock;
    }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
