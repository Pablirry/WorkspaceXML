package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import Logic.Biblioteca;
import Model.Prestamo;

import java.io.File;

public class PrestamosView {

    private VBox view;
    private Biblioteca biblioteca;

    public PrestamosView() {
        biblioteca = new Biblioteca();
        String updatedFilePath = System.getProperty("user.dir") + "\\src\\ficheros\\biblioteca_actualizada.xml";
        String originalFilePath = System.getProperty("user.dir") + "\\src\\ficheros\\biblioteca.xml";
        File updatedFile = new File(updatedFilePath);
        if (updatedFile.exists()) {
            biblioteca.LeerFicheroXML(updatedFilePath);
        } else {
            biblioteca.LeerFicheroXML(originalFilePath);
        }

        view = new VBox(10);
        view.setPadding(new Insets(10));
        view.getStyleClass().add("vbox");

        ListView<String> listView = new ListView<>();
        listView.getStyleClass().add("list-view");
        for (Prestamo prestamo : biblioteca.getPrestamos()) {
            listView.getItems().add(prestamo.toString());
        }

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));
        form.getStyleClass().add("grid-pane");

        TextField txtId = new TextField();
        txtId.getStyleClass().add("text-field");
        TextField txtIdUsuario = new TextField();
        txtIdUsuario.getStyleClass().add("text-field");
        TextField txtIdLibro = new TextField();
        txtIdLibro.getStyleClass().add("text-field");
        TextField txtFechaInicio = new TextField();
        txtFechaInicio.getStyleClass().add("text-field");
        TextField txtFechaFin = new TextField();
        txtFechaFin.getStyleClass().add("text-field");

        form.add(new Label("ID:"), 0, 0);
        form.add(txtId, 1, 0);
        form.add(new Label("ID Usuario:"), 0, 1);
        form.add(txtIdUsuario, 1, 1);
        form.add(new Label("ID Libro:"), 0, 2);
        form.add(txtIdLibro, 1, 2);
        form.add(new Label("Fecha Inicio:"), 0, 3);
        form.add(txtFechaInicio, 1, 3);
        form.add(new Label("Fecha Fin:"), 0, 4);
        form.add(txtFechaFin, 1, 4);

        Button btnAdd = new Button("Añadir Préstamo");
        btnAdd.getStyleClass().add("button");
        btnAdd.setOnAction(e -> {
            Prestamo prestamo = new Prestamo(txtId.getText(), txtIdUsuario.getText(), txtIdLibro.getText(), txtFechaInicio.getText(), txtFechaFin.getText());
            biblioteca.anadirPrestamo(prestamo);
            biblioteca.escribirFicheroXML(updatedFilePath);
            listView.getItems().add(prestamo.toString());
        });

        view.getChildren().addAll(listView, form, btnAdd);
    }

    public VBox getView() {
        return view;
    }
}