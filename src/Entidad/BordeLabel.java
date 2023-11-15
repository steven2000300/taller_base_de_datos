/*
plantilla
Autores:
Jhon Edward Steven Loaiza Díaz (230231046)
Angel Julian Cuero Jiménez (230231018)
Juan Diego Rodriguez(230231020)
Juan Pablo Devia (230231003)
Fecha de ult. actualización: 02-11-23
Versión 1.0
 */

package Entidad;


import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BordeLabel extends JLabel
    {
        private Color borderColor;
        private int cornerRadius = 15;
        
        
         public BordeLabel(int radius) {
            cornerRadius = radius;
            borderColor = getBackground();
            setOpaque(false);
            
        }
        public BordeLabel(int radius, Color bgColor) {
            super();
            cornerRadius = radius;
            borderColor = bgColor;
            setOpaque(false);
        }
        
        
        @Override
        public void paint(Graphics g) {

            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setStroke(new BasicStroke(5));
            graphics.setColor(borderColor);
    
            graphics.drawRoundRect(2, 2, width-4, height-4, cornerRadius, cornerRadius); //paint background
            graphics.setColor(getBackground());
            graphics.fillRoundRect(2, 2, width-4, height-4, cornerRadius, cornerRadius); //paint background
            super.paint(g);
        }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }
        
}
        
    