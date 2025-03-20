package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Logic.Biblioteca;
import Model.Libro;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLibros extends JPanel {

    private Biblioteca biblioteca;
    private JTable table;
    private DefaultTableModel modelo;
    private JTextField tfId, tfTitulo, tfGenero, tfAno, tfAutor;

    public VentanaLibros() {
        biblioteca = new Biblioteca();
        biblioteca.LeerFicheroXML("src/Ficheros/Biblioteca.xml");

        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(245, 245, 245));

        panel.add(new JLabel("ID:"));
        tfId = new JTextField();
        panel.add(tfId);

        panel.add(new JLabel("Título:"));
        tfTitulo = new JTextField();
        panel.add(tfTitulo);

        panel.add(new JLabel("Género:"));
        tfGenero = new JTextField();
        panel.add(tfGenero);

        panel.add(new JLabel("Año:"));
        tfAno = new JTextField();
        panel.add(tfAno);

        panel.add(new JLabel("Autor:"));
        tfAutor = new JTextField();
        panel.add(tfAutor);

        add(panel, BorderLayout.NORTH);

        modelo = new DefaultTableModel(new Object[]{"ID", "Título", "Género", "Año", "Autor"}, 0);
        table = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(173, 216, 230));

        JButton btnAnadirLibro = new JButton("Añadir Libro");
        btnAnadirLibro.setBackground(new Color(60, 179, 113));
        btnAnadirLibro.setForeground(Color.WHITE);
        btnAnadirLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anadirLibro();
            }
        });
        buttonPanel.add(btnAnadirLibro);

        JButton btnMostrarLibros = new JButton("Mostrar Libros");
        btnMostrarLibros.setBackground(new Color(70, 130, 180));
        btnMostrarLibros.setForeground(Color.WHITE);
        btnMostrarLibros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarLibros();
            }
        });
        buttonPanel.add(btnMostrarLibros);

        JButton btnBorrarCampos = new JButton("Borrar");
        btnBorrarCampos.setBackground(new Color(255, 69, 0));
        btnBorrarCampos.setForeground(Color.WHITE);
        btnBorrarCampos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarCampos();
            }
        });
        buttonPanel.add(btnBorrarCampos);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void mostrarLibros() {
        modelo.setRowCount(0);
        for (Libro libro : biblioteca.getLibros()) {
            modelo.addRow(new Object[]{libro.getId(), libro.getTitulo(), libro.getGenero(), libro.getAno(), libro.getAutor()});
        }
    }

    private void anadirLibro() {
        int id;
        try {
            id = Integer.parseInt(tfId.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (biblioteca.existeLibro(id)) {
            JOptionPane.showMessageDialog(this, "El ID ya está registrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String titulo = tfTitulo.getText();
        String genero = tfGenero.getText();
        int ano;
        try {
            ano = Integer.parseInt(tfAno.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El año debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String autor = tfAutor.getText();
        Libro libro = new Libro(id, titulo, genero, ano, autor);
        try {
            biblioteca.anadirLibro(libro);
            biblioteca.escribirFicheroXML("src/Ficheros/Biblioteca.xml");
            mostrarLibros();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al añadir el libro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void borrarCampos() {
        tfId.setText("");
        tfTitulo.setText("");
        tfGenero.setText("");
        tfAno.setText("");
        tfAutor.setText("");
    }
}