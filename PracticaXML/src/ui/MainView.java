package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class MainView {

    private BorderPane view;

    public MainView() {
        view = new BorderPane();

        TabPane tabPane = new TabPane();

        Tab tabLibros = new Tab("Libros", new LibrosView().getView());
        Tab tabUsuarios = new Tab("Usuarios", new UsuariosView().getView());
        Tab tabPrestamos = new Tab("Préstamos", new PrestamosView().getView());

        tabPane.getTabs().addAll(tabLibros, tabUsuarios, tabPrestamos);

        view.setCenter(tabPane);

        HBox bottomMenu = new HBox(10);
        bottomMenu.setPadding(new Insets(10));
        bottomMenu.getStyleClass().add("hbox");
        Button btnSalir = new Button("Salir");
        btnSalir.getStyleClass().add("button");
        btnSalir.setOnAction(e -> System.exit(0));
        HBox.setHgrow(btnSalir, Priority.ALWAYS);
        btnSalir.setMaxWidth(Double.MAX_VALUE);
        bottomMenu.getChildren().add(btnSalir);

        view.setBottom(bottomMenu);
    }

    public BorderPane getView() {
        return view;
    }
}