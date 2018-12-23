/*
  clase de Deportes susceptibles de ser objeto de inscripción
 */
package deportesstucom;

/**
 *
 * @author usuario
 */
import java.util.ArrayList;

public class DeporteListaInscritos {
    
    private String nombre;
    private int numeroJugadores;
    private ArrayList<AlumnoInscrito> alumnosInscritos = new ArrayList<>();  //arraylist de objetos tipo AlumnoInscrito

    public DeporteListaInscritos(String nombre, ArrayList<AlumnoInscrito> inscritos) {
        this.nombre = nombre;
        this.alumnosInscritos = (ArrayList<AlumnoInscrito>) inscritos.clone();
        switch(nombre.toLowerCase()){
            case "futbol": this.numeroJugadores = 22; break;
            case "futbol sala": this.numeroJugadores = 10; break;
            case "basquet": this.numeroJugadores = 10; break;
            case "badminton": this.numeroJugadores = 2; break;
            case "voley playa": this.numeroJugadores = 4;
        }
    }

    public DeporteListaInscritos(String nombre, int numeroJugadores) {
        this.nombre = nombre;
        this.numeroJugadores = numeroJugadores;
    }

    
    
 
    //repasar como funcionan los getter y setters cuando trabajamos con atributos ArrayLists dentro de una clase
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<AlumnoInscrito> getAlumnosInscritos() {
        return alumnosInscritos;
    }

    public void setAlumnosInscritos(ArrayList<AlumnoInscrito> inscritos) {
        this.alumnosInscritos = (ArrayList<AlumnoInscrito>) inscritos.clone();
    }
    
    public void addAlumno(AlumnoInscrito alumno){
        this.alumnosInscritos.add(alumno);
    }

    public int getNumeroJugadores() {
        return numeroJugadores;
    }

    public void setNumeroJugadores(int numeroJugadores) {
        this.numeroJugadores = numeroJugadores;
    }
    
    
}
