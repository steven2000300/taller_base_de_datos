package Listados;
import javax.swing.table.DefaultTableModel;

public class ModeloTabla extends DefaultTableModel{
    
    public ModeloTabla(Object datos[][], Object encazados[]){
        super(datos, encazados);
    }
    
    public boolean isCellEditable(int row, int column){
        //return true;
        return false;
    }
}
