/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deportesstucom.sportMenuMethods;

import deportesstucom.AlumnoInscrito;
import deportesstucom.DeporteListaInscritos;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author usuario
 */
public class inscriptionListMethods {
    
    
    /* Utilizamos el arraylist global deportesDisponibles, de objetos tipo DeporteListaInscritos. Obtenemos un arraylist de alumnos
    por cada deporte disponible en las Jornadas, luego lo ordenamos, y por ultimo lo añadimos al arraylist deportesDisponibles, ya que
    tenemos un atributo en la clase DeporteListaInscritos consistente en un ArrayList de alumnos y un método que sustituye cualquier
    arraylist de alumnos previo por el nuevo que se introduce.
    Para terminar, mostramos el listado a partir de ese arraylist.
    */
    
    public static void listaAlumnosDeporte(){
        
       System.out.println("==============================================");
       System.out.println("|       OPCION 5: LISTADO INSCRIPCIONES      |");
       System.out.println("==============================================");
        
        String deporte = null;
        ArrayList<AlumnoInscrito> alumnos = new ArrayList<>();                            //en este arraylist guardamos los alumnos por deporte. Se vacia tras cada ciclo de bucle for
        for(int i = 0; i < MetodosIO.deportesDisponibles.size(); i++){                    //recorremos los deportes disponibles                         
            deporte = MetodosIO.deportesDisponibles.get(i).getNombre();
            String nombreDeporte = MetodosIO.deportesDisponibles.get(i).getNombre();      //tomamos el nombre de deporte actual
            for(AlumnoInscrito index2: MetodosIO.inscritos){                              //recorremos la lista de inscritos...
                if(index2.getDeporte().equals(nombreDeporte)){                            // ... y si el nombre del deporte del alumno es igual al deporte en que estamos...
                    alumnos.add(index2);                                                  //añadimos su indice al arrayList de alumnoInscrito
                }
            }
            /*por ultimo, tras tener a los alumnos de ese deporte, los añadimos a deportesDisponibles, pero lo hacemos enviando el
            arrayList alumnos al metodo listaOrdena, el cual nos devolverá el mismo arraylist, pero ordenado alfabeticamente.
            */
            MetodosIO.deportesDisponibles.get(i).setAlumnosInscritos(listaOrdena(alumnos,deporte));
            alumnos.clear();   //borramos el arraylist para empezar de nuevo con el siguiente deporte, y asi sucesivamente.
        }
        
        //mostramos la información, que ya se encuentra ordenada como queremos.
       
       for(DeporteListaInscritos index: MetodosIO.deportesDisponibles){
           System.out.println("\n");
           System.out.println("==============================================");
           System.out.println("         Deporte: " + index.getNombre());
           System.out.println("==============================================");
           int i = 1;
           if(index.getAlumnosInscritos().isEmpty()){
               System.out.println("No hay inscritos en este deporte");
           }
           else{
                for(AlumnoInscrito index2: index.getAlumnosInscritos()){
                    String nombre = index2.getNombre() + " " + index2.getApellidos();
                    String curso = index2.getCurso();
                    char sexo = index2.getSexo();
                    int edad = index2.getEdad();
                    
                    System.out.println(i + ". Nombre: "+nombre+". Curso: "+curso+". Sexo: "+sexo+". Edad: "+ edad );
                    i++;
                }
          }  
       }
    }
    
    //metodo que recibe un arraylist 
     public static ArrayList<AlumnoInscrito> listaOrdena(ArrayList alumnos, String deporte){
         
         //partimos de un ArrayList<String> donde guardamos el nombre completo de los alumnos segun
         //deporte pasado como argumento
         ArrayList<String> nombreAlumnos = new ArrayList<>();
         
         //recorremos los inscritos y cuando el deporte sea el deseado, guardamos el nombre completo.
          for(AlumnoInscrito index: MetodosIO.inscritos){
               if(index.getDeporte().equals(deporte)){
                  nombreAlumnos.add(index.getNombre() + " " + index.getApellidos()); 
               }
          }
          
          //ahora ordenamos alfabeticamente ese ArrayList de nombre completo
          Collections.sort(nombreAlumnos);
                  
          //Volvemos a recorrer el ArrayList de inscritos, pero ahora comparamos con el arraylist
          //de nombre completo ordenado. Lo guardamos en el ArrayList de alumno inscrito que devolvemos.
          ArrayList<AlumnoInscrito> alumnoInscrito = new ArrayList<>();
          
          for(String index: nombreAlumnos){
              for(AlumnoInscrito index2: MetodosIO.inscritos){                 
                    if(index.equals(index2.getNombre() + " " + index2.getApellidos())){
                        alumnoInscrito.add(index2);
                    }
              }
          }
          
          return alumnoInscrito;
     }
    
     public static void mostrar(ArrayList<String> alumnos){
         
         for(String index: alumnos){
             System.out.println(index);
         }
     }
}
