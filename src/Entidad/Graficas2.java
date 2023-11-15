package Entidad;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class Graficas2  extends JFrame {
    
    Menu_listado_graficas mlg;
    JFreeChart chart;//objeto para crear la grafica
    int contadores[] = new int[8];
    
    public Graficas2(Menu_listado_graficas obj){
        //construimos el JFrame
        super("Graficos de tipos de RH");
        mlg = obj;
        setSize(800, 600);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        crearGrafico();//cargar los datos y crear la grafica

        ChartPanel panel = new ChartPanel(chart, false);
        panel.setBounds(10, 20, 760, 520);//ubicamos el panel de la grafica
        add(panel);

        setVisible(false);
    }
    
    private void crearGrafico() {
        //crear un conjunto de datos
        DefaultPieDataset dataset = new DefaultPieDataset();
        leerFile();
        dataset.setValue("O+", contadores[0]);
        dataset.setValue("O-", contadores[1]);
        dataset.setValue("A+", contadores[2]);
        dataset.setValue("A-", contadores[3]);
        dataset.setValue("B+", contadores[4]);
        dataset.setValue("B-", contadores[5]);
        dataset.setValue("AB+",contadores[6]);
        dataset.setValue("AB-",contadores[7]);        
        //crear el grafico de pastel
        chart = ChartFactory.createPieChart3D(
                "Tipos de sangre Clientes", //Nombre de la grafica
                dataset, //Data
                true, //Incluir leyenda
                true, //Informacion al pasar el mouse
                false);                      //URls
        boton_volver();
    }
    
    public void boton_volver(){
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
        mlg.setVisible(true);
    }
    
    public void leerFile(){
        FileReader fr = null;
        boolean error = false;
        try {
            fr = new FileReader("clientes.csv");
        } catch (Exception e) {
            error = true;
            JOptionPane.showMessageDialog(null, 
                "Error al abrir el archivo");
        }
        if (!error) {
            BufferedReader br = new BufferedReader(fr);
            String linea = "", tokens[];
            Arrays.fill(contadores, 0);
            try {
                while ((linea=br.readLine()) != null) {                    
                    tokens = linea.split(";");
                    switch (tokens[5]) {
                        case "O+" -> contadores[0]++;
                        case "O-" -> contadores[1]++;
                        case "A+" -> contadores[2]++;
                        case "A-" -> contadores[3]++;
                        case "B+" -> contadores[4]++;
                        case "B-" -> contadores[5]++;
                        case "AB+" -> contadores[6]++;
                        case "AB-" -> contadores[7]++;
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                    "Error al leer el archivo");
            }
            try {
                fr.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                    "Error al cerrar el archivo");
            }
        }
    }
    
}
