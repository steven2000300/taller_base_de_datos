/*
clase para consultar clientes
Autores:
Jhon Edward Steven Loaiza Díaz (230231046)
Angel Julian Cuero Jiménez (230231018)
Juan Diego Rodriguez(230231020)
Juan Pablo Devia (230231003)
Fecha de ult. actualización: 02-11-23
Versión 1.0
 */

package Entidad;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ConsultarCliente extends JFrame {

    MenuPrincipal mp ;

    String nombre;    
    char genero;
    int identificacion;
    JComboBox<String> tipodeSangre;
    JComboBox<String> sexo = new JComboBox<String>();

    JLabel Labeldia;
    JTextField dia;
    JComboBox<String> mes;
    JComboBox<String> year;
    String[] meses = {
        "01", "02", "03", "04", "05", "06",
        "07", "08", "09", "10", "11", "12"
    };

    public ConsultarCliente(MenuPrincipal obj) throws HeadlessException {
        
        mp = obj;
        setTitle("Cliente");
        setSize(500, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        crearGUI();
        setVisible(false);
    }

    public void crearGUI() {
        salir();        
        setFocusable(true);
        
        CrearFechadeNacimiento();
        crearIdentificacion();
        sexo();
        nombre();
        crearTipodeSange();
        boton_consultarcliente();
        botonsalir();
    }

    JTextField jtpk;

    public void crearIdentificacion() {
        JLabel PK = new JLabel("Identificación:");
        PK.setBounds(50, 100, 120, 30);
        add(PK);
        jtpk = new JTextField();
        jtpk.setBounds(140, 100, 200, 30);
        
        add(jtpk);
    }

    public void CrearFechadeNacimiento() {
        Labeldia = new JLabel();
        Labeldia.setBounds(50, 150, 350, 30);
        Labeldia.setLayout(new GridLayout(1, 2));
        JLabel PonerDia = new JLabel("Dia");
        dia = new JTextField();
        dia.setEditable(false);
        dia.setFocusable(false);
        Labeldia.add(PonerDia);
        Labeldia.add(dia);

        mes = new JComboBox<>(meses);
        mes.setEnabled(false);

        Labeldia.add(mes);

        year = new JComboBox<>();

        for (int i = 1900; i <= 2023; i++) {
            year.addItem(String.valueOf(i));
        }
        year.setEnabled(false);
        System.out.println(year.getItemAt(123));

        Labeldia.add(year);
        add(Labeldia);
        
    }

    public void crearTipodeSange() {
        JLabel tipodeSangre = new JLabel();
        JLabel Labeltxtsexo = new JLabel("Tipo RH :");
        tipodeSangre.setLayout(new GridLayout(1, 2));
        tipodeSangre.setBounds(50, 250, 350 / 2, 30);
        tipodeSangre.add(Labeltxtsexo);

        String[] RH = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};

        this.tipodeSangre = new JComboBox<>(RH);
        tipodeSangre.add(this.tipodeSangre);
        this.tipodeSangre.setEnabled(false);
        add(tipodeSangre);

    }

    public void consultar_Cliente() {
        FileReader fr = null;
        boolean error = false, existe = false;
        try {
            fr = new FileReader("clientes.csv");
        } catch (Exception e) {
            error = true;
            JOptionPane.showMessageDialog(null, 
                "Error al abrir el archivo clientes.csv");
        }
        if (!error) {
            BufferedReader br = new BufferedReader(fr);
            String linea = "", tokens[]; 

            
            String primaryKeyText = jtpk.getText();
            try {
                while ((linea=br.readLine()) != null) {                    
                    //System.out.println(linea);
                    tokens = linea.split(";");
                    //System.out.println(tokens[0] + " " + tokens[1]);
                                                            
                    if (tokens[0].equals(primaryKeyText)) {
                        existe = true; 
                        String registro = "";
                        
                        System.out.println(tokens[0]);
                        jtNom.setText(tokens[1]);
                        
                        String[] partes = tokens[2].split("/");

                          System.out.println(tokens[2]);
                        dia.setText(partes[0]);
                        mes.setSelectedItem(partes[1]);
                        year.setSelectedItem(partes[2]);
                        sexo.setSelectedItem(tokens[4]);
                        tipodeSangre.setSelectedItem(tokens[5]);
                            for (String aux: tokens) {
                            System.out.println(aux);
                        }

                        //for (String campo : tokens) {
                            //System.out.print(campo + " ");
                          //  registro+=campo + " ";
                        //}
                        //System.out.println("");
                        //JOptionPane.showMessageDialog(null, registro);
                    }
                    
                }// fin while
                if (!existe) {
                    JOptionPane.showMessageDialog(null, 
                        "El cliente con codigo "  + 
                        " no existe en el archivo clientes.csv");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                   "Error al leer el archivo clientes.csv");
            }
            try {
                fr.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                   "Error al cerrar el archivo clientes.csv");
            }
        }//fin while
    }

    JButton[] botones = new JButton[2];

    public void boton_consultarcliente() {
        botones[1] = new JButton();
        botones[1].setText("consultar Cliente");
        botones[1].addActionListener((e) -> {
           consultar_Cliente();
        });
        botones[1].setBounds(10, 300, 150, 50);
        add(botones[1]);
    }

        public void botonsalir() {
        botones[1] = new JButton();
        botones[1].setText("salir");
        botones[1].addActionListener((e) -> {
           salir2();
        });
        botones[1].setBounds(170, 300, 150, 50);
        add(botones[1]);
    }
    
    public void sexo() {
        JLabel LabelSexo = new JLabel();
        JLabel Labeltxtsexo = new JLabel("Sexo :");
        LabelSexo.setLayout(new GridLayout(1, 2));
        LabelSexo.setBounds(50, 200, 350 / 2, 30);
        LabelSexo.add(Labeltxtsexo);

        sexo.addItem("Seleccione una opcion valida");
        sexo.addItem("F");
        sexo.addItem("M");
        sexo.setBounds(200, 200, 200, 25);
        sexo.setEnabled(false);
        
        LabelSexo.add(sexo);
        add(LabelSexo);

    }

    JTextField jtNom;

    public void nombre() {
        JLabel jlNom = new JLabel("Nombres:");
        jlNom.setBounds(50, 50, 120, 30);
        add(jlNom);
        jtNom = new JTextField();
        jtNom.setBounds(140, 50, 200, 30);
        jtNom.setEditable(false);
        jtNom.setFocusable(false);
        add(jtNom);
    }
        public void salir2() {
            int salir = JOptionPane.showConfirmDialog(null, "Desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
        switch (salir) {
            case JOptionPane.YES_OPTION:
                setVisible(false);
                dispose();
                mp.setVisible(true);
                break;
            case JOptionPane.NO_OPTION:
                System.out.println("ok");
                break;
            default:
                JOptionPane.showMessageDialog(null, "error digite otra opcion");
                break;
            }
        }
        
    public void salir() {
    addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea regresar al menu principal?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    setVisible(false);  // Ocultar la ventana actual
                    dispose();         // Destruir la ventana
                    mp.setVisible(true); // Mostrar la ventana 'mp'
                    
                }
            }
        }
    });
    JLabel piedepag = new JLabel("<html><bold>Para ir atrás, presione ESC<br>Version: 1.0.0</bold></html>");
    piedepag.setBounds(330, 325, 300, 40);
    add(piedepag);
}


}
