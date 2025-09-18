package pe.edu.upeu.producto.lista;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pe.edu.upeu.producto.modelo.Producto;

public class ProductoLista implements CrudLista<Producto> {
    private ObservableList<Producto> listaProductos = FXCollections.observableArrayList();

    @Override
    public void agregarProducto(Producto p) {
        listaProductos.add(p);
    }

    @Override
    public void actualizarProducto(Producto p) {

    }

    @Override
    public void eliminarProducto(Producto p) {
        listaProductos.remove(p);
    }

    @Override
    public ObservableList<Producto> listarProducto() {
        return listaProductos;
    }
}