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
  
  public static void parametros(String id, Object formals){
  
      if (m_table.containsKey(id)) {
          t_simbolo aux = (t_simbolo)m_table.get(id);
          aux.vars = formals.toString();
          m_table.replace(id, aux);
      }
      
      
  }
  
  public static boolean tipos(String metodo, String var){
        if (m_table.containsKey(metodo)) {
            t_simbolo aux = (t_simbolo)m_table.get(metodo);
          ArrayList<String> firma = new ArrayList<>();//Arreglo que guarda la firma de métodos del metodo -> [var1, int], [var2, int]
          ArrayList<String> t_var = new ArrayList<>();//arreglo que guarda las variables que están siendo enviadas al metodo -> metodo(var1, var2)
          firma.addAll(Arrays.asList(aux.vars.split("-")));
          t_var.addAll(Arrays.asList(var.split(",")));
            if (t_var.size()==firma.size()) {//verifico si la firma del método y los parametros de la llamada al método tengan la misma longitud, sino, error.
                            for (int i = 0; i < firma.size(); i++) { //ciclo para recorrer la firma y los parametros enviados y comparar tipos.
                String variable_analizada = "";
                variable_analizada = firma.get(i);
                variable_analizada= variable_analizada.replace("[", "");//-> quito el [, enonces quedaría -> var1, int]
                variable_analizada= variable_analizada.replace("]", "");//-> quito el ], entonces quedaría -> var1, int
                ArrayList<String> tipo = new ArrayList<>();//arrgelo que guarda la firma de nuevo, pero limpia, sin caracteres basura
                tipo.addAll(Arrays.asList(variable_analizada.split(",")));//hago split por coma al array tipo.
                t_simbolo tipo_variable = (t_simbolo)top.table.get(t_var.get(i));//aquí obtengo la variable que se envía como parametro en la invocación y valido el tipo en las siguientes linea
                
                                if (tipo.get(1).compareTo(tipo_variable.tipo_dato)!=0) {
                                    String out = t_var.get(i);
                                    String out2 = tipo.get(i);
                                    System.out.println("El parámetro: "+ out + " no tiene al tipo de método necesario, se esperaba: "+out2);
                                }
                
            }
            }
            else{
                System.out.println("El método: "+metodo+" ha sido invocado sin la cantidad de parametros necesitados");
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