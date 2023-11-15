/*
grafica 3 barchart3d
Autores:
Jhon Edward Steven Loaiza Díaz (230231046)
Angel Julian Cuero Jiménez (230231018)
Juan Diego Rodriguez(230231020)
Juan Pablo Devia (230231003)
Fecha de ult. actualización: 02-11-23
Versión 1.0
 */
package Graficas;

import Entidad.BotonModificado;
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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Graficas3 extends JFrame {
    
    Menu_listado_graficas mlg;
    JFreeChart chart;
    int contadores[] = new int[2]; // Dos categorías: Menores de Edad y Mayores de Edad
    
    public Graficas3(Menu_listado_graficas obj) {
        super("Gráfico de Edades de Clientes");
        mlg = obj;
        setSize(800, 600);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        crearGrafico();
        
        ChartPanel panel = new ChartPanel(chart, false);
        panel.setBounds(10, 20, 760, 520);
        add(panel);

        setVisible(false);
    }
    
    private void crearGrafico() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        leerFile();
        
        dataset.addValue(contadores[0], "Menores de Edad", "Menores de Edad");
        dataset.addValue(contadores[1], "Mayores de Edad", "Mayores de Edad");
        
        chart = ChartFactory.createBarChart3D(
                "Distribución de Edades de Clientes",
                "Edades",
                "Cantidad de Clientes",
                dataset,
                PlotOrientation.HORIZONTAL,
                true,
                true,
                false);
        boton_volver();
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
        mlg.setVisible(true);
    }
    
    public void leerFile() {
        FileReader fr = null;
        boolean error = false;
        try {
            fr = new FileReader("clientes.csv");
        } catch (Exception e) {
            error = true;
            JOptionPane.showMessageDialog(null, "Error al abrir el archivo");
        }
        if (!error) {
            BufferedReader br = new BufferedReader(fr);
            String linea = "", tokens[];
            Arrays.fill(contadores, 0);
            try {
                while ((linea = br.readLine()) != null) {
                    tokens = linea.split(";");
                    int edad = Integer.parseInt(tokens[3]); // Suponiendo que la edad está en el token 3
                    if (edad < 18) {
                        contadores[0]++; // Menores de Edad
                    } else {
                        contadores[1]++; // Mayores de Edad
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al leer el archivo");
            }
            try {
                fr.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar el archivo");
            }
        }
    }
}
