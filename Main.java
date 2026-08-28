interface Arma {
    String getTipo();
    int getDañoBase();
    int atacar();
}
class Espada implements Arma {
    private int dañoBase;
    public Espada(int dañoBase) {
        this.dañoBase = dañoBase;
    }
    @Override
    public String getTipo() {
        return "Espada";
    }
    @Override
    public int getDañoBase() {
        return dañoBase;
    }
    @Override
    public int atacar() {
        return dañoBase;
    }
}
class Arco implements Arma {
    private int dañoBase;
    public Arco(int dañoBase) {
        this.dañoBase = dañoBase;
    }
    @Override
    public String getTipo() {
        return "Arco";
    }
    @Override
    public int getDañoBase() {
        return dañoBase;
    }
    @Override
    public int atacar() {
        return dañoBase;
    }
}
class Hacha implements Arma {
    private int dañoBase;
    public Hacha(int dañoBase) {
        this.dañoBase = dañoBase;
    }
    @Override
    public String getTipo() {
        return "Hacha";
    }
    @Override
    public int getDañoBase() {
        return dañoBase;
    }
    @Override
    public int atacar() {
        return dañoBase;
    }
}
interface DañoElemental {

    String getElemento();

    int getDaño();
}
class DañoFuego implements DañoElemental {
    private int daño;
    public DañoFuego(int daño) {
        this.daño = daño;
    }
    @Override
    public String getElemento() {
        return "Fuego";
    }
    @Override
    public int getDaño() {
        return daño;
    }
}
class DañoHielo implements DañoElemental {
    private int daño;
    public DañoHielo(int daño) {
        this.daño = daño;
    }
    @Override
    public String getElemento() {
        return "Hielo";
    }
    @Override
    public int getDaño() {
        return daño;
    }
}
class DañoRayo implements DañoElemental {
    private int daño;
    public DañoRayo(int daño) {
        this.daño = daño;
    }
    @Override
    public String getElemento() {
        return "Rayo";
    }
    @Override
    public int getDaño() {
        return daño;
    }
}
class DañoCritico {
    private double multiplicador;
    public DañoCritico(double multiplicador) {
        this.multiplicador = multiplicador;
    }
    public int calcular(int daño, boolean esCritico) {
        if (esCritico) {
            return (int) (daño * multiplicador);
        }
        return daño;
    }
}
abstract class Personaje {
    protected String nombre;
    protected int vida;
    protected Arma arma;
    public Personaje(String nombre, int vida, Arma arma) {
        this.nombre = nombre;
        this.vida = vida;
        this.arma = arma;
    }
    public String getNombre() {
        return nombre;
    }
    public int getVida() {
        return vida;
    }
    public Arma getArma() {
        return arma;
    }
    public void recibirDaño(int daño) {
        vida -= daño;
        if (vida < 0) {
            vida = 0;
        }
    }
}
class Jugador extends Personaje {
    private DañoElemental dañoElemental;
    private DañoCritico dañoCritico;
    public Jugador(
            String nombre,
            int vida,
            Arma arma,
            DañoElemental dañoElemental,
            DañoCritico dañoCritico) {
        super(nombre, vida, arma);
        this.dañoElemental = dañoElemental;
        this.dañoCritico = dañoCritico;
    }
    public int atacar(boolean esCritico) {
        int dañoTotal = arma.atacar();
        if (dañoElemental != null) {
            dañoTotal += dañoElemental.getDaño();
        }
        dañoTotal = dañoCritico.calcular(
                dañoTotal,
                esCritico
        );
        return dañoTotal;
    }
    public void mostrarAtaque(boolean esCritico) {
        System.out.println(
                nombre +
                " ataca con " +
                arma.getTipo()
        );
        System.out.println(
                "Daño base: " +
                arma.getDañoBase()
        );
        if (dañoElemental != null) {
            System.out.println(
                    "Elemento: " +
                    dañoElemental.getElemento()
            );
            System.out.println(
                    "Daño elemental: +" +
                    dañoElemental.getDaño()
            );
        }
        if (esCritico) {
            System.out.println("¡GOLPE CRÍTICO!");
        }
        System.out.println(
                "Daño total: " +
                atacar(esCritico)
        );
    }
}
abstract class Enemigo extends Personaje {
    public Enemigo(
            String nombre,
            int vida,
            Arma arma) {
        super(nombre, vida, arma);
    }
    public abstract String getTipoEnemigo();
}
class Guerrero extends Enemigo {
    public Guerrero(String nombre) {
        super(
                nombre,
                150,
                new Espada(40)
        );
    }
    @Override
    public String getTipoEnemigo() {
        return "Guerrero";
    }
}
class Arquero extends Enemigo {
    public Arquero(String nombre) {
        super(
                nombre,
                100,
                new Arco(35)
        );
    }
    @Override
    public String getTipoEnemigo() {
        return "Arquero";
    }
}
class Mago extends Enemigo {
    public Mago(String nombre) {
        super(
                nombre,
                80,
                new Hacha(30)
        );
    }
    @Override
    public String getTipoEnemigo() {
        return "Mago";
    }
}
public class Main {
    public static void main(String[] args) {
        Arma espada = new Espada(50);
        DañoElemental fuego =
                new DañoFuego(20);
        DañoCritico critico =
                new DañoCritico(2.0);
        Jugador jugador = new Jugador(
                "Jugador1",
                200,
                espada,
                fuego,
                critico
        );
        System.out.println("JUGADOR");
        jugador.mostrarAtaque(true);
        Enemigo guerrero =
                new Guerrero("Guerrero");
        Enemigo arquero =
                new Arquero("Arquero");
        Enemigo mago =
                new Mago("Mago");
        System.out.println();
        System.out.println(" ENEMIGOS");
        System.out.println(
                guerrero.getTipoEnemigo() +
                " utiliza " +
                guerrero.getArma().getTipo() +
                " y causa " +
                guerrero.getArma().getDañoBase() +
                " de daño."
        );
        System.out.println(
                arquero.getTipoEnemigo() +
                " utiliza " +
                arquero.getArma().getTipo() +
                " y causa " +
                arquero.getArma().getDañoBase() +
                " de daño."
        );
        System.out.println(
                mago.getTipoEnemigo() +
                " utiliza " +
                mago.getArma().getTipo() +
                " y causa " +
                mago.getArma().getDañoBase() +
                " de daño."
        );
    }
}