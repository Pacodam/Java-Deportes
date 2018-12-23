/*
  Este grupo de métodos se ocupa de la opcion 1 del menú inicial, esto es, recoger los datos del nuevo alumno
y realizar su inscripcion en el deporte escogido. Tras finalizar una nueva inscripcion, se guardan los datos en
los archivos (o se crean si no habia nadie en ese deporte). Además, se actualiza el ArrayList de objetos DeporteListaInscritos.
 */
package deportesstucom.sportMenuMethods;

import deportesstucom.AlumnoInscrito;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Set;


/**
 *
 * CopyRight: Mar Fontana (funciones de entrada de datos tomadas de su perfil de GitHub), se han
 * hecho algunas modificaciones para controlar el formato de entrada de los datos.
 */

import java.util.ArrayList;
import java.util.LinkedHashSet;


public class inscripcionMethods {
    
    /*este ArrayList global de objetos tipo AlumnoInscrito se utilizara para ir almacenando las nuevas inscripciones. 
      Una vez se abandona la opcion de inscripcion de nuevos alumnos, se envia a MetodosIO para actualizar los ficheros .txt.
    */
    public static ArrayList<AlumnoInscrito> nuevasInscripciones = new ArrayList<>();
    
     public static void nuevaInscripcion(){
         
       System.out.println("==============================================");
       System.out.println("|   OPCION 1: INSCRIPCION DE NUEVOS ALUMNOS  |");
       System.out.println("==============================================");
       
       if(MetodosIO.deportesDisponibles.isEmpty()){
           System.out.println("No se pueden dar de alta alumnos porque no hay deportes disponibles");
           System.out.println("¿Dar de alta un deporte? Pulsa RETURN o cualquier otra tecla para volver al menu inicial");
           if(generalMethods.metodoContinuar()){
               sportDeleteMethods.nuevoDeporte();
           }
       } 
       else{
        //Iniciamos las inscripciones dentro de un do-while hasta que deseemos salir
        boolean nuevaInscripcion = true;
        do{
           
            String opcion;
            String nombre = generalMethods.pedirCadena("Nombre:").toUpperCase();
            String apellidos = generalMethods.pedirCadena("Apellido:").toUpperCase();
            String nombreCompleto = nombre + " " + apellidos;
            
             //hemos de evaluar si el nombre que se introduce existe ya o no, si existe se reinicializa bucle, a menos que se 
            //escoja volver al menu inicial. Lo comprobamos llamando a metodo comparadorNombres() que devuelve true si el nombre+apellido
            //ya pertenecen a algun inscrito
            boolean nombreOk = true;
            
            AlumnoInscrito test = comparadorNombres(nombreCompleto);
            if(test != null){
               System.out.println("El alumno " + nombreCompleto + " ya esta dado de alta en " + test.getDeporte());
               nombreOk = false;
               System.out.println("Pulsa RETURN para volver a reiniciar la inscripcion o cualquier otra tecla para volver al menu inicial");
                 if(!generalMethods.metodoContinuar()){
                    nuevaInscripcion = false;
                 }
                 else{
                    nuevaInscripcion = true;
                 } 
            }  
            //si el nombre+apellido no existen  continuamos introduciendo datos      
            else{
                String curso = generalMethods.curso().toString();
                char sexo = generalMethods.sexo();
                int edad = generalMethods.pedirEntero("Edad: ");
                String deporte = deporte();
                AlumnoInscrito alumno = new AlumnoInscrito(nombre, apellidos, curso, sexo, edad, deporte);

                //comprobacion de que se han recogido los datos, no necesario en version final
                //String linea = nombre + ";" + apellidos + ";" + curso + ";" + sexo + ";" + edad; 
                //System.out.println(linea);  
                //
                nuevasInscripciones.add(alumno); //guardamos la nueva inscripción

                //opción de seguir inscribiendo o salir
                System.out.println("Pulsa RETURN para introducir una nueva inscripcion o cualquier otra tecla para guardar datos y volver al menu inicial");
                if(!generalMethods.metodoContinuar()){
                    nuevaInscripcion = false;
                }
              }
                  
        }while(nuevaInscripcion);
        
        //ya no registraremos mas alumnos, asi que:
        if(!nuevasInscripciones.isEmpty()){
            // 1. actualizamos el arrayList global de alumnos inscritos
            actualizarInscritos();
        
            //2. Guardamos los nuevos datos en los ficheros .txt
            actualizarFicheros();
        
            //3. Por último, borramos todos los datos del arrayList provisional.
            nuevasInscripciones.clear();
        }
     } 
     }
     
     /*Tenemos un arraylist de nuevasInscripciones, antes de actualizar el .txt llamando al metodo en MetodosIO,
     tenemos que extraer los diferentes deportes, para que el método no pierda tiempo en recorrerlos todos, es decir,
     solo se modifican los ficheros de deportes en los que se producen nuevas inscripciones, y el metodo I/O recibe como
     argumento el deporte que se modifica */
     public static void actualizarFicheros(){
         
         String[] deportes = new String[nuevasInscripciones.size()];
         Set<String> setUniqueSports = new LinkedHashSet<>();
         for(AlumnoInscrito x : nuevasInscripciones) {
            setUniqueSports.add(x.getDeporte());
         }
         for(String deporte: setUniqueSports) {
            MetodosIO.actualizarAlumnos(deporte);
         }
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
    
    //metodo para recoger el deporte del nuevo inscrito. Solo muestra los deportes existentes (si
    //se anula un deporte, entonces ya no se mostraria
    public static String deporte(){
         MetodosIO.leerDeportes();
         System.out.println("\n");
            System.out.println("============================");
            System.out.println("   Deportes disponibles:");
            System.out.println("============================");
            System.out.println("  Opciones:                 ");
            
            String deporte = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int i;
            int opcion = 0;
            boolean error = false;
            do{
                error = false;
                for(i = 1; i <= MetodosIO.deportesDisponibles.size(); i++){ 
                     System.out.println("        "+ i + ". "+ MetodosIO.deportesDisponibles.get(i-1).getNombre() );
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
                if(!error && (opcion < 1 || opcion > MetodosIO.deportesDisponibles.size())){
                    System.out.println("Debes introducir una opcion valida.");
                    error = true;
                    }
               } while (error); 
            deporte = MetodosIO.deportesDisponibles.get(opcion-1).getNombre();
            return deporte;  
    }
    
    //el metodo devuelve un ArrayList con todos los nombres completos de los alumnos y el deporte que practican.
    public static ArrayList<String[]> nombreCompleto(){
        int lista1 = MetodosIO.inscritos.size();
        int lista2 = nuevasInscripciones.size();
        ArrayList<String[]> datos = new ArrayList<>();
        String[] datosInscritos = new String[2];
        for(AlumnoInscrito index: MetodosIO.inscritos){
            datosInscritos[0] = index.getNombre() + " " + index.getApellidos();
            datosInscritos[1] = index.getDeporte();
            datos.add(datosInscritos);
        }
        for(AlumnoInscrito index: nuevasInscripciones){
            datosInscritos[0] = index.getNombre() + " " + index.getApellidos();
            datosInscritos[1] = index.getDeporte();
            datos.add(datosInscritos);
        }
        return datos;
    }
    
    //volcado de los alumnos que hemos ido recogiendo en el arrayList general
    public static void actualizarInscritos(){
        
        int i = 1;
        for(AlumnoInscrito index: nuevasInscripciones){
            MetodosIO.inscritos.add(index);
             String nombre = index.getNombre() + " " + index.getApellidos();
                    String curso = index.getCurso();
                    char sexo = index.getSexo();
                    int edad = index.getEdad();
                    System.out.println("Nuevo inscrito " + i+ ".   Nombre: "+nombre+". Curso: "+curso+". Sexo: "+sexo+". Edad: "+ edad );
                    i++;
            System.out.println("");
         }    
    } 
    
    
    
    
}


