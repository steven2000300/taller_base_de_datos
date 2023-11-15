/*
gragica 1 barchart
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

public class Graficas extends JFrame {

    Menu_listado_graficas mlg;
    JFreeChart chart; // objeto para crear la gráfica
    int contadores[] = new int[2];

    public Graficas(Menu_listado_graficas obj) {
        // construimos el JFrame
        super("Demo de BarChart");
        mlg = obj;
        setSize(800, 600);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        crearGrafico(); // cargar los datos y crear la gráfica

        ChartPanel panel = new ChartPanel(chart, false);
        panel.setBounds(10, 20, 760, 520); // ubicamos el panel de la gráfica
        add(panel);

        setVisible(false);
    }

    private void crearGrafico() {
        // crear un conjunto de datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        leerArchivo();
        dataset.setValue(contadores[0], "Clientes", "Femenino");
        dataset.setValue(contadores[1], "Clientes", "Masculino");

        // crear el gráfico de barra
        chart = ChartFactory.createBarChart(
                "Cantidad de clientes masculinos y femeninos", // Nombre de la gráfica
                "CANTIDAD DE PERSONAS", // Nombre del eje Horizontal
                "GENERO", // Nombre del eje vertical
                dataset, // Data
                PlotOrientation.VERTICAL, // Orientación HORIZONTAL o VERTICAL
                true, // Incluir leyenda
                true, // Información al pasar el mouse
                true); // URIs
        agregarBotonVolver();
    }

    public void agregarBotonVolver() {
        JPanel buttonPanel = new JPanel();
        BotonModificado botonVolver = new BotonModificado();
        botonVolver.setText("Volver");
        botonVolver.setPreferredSize(new Dimension(200, 50));
        botonVolver.addActionListener((e) -> {
            salir();
        });

        buttonPanel.add(botonVolver);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void salir() {
        setVisible(false);
        dispose();
        mlg.setVisible(true);
    }

    public void leerArchivo() {
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
                    switch (tokens[4]) {
                        case "F" -> contadores[0]++;
                        case "M" -> contadores[1]++;
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
