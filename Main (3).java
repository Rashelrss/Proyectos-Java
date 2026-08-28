import java.util.ArrayList;
import java.util.List;
abstract class Persona {
    public static final String UNIVERSIDAD = "UCSM"; 
    private static int totalPersonas = 0; 
    protected int id;
    protected String nombre;
    protected String email;
    public Persona(int id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        totalPersonas++;
    }
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public static int getTotalPersonas() {
        return totalPersonas;
    }
    public abstract void presentarse();
}
class Categoria {
    private String nombre;
    public Categoria(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public String toString() {
        return nombre;
    }
}
class Silabo {
    private List<String> temas = new ArrayList<>();
    public void agregarTema(String tema) {
        temas.add(tema);
    }
    public void mostrar() {
        if (temas.size() == 0) {
            System.out.println("   no hay temas todavia");
        }
        for (String t : temas) {
            System.out.println("   - " + t);
        }
    }
}
class Profesor extends Persona {
    private String especialidad;
    private List<Curso> misCursos = new ArrayList<>(); 
    public Profesor(int id, String nombre, String email, String especialidad) {
        super(id, nombre, email);
        this.especialidad = especialidad;
    }
    public String getEspecialidad() {
        return especialidad;
    }
    public void asignarCurso(Curso c) {
        misCursos.add(c);
    }
    @Override
    public void presentarse() {
        System.out.println("Soy el profesor " + nombre + ", enseño " + especialidad
                + " y tengo " + misCursos.size() + " curso(s) a cargo.");
    }
}
class Estudiante extends Persona {
    private String codigo;
    private List<Curso> misCursos = new ArrayList<>();
    public Estudiante(int id, String nombre, String email, String codigo) {
        super(id, nombre, email);
        this.codigo = codigo;
    }
    public String getCodigo() {
        return codigo;
    }
    public void inscribirse(Curso curso) {
        boolean ok = curso.matricular(this);
        if (ok) {
            misCursos.add(curso);
            System.out.println(nombre + " se inscribio en " + curso.getNombre());
        } else {
            System.out.println(nombre + " no pudo inscribirse en " + curso.getNombre() + ", no hay cupo");
        }
    }
    @Override
    public void presentarse() {
        System.out.println("Hola, soy " + nombre + " (" + codigo + "), llevo "
                + misCursos.size() + " curso(s).");
    }
}
class Curso {
    public static final int CUPO_DEFAULT = 30; 
    private static int totalCursos = 0;
    private String codigo;
    private String nombre;
    private Categoria categoria;
    private int cupoMax;
    private Profesor profesor; 
    private List<Estudiante> alumnos = new ArrayList<>(); 
    private Silabo silabo; 
    public Curso(String codigo, String nombre, Categoria categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.cupoMax = CUPO_DEFAULT;
        this.silabo = new Silabo();
        totalCursos++;
    }
    public String getNombre() {
        return nombre;
    }
    public void setCupoMax(int cupo) {
        this.cupoMax = cupo;
    }
    public void asignarProfesor(Profesor p) {
        this.profesor = p;
        p.asignarCurso(this);
    }
    public Silabo getSilabo() {
        return silabo;
    }
    public static int getTotalCursos() {
        return totalCursos;
    }
    private boolean hayCupo() {
        return alumnos.size() < cupoMax;
    }
    protected void avisoInterno() {
        System.out.println("revisando cupos del curso " + nombre);
    }
    boolean matricular(Estudiante e) {
        if (hayCupo()) {
            alumnos.add(e);
            return true;
        }
        return false;
    }
    public boolean tieneCupo() {
        return hayCupo();
    }
    public void mostrarInfo() {
        System.out.println(codigo + " - " + nombre + " (" + categoria + ")");
        System.out.println("   profesor: " + (profesor == null ? "no asignado" : profesor.getNombre()));
        System.out.println("   matriculados: " + alumnos.size() + "/" + cupoMax);
        System.out.println("   silabo:");
        silabo.mostrar();
    }
}
class SistemaGestion {
    private List<Estudiante> estudiantes = new ArrayList<>();
    private List<Profesor> profesores = new ArrayList<>();
    private List<Curso> cursos = new ArrayList<>();
    public void agregarEstudiante(Estudiante e) {
        estudiantes.add(e);
    }
    public void agregarProfesor(Profesor p) {
        profesores.add(p);
    }
    public void agregarCurso(Curso c) {
        cursos.add(c);
    }
    public void listarCursosDisponibles() {
        System.out.println("\nCURSOS DISPONIBLES:");
        for (Curso c : cursos) {
            if (c.tieneCupo()) {
                c.mostrarInfo();
            }
        }
    }
    public void presentarATodos() {
        System.out.println("\nPRESENTACIONES:");
        for (Estudiante e : estudiantes) {
            e.presentarse();
        }
        for (Profesor p : profesores) {
            p.presentarse();
        }
    }
    public void resumen() {
        System.out.println("\nRESUMEN:");
        System.out.println("Universidad: " + Persona.UNIVERSIDAD);
        System.out.println("Personas registradas: " + Persona.getTotalPersonas());
        System.out.println("Cursos creados: " + Curso.getTotalCursos());
    }
}
public class Main {
    public static void main(String[] args) {
        SistemaGestion sistema = new SistemaGestion();
        Categoria progra = new Categoria("Programacion");
        Categoria mate = new Categoria("Matematicas");
        Profesor profe1 = new Profesor(1, "Karim Guevara", "kguevara@ucsm.edu.pe", "Programacion");
        Profesor profe2 = new Profesor(2, "Mario Santillana", "msantillana@ucsm.edu.pe", "Matematicas");
        sistema.agregarProfesor(profe1);
        sistema.agregarProfesor(profe2);
        Curso java = new Curso("LP3-01", "Lenguajes de Programacion III", progra);
        java.setCupoMax(2); 
        java.getSilabo().agregarTema("Clases y objetos");
        java.getSilabo().agregarTema("Herencia y polimorfismo");
        java.asignarProfesor(profe1);
        sistema.agregarCurso(java);
        Curso calculo = new Curso("MAT-02", "Calculo II", mate);
        calculo.getSilabo().agregarTema("Integrales");
        calculo.getSilabo().agregarTema("Series");
        calculo.asignarProfesor(profe2);
        sistema.agregarCurso(calculo);
        Estudiante est1 = new Estudiante(101, "Ana Torres", "ana@ucsm.edu.pe", "2021001");
        Estudiante est2 = new Estudiante(102, "Luis Rivas", "luis@ucsm.edu.pe", "2021002");
        Estudiante est3 = new Estudiante(103, "Carla Diaz", "carla@ucsm.edu.pe", "2021003");
        sistema.agregarEstudiante(est1);
        sistema.agregarEstudiante(est2);
        sistema.agregarEstudiante(est3);
        System.out.println("INSCRIPCIONES:");
        est1.inscribirse(java);
        est2.inscribirse(java);
        est3.inscribirse(java); 
        est1.inscribirse(calculo);
        est3.inscribirse(calculo);
        sistema.listarCursosDisponibles();
        sistema.presentarATodos();
        sistema.resumen();
    }
}