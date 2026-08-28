public class Coche {
    private String marca;
    private String modelo;
    private int añoFabricacion;
    private double precio;
    public Coche(String marca, String modelo, int añoFabricacion, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.añoFabricacion = añoFabricacion;
        this.precio = precio;
    }
    public Coche() {
        this.marca = "";
        this.modelo = "";
        this.añoFabricacion = 0;
        this.precio = 0.0;
    }
    public boolean aplicarDescuento(double descuento) {
        if (añoFabricacion < 2010) {
            precio = precio - (precio * descuento / 100);
            return true;
        }
        return false;
    }
    public void encender() {
        System.out.println(marca + " " + modelo + " está encendido.");
    }
    public void acelerar() {
        System.out.println(marca + " " + modelo + " está acelerando.");
    }
    public void frenar() {
        System.out.println(marca + " " + modelo + " está frenando.");
    }
    public void apagar() {
        System.out.println(marca + " " + modelo + " está apagado.");
    }
    public String getMarca() {
        return marca;
    }
    public String getModelo() {
        return modelo;
    }
    public int getAñoFabricacion() {
        return añoFabricacion;
    }
    public double getPrecio() {
        return precio;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public void setAñoFabricacion(int añoFabricacion) {
        this.añoFabricacion = añoFabricacion;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
