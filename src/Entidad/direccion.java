package Entidad;

import javax.swing.JOptionPane;

class direccion {

    String calle;
    String ciudad;
    String codigoPostal;

    public direccion() {
        calle = "";
        ciudad = "";
        codigoPostal = "";
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle() {
        this.calle = JOptionPane.showInputDialog("Digite la dirrecion de la vivienda");
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad() {
        this.ciudad = JOptionPane.showInputDialog("Digite la ciudad de residencia");
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal() {
        this.codigoPostal = JOptionPane.showInputDialog("Digite codigo postal");
    }

    @Override
    public String toString() {
        return "La direccion de la vivienda es"
                + ":{" + "calle=" + calle + ", ciudad=" + ciudad + ", codigoPostal=" + codigoPostal + '}';
    }

}
