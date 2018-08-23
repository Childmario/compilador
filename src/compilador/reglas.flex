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

public String posicion(String token){

      return "Token: "+ token +" -> " +"<" + yytext() + "> " + "Línea: " + yyline + " Columna: " + yycolumn;

}

%}
InputChar = [^\n\r]
SpaceChar = [\ \t]
LineChar = \n|\r|\r\n
Zero = 0
DecInt = [1-9][0-9]*
OctalInt = 0[0-7]+
HexInt = 0[xX][0-9a-fA-F]+
Integer = ( {Zero} | {DecInt} | {OctalInt} | {HexInt} )[lL]?
Exponent = [eE] [\+\-]? [0-9]+
Float1 = [0-9]+ \. [0-9]+ {Exponent}?
Float2 = \. [0-9]+ {Exponent}?
Float3 = [0-9]+ \. {Exponent}?
Float4 = [0-9]+ {Exponent}
Float = ( {Float1} | {Float2} | {Float3} | {Float4} ) [fFdD]? |
[0-9]+ [fFDd]
Ident = [A-Za-z_$] [A-Za-z_$0-9]*
CChar = [^\'\\\n\r] | {EscChar}
SChar = [^\"\\\n\r] | {EscChar}
EscChar = \\[ntbrf\\\'\"] | {OctalEscape}
OctalEscape = \\[0-7] | \\[0-7][0-7] | \\[0-3][0-7][0-7]
%% 
void { return posicion("void"); }
int {return posicion("int");}
double {return posicion("double");}
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

"(" { return "("; }
")" { return ")"; }
"{" { return "{"; }
"}" { return "}"; }
"[" { return "["; }
"]" { return "]"; }
"[]" {return "[]";}
"{}" {return "[]";}
"()" {return "()";}
"." {return ".";}
"," {return ",";}
";" {return ";";}
"!" {return "!";}
"||" {return "||";}
"&&" {return "&&";}
"!=" {return "!=";}
"==" {return "==";}
"=" {return "=";}
">=" {return ">=";}
">" {return ">";}
"<=" {return "<=";}
"<" {return "<";}
"%" {return "%";}
"/" {return "/";}
"*" {return "*";}
"-" {return "-";}
"+" {return "+";}
{SpaceChar} { }
{Ident} { return posicion("Id"); }
{Integer} { return posicion("Int"); }
"//"{InputChar}* { return posicion("comentario");}
{LineChar} { }
"/*"~"*/"  { return "comentario";} 
<<EOF>> { return "FIN"; }
. { return posicion("Error"); }