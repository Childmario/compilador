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
  static ArrayList<Salida> t_salida = new ArrayList<>();
  String Operacion;
  HashMap table;  
  Env prev;  

  public Env(Env p) {
	table = new HashMap();
	prev = p;     
  }

  public static int putClass(String c, String sc, t_simbolo s) {
	if(root.table.containsKey(c)){ System.out.print("Clase creada: "+c);
	  					 push();
	  					 return 1;
	}
	if(sc == null){ root.table.put(c,s);
	  		    System.out.print("Clase creada: "+c);
	  		    top.table.put(c, s);
	  		    push();
                            s = new t_simbolo();
                            s.ambito = "***GLOBAL***";
                            s.tipo = "***CLASE***";
                            Salida v_class =new Salida(c, s);
                            t_salida.add(v_class);
	  		    return 0;
	}
	if(!root.table.containsKey(sc)){ System.out.print("Clase creada: "+c);
						   push();
						   return 2;
	}
	else { root.table.put(c,s);
		 System.out.print("Clase creada: "+c);
		 top.table.put(c, s);
		 push();
		 return 0;
	}    
  }

  public static boolean get_class(Object in){
      return root.table.containsKey(in.toString());
  }
  
  public static boolean put(String name, t_simbolo s) {
	if(!top.table.containsKey(name)) { 
            top.table.put(name,s);   
            s.ambito = top.prev.table.toString();
	    System.out.println("  NEW IDENTIFIER: "+name+" -> CURRENT ENVIRONMENT: "+top);
                          if (s != null) {
                                 if (s.tipo.compareTo("m")==0) {
                                           if (!m_table.containsKey(name)) {
                                                    m_table.put(name, s);
                                                    Salida out = new Salida(name, s);
                                                    t_salida.add(out);                                                    
                                                }
                                 }
                                 else{
                                        Salida out = new Salida(name, s);
                                        t_salida.add(out);
                                 }
                          }
                          return true;
        }
	return false;    
  }
  
  public static void parametros(String id, Object formals){//método que inserta los parámetros de su firma y los guarda en el hashmap m_table.
  
      if (m_table.containsKey(id)) {
          t_simbolo aux = (t_simbolo)m_table.get(id);
          aux.vars = formals.toString();
          m_table.replace(id, aux);
          for (int i = 0; i < t_salida.size(); i++) {
              if (t_salida.get(i).nombre.compareTo(id)==0) {
                  Salida element = new Salida(id, aux);
                  t_salida.set(i, element);
                  break;
              }
          }
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
                String tipo_aux = tipo.get(1);
                                if (top.table.containsKey(t_var.get(i))) {
                t_simbolo tipo_variable = (t_simbolo)top.table.get(t_var.get(i));//aquí obtengo la variable que se envía como parametro en la invocación y valido el tipo en las siguientes linea
                
                                if (tipo_aux.compareTo(tipo_variable.tipo_dato)!=0) {
                                    String out = t_var.get(i);
                                    String out2 = tipo.get(1);
                                    System.err.println("El parámetro: "+ out + " no tiene al tipo de método necesario, se esperaba: "+out2+ "para: "+aux.vars);
                                }                                    
                                }
                                else{
                                    ArrayList<String> v_tipos = new ArrayList<>();
                                    v_tipos.addAll(Arrays.asList(t_var.get(i).split("&")));
                                    int f = i+1;
                                    switch(v_tipos.get(1)){
                                        case "int":
                                            if (tipo_aux.compareTo("INT")!=0) {
                                    String out2 = tipo.get(1);
                                    System.err.println("El parámetro: "+ f + " no cumple con la firma, se esperaba: "+out2+" para: "+aux.vars);                                                
                                            }
                                            break;
                                        case "str":
                                            if (tipo_aux.compareTo("STR")!=0) {
                                    String out2 = tipo.get(1);
                                    System.err.println("El parámetro: "+ f + " no cumple con la firma, se esperaba: "+out2+" para: "+aux.vars);                                                    
                                                
                                            }                                            
                                            break;
                                        case "dob":
                                            if (tipo_aux.compareTo("DOB")!=0) {
                                    String out2 = tipo.get(1);
                                    System.err.println("El parámetro: "+ f + " no cumple con la firma, se esperaba: "+out2+" para: "+aux.vars);                                                   
                                            }                                            
                                            break;
                                        case "null":
                                            if (tipo_aux.compareTo("NULL")!=0) {
                                    String out2 = tipo.get(1);
                                    System.err.println("El parámetro: "+ f + " no cumple con la firma, se esperaba: "+out2+" para: "+aux.vars);                                                    
                                            }                                            
                                            break;
                                        case "bool":
                                            if (tipo_aux.compareTo("BOOL")!=0) {
                                    String out2 = tipo.get(1);
                                    System.err.println("El parámetro: "+ f + " no cumple con la firma, se esperaba: "+out2+" para: "+aux.vars);                                                  
                                            }                                            
                                            break;
                                        default:
                                            String out2 = tipo.get(i);
                                            System.err.println("El termino: "+f+" no cumple con lo que esperaba el parametro del método, se esperaba: "+out2);
                                            break;
                                    }
                                }

                
            }
            }
            else{
                System.err.println("El método: "+metodo+" ha sido invocado sin la cantidad de parametros necesitados");
            }
            

      }
      return false;
  }
  
  public static void mreturn(String tipo, Object regreso){
      
      if (regreso!=null) {
                if (top.table.containsKey(regreso)) {
          t_simbolo aux = (t_simbolo)top.table.get(regreso);
          if (tipo.toString().compareTo(aux.tipo_dato)!=0) {
              System.err.println("La instrucción return está tratando regresando: +"+aux.tipo_dato+", se esperaba: "+tipo);
          }
      }
      else{
           ArrayList<String> v_tipos = new ArrayList<>();
           v_tipos.addAll(Arrays.asList(regreso.toString().split("&")));
                    if (v_tipos.size()==1) {
                        System.err.println("La variable:"+v_tipos.get(0)+" no ha sido declarada en el ámbito actual");
                    }
                    else{
          switch(v_tipos.get(1)){
              case "int":
                  if (tipo.toString().compareTo("INT")!=0) {
                      System.err.println("La instrucción return está tratando regresando: INT, se esperaba: "+tipo);
                  }
                  break;
              case "str":
                  if (tipo.toString().compareTo("STR")!=0) {
                      System.err.println("La instrucción return está tratando regresando: STRING, se esperaba: "+tipo);
                  }                  
                  break;
              case "dob":
                  if (tipo.toString().compareTo("DOB")!=0) {
                      System.err.println("La instrucción return está tratando regresando: DOBLE, se esperaba: "+tipo);
                  }                  
                  break;
              case "null":
                  if (tipo.toString().compareTo("NULL")!=0) {
                      System.err.println("La instrucción return está tratando regresando: NULL, se esperaba: "+tipo);
                  }                  
                  break;
              case "bool":
                  if (tipo.toString().compareTo("BOOL")!=0) {
                      System.err.println("La instrucción return está tratando regresando: BOOL, se esperaba: "+tipo);
                  }                  
                  break;
                  default:
                      break;
          }
                    }
      }//fin else
      }
      else{}
  }

  public static Symb get(String name) {
 	for(Env e = top; e != null; e = e.prev) { Symb found = (Symb)(e.table.get(name));
	  							if (found != null) return found;
	}
	return null;   
  }
  
  public static boolean get_name(String n, String m){
      if (m.compareTo("m")==0) {
          if (!m_table.containsKey(n)) {
              return false;
          }
          else{
          return true;
          }
      }
      else{
          if (!top.table.containsKey(n)) {
          return false;
      }
          else{
              return true;
          }
      }
    
  }

  public static void push() {
	top = new Env(top);
	System.out.println(" -> CURRENT ENVIRONMENT: "+top);
  }

  public static void pop() {
	top = top.prev;
        t_simbolo aux = new t_simbolo();
        aux.ambito = top.toString();
        aux.tipo="ELIMINACION";
        aux.tipo_dato = "E";
        Salida out = new Salida("SALIENDO AMBITO", aux);
        t_salida.add(out);
   	System.out.println(" -> CURRENT ENVIRONMENT: "+top);
  }

  public String toString() {
	if(prev != null) return prev.toString()+table;
	else return ""+table; 
  }
  
  //Extras
      public static boolean getInterfaces(String I)
    {
        if(!root.table.containsKey(I))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
      
    public static void Validar(String Lvalue, String Valor)
    {
        if(top.table.containsKey(Lvalue))
        {
            Valor = Valor.replaceAll("&int", "");
            Valor = Valor.replace("&dob", "");
            Valor = Valor.replace("&str", "");
            t_simbolo aux = (t_simbolo) (top.table.get(Lvalue));
            if(Valor.contains("+") || Valor.contains("-") || Valor.contains("*") || Valor.contains("/"))
            {
                String Resultado = Resolve(Valor);

                if(Resultado.equals("-1"))
                {
                    System.out.println("  Error: No se puede realizar la operacion de asignacion para el valor de  " + Lvalue + " -> Ambito Actual: "+top);
                }
                else
                {               
                    if(aux.tipo_dato == "STR" )
                    {                    
                       System.out.println("  Error: No se puede asignar un valor numerico a un string " + Lvalue + " -> Ambito Actual: "+top);
                     }
                    else
                    {                     
                         String tipo;
                        if(RevFloat(Resultado))
                        {                        
                            tipo = "INT";
                        }
                        else
                        {
                             tipo = "DOB";
                         }
                        if(aux.tipo_dato == tipo)
                        {
                            aux.valor = Resultado;
                            top.table.replace(Lvalue, aux);
                            System.out.println("  Nuevo valor para el identificador "+Lvalue+ " -> Valor: "+Resultado + " -> Ambito Actual: "+top);
                            for (int i = 0; i < t_salida.size(); i++) {
                                if (t_salida.get(i).nombre.compareTo(Lvalue)==0) {
                                    Salida element = new Salida(Lvalue, aux);
                                    t_salida.set(i, element);
                                    break;
                                }
                            }
                        }
                        else
                        {
                           System.out.println("  Error: No se puede asignar un valor a la variable " +Lvalue + " -> " + aux.tipo_dato + " & " + tipo + " no son compatibles" + " -> Ambito Actual: "+top);   
                        }
                    }
                }
            }
            else
            {
                    boolean Variable = isNumeric(Valor);
                    String tipo;
                    if (Variable == true)
                    {
                        if(aux.tipo_dato == "STR" )
                        {
                            System.out.println("  Error: No se puede asignar un valor numerico a un string " + Lvalue + " -> Ambito Actual: "+top);
                        }
                        else
                        {
                            if(RevFloat(Valor))
                            {
                                tipo = "INT";
                            }
                            else
                            {
                                tipo = "DOB";
                            }
                            if(aux.tipo_dato == tipo)
                            {
                                aux.valor = Valor;
                                top.table.replace(Lvalue, aux);            
                                System.out.println("  Nuevo valor para el identificador "+Lvalue+ " -> Valor: "+Valor + " -> Ambito Actual: "+top);
                                                            for (int i = 0; i < t_salida.size(); i++) {
                                if (t_salida.get(i).nombre.compareTo(Lvalue)==0) {
                                    Salida element = new Salida(Lvalue, aux);
                                    t_salida.set(i, element);
                                    break;
                                }
                            }
                            }
                            else
                            {
                               System.out.println("  Error: No se puede asignar un valor a la variable " +Lvalue + " -> " + aux.tipo_dato + " & " + tipo + " no son compatibles" + " -> Ambito Actual: "+top);  
                            }
                        }
                    }
                    else
                    {
                        String tipoLval = aux.tipo_dato;
                        if(top.table.containsKey(Valor))
                        {
                            //Verificar tipos
                             t_simbolo tmp = (t_simbolo) (top.table.get(Valor));
                             if(tipoLval == tmp.tipo_dato)
                             {
                                 if(tmp.valor == null)
                                 {
                                     System.out.println("  Error: No se puede asignar un valor nulo a la variable " + Lvalue + " -> Ambito Actual: "+top);
                                 }
                                 else
                                 {
                                    aux.valor = tmp.valor;
                                    top.table.replace(Lvalue, aux.valor);
                                    System.out.println("  Nuevo valor para el identificador "+Lvalue+ " -> Valor: "+aux.valor + " -> Ambito Actual: "+top);
                            for (int i = 0; i < t_salida.size(); i++) {
                                if (t_salida.get(i).nombre.compareTo(Lvalue)==0) {
                                    Salida element = new Salida(Lvalue, aux);
                                    t_salida.set(i, element);
                                    break;
                                }
                            }                                    
                                 }
                             }
                             else
                             {
                                 System.out.println("  Error: No se puede asignar un valor a la variable " +Lvalue + " -> " + tipoLval + " & " + tmp.tipo_dato + " no son compatibles" + " -> Ambito Actual: "+top);
                             }

                        }
                        else
                        {
                            if(Valor.contains("\""))
                            {
                                if(aux.tipo_dato == "STR")
                                {
                                     aux.valor = Valor;
                                     top.table.replace(Lvalue, aux);            
                                     System.out.println("  Nuevo valor para el identificador "+Lvalue+ " -> Valor: "+Valor + " -> Ambito Actual: "+top);
                            for (int i = 0; i < t_salida.size(); i++) {
                                if (t_salida.get(i).nombre.compareTo(Lvalue)==0) {
                                    Salida element = new Salida(Lvalue, aux);
                                    t_salida.set(i, element);
                                    break;
                                }
                            }                                     

                                }
                                else
                                {
                                    System.out.println("  Error: No se puede asignar un valor String a la variable " + Lvalue + " -> Ambito Actual: "+top);
                                }
                            }
                            else
                            {
                                System.out.println("  Error: No se puede asignar el valor de una variable no existente a la variable " + Lvalue + " -> Ambito Actual: "+top);
                            }
                        }
                    }
            }
        }
        else
        {
            System.out.println("  Error: No se puede asignar realizar la asignacion de valor a " + Lvalue + " ya que no esta declarada -> Ambito Actual: "+top);
        }
    }      
    public static boolean isNumeric(String S)
    {
        try
        {
            double d = Double.parseDouble(S);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
        public static String ReturnVal(String S)
    {
        t_simbolo aux = (t_simbolo) (top.table.get(S));       
        return aux.valor.toString();
    }
    

    public static String Resolve(String S)
    {
        Posfijo Stage1 = new Posfijo(S);
        String Posfijo = Stage1.getPostFix();    
        Solver Stage2 = new Solver(Posfijo);
        return Stage2.getResult();
    }
    
   private static boolean RevFloat(String Result)
  {
      float N = Float.parseFloat(Result);
      
      if(N % 1 == 0)
      {
         return true;
      }
      else
      {
          return false;
      }
  }  
   
   public static String escritura(String size_id, int espacios, int cols){
       String nespacios = "";
       int tamano = size_id.length();
       int ciclos = espacios - (tamano+cols);
       for (int i = 0; i < ciclos; i++) {
           nespacios+=" ";
       }
       return nespacios;
   }
}