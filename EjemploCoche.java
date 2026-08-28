public class EjemploCoche {
    public static void main(String[] args) {
        Coche cocheDeportivo = new Coche("Ferrari", "F8", 2020, 250000);
        Coche cocheTodoTerreno = new Coche("Toyota", "Hilux", 2008, 120000);
        cocheDeportivo.encender();
        cocheDeportivo.acelerar();
        cocheDeportivo.frenar();
        cocheDeportivo.apagar();
        cocheTodoTerreno.encender();
        cocheTodoTerreno.acelerar();
        cocheTodoTerreno.frenar();
        cocheTodoTerreno.apagar();
    }
}