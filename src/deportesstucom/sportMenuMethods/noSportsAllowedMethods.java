/*
 Metodos para la opcion de deportes que no se pueden practicar.
Nos basamos en los siguientes valores:
FUTBOL: 22 inscritos
FUTBOL SALA: 10 inscritos
BASQUET: 10 inscritos
BADMINTON: 2 inscritos
VOLEY PLAYA: 4 inscritos
 */
package deportesstucom.sportMenuMethods;


import deportesstucom.DeporteListaInscritos;
import deportesstucom.AlumnoInscrito;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author usuario
 */
public class noSportsAllowedMethods {
    
    public static void showNotAllowedSports(){
        
     
      System.out.println("==============================================");
      System.out.println("|    OPCION 3: ANALISIS DE CUPO DEPORTES     |");
      System.out.println("==============================================");
        
     if(MetodosIO.inscritos.isEmpty()){
            System.out.println("Actualmente no hay alumnos inscritos en ningun deporte");
     }
     else{
         /*Dado que tenemos una clase 'DeporteListaInscritos', que fundamentalmente se usa para guardar los deportes
         dados de alta en las jornadas pero que tambien permite guardar un ArrayList de alumnos inscritos en ese deporte,
         lo que haremos es añadir
         */
         
         //incorporamos a los deportes existentes, en el arrayList deportesDisponibles, a sus alumnos
         cargarAlumnosDeporte();
         
         //recorremos el arraylist y vamos obteniendo los datos necesarios
         for(DeporteListaInscritos index : MetodosIO.deportesDisponibles){
             String deporte = index.getNombre();
             int jugadoresRequeridos = index.getNumeroJugadores();
             int alumnosInscritos = index.getAlumnosInscritos().size();
             System.out.println("\n");
             System.out.println("Deporte: " + deporte.toUpperCase());
             System.out.println("Total alumnos inscritos: " + alumnosInscritos);
             System.out.println("Jugadores requeridos: " + jugadoresRequeridos);
             
             //por ultimo, dependiendo de la cantidad obtenida, ofrecemos la información en detalle.
             if(alumnosInscritos < jugadoresRequeridos){
                 System.out.println("No se alcanza el cupo necesario. Faltan " + (jugadoresRequeridos - alumnosInscritos) + " participantes");
             }
             else if(alumnosInscritos % jugadoresRequeridos == 0){
                 int equipos = alumnosInscritos/jugadoresRequeridos;
                 System.out.println("Este deporte se puede practicar. Se pueden formar " + equipos + " equipos diferentes.");
            }
             else{
                 int equipos = alumnosInscritos/jugadoresRequeridos;
                 int faltan = jugadoresRequeridos - (alumnosInscritos%jugadoresRequeridos);
                 System.out.println("Este deporte se puede practicar. Se pueden formar " + equipos + " equipos diferentes.");
                 System.out.println("Faltan " + faltan + " nuevos alumnos para formar un nuevo equipo");
             }
         }
     }
    }
    
    //metodo para añadir un arrayList de AlumnoInscrito al objeto DeporteListaInscritos
    public static void cargarAlumnosDeporte(){
        
        int totalDeportes = MetodosIO.deportesDisponibles.size();
        for(int i = 0; i < totalDeportes; i++){
            for(AlumnoInscrito index: MetodosIO.inscritos){
                if(index.getDeporte().equals(MetodosIO.deportesDisponibles.get(i).getNombre())){
                    AlumnoInscrito alumno = index;
                    MetodosIO.deportesDisponibles.get(i).addAlumno(alumno);
                }
            }
        }
    }
    
    public static ArrayList<DeporteListaInscritos> inscritosPorDeporte(){
        ArrayList<DeporteListaInscritos> inscritosDeporte = new ArrayList<>();
        
        //creamos un arrayList para cada uno de los cinco posibles deportes:
        ArrayList<AlumnoInscrito> fu = new ArrayList<>();
        ArrayList<AlumnoInscrito> fS = new ArrayList<>();
        ArrayList<AlumnoInscrito> bk = new ArrayList<>();
        ArrayList<AlumnoInscrito> bd= new ArrayList<>();
        ArrayList<AlumnoInscrito> vP = new ArrayList<>();
        
        //recorremos el arraylist de inscritos y vamos añadiendo a cada arraylist
        for(AlumnoInscrito index : MetodosIO.inscritos){
             String deporte = index.getDeporte();
             
             switch(deporte){
                 case "futbol": fu.add(index); break;
                 case "futbol sala": fS.add(index); break;
                 case "basquet": bk.add(index); break;
                 case "badminton": bd.add(index); break;
                 case "voley playa": vP.add(index);
             }
        }
        //por ultimo introducimos en 'inscritosDeporte' y devolvemos
        inscritosDeporte.add(new DeporteListaInscritos("futbol", fu));
        inscritosDeporte.add(new DeporteListaInscritos("futbol sala", fS));
        inscritosDeporte.add(new DeporteListaInscritos("basquet", bk));
        inscritosDeporte.add(new DeporteListaInscritos("badminton", bd));
        inscritosDeporte.add(new DeporteListaInscritos("voley playa", vP));
        
        return inscritosDeporte;
    }
}
