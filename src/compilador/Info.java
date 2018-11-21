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
public class Info {
    
    String type;
    Object value;
    
    public  Info(String tipo, Object val)
    {
        type = tipo;
        value = val;        
    }
    
   public String getType()
   {
       return type;
   }
   
   public Object getValue()
   {
       return value;
   }
    
}
