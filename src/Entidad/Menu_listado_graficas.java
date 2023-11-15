package Entidad;

import Listados.*;
import Entidad.BordeLabel;
import Entidad.BotonModificado;
import Entidad.MenuPrincipal;
import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Menu_listado_graficas extends JFrame {

    MenuPrincipal mp;
    Graficas grafica1 = new Graficas(this);
    Graficas2 grafica2 = new Graficas2(this);
    Graficas3 grafica3 = new Graficas3(this);
    public Menu_listado_graficas(MenuPrincipal obj) throws HeadlessException {
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
        grafica1.setVisible(true);
        dispose();
    }

    public void crear_listado2() {
        grafica2.setVisible(true);
        dispose();
    }

    public void crear_listado3() {
        grafica3.setVisible(true);
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
