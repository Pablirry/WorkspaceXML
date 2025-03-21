package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Logic.Biblioteca;
import Model.Prestamo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VentanaPrestamos extends JPanel {

    private Biblioteca biblioteca;
    private JTable table;
    private DefaultTableModel modelo;
    private JTextField tfId, tfIdUsuario, tfIdLibro, tfFechaInicio, tfFechaFin;

    public VentanaPrestamos() {
        biblioteca = new Biblioteca();
        biblioteca.LeerFicheroXML("src/Ficheros/Biblioteca.xml");

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(new Color(245, 245, 245));

        inputPanel.add(new JLabel("ID:"));
        tfId = new JTextField();
        inputPanel.add(tfId);

        inputPanel.add(new JLabel("ID Usuario:"));
        tfIdUsuario = new JTextField();
        inputPanel.add(tfIdUsuario);

        inputPanel.add(new JLabel("ID Libro:"));
        tfIdLibro = new JTextField();
        inputPanel.add(tfIdLibro);

        inputPanel.add(new JLabel("Fecha Inicio (yyyy-MM-dd):"));
        tfFechaInicio = new JTextField();
        inputPanel.add(tfFechaInicio);

        inputPanel.add(new JLabel("Fecha Fin (yyyy-MM-dd):"));
        tfFechaFin = new JTextField();
        inputPanel.add(tfFechaFin);

        add(inputPanel, BorderLayout.NORTH);

        modelo = new DefaultTableModel(new Object[]{"ID", "ID Usuario", "ID Libro", "Fecha Inicio", "Fecha Fin"}, 0);
        table = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(173, 216, 230));

        JButton btnAnadirPrestamo = new JButton("Añadir Préstamo");
        btnAnadirPrestamo.setBackground(new Color(60, 179, 113));
        btnAnadirPrestamo.setForeground(Color.WHITE);
        btnAnadirPrestamo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anadirPrestamo();
            }
        });
        buttonPanel.add(btnAnadirPrestamo);

        JButton btnMostrarPrestamos = new JButton("Mostrar Préstamos");
        btnMostrarPrestamos.setBackground(new Color(70, 130, 180));
        btnMostrarPrestamos.setForeground(Color.WHITE);
        btnMostrarPrestamos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPrestamos();
            }
        });
        buttonPanel.add(btnMostrarPrestamos);

        JButton btnMostrarPrestamosActivos = new JButton("Préstamos Activos");
        btnMostrarPrestamosActivos.setBackground(new Color(255, 165, 0));
        btnMostrarPrestamosActivos.setForeground(Color.WHITE);
        btnMostrarPrestamosActivos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPrestamosActivos();
            }
        });
        buttonPanel.add(btnMostrarPrestamosActivos);

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

    private void mostrarPrestamos() {
        modelo.setRowCount(0);
        for (Prestamo prestamo : biblioteca.getPrestamos()) {
            modelo.addRow(new Object[]{prestamo.getId(), prestamo.getIdUsuario(), prestamo.getIdLibro(), prestamo.getFechaInicio(), prestamo.getFechaFin()});
        }
    }

    private void mostrarPrestamosActivos() {
        modelo.setRowCount(0);
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (Prestamo prestamo : biblioteca.getPrestamos()) {
            LocalDate fechaFin = LocalDate.parse(prestamo.getFechaFin(), formatter);
            if (fechaFin.isAfter(fechaActual)) {
                modelo.addRow(new Object[]{prestamo.getId(), prestamo.getIdUsuario(), prestamo.getIdLibro(), prestamo.getFechaInicio(), prestamo.getFechaFin()});
            }
        }
    }

    private void anadirPrestamo() {
        int id;
        try {
            id = Integer.parseInt(tfId.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (biblioteca.existePrestamo(id)) {
            JOptionPane.showMessageDialog(this, "El ID ya está registrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int idUsuario;
        try {
            idUsuario = Integer.parseInt(tfIdUsuario.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID de usuario debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!biblioteca.existeUsuario(idUsuario)) {
            JOptionPane.showMessageDialog(this, "El ID de usuario no existe", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int idLibro;
        try {
            idLibro = Integer.parseInt(tfIdLibro.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID de libro debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!biblioteca.existeLibro(idLibro)) {
            JOptionPane.showMessageDialog(this, "El ID de libro no existe", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String fechaInicio = tfFechaInicio.getText();
        String fechaFin = tfFechaFin.getText();
        Prestamo prestamo = new Prestamo(id, idUsuario, idLibro, fechaInicio, fechaFin);
        try {
            biblioteca.anadirPrestamo(prestamo);
            biblioteca.escribirFicheroXML("src/Ficheros/Biblioteca.xml");
            mostrarPrestamos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al añadir el préstamo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void borrarCampos() {
        tfId.setText("");
        tfIdUsuario.setText("");
        tfIdLibro.setText("");
        tfFechaInicio.setText("");
        tfFechaFin.setText("");
    }
}