/*
listado clientes
Autores:
Jhon Edward Steven Loaiza Díaz (230231046)
Angel Julian Cuero Jiménez (230231018)
Juan Diego Rodriguez(230231020)
Juan Pablo Devia (230231003)
Fecha de ult. actualización: 02-11-23
Versión 1.0
 */
package Listados;

import Entidad.BotonModificado;
import Entidad.MenuPrincipal;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;


public class ListadoClientes extends JFrame {

    MenuPrincipal mp;
    ModeloTabla mt;
    JTable tabla;
    Menu_listado lista1;

    public ListadoClientes(Menu_listado obj) {
        super("Listado de clientes");
        lista1 = obj;
        setSize(500, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        salirboton();
        setFocusable(true);
        crearGUI();
        
        setVisible(false);
    }

    private void crearGUI() {
        JLabel jl = new JLabel("  Listado de Clientes");
        jl.setBounds(0, 0, 500, 50);
        jl.setBorder(new EtchedBorder());
        jl.setOpaque(true);
        jl.setBackground(Color.WHITE);
        jl.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(jl);

        String encabezados[] = {"Identificacion", "Nombre", "Fecha de nacimiento", "edad", "genero", "Tipo de RH"};
        String datos[][] = {{"", "", "", "", "", "", ""}};

        mt = new ModeloTabla(datos, encabezados);
        tabla = new JTable(mt);

        tabla.setSelectionBackground(Color.lightGray);
        tabla.setSelectionForeground(Color.RED);

        TableColumn tc = tabla.getColumn("Identificacion");
        tc.setPreferredWidth(130);

        
        
        DefaultTableCellRenderer tc1 = new DefaultTableCellRenderer();
        tc1.setHorizontalAlignment(SwingConstants.CENTER);
        tabla.getColumnModel().getColumn(0).setCellRenderer(tc1);

        DefaultTableCellRenderer tc2 = new DefaultTableCellRenderer();
        tc2.setHorizontalAlignment(SwingConstants.RIGHT);
        tabla.getColumnModel().getColumn(1).setCellRenderer(tc2);

        JScrollPane js = new JScrollPane(tabla);
        js.setBounds(20, 60, 450, 150);
        
        ArrayList<String[]> si = llenarTabla();
        
        for (String[] strings : si) {
            mt.addRow(strings);
        }

        add(js);
    }

    public void salirboton() {
        BotonModificado salir = new BotonModificado();
        salir.setText("Salir");
        salir.setBounds(380, 220, 100, 30);
        salir.addActionListener((e) -> {
            salir();
        });
        add(salir);
    }

    
    
    public ArrayList <String[]> llenarTabla() {
        FileReader fr = null;
        boolean error = false;
        ArrayList <String[]> datos = new ArrayList<>();
        
        try {
            fr = new FileReader("clientes.csv");
        } catch (Exception e) {
            error = true;
            JOptionPane.showMessageDialog(this,
                    "Error al tratar de abrir el archivo");
        }
        if (!error) {
            BufferedReader br = new BufferedReader(fr);
            String linea = "";
            //DecimalFormat df = new DecimalFormat("###,###");

            mt.removeRow(0);
            
            
            try {
                while ((linea = br.readLine()) != null) {
                    String tokens[] = linea.split(";");
                    datos.add(tokens);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Error al tratar de leer el archivo");
            }
            try {
                fr.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Error al tratar de cerrar el archivo");
            }
        }
        return datos;
                
    }

    public void salir() {
        setVisible(false);
        dispose();
        lista1.setVisible(true);

    }

}
