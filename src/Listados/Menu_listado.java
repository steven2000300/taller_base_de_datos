/*
El menu de todas las listas para consultar
Autores:
Jhon Edward Steven Loaiza Díaz (230231046)
Angel Julian Cuero Jiménez (230231018)
Juan Diego Rodriguez(230231020)
Juan Pablo Devia (230231003)
Fecha de ult. actualización: 02-11-23
Versión 1.0
 */
package Listados;

import Entidad.BordeLabel;
import Entidad.BotonModificado;
import Entidad.MenuPrincipal;
import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Menu_listado extends JFrame {

    MenuPrincipal mp;

    ListadoClientes lista1 = new ListadoClientes(this);
    ListadoClientes lista2 = new ListadoClientes(this);
    ListadoClientes lista3 = new ListadoClientes(this);

    public Menu_listado(MenuPrincipal obj) throws HeadlessException {
        mp = obj;
        setTitle("Cliente");
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFocusable(true);
        setLayout(null);
        
        getContentPane().setBackground(new Color(40,116,166));
        crearGUI();
        setVisible(false);
    }

    public void crearGUI() {
        boton_listado1();
        boton_listado2();
        boton_listado3();
        boton_listado4();
        eliminarcol();
        eliminarcol2();
        label();
    }

    BotonModificado[] botones = new BotonModificado[4];

    public void boton_listado1() {
        botones[0] = new BotonModificado();
        botones[0].setBorderColor(new Color(30, 40, 70));

        botones[0].setText("Listado 1");
        botones[0].addActionListener((e) -> {
            crear_listado1();
        });
        botones[0].setBounds(100, 200, 120, 50);
        add(botones[0]);
        
    }

    public void crear_listado1() {
        lista1.setVisible(true);
        dispose();
    }

    public void crear_listado2() {
        lista2.setVisible(true);
        dispose();
    }

    public void crear_listado3() {
        lista3.setVisible(true);
        dispose();
    }

    public void boton_listado2() {
        botones[1] = new BotonModificado();
        botones[1].setBorderColor(new Color(30, 40, 70));
        botones[1].setText("listado 2");
        botones[1].addActionListener((e) -> {
            crear_listado2();

        });
        botones[1].setBounds(250, 200, 120, 50);
        add(botones[1]);
    }

    public void boton_listado3() {
        botones[2] = new BotonModificado();
        botones[2].setBorderColor(new Color(30, 40, 70));

        botones[2].setText("listado 3");
        botones[2].addActionListener((e) -> {
            crear_listado3();
        });
        botones[2].setBounds(100, 300, 120, 50);
        add(botones[2]);
    }

    public void boton_listado4() {
        botones[3] = new BotonModificado();
        botones[3].setBorderColor(new Color(30, 40, 70));

        botones[3].setText("Salir");
        botones[3].addActionListener((e) -> {
            salir2();
        });
        botones[3].setBounds(250, 300, 120, 50);
        add(botones[3]);
    }

    public void eliminarcol() {
        lista2.tabla.removeColumn(lista2.tabla.getColumn("Fecha de nacimiento"));
        lista2.tabla.removeColumn(lista2.tabla.getColumn("genero"));
    }

    public void eliminarcol2() {
        lista3.tabla.removeColumn(lista3.tabla.getColumn("Fecha de nacimiento"));
        lista3.tabla.removeColumn(lista3.tabla.getColumn("genero"));
        lista3.tabla.removeColumn(lista3.tabla.getColumn("Tipo de RH"));

    }
    
    public void label (){
        BordeLabel JLtitulo = new BordeLabel(30);
        JLtitulo.setBounds(165, 20, 150, 50);
        JLtitulo.setText("Listados de Clientes");
        JLtitulo.setHorizontalAlignment(JLabel.CENTER);
        JLtitulo.setBorderColor(Color.yellow);
        add(JLtitulo);
    }

    public void salir2() {
        setVisible(false);
        dispose();
        mp.setVisible(true);

    }

}
