/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.*;

/**
 *
 * @author Mario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String path = "src\\compilador\\reglas.flex";
        String path_sintactico = "C:\\Users\\Mario\\Documents\\NetBeansProjects\\compilador\\src\\compilador\\a_sintactico.cup";
        reglas_lex(path, path_sintactico, "x");
    }
    
    public static void reglas_lex(String path, String path_sintactico, String x){
    File input_reglas = new File (path);
    jflex.Main.generate(input_reglas);
        if (x.compareTo("x")==0) {
                String[] input_sintactico = {"-parser","Sintactico",path_sintactico};
        try {
            java_cup.Main.main(input_sintactico);
        } catch (Exception e) {
            System.out.println("Error creando el arhivo sintactico");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
        
        //movemos archivos a src
        boolean mvAS = moverArch("Sintactico.java");
        boolean mvSym = moverArch("sym.java");
            if (mvSym && mvAS) {
                System.exit(0);
            }
            System.out.println("Generado CUP");
        
        }

    
    
    }
    
    private static boolean moverArch (String archNombre){
    
        boolean efectuado = false;
        File arch = new File(archNombre);
        if (arch.exists()) {
            Path currentRelativePath = Paths.get("");
            String nuevoDir = currentRelativePath.toAbsolutePath().toString()
                    +File.separator + "src" + File.separator + "compilador"
                    + File.separator + arch.getName();
            if (arch.renameTo(new File(nuevoDir))) {
                efectuado = true;
            }
        }
        System.out.println(efectuado);
        return efectuado;
    }
    
}
