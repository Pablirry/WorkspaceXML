package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import Logic.Biblioteca;
import Model.Libro;

import java.io.File;

public class LibrosView {

    private VBox view;
    private Biblioteca biblioteca;

    public LibrosView() {
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
        for (Libro libro : biblioteca.getLibros()) {
            listView.getItems().add(libro.toString());
        }

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));
        form.getStyleClass().add("grid-pane");

        TextField txtId = new TextField();
        txtId.getStyleClass().add("text-field");
        TextField txtTitulo = new TextField();
        txtTitulo.getStyleClass().add("text-field");
        TextField txtGenero = new TextField();
        txtGenero.getStyleClass().add("text-field");
        TextField txtAno = new TextField();
        txtAno.getStyleClass().add("text-field");
        TextField txtAutor = new TextField();
        txtAutor.getStyleClass().add("text-field");

        form.add(new Label("ID:"), 0, 0);
        form.add(txtId, 1, 0);
        form.add(new Label("Título:"), 0, 1);
        form.add(txtTitulo, 1, 1);
        form.add(new Label("Género:"), 0, 2);
        form.add(txtGenero, 1, 2);
        form.add(new Label("Año:"), 0, 3);
        form.add(txtAno, 1, 3);
        form.add(new Label("Autor:"), 0, 4);
        form.add(txtAutor, 1, 4);

        Button btnAdd = new Button("Añadir Libro");
        btnAdd.getStyleClass().add("button");
        btnAdd.setOnAction(e -> {
            Libro libro = new Libro(txtId.getText(), txtTitulo.getText(), txtGenero.getText(), txtAno.getText(), txtAutor.getText());
            biblioteca.anadirLibro(libro);
            biblioteca.escribirFicheroXML(updatedFilePath);
            listView.getItems().add(libro.toString());
        });

        view.getChildren().addAll(listView, form, btnAdd);
    }

    public VBox getView() {
        return view;
    }
}