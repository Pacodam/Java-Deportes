/*
 En esta opción, van los métodos para borrar un alumno de las inscripciones.
En primer lugar tomaremos por teclado el nombre del alumno que se quiere borrar. 
Una vez verificado que ese alumno existe:
  1)Lo borramos del ArrayList de alumnos inscritos
  2)Eliminamos el fichero .txt donde estaba ese alumno guardado
  3)Volvemos a crear un fichero .txt a partir del ArrayList actualizado

*/


package deportesstucom.sportMenuMethods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import deportesstucom.AlumnoInscrito;


/**
 *
 * @author usuario
 */
public class inscriptionDeleteMethods {
    
    public static void borrarAlumno(){
        
       System.out.println("==============================================");
       System.out.println("|         OPCION 4: BORRAR UN ALUMNO         |");
       System.out.println("==============================================");
       
    
      
        boolean salir = false;
        do{
           if(MetodosIO.inscritos.isEmpty()){
                 System.out.println("Actualmente no hay ningun alumno inscrito");
                 salir = false;
           }
           else{
                //pedimos nombre alumno
                String nombre = pedirCadena("Nombre alumno a borrar: ").toUpperCase();
                String apellido = pedirCadena("Apellido alumno a borrar: ").toUpperCase();

                //verificamos si existe, si existe mostramos sus datos y pedimos confirmación de borrado
                AlumnoInscrito alumno = buscaAlumno(nombre, apellido);
                if(alumno == null){
                    System.out.println("No hay ningun alumno registrado con ese nombre y apellido");
                }
                else{
                    System.out.println("\n");
                    System.out.println("Datos del alumno:");
                    System.out.println("Nombre: " +  alumno.getNombre() + " " + alumno.getApellidos());
                    System.out.println("Curso: " + alumno.getCurso());
                    System.out.println("Sexo: " + alumno.getSexo());
                    System.out.println("Edad: " + alumno.getEdad());
                    System.out.println("Deporte: " + alumno.getDeporte());

                    System.out.println("\n");
                    System.out.println("Pulsa RETURN para BORRAR DEFINITIVAMENTE, o cualquier otra tecla para cancelar");
                    if(generalMethods.metodoContinuar()){
                        String deporte = alumno.getDeporte();
                        //borramos al alumno del ArrayList de alumnos inscritos en memoria local
                        MetodosIO.inscritos.remove(alumno);
                        //lo borramos del .txt. Si ese alumno era el ultimo inscrito en ese deporte, directamente borramos el txt
                        if(contarPorDeporte(deporte) > 0){
                        MetodosIO.actualizarAlumnos(deporte);  
                        }
                        else{
                            MetodosIO.borrarTxt(deporte);
                        }
                    }
                }
                System.out.println("\n");
                System.out.println("¿Borrar un nuevo alumno? Pulsa RETURN, o cualquier otra tecla para volver al menu inicial");
                if(!generalMethods.metodoContinuar()){
                    salir = true;
                }
               }
            }while(!salir);
       }
        
    
    public static int contarPorDeporte(String deporte){
        int numero = 0;
        for(AlumnoInscrito index: MetodosIO.inscritos){
            if(deporte.equals(index.getDeporte())){  
                numero++;
            }
        }
        return numero;
    }
    
    public static AlumnoInscrito buscaAlumno(String nombre, String apellido){
        
        AlumnoInscrito alumno = null;
        for(AlumnoInscrito index: MetodosIO.inscritos){
            if(index.getNombre().equals(nombre) && index.getApellidos().equals(apellido)){
                alumno = index;
            }
        }
        return alumno;   
    }
    
     public static String pedirCadena(String texto) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         System.out.println("\n");
            System.out.println("============================");
            System.out.println("         " +texto );
            System.out.println("============================");
        String cadena = "";
        do {
            try {
                System.out.println(texto);
                cadena = br.readLine();
                if (cadena.equals("")) {
                    System.out.println("No se puede dejar el campo en blanco.");
                }
            } catch (IOException ex) {
                System.out.println("Error de entrada / salida.");
            }
        } while (cadena.equals(""));
        return cadena;
    }
     
}
