package gm.zona_fit.gui;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicio.ClienteServicio;
import gm.zona_fit.servicio.IClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@Component
public class ZonaFitForma extends JFrame {
    private JPanel panelPrincipal;
    private JTable clientesTabla;
    private JTextField nombreTexto;
    private JTextField apellidoTexto;
    private JTextField membresiaTexto;
    private JButton guardarButton;
    private JButton eliminarButton;
    private JButton limpiarButton;
    IClienteServicio clienteServicio;
    private DefaultTableModel tablaModeloCliente;

    @Autowired
    public ZonaFitForma(ClienteServicio clienterServicio) {
        this.clienteServicio = clienterServicio;
        iniciarForma();
        guardarButton.addActionListener(actionEvent -> guardarCliente());
    }


    private void iniciarForma() {
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.tablaModeloCliente = new DefaultTableModel(0, 4);
        String[] cabeseros = {"id", "Nambre", "Apellido", "Menbresia"};
        this.tablaModeloCliente.setColumnIdentifiers(cabeseros);
        this.clientesTabla = new JTable(tablaModeloCliente);
        // Formatear listado de clientes
        listarClientes();
    }

    private void listarClientes() {
        this.tablaModeloCliente.setRowCount(0);
        var clients = this.clienteServicio.listarCliente();
        clients.forEach(cliente -> {
            Object[] renglonCliente = {
                    cliente.getId(),
                    cliente.getName(),
                    cliente.getLastname(),
                    cliente.getMembership(),
            };
            this.tablaModeloCliente.addRow(renglonCliente);
        });
    }

    private void guardarCliente() {
        if (nombreTexto.getText().equals("")) {
            mostrarMensaje("Proporciona un nombre");
            nombreTexto.requestFocusInWindow();
            return;
        }
        if (membresiaTexto.getText().equals("")) {
            mostrarMensaje("Proporciona una membresia");
            membresiaTexto.requestFocusInWindow();
            return;
        }
//        Recuperamos los valores del formulario
        var name = nombreTexto.getText();
        var lastname = apellidoTexto.getText();
        var membership = Integer.parseInt(membresiaTexto.getText());
        var cliente = new Cliente();
        cliente.setName(name);
        cliente.setLastname(lastname);
        cliente.setMembership(membership);
        this.clienteServicio.guardarCliente(cliente); // inserta en bd
        limpiarFormulario();
        listarClientes();
    }

    private void limpiarFormulario() {
        nombreTexto.setText("");
        apellidoTexto.setText("");
        membresiaTexto.setText("");
    }

    private void mostrarMensaje(String mesaje) {
        JOptionPane.showMessageDialog(this, mesaje);
    }
}
