/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deportesstucom;

import deportesstucom.sportMenuMethods.*;
import java.util.Arrays;

/**
 *
 * @Francisco Moreno Alcaraz (DAM2T2)
 */
public class DeportesStucom {

    public static void main(String[] args) {
        
       
        //dado que a lo largo de la aplicacion ingresaremos información desde menus de opciones, la practica general consistirá en
        //enviar un array de String al método encargado de generarlo. 
        int opcion = 0;
        String[] opciones = {"DEPORTES STUCOM", "Registrar inscripción de un alumno", "Consultar los alumnos que hay inscritos de un curso determinado",
                             "Consultar que deportes no podrían llevarse a cabo porque tienen alguna incidencia", "Eliminar una inscripción de un alumno",
                             "Ver el listado de todas las inscripciones , agrupadas por deporte y con los alumnos ordenados alfabéticamente",
                             "Anular un deporte de las jornadas o introducir uno nuevo", "EXTRA 1: Cambiar un alumno de un deporte inscrito a otro",
                             "EXTRA 2: Generador interactivo de ficheros txt de datos de alumnos"};
        do{
            //al iniciar el menu, evaluamos si el ArrayList con alumnos inscritos (que se encuentra en MetodosIO) está vacio, y si lo está cargamos los datos
            //existentes en los ficheros .txt
            if(MetodosIO.inscritos.isEmpty()){
                MetodosIO.leerFicheros();
            }
                //leemos los deportes disponibles en deportesDisponibles.txt
                MetodosIO.leerDeportes();
                
                 /*SE DEJA COMENTADO PARA TESTEO PROFESOR. damos persistencia a los deportes disponibles en las jornadas. Dado que al testear esta aplicación el profesor evaluador podría eliminar
                  el fichero deportesDisponibles.txt, donde se guardan los deportes, lo que hacemos por precaución aquí es crear (en caso de que no exista ese fichero)
                  crearlo con los deportes por defecto que se piden en la actividad. La aplicación siempre da prioridad a los
                  deportes dados de alta en ese fichero deportesDisponibles.txt
                 
                if(MetodosIO.deportesDisponibles.isEmpty()){
                    DeporteListaInscritos a = new DeporteListaInscritos("futbol", 22);
                    DeporteListaInscritos b = new DeporteListaInscritos("futbol sala", 10);
                    DeporteListaInscritos c = new DeporteListaInscritos("basquet", 10);
                    DeporteListaInscritos d = new DeporteListaInscritos("badminton", 2);
                    DeporteListaInscritos e = new DeporteListaInscritos("voley playa", 4);
                    MetodosIO.deportesDisponibles.addAll(Arrays.asList(a,b,c,d,e));
                    MetodosIO.actualizarDeportes();
                } */
            
            
            //a continuación desplegamos el menu de opciones:
            opcion = generalMethods.menu(opciones);
            switch(opcion){
                case 1:
                    inscripcionMethods.nuevaInscripcion();
                    break;
                case 2:
                    inscripcionCursoMethods.getAlumnosPorCurso();
                    break;
                case 3:
                    noSportsAllowedMethods.showNotAllowedSports();
                    break;
                case 4:
                    inscriptionDeleteMethods.borrarAlumno();
                    break;
                case 5:
                    inscriptionListMethods.listaAlumnosDeporte();
                    break;
                case 6:
                   sportDeleteMethods.opcionesDeporte();
                   break;
                case 7:
                    changeSportsMethods.cambiarAlumnoDeporte();  //función extra para cambiar a un alumno de un deporte a otro
                    break;
                case 8:
                    randomGeneratorMethods.generarAlumnos();     //función extra 2 para generar interactivamente listados de alumnos
            }    
        }while(opcion != opciones.length);
    
  }
      
}

    
    

