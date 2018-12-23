/*
 En este conjunto de métodos realizamos el proceso de lectura de los archivos (en caso de haberlos)
dentro del directorio ./jornadasDeportivas (caso de haberlo, también). El contenido de cada archivo, consistente en los alumnos
inscritos en el deporte correspondiente que representa el archivo, se vuelca en un ArrayList formado por
objetos del tipo DeporteListaInscritos que está declarado en el main y al cual se accede de forma global.
El método de lectura de archivos se efectua siempre que se accede al menú inicial, con lo cual siempre 
tenemos los arrayLists actualizados.
Asimismo, en este set  iran todos los métodos que tengan que ver con lectura o escritura de ficheros
 */
package deportesstucom.sportMenuMethods;

import deportesstucom.AlumnoInscrito;
import deportesstucom.DeporteListaInscritos;
import java.util.ArrayList;
/**
 *
 * @author usuario
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class MetodosIO {

    /*este ArrayList global de objetos tipo AlumnoInscrito se utilizara para ir almacenando las nuevas inscripciones. 
    El ArrayList se carga desde los .txt de deportes (si hubiera alguno)
    El ArrayList se actualiza siempre que se produce una nueva inscripción satisfactoria, o cuando se elimina algun alumno o deportes, etc.
    Asimismo, cuando se abandona la sección de inscripción de nuevos alumnos, todos los alumnos nuevos se vuelcan en los respectivos 
    ficheros .txt de deportes. 
    Para mantener persistencia tambien de los deportes que se vayan añadiendo o eliminando, tambien tenemos un ArrayList de objetos DeporteListaInscritos,
    es decir, de deportes con sus datos y la posibilidad de añadir a los alumnos inscritos en el mismo.
     */
    public static ArrayList<AlumnoInscrito> inscritos = new ArrayList<>();
    public static ArrayList<DeporteListaInscritos> deportesDisponibles = new ArrayList<>();

    
    
    
                                /**********************LECTURA DE FICHEROS AL INICIAR EL MENU POR PRIMERA VEZ *********************************/    
    
    
    //método para leer los ficharos .txt de deportes existentes y guardarlos en el ArrayList de alumnos inscritos. Se ejecuta al iniciar menu inicial,
    // si y solo si el ArrayList donde se guardan los datos está vacio.
    public static void leerFicheros() {

        File carpeta = new File("." + File.separator + "jornadasDeportivas");  //nueva File con el nombre de la carpeta
        FileReader fr = null;

        if (carpeta.exists()) {
            File[] listaFicheros = carpeta.listFiles();  //Un array de Files con los diferentes ficheros existentes en la carpeta
            if (listaFicheros.length > 1) {
                for (int i = 0; i < listaFicheros.length; i++) {
                  String nombreDeporte = listaFicheros[i].getName().split("\\.txt")[0];   //extraemos el nombre del deporte usando split sobre el nombre del fichero
                  if(!nombreDeporte.equals("deportesDisponibles")){ 
                    try {                                    //apertura del array File recorriendolo, para cada uno creación de BufferedReader para poder hacer una lectura comoda (disponer del metodo readLine()).
                        fr = new FileReader(listaFicheros[i]);
                        BufferedReader br = new BufferedReader(fr);
                        //lectura del fichero
                        String linea;
                        while ((linea = br.readLine()) != null) {
                            inscritos.add(generalMethods.extractData(linea, nombreDeporte));  //el método extractData() separa los campos de cada linea separados por ';'
                        }
                    } catch (IOException e) {
                        System.out.println("ERROR al abrir o leer el archivo: " + e.getMessage());
                    } finally {   // En el finally cerramos el fichero, para asegurarnos que se cierra tanto si todo va bien como si salta una excepcion
                        try {
                            if (fr != null) {
                                fr.close();
                            }
                        } catch (IOException e) {
                            System.out.println("ERROR al cerrar el archivo: " + e.getMessage());
                        }
                    }
                  }
                }
            }
        }

    }
    
    //tambien damos persistencia a los deportes disponibles en las jornadas, por eso leemos los datos de deportesDisponibles.txt al iniciar las jornadas
    public static void leerDeportes(){
        
          deportesDisponibles.clear();
          File carpeta = new File("." + File.separator + "jornadasDeportivas");  //nueva File con el nombre de la carpeta
          FileReader fr = null;

          if (carpeta.exists()) {
            File listaDeportes =  new File(carpeta+ File.separator + "deportesDisponibles.txt");
            if(listaDeportes.exists()){
                try {                                   
                    fr = new FileReader(listaDeportes);
                    BufferedReader br = new BufferedReader(fr);
                    //lectura del fichero
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        String[] deportes = linea.split(";");
                        String deporte = deportes[0];
                        int participantes = Integer.parseInt(deportes[1]);
                       deportesDisponibles.add(new DeporteListaInscritos(deporte, participantes));
                    }
                } catch (IOException e) {
                    System.out.println("ERROR al abrir o leer el archivo: " + e.getMessage());
                } finally {   // En el finally cerramos el fichero, para asegurarnos que se cierra tanto si todo va bien como si salta una excepcion
                    try {
                        if (fr != null) {
                            fr.close();
                        }
                    } catch (IOException e) {
                        System.out.println("ERROR al cerrar el archivo: " + e.getMessage());
                    }
                }
                  
            }
          }

    }
    
    /* Este método es para tomar los datos en el ArrayList global deportesDisponibles y volcarlo en un fichero dentro
    de la carpeta jornadasDeportivas llamado deportesDisponibles.txt. Hemos decidido hacerlo así porque en un escenario real
    ese fichero podría estar protegido para que solo un administrador de la aplicación pudiera editarlo, con lo cual garantizamos
    que los deportes (o cualquier otro tipo de elemento) disponibles pudieran estar siempre protegidos.
    */ 
    
    public static void actualizarDeportes(){
        
        //primero creamos la carpeta si no existe
        File carpeta = new File("." + File.separator + "jornadasDeportivas");
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }
        
        //como queremos recoger los deportes del arrayList, evaluamos si el fichero existe, si existe lo borramos
        // y a continuación lo creamos de nuevo, ya vacio.
        File fichero = new File(carpeta + File.separator + "deportesDisponibles.txt");
        boolean error = false;
        if(fichero.exists()){
            fichero.delete();
        }
        if (!fichero.exists()) {
            try{
               fichero.createNewFile();       
            }catch(IOException e){
                System.out.println("problema al crear deportesDisponibles.txt " + e.getMessage());
                error = true;
            }   
        }
        //y por ultimo volcamos los deportes en el fichero
        if(!error){
            FileWriter fw = null;
	    BufferedWriter bw = null;
            try{
               fw = new FileWriter(fichero,true);
               bw  = new BufferedWriter(fw);
               for(DeporteListaInscritos index: deportesDisponibles){
                 String deporte = index.getNombre() + ";" + Integer.toString(index.getNumeroJugadores());
                 try {                                                                
                     bw.write(deporte);
                     bw.write("\r\n");
                     bw.flush();
                 }catch(IOException e){
                     System.out.println("Error al escribir " + e.getMessage());
                 }
               }
                System.out.println("Se ha actualizado deportesDisponibles.txt");
            }catch(IOException e){
                System.out.println("Error al crear filewriter " + e.getMessage());
            }finally{
                   try {
		     if(fw != null){
			 fw.close();
	             }
		     if(bw != null){
		         bw.close();	}
		    }catch (IOException e) {
                         System.out.println(e.getMessage()); 
                    }
	   }
        }
    }
    
    
                               /***********************ACTUALIZACIONES DE FICHEROS DE DEPORTES TRAS CAMBIOS ******************************/ 

   // Se actualiza el fichero .txt del deporte pasado por argumento
    public static void actualizarAlumnos(String deporte) {

        
         //primero creamos la carpeta si no existe   
        File carpeta = new File("." + File.separator + "jornadasDeportivas");
        if (!carpeta.exists()) {     
            carpeta.mkdir();
        }
        
        //como queremos recoger los alumnos del arrayList ya sin el alumno que queremos borrar, evaluamos si el fichero existe, si existe lo borramos
        // y a continuación lo creamos de nuevo, ya vacio.
        File fichero = new File(carpeta + File.separator + deporte + ".txt");
        boolean error = false;
        if(fichero.exists()){
            fichero.delete();
        }
        if (!fichero.exists()) {
            try{
               fichero.createNewFile();       
            }catch(IOException e){
                System.out.println("problema al crear " + deporte + ".txt " + e.getMessage());
                error = true;
            }   
        }
        //y por ultimo volcamos los alumnos de ese deporte en el fichero
        if(!error){
            FileWriter fw = null;
	    BufferedWriter bw = null;
            try{
               fw = new FileWriter(fichero,true);
               bw  = new BufferedWriter(fw);
               for(AlumnoInscrito index: inscritos){
                   String linea;
                   if(index.getDeporte().equals(deporte)){
                     linea = generalMethods.createLine(index);
                       try {                                                                
                          bw.write(linea);
                          bw.write("\r\n");
                          bw.flush();
                       }catch(IOException e){
                          System.out.println("Error al escribir " + e.getMessage());
                       }
                   }
               }
               System.out.println("\nEl fichero "+ deporte + ".txt se ha actualizado satisfactoriamente");
            }catch(IOException e){
                System.out.println("Error al crear filewriter " + e.getMessage());
            }finally{
                   try {
		     if(fw != null){
			 fw.close();
	             }
		     if(bw != null){
		         bw.close();	}
		    }catch (IOException e) {
                         System.out.println(e.getMessage()); 
                    }
	   }
        }
    }
    
    //eliminación de un fichero deporte correspondiente al deporte que se elimina de las jornadas
    public static void borrarTxt(String deporte){
        
        File fichero = new File("." + File.separator + "jornadasDeportivas"+ File.separator + deporte + ".txt");
        if(fichero.exists()){
            fichero.delete();
        }
    }
}
