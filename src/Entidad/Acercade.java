package Entidad;
/*
clase para conocer los autores del programa
Autores:
Jhon Edward Steven Loaiza Díaz (230231046)
Angel Julian Cuero Jiménez (230231018)
Juan Diego Rodriguez(230231020)
Juan Pablo Devia (230231003)
Fecha de ult. actualización: 02-11-23
Versión 1.0
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.print.DocFlavor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class Acercade extends JDialog {

    JLabel[] JLfoto  = new JLabel[4];
    JLabel[] codigos = new JLabel[4];
    JLabel[] nombres = new JLabel[4];
    MenuPrincipal mp;
    public Acercade(MenuPrincipal obj) {
        mp = obj;
        initGraphics();
    }

    public void fotos() {
        int x = 0;
        for (int i = 0; i < 4; i++, x += 180) {
            
            
            
            JLfoto[i] = new JLabel(imagen("src/Imagenes/imagen" + i +".jpg"));           
            JLfoto[i].setBounds(40 + x, 100, 150, 200);
            JLfoto[i].setBackground(Color.white);
            JLfoto[i].setOpaque(true);
            add(JLfoto[i]);

            String [] name = new String[]{"Steven Loaiza Diaz","Juan Diego Rodriguez","Juan Pablo Debia","Angel Julian Cuero"};
            nombres[i] = new JLabel(name[i]);
            
            String[] cod = new String[]{"230231046","230231020","230231003","230231018"};
            nombres[i].setBounds(40 + x, 310, 150, 30);
            nombres[i].setBackground(Color.gray);
            nombres[i].setOpaque(true);
            add(nombres[i]);

            codigos[i] = new JLabel(cod[i]);
            codigos[i].setBounds(40 + x, 350, 150, 30);
            codigos[i].setBackground(Color.gray);
            codigos[i].setOpaque(true);
            add(codigos[i]);
        }
            
    }

    public void initGraphics() {
        setTitle("Acerca de:");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        fotos();
        acerca_dePANEL();
        boton_volver();
        setModal(true);
        setVisible(false);

    }
    
        public void boton_volver() {
        JPanel buttonPanel = new JPanel();
        BotonModificado boton_volver = new BotonModificado();
        boton_volver.setText("Volver");
        boton_volver.setPreferredSize(new Dimension(200,50));
        boton_volver.addActionListener((e) -> {
            salir2();
        });
        
        buttonPanel.add(boton_volver);
        add(buttonPanel,BorderLayout.SOUTH);
    }
    
    public void salir2() {
        setVisible(false);
        dispose();
        mp.setVisible(true);
    }
    
    public Icon imagen(String source){
        ImageIcon imagen = new ImageIcon(source);
        float scale = (float)  200/imagen.getIconHeight();
        Icon icon = new ImageIcon(
                imagen.getImage().getScaledInstance((int) (imagen.getIconWidth()*scale), (int) (imagen.getIconHeight()*scale), Image.SCALE_DEFAULT));
        return icon;
    }

    public void acerca_dePANEL() {
        JPanel panel = new JPanel();
        JLabel label1 = new JLabel("Texto de Acerca de...");
        panel.add(label1);
        add(panel);
    }

}
