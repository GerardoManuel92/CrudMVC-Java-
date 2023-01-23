package Controlador;

import java.awt.event.ActionListener;
import Modelo.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CtrlCliente implements ActionListener {

    private final Cliente mod;
    private final ConsultaClientes modC;
    private final FrmCliente frm;

    public CtrlCliente(Cliente mod, ConsultaClientes modC, FrmCliente frm) {
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;

        this.frm.btnRegistrar.addActionListener(this);
        this.frm.btnBuscar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
    }

    public void iniciar() {
        frm.setTitle("Clientes");
        frm.setLocationRelativeTo(null);
    }
    
    public void Limpiar(){
        frm.txtId.setText(null);
        frm.txtPaterno.setText(null);
        frm.txtMaterno.setText(null);
        frm.txtNombre.setText(null);
        frm.txtRFC.setText(null);
        frm.txtCurp.setText(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.btnRegistrar) {
            mod.setPaterno(frm.txtPaterno.getText());
            mod.setMaterno(frm.txtMaterno.getText());
            mod.setNombre(frm.txtNombre.getText());
            mod.setCurp(frm.txtCurp.getText());
            mod.setRfc(frm.txtRFC.getText());

            try {
                if (modC.registrar(mod)) {
                    JOptionPane.showMessageDialog(null, "Registro guardado correctamente");
                    Limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "El registro no se ha guardado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CtrlCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource() == frm.btnLimpiar){
            Limpiar();
        }
        if(e.getSource() == frm.btnModificar){
            mod.setId(Integer.parseInt(frm.txtId.getText()));
            mod.setPaterno(frm.txtPaterno.getText());
            mod.setMaterno(frm.txtMaterno.getText());
            mod.setNombre(frm.txtNombre.getText());
            mod.setCurp(frm.txtCurp.getText());
            mod.setRfc(frm.txtRFC.getText());            

            try {
                if (modC.modificar(mod)) {
                    JOptionPane.showMessageDialog(null, "Registro modificado correctamente");
                    Limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "El registro no se ha podido modificar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CtrlCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource() == frm.btnEliminar){
            mod.setId(Integer.parseInt(frm.txtId.getText()));           

            try {
                if (modC.eliminar(mod)) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado correctamente");
                    Limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "El registro no se ha podido eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CtrlCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource() == frm.btnBuscar){
            mod.setId(Integer.parseInt(frm.txtId.getText()));           

            try {
                if (modC.buscar(mod)) {
                    frm.txtPaterno.setText(mod.getPaterno());
                    frm.txtMaterno.setText(mod.getMaterno());
                    frm.txtNombre.setText(mod.getNombre());
                    frm.txtCurp.setText(mod.getCurp());
                    frm.txtRFC.setText(mod.getRfc());                    
                    JOptionPane.showMessageDialog(null, "Registro encontrado");                    
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontraron coincidencias", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CtrlCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
