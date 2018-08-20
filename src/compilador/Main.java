/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.io.*;

/**
 *
 * @author Mario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String path = "C:/Users/Mario/Documents/NetBeansProjects/compilador/src/compilador/reglas.flex";
        reglas_lex(path);
    }
    
    public static void reglas_lex(String path){
    File input_reglas = new File (path);
    jflex.Main.generate(input_reglas);
    }
    
}
