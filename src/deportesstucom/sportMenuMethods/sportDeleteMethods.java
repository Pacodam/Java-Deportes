/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deportesstucom.sportMenuMethods;

import deportesstucom.DeporteListaInscritos;
import deportesstucom.AlumnoInscrito;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 *
 * @author usuario
 */
public class sportDeleteMethods {
    
    
    /*En esta opcion, podemos eliminar o añadir deportes. Cada vez que se realiza un cambio, actualizamos el fichero deportesDisponibles.txt
    y el ArrayList deportesDisponibles en memoria local.
    */
    
    public static void opcionesDeporte(){
        
     
     System.out.println("==============================================");
     System.out.println("|  OPCION 6: ELIMINAR/INTRODUCIR DEPORTE     |");
     System.out.println("==============================================");
     
     int opcion;
     //mostramos los deportes actuales o si no decimos que no hay ninguno
     if(MetodosIO.deportesDisponibles.isEmpty()){
         System.out.println("No hay ningun deporte disponible");
         String[] opciones = {"JORNADAS DEPORTIVAS ", "Introducir nuevo deporte:"};
         opcion = generalMethods.menu(opciones);
         if(opcion == 1){
             nuevoDeporte();
             System.out.println("¿Introducir otro deporte? Pulsa RETURN o cualquier otra tecla para volver al menu inicial");
                if(generalMethods.metodoContinuar()){
                    opcionesDeporte();
                }
         }   
     }
     else{
         System.out.println("Deportes disponibles: ");
         int i = 1;
         for(DeporteListaInscritos index: MetodosIO.deportesDisponibles){
             System.out.println("    " + i + ". "+ index.getNombre());
             i++;
         }
         //mostramos las opciones de lo que se puede hacer: nuevo deporte (lo ponemos como extra) o eliminar deporte
         String[] opciones = {"JORNADAS DEPORTIVAS ", "Introducir nuevo deporte:", "Eliminar un deporte"};
         opcion = generalMethods.menu(opciones);
         if(opcion != 3){
            if(opcion == 1){
                nuevoDeporte();
            }  
            else if(opcion == 2){
                borrarDeporte();    
            }
         }
     }
  }
    
   
     //metodo para añadir un nuevo deporte a las jornadas
    public static void nuevoDeporte(){
        
        System.out.println("Introduce el nombre del nuevo deporte para las jornadas");
        
        boolean existe = false;
        String nombre = generalMethods.pedirCadena("Nuevo deporte:");
        for(int i = 0; i < MetodosIO.deportesDisponibles.size(); i++){
            if(nombre.equals(MetodosIO.deportesDisponibles.get(i).getNombre())){
                existe = true;
                System.out.println("El deporte " + nombre + " ya esta dado de alta. Pulsa RETURN para realizar otro cambio o cualquier otra tecla para volver al menu inicial");
                if(generalMethods.metodoContinuar()){
                     opcionesDeporte();
                 }
            break;
            }
        }
            if(!existe){
                int numero = generalMethods.pedirEntero("Numero minimo de participantes:");
                System.out.println("¿Confirmas el nuevo deporte? Pulsa RETURN o cualquier otra tecla para descartar");
                if(generalMethods.metodoContinuar()){
                    //actualizamos el deporte en el arraylist
                     MetodosIO.deportesDisponibles.add(new DeporteListaInscritos(nombre, numero));
                     //guardamos los cambios en deportesDisponibles.txt
                     MetodosIO.actualizarDeportes();
                     //actualizamos el arraylist de deportes
                     MetodosIO.leerDeportes();
                }
                else{
                    System.out.println("Se han descartado los cambios");
                }
                System.out.println("¿Realizar mas cambios en las Jornadas? Pulsa RETURN  o cualquier otra tecla para volver al menu inicial");
                if(generalMethods.metodoContinuar()){
                     
                     opcionesDeporte();
                 }
                else{
                    
                }
            }
        
        
        
    }
    
    //metodo para cuando se selecciona la opción de eliminar un deporte de las jornadas
    public static void borrarDeporte(){
        
        
        //recogemos el deporte a eliminar de las jornadas:
        DeporteListaInscritos deporteBorrar = deporte();
        String deporte = deporteBorrar.getNombre();
        
        System.out.println("¿Confirmas el BORRADO DE " + deporte + "? Pulsa RETURN o cualquier otra tecla para descartar");
        if(generalMethods.metodoContinuar()){
            
            //borramos ese deporte del arrayList deportesDisponibles
            MetodosIO.deportesDisponibles.remove(deporteBorrar);

            //borramos la linea del fichero deportesDisponibles.txt
            MetodosIO.actualizarDeportes();

            //actualizamos el arraylist de deportes
            MetodosIO.leerDeportes();
           //mostramos alumnos que quedan sin inscripcion y simultaneamente los eliminamos del ArrayList inscritos.
           listaAlumnosSinDeporte(deporteBorrar.getNombre()); 

           //por ultimo eliminamos los datos del fichero txt donde se guardan los alumnos
           MetodosIO.borrarTxt(deporte);
        }
        System.out.println("¿Realizar mas cambios en las Jornadas? Pulsa RETURN  o cualquier otra tecla para volver al menu inicial");
        if(generalMethods.metodoContinuar()){
             opcionesDeporte();
        }
    }

    
     //metodo para recoger el deporte que queremos eliminar. Solo muestra los deportes existentes (si
    //se anula un deporte, entonces obviamente ya no se mostraria
    public static DeporteListaInscritos deporte(){
        
         System.out.println("\n");
            System.out.println("============================");
            System.out.println("   Deportes para eliminar:");
            System.out.println("============================");
            System.out.println("  Opciones:                 ");
            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int i;
            int opcion = 0;
            boolean error = false;
            do{
                for(i = 1; i < MetodosIO.deportesDisponibles.size()+1; i++){ 
                     System.out.println("        "+ i + ". "+ MetodosIO.deportesDisponibles.get(i-1).getNombre());
                }
                try {
                   opcion = Integer.parseInt(br.readLine());
                   error = false;
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
            DeporteListaInscritos deporte = MetodosIO.deportesDisponibles.get(opcion-1);
            return deporte;  
    }
    
    //mostramos los datos de alumnos de un deporte determinado pasado por argumento, tambien lo borramos del arraylist a la vez
    public static void listaAlumnosSinDeporte(String deporte){
        
        int cantidad = 0;
        for(AlumnoInscrito index: MetodosIO.inscritos){
            if(index.getDeporte().equals(deporte)) cantidad++;
        }
        if(cantidad == 0){
            System.out.println("El deporte " + deporte + " no tiene ningun alumno inscrito");
        }
        else{
            System.out.println("\nLos siguientes alumnos quedan sin inscripcion: \n");
            int num = 1;
            for(int i = 0; i < MetodosIO.inscritos.size(); i++){
                AlumnoInscrito index = MetodosIO.inscritos.get(i);
                if(index.getDeporte().equals(deporte)){
                     String nombre = index.getNombre() + " " + index.getApellidos();
                     String curso = index.getCurso();
                     char sexo = index.getSexo();
                     int edad = index.getEdad(); 
                     System.out.println(num + ". Nombre: "+nombre+". Curso: "+curso+". Sexo: "+sexo+". Edad: "+ edad );
                     MetodosIO.inscritos.remove(index);
                     i--;
                     num++;
                }   
            }
        }
    }
    
    //borramos los alumnos de un deporte determinado pasado por argumento
    public static void borrarAlumnosDeporte(String deporte){
        
        for(int i = 0; i < MetodosIO.inscritos.size(); i++){
            AlumnoInscrito index = MetodosIO.inscritos.get(i);
            if(index.getDeporte().equals(deporte)){
                MetodosIO.inscritos.remove(index);
                i--;
            }
        }
        System.out.println("Se han eliminado a los alumnos de la memoria local");
    }
    
   
    
    
}
