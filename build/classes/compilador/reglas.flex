package compilador;
import static compilador.Token.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java_cup.runtime.*;
%%
%class reglas //nombre de la clase JFlex que se va a crear
%unicode
%line         //Activa el contador de línea para yytext()
%column       //Activa el contador de columnas para yyext()
%type String   //Tipo de return que nos va a regresar yytext()
//%cup
//%cupsym content

%init{
%init}
%{

public String posicion(String token){

       String auxiliar = yytext();
int col = auxiliar.length();
boolean truncado = false;
         if (token.compareTo("Id")==0) {
        if (col>31) {
            auxiliar = auxiliar.substring(0, 31);
truncado = true;
        }
    }
col += yycolumn;
if(truncado){
//return "Token: "+ token +" -> " +"<" + auxiliar + "> " +" ***Id truncado*** "+ "Línea: " + yyline + " Columna: " + yycolumn +"-"+col;
return token+","+auxiliar+"*"+","+yyline+","+yycolumn +"-"+col;
}
else{
//return "Token: "+ token +" -> " +"<" + auxiliar + "> " + "Línea: " + yyline + " Columna: " + yycolumn +"-"+col;
return token+","+auxiliar+","+yyline+","+yycolumn +"-"+col;
}
      

}

%}
InputChar = [^\n\r]
SpaceChar = [\ \t]
LineChar = \n|\r|\r\n
//Zero = 0
DecInt = [0-9][0-9]*
HexInt = 0[xX][0-9a-fA-F]+
Integer = {DecInt} | {HexInt} //| {Zero}  
Exponent = [eE] [\+\-]? [0-9]+
Ident = [A-Za-z] [A-Za-z0-9_]*
constante = "true"|"false"
CChar = [^\'\\\n\r] | {EscChar}
SChar = [^\"\\\n\r] | {EscChar}
EscChar = \\[ntbrf\\\'\"] | {OctalEscape}
OctalEscape = \\[0-7] | \\[0-7][0-7] | \\[0-3][0-7][0-7]
%% 
void { return posicion("void"); }
int {return posicion("int");}
bool {return posicion("bool");}
string {return posicion("string");}
class {return posicion("class");}
interface {return posicion("interface");}
null {return posicion("null");}
this {return posicion("this");}
extends {return posicion("extends");}
implements {return posicion("implements");}
for {return posicion("for");}
while {return posicion("while");}
if {return posicion("if");}
else {return posicion("else");}
return {return posicion("return");}
break {return posicion("break");}
New {return posicion("New");}
NewArray {return posicion("New Array");}
constante {return posicion("Constante");}
({Integer})+"."({Integer})*({Exponent})? {return posicion("double");}
Float {return posicion("Float");}

"(" { return posicion("("); }
")" { return posicion(")"); }
"{" { return posicion("{"); }
"}" { return posicion("}"); }
"[" { return posicion("["); }
"]" { return posicion("]"); }
"[]" {return posicion("[]");}
"{}" {return posicion("{}");}
"()" {return posicion("()");}
"." {return posicion(".");}
"," {return posicion(",");}
";" {return posicion(";");}
"!" {return posicion("!");}
"||" {return posicion("||");}
"&&" {return posicion("&&");}
"!=" {return posicion("!=");}
"==" {return posicion("==");}
"=" {return posicion("=");}
">=" {return posicion(">=");}
">" {return posicion(">");}
"<=" {return posicion("<=");}
"<" {return posicion("<");}
"%" {return posicion("%");}
"/" {return posicion("/");}
"*" {return posicion("*");}
"-" {return posicion("-");}
"+" {return posicion("+");}
{SpaceChar} { }
{Ident} { return posicion("Id"); }
{Integer} { return posicion("Int"); }
"//"{InputChar}* { return "comentario";}
{LineChar} { }
"/*"~"*/"  { return "comentario";} 
"/*"[^*]+~[^/]+  { return posicion("Error de comentario");}
\"{SChar}*\" {return posicion("String");}
<<EOF>> { return "FIN"; }
. { return posicion("Error"); }