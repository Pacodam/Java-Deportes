/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deportesstucom.sportMenuMethods;

import deportesstucom.AlumnoInscrito;
import static deportesstucom.sportMenuMethods.inscripcionMethods.nuevasInscripciones;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author usuario
 */
public class changeSportsMethods {
    
    public static void cambiarAlumnoDeporte(){
        
        System.out.println("==============================================");
        System.out.println("  OPCION EXTRA: cambiar a un alumno de deporte");
        System.out.println("==============================================");
        
        //si no hay ningun alumno, avisamos, y damos opcion de inscribir nuevos alumnos
        if(MetodosIO.inscritos.isEmpty()){
           System.out.println("No hay alumnos registrados en ningun deporte");
           System.out.println("¿Dar de alta un alumno? Pulsa RETURN o cualquier otra tecla para volver al menu inicial");
           if(generalMethods.metodoContinuar()){
               inscripcionMethods.nuevaInscripcion();
           }
        } 
        else{
            //si solo hay un deporte disponible para las Jornadas, avisamos, y damos opcion de crear nuevo deporte
            if(MetodosIO.deportesDisponibles.size() == 1){
               System.out.println("Solo hay un deporte dado de alta en las jornadas. No es posible cambiar a un alumno");
               System.out.println("¿Dar de alta un deporte nuevo? Pulsa RETURN o cualquier otra tecla para volver al menu inicial");
               if(generalMethods.metodoContinuar()){
                   sportDeleteMethods.opcionesDeporte();
               }
            }
            else{
             //Opciones de cambios ulteriores se realizaran mientras se desee continuar
             boolean nuevoCambio = true;
             do{
                 String opcion;
                 String nombre = generalMethods.pedirCadena("Nombre:").toUpperCase();
                 String apellidos = generalMethods.pedirCadena("Apellido:").toUpperCase();
                 String nombreCompleto = nombre + " " + apellidos;

                  //hemos de evaluar si el nombre que se introduce existe, si no existe se reinicializa bucle, a menos que se 
                 //escoja volver al menu inicial. Lo comprobamos llamando a metodo comparadorNombres() que devuelve true si el nombre+apellido
                 //ya pertenecen a algun inscrito

                 AlumnoInscrito alumno = comparadorNombres(nombreCompleto);
                 if(alumno == null){
                    System.out.println("ERROR. " + nombreCompleto + "  no esta dado de alta en ningun deporte");
                    System.out.println("\n");
                    System.out.println("Pulsa RETURN para volver a reiniciar la modificacion o cualquier otra tecla para volver al menu inicial");
                      if(!generalMethods.metodoContinuar()){
                         nuevoCambio = false;
                      }
                      else{
                         nuevoCambio = true;
                      } 
                 } 
                 else{
                     String deporteOrigen = alumno.getDeporte();
                     System.out.println("El alumno " + alumno.getNombre() + " " + alumno.getApellidos() + " esta inscrito en " + deporteOrigen);
                     //llamamos a método para seleccionar deporte de destino. Enviamos deporte actual para que no se muestre en las opciones
                     String deporteDestino = deporte(alumno.getDeporte());

                     //una vez disponemos del alumno a cambiar y del deporte a donde cambiarlo, solicitamos confirmación:
                      System.out.println("Pulsa RETURN para CONFIRMAR EL CAMBIO o cualquier otra tecla para descartar y volver al menu inicial");
                      if(!generalMethods.metodoContinuar()){
                         nuevoCambio = false;
                      }
                      else{
                        //borramos del ArrayList de alumnos al alumno
                        MetodosIO.inscritos.remove(alumno);
                        //modificamos el ficheros .txt del deporte origen
                        MetodosIO.actualizarAlumnos(deporteOrigen);
                        //añadimos al alumno en el ArrayList, con el nuevo deporte, antes le asignamos su nuevo deporte
                        alumno.setDeporte(deporteDestino);
                        MetodosIO.inscritos.add(alumno);
                        //modificamos el fichero .txt del deporte destino
                        MetodosIO.actualizarAlumnos(deporteDestino);
                        
                        //preguntamos si se desea realizar un nuevo cambio de alumno:
                        System.out.println("Pulsa RETURN para realizar un nuevo cambio o cualquier otra tecla para volver al menu inicial");
                        if(!generalMethods.metodoContinuar()){
                           nuevoCambio = false;
                        }
                      } 
                 }
               }while(nuevoCambio);
             }
        }
 }
        
    //metodo para recoger el deporte al cual se quiere mover el alumno. El deporte actual no se da como opción, se muestra como actual.
    public static String deporte(String viejo){
        
         System.out.println("\n");
            System.out.println("============================");
            System.out.println("  Nuevo deporte para alumno:");
            System.out.println("============================");
            System.out.println("  Opciones:                 ");
            
            String nuevo = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int i;
            int opcion = 0;
            int noDisponible = 0;
            boolean error = false;
            do{
                error = false;
                for(i = 1; i <= MetodosIO.deportesDisponibles.size(); i++){ 
                     String deporte = MetodosIO.deportesDisponibles.get(i-1).getNombre();
                     if(deporte.equals(viejo)){
                         System.out.println("           ACTUAL ");
                         noDisponible = i;
                     }
                     else{
                        System.out.println("        "+ i + ". "+ deporte);
                     }
                }
                try {
                   opcion = Integer.parseInt(br.readLine());
                } catch (IOException ex) {
                     System.out.println("Error de entrada / salida.");
                     error = true;
                } catch (NumberFormatException ex) {
                     System.out.println("Debes introducir una opcion valida.");
                     error = true;
                }
                if(!error && (opcion == noDisponible || opcion < 1 || opcion > MetodosIO.deportesDisponibles.size())){
                    System.out.println("Debes introducir una opcion valida.");
                    error = true;
                    }
               } while (error); 
            nuevo = MetodosIO.deportesDisponibles.get(opcion-1).getNombre();
            return nuevo;  
    }

        
        //comparamos el nombre introducido por teclado con los inscritos y ademas con las nuevasInscripciones que no han
     //sido volcadas todavía.
     public static AlumnoInscrito comparadorNombres(String nombreCompleto){
         
         AlumnoInscrito alumno = null;
         for(AlumnoInscrito index: MetodosIO.inscritos){
             if(nombreCompleto.equals(index.getNombre() + " " + index.getApellidos())){
                 alumno = index;
             }
         }
         for(AlumnoInscrito index: nuevasInscripciones){
             if(nombreCompleto.equals(index.getNombre() + " " + index.getApellidos())){
                 alumno = index;
             }
          }
         return alumno;
     }

    
}
