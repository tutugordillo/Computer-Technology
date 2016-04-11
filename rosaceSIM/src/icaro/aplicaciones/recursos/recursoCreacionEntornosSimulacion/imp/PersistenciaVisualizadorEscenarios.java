/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.recursos.recursoCreacionEntornosSimulacion.imp;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 *
 * @author FGarijo
 */
public class PersistenciaVisualizadorEscenarios {
    public PersistenciaVisualizadorEscenarios(){
        
    }
    public void guardarInfoEscenarioSimulacion(String rutaFicheroInfoPersistencia,EscenarioSimulacionRobtsVictms escenario){
         Serializer serializer = new Persister();
         String identFichero = escenario.getIdentEscenario();
         try {
              File dirFicherosPersistencia = new File(rutaFicheroInfoPersistencia);
                if(!dirFicherosPersistencia.exists()){
                    dirFicherosPersistencia.mkdir();
//                    rutaFicheroInfoPersistencia= dirFicherosPersistencia.getAbsolutePath()+"\\";
                }
//             File result = new File(rutaFicheroInfoPersistencia+identFichero+".xml");

          serializer.write(escenario, new File(rutaFicheroInfoPersistencia+identFichero+".xml"));
          
          System.out.println("En el fichero   : "+ rutaFicheroInfoPersistencia+identFichero);
          System.out.println("Se va a guardar  : "+ escenario.toString());
          
          
        
        } catch (Exception e) { // catches ANY exception
            e.printStackTrace();
        }    
     }
     public boolean  eliminarEscenarioSimulacion(String rutaFicheroInfoPersistencia){
         try {
              File dirFicherosPersistencia = new File(rutaFicheroInfoPersistencia);
                if(!dirFicherosPersistencia.exists()){
                     System.out.println("El fichero   : "+ rutaFicheroInfoPersistencia+ " No existe " );
//                    rutaFicheroInfoPersistencia= dirFicherosPersistencia.getAbsolutePath()+"\\";
                    
                }else{
                    //confirmar eliminacion y si lo confirma eliminar
                    dirFicherosPersistencia.delete();
                    return true;
                }
         }catch (Exception e) { // catches ANY exception
                e.printStackTrace();
        }  
         return false;
     }
     public EscenarioSimulacionRobtsVictms obtenerInfoEscenarioSimulacion(String rutaFicheroInfoPersistencia){
         try {
              File ficheroEscenario = new File(rutaFicheroInfoPersistencia);
                if(!ficheroEscenario.exists()){
//                    dirFicherosPersistencia.mkdir();
//                    rutaFicheroInfoPersistencia= dirFicherosPersistencia.getAbsolutePath()+"\\";
                System.out.println("El fichero   : "+ rutaFicheroInfoPersistencia+ " No existe " );
             
                }else {
                   Serializer serializer = new Persister();
                return   serializer.read(EscenarioSimulacionRobtsVictms.class,ficheroEscenario, false);
                    
                }   
                }catch (Exception e) { // catches ANY exception
        e.printStackTrace();
    }
         return null;
     }
     public HashSet obtenerIdentsEscenarioSimulacion(String rutaDirectorioInfoPersistencia){
         
         HashSet conjIdentsFicheros = new HashSet<String>();
         try {
              File directorioEscenario = new File(rutaDirectorioInfoPersistencia);
                if(directorioEscenario.exists()){
                    File[] ficheros = directorioEscenario.listFiles();
                    for (int x=0;x<ficheros.length;x++){
                    System.out.println(ficheros[x].getName());
                    conjIdentsFicheros.add(x);
                    } return conjIdentsFicheros;
                }else return null;
     }catch (Exception e) { // catches ANY exception
        e.printStackTrace();
    }   return null;
}
}

