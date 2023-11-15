/*
clase para crear clientes
Autores:
Jhon Edward Steven Loaiza Díaz (230231046)
Angel Julian Cuero Jiménez (230231018)
Juan Diego Rodriguez(230231020)
Juan Pablo Devia (230231003)
Fecha de ult. actualización: 02-11-23
Versión 1.0
 */
package Entidad;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;



public class CrearCliente extends JFrame {

    MenuPrincipal mp;
    //Fecha fecha;
    String nombre;
    int edad;
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

    public CrearCliente(MenuPrincipal obj) throws HeadlessException {
        mp = obj;
        setTitle("Cliente");
        setSize(500, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFocusable(true);
        setLayout(null);
        
        crearGUI();
        setVisible(false);
    }

    public void crearGUI() {
        CrearFechadeNacimiento();
        crearIdentificacion();
        sexo();
        nombre();
        crearTipodeSange();
        boton_crearcliente();
        boton_salir();
        salir();
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
        dia.setEditable(true);
        Labeldia.add(PonerDia);
        Labeldia.add(dia);

        mes = new JComboBox<>(meses);
        Labeldia.add(mes);

        year = new JComboBox<>();

        for (int i = 1900; i <= 2023; i++) {
            year.addItem(String.valueOf(i));
        }
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
        add(tipodeSangre);

    }

    public static void consultar_Cliente() throws IOException {
        
        FileReader fr = null;
        boolean error = false, existe = false;
        try {
            
            fr = new FileReader("clientes.csv");
            
            
        } catch (Exception e) {
            error = true;
            
            JOptionPane.showMessageDialog(null,
                    "Error al leer/abrir el archivo");
        }


            
        
        
        if (!error) {

            BufferedReader br = new BufferedReader(fr);
            String linea = "";

            try {
                while ((linea = br.readLine()) != null) {
                    String tokens[] = linea.split(";");

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Error al tratar de leer el archivo");
            }

        }

        try {
            fr.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error al cerrar el archivo");
        }
    }

    public void crear_Cliente() {
        FileWriter fw = null;
        boolean error = false, primaryKey = false;
        try {
            fw = new FileWriter("clientes.csv", true);
        } catch (IOException e) {
            error = true;
            JOptionPane.showMessageDialog(null, "Error al crear/abrir el archivo");
        }

        if (!error) {
            String nom_tmp = jtNom.getText();
            String fecha = dia.getText() + "-" + mes.getSelectedItem() + "-" + year.getSelectedItem();
            
            String primaryKeyText = jtpk.getText();

            try (BufferedReader br = new BufferedReader(new FileReader("clientes.csv"))) {
                String linea;

                while ((linea = br.readLine()) != null) {
                    // Utiliza split para dividir la línea por el delimitador ";"
                    String[] tokens = linea.split(";");

                    // Comprueba si la clave primaria (primaryKeyText) ya existe
                    if (tokens.length > 0 && tokens[0].equals(primaryKeyText)) {
                        primaryKey = true;
                        break;
                    }
                }

                if (primaryKey) {
                    JOptionPane.showMessageDialog(null, "Ya existe la clave primaria (PK)");
                } else {
                    // Si no hay clave primaria duplicada, escribe los datos en el archivo
                    //this.fecha = new Fecha(dia.getText(),(String) mes.getSelectedItem(),(String) year.getSelectedItem());
                    
                    fw.write(jtpk.getText() + ";" + nom_tmp + ";" + dia.getText()+"/"+(String) mes.getSelectedItem()+"/"+(String) year.getSelectedItem()+ ";" + Utilerias.calcularEdad(fecha)
                            + ";" + sexo.getSelectedItem() + ";" + tipodeSangre.getSelectedItem() + "\r\n");
                    JOptionPane.showMessageDialog(null, "Se guardó el cliente con éxito.");
                }

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al guardar en el archivo");
            } finally {
                try {
                    if (fw != null) {
                        fw.close();
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar el archivo");
                }
            }
        }
    }

    JButton[] botones = new JButton[2];

    public void boton_crearcliente() {
        botones[0] = new JButton();
        botones[0].setText("Crear Cliente");
        botones[0].addActionListener((e) -> {
            crear_Cliente();
        });
        botones[0].setBounds(10, 300, 120, 50);
        add(botones[0]);
    }

    public void boton_salir() {
        botones[1] = new JButton();
        botones[1].setText("Salir");
        botones[1].addActionListener((e) -> {
            salir2();
        });
        botones[1].setBounds(150, 300, 120, 50);
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
        add(jtNom);
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
                botones[0].requestFocus();
                break;
            default:
                JOptionPane.showMessageDialog(null, "error digite otra opcion");
                break;
        }
    }

}
