package pe.edu.upeu.producto.lista;

import javafx.collections.ObservableList;

public interface CrudLista<T> {
    void agregarProducto(T item);
    void actualizarProducto(T item);
    void eliminarProducto(T item);
    ObservableList<T> listarProducto();
}