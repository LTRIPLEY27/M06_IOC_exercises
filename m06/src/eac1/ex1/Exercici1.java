/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/*package eac1.ex1;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author : Isabel Calzadilla
 * @Data : 24-09-2022
 * @Resum : Activitat 1, M06
 */
/*public class Exercici1 {

    /**
     * @param args the command line arguments
     */
   /* public static void main(String[] args) {

        //|| !(args[1].equalsIgnoreCase("e") || args[1].equalsIgnoreCase("l"))
        // THROWS A ERROR EXCEPTION WITH CODE 2 IF THE ARGUMENTS NOT ARE 3 OR 4  //     || ((args[2].length() != 1)  || !(args[2].equalsIgnoreCase("d") || args[2].equalsIgnoreCase("n") )    // || Integer.parseInt(args[3])< 1
        //if (args.length <= 2 || args.length >= 5 || !archivo.isDirectory()){

            /*File archivo = new File(args[0]); // --> LANZA EXCEPCION NULLPOINTER AL INDICAR UNA RUTA ERRADA
            boolean isDirectory = false;
            boolean isNoOk = false;
            boolean isNotOk = false;
            var countArgs = args.length;
            
            isDirectory = args.length <= 2 || args.length >= 5 || !archivo.isDirectory() || !(args[1].equalsIgnoreCase("e") || args[1].equalsIgnoreCase("l")) ? true : false;
         
            //(args[2].length() != 1 ? true
            //isNoOk = (args[2].length() != 1 && countArgs == 4  ? (Integer.parseInt(args[3]) < 1 ? true : false)  :  false ); 
            
            System.out.println(args[2].length());
            isNoOk =  args[2].length() != 1 ?  (countArgs == 4  ? (Integer.parseInt(args[3]) < 1 ? true : false) : false) : true;
            
            isNotOk =  !(args[2].equalsIgnoreCase("d") || args[2].equalsIgnoreCase("n"))  ? true : false;
            
            System.out.println(isDirectory && (isNoOk || isNotOk));
            if(isDirectory && (isNoOk || isNotOk)){  
                System.out.println(isDirectory || (isNoOk || isNotOk));
                System.err.println("Code error " + 2);
                System.exit(2);            
            } else {
      
        String letter = args[1].toUpperCase();
            switch (letter) {
            // example -->   "/home/ioc/prueba/" e f 1
                case "E" : 
                          esborrar(args);
                    break;
                case "L" :
                         llista(args);
                     break;
                default :
                    System.err.println("Code error " + 2);
                    System.exit(2);
                 }      
            }
        }
        
       public static  void esborrar(String ... vargs){
            
                 var counter = vargs.length;

                // UBICACI??N EN LA RUTA INDICADA COMO PARAMETRO 0
                
                File archivo = new File(vargs[0]); // --> LANZA EXCEPCION NULLPOINTER AL INDICAR UNA RUTA ERRADA
                FileFilter withOne = null;
            
                withOne = getTheLambda(vargs[2]);
                        File [] arrayOfFiles =  archivo.listFiles(withOne);

                        if (counter <= 3) {
                                var cont = 0;
                                System.out.println("Comen??a el proc??s d'esborrat");
                                for (var i : arrayOfFiles) {
                                    System.out.println("Fitxer:   " + i.getAbsolutePath() + "   esborrat amb ??xit");
                                    i.delete();
                                    cont ++;
                                }
                                System.out.println(cont  + "  fitxer/s esborrat/s");
                        }
                        else {
                            var i = 0;
                            var sizer = Integer.parseInt(vargs[3]);
                            
                            System.out.println("Comen??a el proc??s d'esborrat");
                            while(i < sizer){
                                    System.out.println("Fitxer:   " + arrayOfFiles[i].getAbsolutePath() + "   esborrat amb ??xit");
                                   arrayOfFiles[i].delete();
                                    i++;
                            }    
                            System.out.println(i  + "  fitxer/s esborrat/s");
                        }      
        }

        public static FileFilter getTheLambda(String var2){
            
                  FileFilter withOneCharacter = (File toDeleted) ->{
                        return toDeleted.getName().contains(var2) || toDeleted.getName().contains(var2.toUpperCase());
                  };
                  
                 return withOneCharacter;
        }
 
        
        public static void llista(String ... letter){
            
                File archivo = new File(letter[0]);
                var contF = 0;
                var contH = 0;
                String value = letter[2];
                switch(value.toUpperCase()){
                    case "N" :                     
                                for(var i : archivo.listFiles()){
                                        if(i.isFile()){                                      
                                                  if (i.isHidden()){
                                                    System.out.println(i.getName() + "  HIDDEN  " + i.length() + "B");
                                                 }
                                                  else {
                                                      System.out.println(i.getName() + " "+ i.length()+ " B" );
                                                        if(i.canWrite()){
                                                            contF++;
                                                              }
                                                        }
                                                  contH++;
                                             }
                                }       
                                System.out.println("Totals " +  contH + "  fitxers i " +  contF + " amb permis d'escriptura");
                        break;
                    case "D" :                          
                                for(var i : archivo.listFiles()){
                                      if(i.isDirectory()){                 
                                            if (i.isHidden()){
                                                    System.out.println("/" + i.getName() + "  HIDDEN  " + i.length() + "B");
                                            } else {
                                                System.out.println("/" + i.getName());
                                            }                                        
                                            contH++;
                                        }
                                      else if(i.isFile() ){
                                          System.out.println(i.getName() + " "+ i.length()+ " B" );
                                          contF++;
                                               if (i.isHidden()){
                                                    System.out.println(i.getName() + "  HIDDEN  " + i.length() + "B");
                                               }
                                      }                                  
                            }
                                System.out.println("Totals " +  contF + "  fitxers i " +  contH + " directoris amb permis d'escriptura");
                        break;
                }
        }

}
*/