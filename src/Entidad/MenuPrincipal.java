/*
El menu principal
Autores:
Jhon Edward Steven Loaiza Díaz (230231046)
Angel Julian Cuero Jiménez (230231018)
Juan Diego Rodriguez(230231020)
Juan Pablo Devia (230231003)
Fecha de ult. actualización: 02-11-23
Versión 1.0
 */
package Entidad;

import Graficas.Menu_listado_graficas;
import Listados.Menu_listado;
import Listados.ListadoClientes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class MenuPrincipal extends JFrame{
    EnviarCorreo enviar;
    BotonModificado[] botones = new BotonModificado[7];
    CrearCliente c1 = new CrearCliente(this);
    ConsultarCliente consultar = new ConsultarCliente(this);
    Menu_listado lista1 = new Menu_listado(this);
    Menu_listado_graficas grafica1 = new Menu_listado_graficas(this);
    ListadoClientes l1 = new ListadoClientes(lista1);
    Acercade ac = new Acercade(this);
    public MenuPrincipal(){

        
        setTitle("Cliente");
        setSize(720, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());  
        getContentPane().setBackground(new Color(52, 73,94));
        crearEncabezado();
        repaint();
        botones();
        inicializar_botones(); 
        setVisible(true);
    }
    
    public void crearEncabezado() {
    // Crear el JLabel
    BordeLabel encabezado = new BordeLabel(30);
    encabezado.setSize(100, HEIGHT);
    // Configurar el encabezado
    encabezado.setText("Persona Natural");
    encabezado.setHorizontalAlignment(JLabel.CENTER); // Centrar el texto horizontalmente
    encabezado.setVerticalAlignment(JLabel.TOP); // Alinear el texto en la parte superior verticalmente
    encabezado.setFont(new Font("Arial", Font.BOLD, 24)); // Establecer la fuente y el tamaño del texto

    // Añadir el encabezado al JFrame
    add(encabezado, BorderLayout.NORTH); // Añadir el encabezado en la parte superior del JFrame
}

    
        public void botones() {
        RoundedPanel JPbotones = new RoundedPanel(30);
        JPbotones.setLayout(new GridLayout(4,2));
        JPbotones.setSize(800, 400);
        JPbotones.setLocation(150, 150);
        JPbotones.setBackground(new Color (169,204,227));
        
        //JPbotones.setLayout(null);
        add(JPbotones);

        int x = 150;
        int y = 100;
        for (int i = 0; i < botones.length; i++, x+=200  ) {
             if(i==4 ){y+=300;
                     x-=800;}
            botones[i] = new BotonModificado();
            JPbotones.add(botones[i]);       
        }

    }

     public void evento_crearCliente(){
         c1.setVisible(true);
         dispose();       
     }
     
    public void evento_consultarCliente(){
         consultar.setVisible(true);
         dispose();       
     }
        
    public void inicializar_botones() {

        botones[0].setText("Crear Cliente");
        botones[0].addActionListener((e) -> {
            evento_crearCliente();
        });

        botones[1].setText("Consultar Cliente");
        botones[1].addActionListener((e) -> {
          evento_consultarCliente();
        });
        
        botones[2].setText("listado Cliente");
        botones[2].addActionListener((e) -> {
          evento_listarCliente();
        });
        
        botones[3].setText("Graficas Clientes");
        botones[3].addActionListener((e) -> {
          evento_graficarCliente();
        });
        
        botones[4].setText("Enviar correo");
        botones[4].addActionListener((e) -> {
          evento_enviarcorreo();
        });


        botones[5].setText("Acerca de: ");
        botones[5].addActionListener((e) -> {
             acercade(ac);
        });
        
        botones[6].setText("Salir ");
        botones[6].addActionListener((e) -> {
             salir();
        });
    }
    public void evento_enviarcorreo(){
        int resp;
        resp = JOptionPane.showConfirmDialog
        (null, "Desea enviar correo?", "Correo", JOptionPane.YES_NO_OPTION);
        if(resp == JOptionPane.YES_OPTION){
            enviar = new EnviarCorreo(); 
        }else{
            JOptionPane.showMessageDialog(null, "Envio cancelado");
        }
    }
    
    public void evento_graficarCliente(){
        grafica1.setVisible(true);
        dispose();
    }
    
    public void evento_listarCliente(){
        lista1.setVisible(true);
        dispose();
    }
    
    public void acercade(Acercade ac) {
        ac.setVisible(true);
    }
    
    public void salir(){
        System.exit(0);
    }
    
    public static void main(String[] args) {
        MenuPrincipal m1 = new MenuPrincipal();
    }
    

    
}
    
    
            


