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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Graficas extends JFrame {

    Menu_listado_graficas mlg;
    JFreeChart chart;//objeto para crear la grafica
    int contadores[] = new int[4];

    public Graficas(Menu_listado_graficas obj) {
        //construimos el JFrame
        super("Demo de BarChart");
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
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        leerFile();
        dataset.setValue(contadores[0], "Planes academicos", "F");
        dataset.setValue(contadores[1], "Planes academicos", "M");

        //crear el grafico de barra
        chart = ChartFactory.createBarChart(
                "Cantidad de clientes masculinos y femeninos", //Nombre de la grafica
                "CANTIDAD DE PERSONAS", //Nombre del eje Horizontal
                "GENERO", //Nombre del eje vertical
                dataset, //Data
                PlotOrientation.VERTICAL, //Orientacion HORIZONTAL o VERTICAL
                true, //Incluir leyenda
                true, //Informacion al pasar el mouse
                true);                      //URls
        boton_volver();
    }

    public void boton_volver() {
        JPanel buttonPanel = new JPanel();
        BotonModificado boton_volver = new BotonModificado();
        boton_volver.setText("Volver");
        boton_volver.setPreferredSize(new Dimension(200, 50));
        boton_volver.addActionListener((e) -> {
            salir2();
        });

        buttonPanel.add(boton_volver);
        add(buttonPanel, BorderLayout.SOUTH);
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
            JOptionPane.showMessageDialog(null,
                    "Error al abrir el archivo");
        }
        if (!error) {
            BufferedReader br = new BufferedReader(fr);
            String linea = "", tokens[];
            Arrays.fill(contadores, 0);
            try {
                while ((linea = br.readLine()) != null) {
                    tokens = linea.split(";");
                    switch (tokens[4]) {
                        case "F" ->
                            contadores[0]++;
                        case "M" ->
                            contadores[1]++;
                        //case "INGENIERIA DE SISTEMAS" -> contadores[2]++;
                        //case "INGENIERIA INDUSTRIAL" -> contadores[3]++;
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
