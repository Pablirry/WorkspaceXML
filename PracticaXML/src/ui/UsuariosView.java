package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import Logic.Biblioteca;
import Model.Usuario;

import java.io.File;

public class UsuariosView {

    private VBox view;
    private Biblioteca biblioteca;

    public UsuariosView() {
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
        for (Usuario usuario : biblioteca.getUsuarios()) {
            listView.getItems().add(usuario.toString());
        }

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));
        form.getStyleClass().add("grid-pane");

        TextField txtId = new TextField();
        txtId.getStyleClass().add("text-field");
        TextField txtNombre = new TextField();
        txtNombre.getStyleClass().add("text-field");
        TextField txtNacionalidad = new TextField();
        txtNacionalidad.getStyleClass().add("text-field");

        form.add(new Label("ID:"), 0, 0);
        form.add(txtId, 1, 0);
        form.add(new Label("Nombre:"), 0, 1);
        form.add(txtNombre, 1, 1);
        form.add(new Label("Nacionalidad:"), 0, 2);
        form.add(txtNacionalidad, 1, 2);

        Button btnAdd = new Button("Añadir Usuario");
        btnAdd.getStyleClass().add("button");
        btnAdd.setOnAction(e -> {
            Usuario usuario = new Usuario(txtId.getText(), txtNombre.getText(), txtNacionalidad.getText());
            biblioteca.anadirUsuario(usuario);
            biblioteca.escribirFicheroXML(updatedFilePath);
            listView.getItems().add(usuario.toString());
        });

        view.getChildren().addAll(listView, form, btnAdd);
    }

    public VBox getView() {
        return view;
    }
}