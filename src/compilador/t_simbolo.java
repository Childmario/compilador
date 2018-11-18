/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

/**
 *
 * @author Mario
 */
public class t_simbolo {
    String tipo; //si es variable, funcion, clase, objeto.
    String tipo_dato;
    String valor;
public t_simbolo (){
    
    this.valor = "NA";
    this.tipo = "NA";
    this.tipo_dato = "NA";
}

public t_simbolo(String simbolo, String tipo){

    this.tipo = tipo;
    
}

public t_simbolo(String simbolo,String tipo_dato, String tipo){

    this.valor = simbolo;
    this.tipo = tipo;
    this.tipo_dato = tipo_dato;
    
}
   
    
}
