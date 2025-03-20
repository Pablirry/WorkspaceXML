package ui;

import javax.swing.*;

public class VentanaGestion extends JFrame {

	public VentanaGestion() {
        setTitle("Gestión de Biblioteca");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Libros", new VentanaLibros());
        tabbedPane.addTab("Usuarios", new VentanaUsuarios());
        tabbedPane.addTab("Préstamos", new VentanaPrestamos());

        add(tabbedPane);
    }

}
