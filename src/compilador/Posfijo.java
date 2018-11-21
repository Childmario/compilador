/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.util.Stack;

/**
 *
 * @author Mario
 */
public class Posfijo 
{
    String Operacion;
    
    public Posfijo(String OP)
    {
        Operacion = Post(OP);
    }
    
    public String Post(String Operacion)
    {
        String expr = depurar(Operacion);
        String[] arrayInfix = expr.split(" ");
        Stack < String > E = new Stack < String > (); //Pila entrada
        Stack < String > P = new Stack < String > (); //Pila temporal para operadores
        Stack < String > S = new Stack < String > (); //Pila salida
        
         for (int i = arrayInfix.length - 1; i >= 0; i--)
         {
            E.push(arrayInfix[i]);
         }
         while (!E.isEmpty()) 
         {
            switch (pref(E.peek()))
            {
                case 1:
                  P.push(E.pop());
                  break;
                case 3:
                case 4:
                  while(pref(P.peek()) >= pref(E.peek()))
                  {
                    S.push(P.pop());
                  }
                  P.push(E.pop());
                  break; 
                case 2:
                  while(!P.peek().equals("("))
                  {
                    S.push(P.pop());
                  }
                  P.pop();
                  E.pop();
                  break; 
                default:
                  S.push(E.pop()); 
            } 
      }
         String postfix = S.toString().replaceAll("[\\]\\[,]", "");
         return postfix;
    }
    
    public String getPostFix()
    {
        return Operacion;
    }
     private String depurar(String s)
     {
        s = s.replaceAll("\\s+", ""); //Elimina espacios en blanco
        s = "(" + s + ")";
        String simbols = "+-*/()";
        String str = "";

        //Deja espacios entre operadores
        for (int i = 0; i < s.length(); i++)
        {
            if (simbols.contains("" + s.charAt(i)))
            {
              str += " " + s.charAt(i) + " ";
            }
            else str += s.charAt(i);
        }
        return str.replaceAll("\\s+", " ").trim();
  }
     
       private static int pref(String op)
       {
            int prf = 99;
            if (op.equals("^")) prf = 5;
            if (op.equals("*") || op.equals("/")) prf = 4;
            if (op.equals("+") || op.equals("-")) prf = 3;
            if (op.equals(")")) prf = 2;
            if (op.equals("(")) prf = 1;
            return prf;        
        }
}
