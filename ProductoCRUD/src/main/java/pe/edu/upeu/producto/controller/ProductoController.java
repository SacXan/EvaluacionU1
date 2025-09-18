package pe.edu.upeu.producto.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import pe.edu.upeu.producto.lista.ProductoLista;
import pe.edu.upeu.producto.modelo.Producto;

public class ProductoController {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtStock;
    @FXML
    private TableView<Producto> tablaProductos;
    @FXML
    private TableColumn<Producto, Integer> colId;
    @FXML
    private TableColumn<Producto, String> colNombre;
    @FXML
    private TableColumn<Producto, String> colDescripcion;
    @FXML
    private TableColumn<Producto, Double> colPrecio;
    @FXML
    private TableColumn<Producto, Integer> colStock;

    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuEstilo = new Menu("Estilos");
    @FXML
    private CustomMenuItem customMenuEstilo = new CustomMenuItem();
    @FXML
    private MenuItem menuItem1 = new MenuItem("Estilo Oscuro");
    @FXML
    private MenuItem menuItem2 = new MenuItem("Estilo Azul");
    @FXML
    private MenuItem menuItem3 = new MenuItem("Estilo Verde");
    @FXML
    private MenuItem menuItem4 = new MenuItem("Estilo Rosado");

    private ProductoLista productoLista;

    @FXML
    public void initialize() {
        productoLista = new ProductoLista();

        colId.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getId()).asObject());
        colNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        colDescripcion.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDescripcion()));
        colPrecio.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getPrecio()).asObject());
        colStock.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getStock()).asObject());
        tablaProductos.setItems(productoLista.listarProducto());

        tablaProductos.getSelectionModel().selectedItemProperty().addListener((obs, antiguiaV, nuevaV) -> {
            if (nuevaV != null) {
                txtId.setText(String.valueOf(nuevaV.getId()));
                txtNombre.setText(nuevaV.getNombre());
                txtDescripcion.setText(nuevaV.getDescripcion());
                txtPrecio.setText(String.valueOf(nuevaV.getPrecio()));
                txtStock.setText(String.valueOf(nuevaV.getStock()));
            }
        });

        comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Estilo por defecto", "Estilo Oscuro", "Estilo Azul", "Estilo Verde", "Estilo Rosado");
        comboBox.setOnAction((e) -> cambiarEstilo());

        customMenuEstilo.setContent(comboBox);
        customMenuEstilo.setHideOnClick(false);

        menuEstilo.getItems().addAll(customMenuEstilo, new SeparatorMenuItem(), menuItem1, menuItem2, menuItem3, menuItem4);

        MenuItemListener miL = new MenuItemListener();
        menuItem1.setOnAction(miL::handle);
        menuItem2.setOnAction(miL::handle);
        menuItem3.setOnAction(miL::handle);
        menuItem4.setOnAction(miL::handle);
    }

    //METODOS CRUD
    @FXML
    private void agregarProducto() {
        int id = Integer.parseInt(txtId.getText());
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        double precio = Double.parseDouble(txtPrecio.getText());
        int stock = Integer.parseInt(txtStock.getText());

        Producto p = new Producto(id, nombre, descripcion, precio, stock);
        productoLista.agregarProducto(p);
        limpiarCampos();
    }

    @FXML
    private void actualizarProducto() {
        Producto seleccionado = tablaProductos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            seleccionado.setId(Integer.parseInt(txtId.getText()));
            seleccionado.setNombre(txtNombre.getText());
            seleccionado.setDescripcion(txtDescripcion.getText());
            seleccionado.setPrecio(Double.parseDouble(txtPrecio.getText()));
            seleccionado.setStock(Integer.parseInt(txtStock.getText()));

            tablaProductos.refresh();
            limpiarCampos();
        }
    }

    @FXML
    private void eliminarProducto() {
        Producto seleccionado = tablaProductos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            productoLista.eliminarProducto(seleccionado);
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        txtId.clear();
        txtNombre.clear();
        txtDescripcion.clear();
        txtPrecio.clear();
        txtStock.clear();
    }

    public void cambiarEstiloPorNombre(String estiloSeleccionado) {
        Scene scene = menuBar.getScene();
        scene.getStylesheets().clear();

        switch (estiloSeleccionado) {
            case "Estilo Oscuro":
                scene.getStylesheets().add(getClass().getResource("/css/estilo-oscuro.css").toExternalForm());
                break;
            case "Estilo Azul":
                scene.getStylesheets().add(getClass().getResource("/css/estilo-azul.css").toExternalForm());
                break;
            case "Estilo Verde":
                scene.getStylesheets().add(getClass().getResource("/css/estilo-verde.css").toExternalForm());
                break;
            case "Estilo Rosado":
                scene.getStylesheets().add(getClass().getResource("/css/estilo-rosado.css").toExternalForm());
                break;
            default:
                scene.getStylesheets().clear();
                break;
        }
    }

    public void cambiarEstilo() {
        String estiloSeleccionado = comboBox.getSelectionModel().getSelectedItem();
        cambiarEstiloPorNombre(estiloSeleccionado);
    }

    class MenuItemListener {
        public void handle(ActionEvent e) {
            MenuItem source = (MenuItem) e.getSource();
            cambiarEstiloPorNombre(source.getText());
        }
    }
}