package consorcio;

import Controlador.CtrlCliente;
import Controlador.FrmCliente;
import Modelo.Cliente;
import Modelo.ConsultaClientes;

public class Consorcio {


    public static void main(String[] args) {
        Cliente mod = new Cliente();
        ConsultaClientes modC = new ConsultaClientes();
        FrmCliente frm = new FrmCliente();
        CtrlCliente ctrl = new CtrlCliente(mod, modC, frm);
        ctrl.iniciar();
        frm.setVisible(true);
    }
    
}
