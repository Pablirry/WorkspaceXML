package ui;

import javax.swing.*;
import Logic.Biblioteca;
import Model.Usuario;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaUsuarios extends JPanel {

    private Biblioteca biblioteca;
    private JList<String> listaUsuario;
    private DefaultListModel<String> modelo;
    private JTextField idField, nombreField, nacionalidadField;

    public VentanaUsuarios() {
        biblioteca = new Biblioteca();
        biblioteca.LeerFicheroXML("src/Ficheros/Biblioteca.xml");

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(new Color(245, 245, 245));

        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        inputPanel.add(nombreField);

        inputPanel.add(new JLabel("Nacionalidad:"));
        nacionalidadField = new JTextField();
        inputPanel.add(nacionalidadField);

        add(inputPanel, BorderLayout.NORTH);

        modelo = new DefaultListModel<>();
        listaUsuario = new JList<>(modelo);
        JScrollPane scrollPane = new JScrollPane(listaUsuario);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(173, 216, 230));

        JButton btnAnadirUsuario = new JButton("Añadir Usuario");
        btnAnadirUsuario.setBackground(new Color(60, 179, 113));
        btnAnadirUsuario.setForeground(Color.WHITE);
        btnAnadirUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anadirUsuario();
            }
        });
        buttonPanel.add(btnAnadirUsuario);

        JButton btnMostrarUsuarios = new JButton("Mostrar Usuarios");
        btnMostrarUsuarios.setBackground(new Color(70, 130, 180));
        btnMostrarUsuarios.setForeground(Color.WHITE);
        btnMostrarUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarUsuarios();
            }
        });
        buttonPanel.add(btnMostrarUsuarios);

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

    private void mostrarUsuarios() {
        modelo.clear();
        for (Usuario usuario : biblioteca.getUsuarios()) {
            modelo.addElement("ID: " + usuario.getId() + ", Nombre: " + usuario.getNombre() + ", Nacionalidad: " + usuario.getNacionalidad());
        }
    }

    private void anadirUsuario() {
        int id;
        try {
            id = Integer.parseInt(idField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (biblioteca.existeUsuario(id)) {
            JOptionPane.showMessageDialog(this, "El ID ya está registrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String nombre = nombreField.getText();
        String nacionalidad = nacionalidadField.getText();
        Usuario usuario = new Usuario(id, nombre, nacionalidad);
        try {
            biblioteca.anadirUsuario(usuario);
            biblioteca.escribirFicheroXML("src/Ficheros/Biblioteca.xml");
            mostrarUsuarios();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al añadir el usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void borrarCampos() {
        idField.setText("");
        nombreField.setText("");
        nacionalidadField.setText("");
    }
}