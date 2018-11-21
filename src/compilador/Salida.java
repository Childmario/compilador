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
public class Salida {
    
    String nombre = "";
    t_simbolo simbolo = new t_simbolo();
    
    public Salida(String nombre, t_simbolo simbolo){
    
    this.nombre = nombre;
    this.simbolo = simbolo;
    
    }
    
}
