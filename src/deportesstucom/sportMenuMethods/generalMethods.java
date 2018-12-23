/*
 métodos de uso general a lo largo de la aplicacion
 
 */
package deportesstucom.sportMenuMethods;

import deportesstucom.AlumnoInscrito;
import static deportesstucom.sportMenuMethods.MetodosIO.inscritos;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author usuario
 */
public class generalMethods {
    
     //metodo para generar un menu con control de errores. Recibe un String[] con la cabecera y las diferentes opciones y devuelve la opcion escogida.
     public static int menu(String[] opciones){
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int opcion = 0;
        boolean error;
        do{ 
            System.out.println("\n");
            System.out.println("============================");
            System.out.println("    " + opciones[0]);
            System.out.println("============================");
            System.out.println("  Opciones:                 ");
         
            int i;
            for(i = 1; i < opciones.length; i++){ 
                System.out.println("        "+ i + ". "+ opciones[i] );
            }
           if(i == opciones.length){
                System.out.println("        "+ i + ". SALIR");
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
                if(!error && (opcion < 1 || opcion > opciones.length)){
                    System.out.println("Debes introducir una opcion valida.");
                    error = true;
                }
           } while (error);   
           return opcion;      
   }
      
     //metodo para generar un menu con control de errores. Recibe un String[] con la cabecera y las diferentes opciones 
     public static int menuSinExit(String[] opciones){
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int opcion = 0;
        boolean error;
        do{ 
            System.out.println("\n");
            System.out.println("============================");
            System.out.println("    " + opciones[0]);
            System.out.println("============================");
            System.out.println("  Opciones:                 ");
         
            int i;
            for(i = 1; i < opciones.length; i++){ 
                System.out.println("        "+ i + ". "+ opciones[i] );
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
                if(!error && (opcion < 1 || opcion >= opciones.length)){
                    System.out.println("Debes introducir una opcion valida.");
                    error = true;
                }
           } while (error);   
           return opcion;  
     }
     
      //metodo específico para cuando se requiere continuar pulsando RETURN, devuelve true si se pulsa RETURN
       public static boolean metodoContinuar(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cadena = "";
            try {
                cadena = br.readLine();
            } catch (IOException ex) {
                System.out.println("Error de entrada / salida.");
            }
        if(cadena.isEmpty()) return true;
        else return false;
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

    public static double pedirDouble(String texto) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         System.out.println("\n");
            System.out.println("============================");
            System.out.println("         " +texto );
            System.out.println("============================");
        double num = 0;
        boolean error;
        do {
            try {
                System.out.println(texto);
                num = Double.parseDouble(br.readLine());
                error = false;
            } catch (IOException ex) {
                System.out.println("Error de entrada / salida.");
                error = true;
            } catch (NumberFormatException ex) {
                System.out.println("Debes introducir un número.");
                error = true;
            }
        } while (error);    
        return num;
    }

    public static int pedirEntero(String texto) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         System.out.println("\n");
            System.out.println("============================");
            System.out.println("         " +texto );
            System.out.println("============================");
        int num = 0;
        boolean error;
        do {
            try {
                System.out.println(texto);
                num = Integer.parseInt(br.readLine());
                error = false;
            } catch (IOException ex) {
                System.out.println("Error de entrada / salida.");
                error = true;
            } catch (NumberFormatException ex) {
                System.out.println("Debes introducir un número entero.");
                error = true;
            }
        } while (error);   
        return num;
    }
    
    // Método que indica si un String es un entero o no
    public static boolean esEntero(String numero) {
        try {
            Integer.parseInt(numero);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
    
    //el método se utiliza para recoger el curso
    public static StringBuilder curso(){
       int opcion;
       int i;
       StringBuilder curso1 = new StringBuilder();
       //curso, año y grupo... presentamos menus por separado y tras seleccion concatenamos
       String[] nombreCurso = {"SELECCIONA EL GRADO DEL ALUMNO", "DAM","DAW","ASIR","BAT"};
       String[] year = {"SELECCIONA EL CURSO", "1","2"};
       String[] grupo = {"SELECCIONA EL GRUPO", "T1","T2","NO"}; 
       
       curso1.append(nombreCurso[generalMethods.menuSinExit(nombreCurso)]);
       curso1.append(year[generalMethods.menuSinExit(year)]);
       curso1.append(grupo[generalMethods.menuSinExit(grupo)]);
            
       return curso1;
    }
    
     //método para recoger el sexo del nuevo inscrito
    public static char sexo(){
        
        char sexo = ' ';
        String[] opciones = {"SELECCIONA EL SEXO", "Hombre", "Mujer"};
        int opcion = generalMethods.menuSinExit(opciones);
        switch(opcion){
            case 1: sexo = 'H'; break;
            case 2: sexo = 'M'; break;
        }
        return sexo;
    }
    
    //metodos que se utilizan en MetodosIO pero que no tiene que ver con lectura/escritura de ficheros
    
    //comparar dos alumnos, si tienen los mismos valores devuelve true
    public static boolean compareAlumnos(AlumnoInscrito alumno1, AlumnoInscrito alumno2){
        boolean iguales = true;
        String a = alumno1.getNombre() + " " + alumno1.getApellidos();
        String b = alumno2.getNombre() + " " + alumno2.getApellidos();
        if(!a.equals(b)) iguales = false;
        //System.out.println(iguales);
        return iguales;
    }
    
    //se recibe un String (una linea del archivo) y se devuelve un objeto tipo AlumnoInscrito
    public static AlumnoInscrito extractData(String linea, String nombreDeporte) {

        String[] datos = linea.split(";");
        String nombre = datos[0];
        String apellidos = datos[1];
        String curso = datos[2];
        char sexo = datos[3].charAt(0);
        int edad = Integer.parseInt(datos[4]);

        AlumnoInscrito alumno = new AlumnoInscrito(nombre, apellidos, curso, sexo, edad, nombreDeporte);
        return alumno;
    }

    //se recibe un AlumnoInscrito y se devuelve un String con los datos del alumno en una linea con los
    //campos separados por ';'
    public static String createLine(AlumnoInscrito a) {
        String linea = a.getNombre() + ";" + a.getApellidos() + ";" + a.getCurso() + ";" + a.getSexo() + ";" + a.getEdad();
        return linea;
    }

    //test para ver si funciona la lectura de ficheros (solo operativo para testear)
    public static void mostrarDatos() {

        int num = inscritos.size();   //num sera el numero de alumnos inscritos
        for (int i = 0; i < num; i++) {
            //recorremos el arraylist
            AlumnoInscrito alumno = inscritos.get(i);  //recorremos iterativamente la lista   
            System.out.println("Nombre: " + alumno.getNombre());
            System.out.println("Apellidos: " + alumno.getApellidos());
            System.out.println("Curso: " + alumno.getCurso());
            System.out.println("Sexo: " + alumno.getSexo());
            System.out.println("Edad: " + alumno.getEdad());
            System.out.println("Deporte: " + alumno.getDeporte());
        }
    }
    
}
