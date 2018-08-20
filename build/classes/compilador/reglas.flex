package compilador;
import static compilador.Token.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
%%
%class reglas //nombre de la clase JFlex que se va a crear
%unicode
%line         //Activa el contador de línea para yytext()
%column       //Activa el contador de columnas para yyext()
%type String   //Tipo de return que nos va a regresar yytext()


%init{

%init}

%{
//private ArrayList token
%}


D     = [0-9]+                /* Macro for Integer numbers */
O       = "o"[0-7]+             /* Macro for Octal numbers */
H         = "0x"[0-9|A-F]+        /* Macro for Hexadecimal numbers */
PClave = "void"|"double"
Id  = [a-zA-Z][a-zA-Z0-9_]* /* Macro for Identifiers */
S  = [ \n\t\r]+ //Espacios
%%

{D} {return Decimal.toString() + ", "+ yytext()+" Linea: "+ yyline+" Columna: " + yycolumn;}
{O} {return Octal.toString() + ", "+ yytext()+" Linea: "+ yyline+" Columna: " + yycolumn;}
{H} {return Hex.toString() + ", "+ yytext()+" Linea: "+ yyline+" Columna: " + yycolumn;}
{PClave} {return "PClave" + ", "+ yytext()+" Linea: "+ yyline+" Columna: " + yycolumn; }
{Id} {return Identifier.toString() + ", "+ yytext()+" Linea: "+ yyline+" Columna: " + yycolumn;}
{S} {return "";}
. {return Error.toString();}