/*
 Genera alumnos inscritos del deporte especificado. Útil para crear los ficheros para testeo.
Utiliza arrays de datos para cada uno  de los campos y los combina aleatoriamente.
Tiene en cuenta que cada par nombre+apellido debe ser único, no puede haber alumnos repetidos.
Se pide por teclado el deporte del que crear alumnos y el numero de alumnos que se quieren crear.
Tras confirmar, se borra cualquier dato anterior de ese deporte y se añaden los alumnos generados por la aplicación.
 */
package deportesstucom.sportMenuMethods;


import deportesstucom.AlumnoInscrito;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author usuario
 */
public class randomGeneratorMethods {
    
    public static void generarAlumnos(){
        
       System.out.println("==============================================");
       System.out.println("| EXTRA 2: GENERACION INTERACTIVA DE ALUMNOS |");
       System.out.println("==============================================");
       
           MetodosIO.leerDeportes();
           //Los arrays que utilizaremos para generar aleatorios.
           
           String[] nombreHombre = {
               "ANTONIO","JOSE","JUAN","FRANCISCO","MANUEL","DAVID","LUIS","JOSE ANTONIO","MIGUEL","JOSE LUIS","JAVIER","JESUS","ANGEL",
               "JAVIER","CARLOS","PEDRO","JOSE MANUEL","RAFAEL","ANGEL","FERNANDO","DANIEL","RAMON","FRANCISCO JAVIER",	"LUIS",	"ALBERTO",
               "ALEJANDRO","MIGUEL ANGEL","VICENTE","JOSE MARIA","JORGE","PABLO","SERGIO","ENRIQUE","ALBERTO","ANDRES","IGNACIO","JUAN JOSE",		
               "JOAQUIN","JUAN CARLOS","VICTOR","RAMON","DIEGO","VICENTE","EDUARDO","SANTIAGO","JUAN ANTONIO","RAUL","OSCAR","ANDRES","ALFONSO",
               "DIEGO","IVAN","EMILIO","RUBEN",	"JUAN MANUEL","ROBERTO","EDUARDO","ALVARO","JULIO","RICARDO","ADRIAN","SALVADOR","JAIME","ALFONSO","SALVADOR",
               "ROBERTO","JAIME","FRANCISCO JOSE","VICTOR","RICARDO","IGNACIO","EMILIO","JULIAN","GUILLERMO","JORDI","MARCOS","JULIO","JOAN","AGUSTIN",
               "MARIANO","TOMAS","CESAR","FELIX","FELIPE","ALFREDO","MARIO","JOSE MIGUEL","GONZALO","JOSE RAMON","SEBASTIAN","GUILLERMO","GABRIEL",
               "MARCOS","GREGORIO","MARIANO","DOMINGO","JOSEP","JOAN","GONZALO","ALFREDO","FELIPE","SEBASTIAN","XAVIER","JUAN FRANCISCO","ISMAEL","ANTONIO JOSE",
               "MOHAMED","HECTOR","GREGORIO","MARC","JOSE CARLOS","CRISTIAN","BENITO","CESAR","JOSE IGNACIO","JOSE ANGEL","NICOLAS","MARTIN","LORENZO",
               "VICTOR MANUEL","EUGENIO","CRISTOBAL","ESTEBAN","LUIS MIGUEL","JUAN LUIS","JOSE FRANCISCO","AITOR","SAMUEL","ALBERT","XAVIER","ARTURO"};
           
           String[] nombreMujer = { "MARIA","MARIA CARMEN","CARMEN","JOSEFA","ISABEL","MARIA DOLORES","ANA MARIA","FRANCISCA","DOLORES","MARIA PILAR",
               "ANTONIA","MARIA TERESA","ANA","MARIA ANGELES","CRISTINA","LAURA","MARIA JOSE","MARIA ISABEL","PILAR","CONCEPCION","MARTA","MARIA LUISA",
               "MERCEDES","MANUELA","TERESA","JUANA","ROSARIO","ROSA MARIA","MARIA JESUS","RAQUEL","ENCARNACION","ROSA","ELENA","BEATRIZ","MONTSERRAT",
               "SARA","LUCIA","NURIA","SILVIA","MARGARITA","JULIA","PATRICIA","MONICA","MARIA MAR","MARIA JOSEFA","SUSANA","SONIA","PAULA",
               "ANGELA","YOLANDA","ANGELES","ROCIO","ALICIA","SANDRA","INMACULADA","MARIA ROSARIO","IRENE","AMPARO","ANDREA","MARINA","ESTHER",
               "ANA ISABEL","MARIA MERCEDES","CONSUELO","EVA","CATALINA","NATALIA","MARIA ROSA","VERONICA","ALBA","LUISA","EMILIA","MARIA VICTORIA",
               "NOELIA","MARIA ANTONIA","MARIA CONCEPCION","EVA MARIA","VICTORIA","ANA BELEN","JOSEFINA","MARIA ELENA","CAROLINA","ESPERANZA","AURORA",
               "GLORIA","LORENA","MILAGROS","MARIA NIEVES","PURIFICACION","MIRIAM","INES","VICENTA","LIDIA","MARIA SOLEDAD","ASUNCION","MARIA LUZ",
               "OLGA","MAGDALENA","VIRGINIA","LOURDES"};
           
           String[] apellidos = { "GARCIA","GONZALEZ","RODRIGUEZ","FERNANDEZ","LOPEZ","MARTINEZ","SANCHEZ","PEREZ","GOMEZ","MARTIN","JIMENEZ","GIL",
               "RUIZ","HERNANDEZ","DIAZ","MORENO","MUNOZ","ALVAREZ","ROMERO","ALONSO","GUTIERREZ","NAVARRO","TORRES","DOMINGUEZ","VAZQUEZ","RAMOS",
               "RAMIREZ","SERRANO","BLANCO","MOLINA","MORALES","SUAREZ","ORTEGA","DELGADO","CASTRO","ORTIZ","RUBIO","MARIN","SANZ","NUNEZ","IGLESIAS",
               "MEDINA","GARRIDO","CORTES","CASTILLO","SANTOS","LOZANO","GUERRERO","CANO","PRIETO","MENDEZ","CRUZ","CALVO","GALLEGO","VIDAL","LEON",
               "MARQUEZ","HERRERA","PENA","FLORES","CABRERA","CAMPOS","VEGA","FUENTES","CARRASCO","DIEZ","CABALLER","REYES","NIETO","AGUILAR","PASCUAL",
                "SANTANA","HERRERO","LORENZO","MONTERO","HIDALGO","GIMENEZ","IBANEZ","FERRER","DURAN","SANTIAGO","BENITEZ","MORA","VICENTE","VARGAS",
               "ARIAS","CARMONA","CRESPO","ROMAN","PASTOR","SOTO","SAEZ","VELASCO","MOYA","SOLER","PARRA","ESTEBAN","BRAVO","GALLARDO","ROJAS","ALCARAZ" };

           char[] sexos = {'H','M'};  
           String[] nombreCursos = {"BAT","DAM","DAW","ASIR"};
           String[] cursos = {"1","2"};
           String[] grupos = {"T1","T2"};
                  

          boolean nuevaGen = false;
          do{
            //recogemos el tipo de deporte:
            String deporte = deporte();
            System.out.println(deporte);
            System.out.println("Ha escogido " + deporte + ". Se perderan todos los datos actuales de ese deporte.");
            System.out.println("Pulsa RETURN para confirmar o cualquier otra tecla para volver al menu inicial");
            if(!generalMethods.metodoContinuar()){
                nuevaGen = false;
            }
            else{
                int numeroGenerados = generalMethods.pedirEntero("Introduce el numero de alumnos a generar:"); //el numero de generados que introduce el usuario
                //arraylist para verificacion
                ArrayList<String> nombresCompletos = nombreCompleto();
               
                //eliminamos el .txt del deporte y todos los alumnos del arraylist
                borrarAlumnosDeporte(deporte);
                MetodosIO.borrarTxt(deporte);
                
                while(numeroGenerados > 0){
                    
                     //aleatorio del sexo que tendra el alumno:
                    char sexo = sexos[aleatorio(0,sexos.length-1)];
                   
                    boolean existe = false;
                    String nombre = null;
                    String nombreCompleto = null;
                    String apellidosCompletos = null;
                    do{
                        //aleatorio del nombre y apellidos. Se requiere verificar que no exista ya.
                        switch(sexo){
                            case 'H':
                               nombre = nombreHombre[aleatorio(0,nombreHombre.length-1)];
                               break;
                            case 'M':
                               nombre = nombreMujer[aleatorio(0,nombreMujer.length-1)];
                        }
                        String apellido1 = apellidos[aleatorio(0,apellidos.length-1)];
                        String apellido2 = apellidos[aleatorio(0,apellidos.length-1)];
                        apellidosCompletos = apellido1 + " "+ apellido2;

                        nombreCompleto = nombre + " " + apellidosCompletos;

                        for(String index: nombresCompletos){
                            if (nombreCompleto.equals(index)){
                                existe = true;
                                break;
                            }
                        }
                    }while(existe);
                    nombresCompletos.add(nombreCompleto);
                    String nombreCurso = nombreCursos[aleatorio(0,nombreCursos.length-1)];  //como vemos, el metodo de generacion del aleatorio devuelve directamente el indice del array
                    String curso = cursos[aleatorio(0,cursos.length-1)];
                    String grupo = grupos[aleatorio(0,grupos.length-1)];
                    String cursoDefinitivo = nombreCurso+curso+grupo;
                    int edad = aleatorio(18, 50);
                    //añadimos al arraylist de inscritos
                    MetodosIO.inscritos.add(new AlumnoInscrito(nombre, apellidosCompletos, cursoDefinitivo, sexo, edad, deporte));
                   
                    numeroGenerados--; //tras cada generacion de nuevo alumno, se resta uno
                }   
                //generalMethods.mostrarDatos();  //mostramos, testeo de que funciona
                //actualizamos el fichero txt del deporte
                MetodosIO.actualizarAlumnos(deporte);
                
                System.out.println("¿Deseas crear una nueva lista? PULSA RETURN o cualquier otra tecla para volver al menu inicial");
                if(generalMethods.metodoContinuar()){
                    nuevaGen = true;
                }
                else{
                    nuevaGen = false;
                }
            }
           }while(nuevaGen);
        
    } 
 
         
    //el metodo devuelve un ArrayList con todos los nombres completos de los alumnos y el deporte que practican.
    public static ArrayList<String> nombreCompleto(){
       
        ArrayList<String> datos = new ArrayList<>();
        for(AlumnoInscrito index: MetodosIO.inscritos){
            datos.add(index.getNombre() + " " + index.getApellidos());
        }
        return datos;
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
          
    //metodo al que se le pasa un numero y devuelve un int aleatorio entre 0 y el numero pasado menos 1
    public static int aleatorio(int minimo, int maximo){
        Random rand = new Random();
        int valor = rand.nextInt((maximo - minimo) + 1) + minimo;
        return valor;
    }
      
    
    
     //metodo para recoger el deporte del nuevo inscrito. Solo muestra los deportes existentes (si
    //se anula un deporte, entonces ya no se mostraria
    public static String deporte(){
        
         System.out.println("\n");
            System.out.println("============================");
            System.out.println("   Deportes del cual generar lista:");
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
}
