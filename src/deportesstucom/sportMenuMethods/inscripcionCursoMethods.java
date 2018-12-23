/*
 Hemos decidido retocar ligeramente esta parte del ejercicio. En lugar de buscar segun el formato "DAM1T2", etc, buscaremos
meramente por DAM1, DAM2, etc, sin agregar el grupo. El resultado decir el número de alumnos que hay y dar el nombre, apellido y
deporte en el que está inscrito.
 */
package deportesstucom.sportMenuMethods;


/**
 *
 * @author usuario
 */
import deportesstucom.AlumnoInscrito;

import java.util.ArrayList;
//import java.util.List;


public class inscripcionCursoMethods {
    
    public static void getAlumnosPorCurso(){
        
   
       System.out.println("==============================================");
       System.out.println("|    OPCION 2: ALUMNOS INSCRITOS POR CURSO   |");
       System.out.println("==============================================");
       
        //verificamos que existan alumnos dados de alta
        if(MetodosIO.inscritos.isEmpty()){
            System.out.println("Actualmente no hay ;ningun alumno inscrito");
        }
        else{
            boolean salir = false;
            do{
                //usamos un arraylist para almacenar los alumnos del curso buscado. Se borra al reiniciar el bucle do-while
                ArrayList<AlumnoInscrito> alumnosCurso = new ArrayList<>();
                
                //el usuario selecciona el curso sobre el que quiere hacer la búsqueda
                String curso = curso().toString();
                
                for(AlumnoInscrito index: MetodosIO.inscritos){
                    if(index.getCurso().contains(curso)){
                        alumnosCurso.add(index);
                    }
                }
                
               System.out.println("============================");
               System.out.println("      " + curso              );
               System.out.println("============================");
               
                //los mostramos:
                int i = 1;
                for(AlumnoInscrito index: alumnosCurso){
                    String nombre = index.getNombre();
                    String apellido = index.getApellidos();
                    String deporte = index.getDeporte();
                    String curso1 = index.getCurso();
                    System.out.println( i + ". Nombre: " + nombre + " " + apellido + ". Deporte: " + deporte + ". Curso: " + curso1);
                    i++;
                }
                System.out.println("Total: " + alumnosCurso.size() + " alumnos inscritos de " + curso );
                
                System.out.println("\n");
                System.out.println("¿Nueva busqueda? Pulsa RETURN o cualquier otra tecla para regresar al menu inicial.");
                if(!generalMethods.metodoContinuar()){
                    salir = true;
                }           
           }while(!salir);       
        }
    }
    
     //el método se utiliza para recoger el curso
    public static StringBuilder curso(){
       int opcion;
       int i;
       StringBuilder curso1 = new StringBuilder();
       //curso, año y grupo... presentamos menus por separado y tras seleccion concatenamos
       //damos tres opciones de curso, obviamente podrian ser muchas mas, bastaria con que los introdujeramos
       //en el String[]
       String[] nombreCurso = {"SELECCIONA EL GRADO DEL ALUMNO", "DAM","DAW","ASIR","BAT"};
       String[] year = {"SELECCIONA EL CURSO", "1","2"};
       
       curso1.append(nombreCurso[generalMethods.menuSinExit(nombreCurso)]);
       curso1.append(year[generalMethods.menuSinExit(year)]);
         
       return curso1;
    }
    
    public static void mostrarAlumnos(ArrayList<AlumnoInscrito> alumnos, String curso){
        
        System.out.println("Alumnos en curso " + curso + ":");
       for(AlumnoInscrito index : alumnos){
           if(index.getCurso().equals(curso)){
               System.out.println(index.getNombre() + " " + index.getApellidos());
           }
       }     
    } 
       
       /*este metodo no lo usamos, pero podria darse el caso si quisieramos hacer uso de una opción tipo "obtener
    //los diferentes cursos existentes entre los inscritos.
    public static List<String> getCursosDiferentes() {
        
        List<String> cursosDiferentes = new ArrayList <>();
        for(AlumnoInscrito index : MetodosIO.inscritos){
           if(!cursosDiferentes.contains(index.getCurso())) {
              cursosDiferentes.add(index.getCurso());
           }
        }
        return cursosDiferentes;  
    }  */
    
}
