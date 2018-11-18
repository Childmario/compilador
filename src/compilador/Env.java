/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;


import java.util.*;

public class Env  {
  static Env root = new Env(null);
  static Env top = root;
  static HashMap m_table = new HashMap();
  HashMap table;  
  Env prev;  

  public Env(Env p) {
	table = new HashMap();
	prev = p;     
  }

  public static int putClass(String c, String sc, Symb s) {
	if(root.table.containsKey(c)){ System.out.print("CLASS ENTRY: "+c);
	  					 push();
	  					 return 1;
	}
	if(sc == null){ root.table.put(c,s);
	  		    System.out.print("CLASS ENTRY: "+c);
	  		    top.table.put(c, s);
	  		    push();
	  		    return 0;
	}
	if(!root.table.containsKey(sc)){ System.out.print("CLASS ENTRY: "+c);
						   push();
						   return 2;
	}
	else { root.table.put(c,s);
		 System.out.print("CLASS ENTRY: "+c);
		 top.table.put(c, s);
		 push();
		 return 0;
	}    
  }

  public static boolean put(String name, t_simbolo s) {
	if(!top.table.containsKey(name)) { top.table.put(name,s);
	  					     System.out.println("  NEW IDENTIFIER: "+name+" -> CURRENT ENVIRONMENT: "+top);
                                                     if (s != null) {
                                                     if (s.tipo.compareTo("m")==0) {
                                                         if (!m_table.containsKey(name)) {
                                                             m_table.put(name, s);
                                                         }
                                                         
            }
                                                     }
	  					     return true;
	}
	return false;    
  }
  
  public static boolean tipos(String metodo, String var){
        if (m_table.containsKey(metodo)) {
            t_simbolo aux = (t_simbolo)m_table.get(metodo);
            if (top.table.containsKey(var)) {
                t_simbolo aux2 = (t_simbolo)top.table.get(var);
                if (aux.tipo_dato.compareTo(aux2.tipo_dato)==0) {
                    System.out.println("Si se puede operar, los parametros no cumplen con el método");
                }
                else{
                    System.out.println("No se puede operar, los parametros no cumplen con el método");
                }
            }
      }
      return false;
  }

  public static Symb get(String name) {
 	for(Env e = top; e != null; e = e.prev) { Symb found = (Symb)(e.table.get(name));
	  							if (found != null) return found;
	}
	return null;   
  }
  
  public static boolean get_name(String n){
        if (!top.table.containsKey(n)) {
          return false;
      }
      return true;
  }

  public static void push() {
	top = new Env(top);
	System.out.println(" -> CURRENT ENVIRONMENT: "+top);
  }

  public static void pop() {
	top = top.prev;
   	System.out.println(" -> CURRENT ENVIRONMENT: "+top);
  }

  public String toString() {
	if(prev != null) return prev.toString()+table;
	else return ""+table; 
  }

}