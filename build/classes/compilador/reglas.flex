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
//%type String   //Tipo de return que nos va a regresar yytext()
%cupsym sym
%cup
%full
%char
%public


%init{
%init}
%{


   
   private Symbol symhbol(int type){
      return new Symbol(type, yyline, yycolumn);
   }
   private Symbol symbhol(int type, Object value){
       return new Symbol(type, yyline, yycolumn, value);
   }

public String posiciocn(String token){

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
cbool = "true"|"false"
CChar = [^\'\\\n\r] | {EscChar}
SChar = [^\"\\\n\r] | {EscChar}
EscChar = \\[ntbrf\\\'\"] | {OctalEscape}
OctalEscape = \\[0-7] | \\[0-7][0-7] | \\[0-3][0-7][0-7]
%% 
<YYINITIAL>{
Print { return new Symbol(sym.zprint, yycolumn, yyline, yytext()); }
ReadInteger { return new Symbol(sym.zreadinteger, yycolumn, yyline, yytext()); }
ReadLine { return new Symbol(sym.zreadline, yycolumn, yyline, yytext()); }
Malloc { return new Symbol(sym.zmalloc, yycolumn, yyline, yytext()); }
void { return new Symbol(sym.zvoid, yycolumn, yyline, yytext()); }
int {return new Symbol(sym.zint, yycolumn, yyline, yytext());}
double {return new Symbol(sym.zdouble, yycolumn, yyline, yytext());}
bool {return new Symbol(sym.zbool, yycolumn, yyline, yytext());}
string {System.out.println("stringtipo");return new Symbol(sym.zstring, yycolumn, yyline, yytext());}
class {return new Symbol(sym.zclass, yycolumn, yyline, yytext());}
interface {return new Symbol(sym.zinterface, yycolumn, yyline, yytext());}
null {return new Symbol(sym.znull, yycolumn, yyline, yytext());}
this {return new Symbol(sym.zthis, yycolumn, yyline, yytext());}
extends {return new Symbol(sym.zextends, yycolumn, yyline, yytext());}
implements {return new Symbol(sym.zimplements, yycolumn, yyline, yytext());}
for {return new Symbol(sym.zfor, yycolumn, yyline, yytext());}
while {return new Symbol(sym.zwhile, yycolumn, yyline, yytext());}
if {return new Symbol(sym.zif, yycolumn, yyline, yytext());}
else {return new Symbol(sym.zelse, yycolumn, yyline, yytext());}
return {return new Symbol(sym.zreturn, yycolumn, yyline, yytext());}
break {return new Symbol(sym.zbreak, yycolumn, yyline, yytext());}
New {return new Symbol(sym.zNew, yycolumn, yyline, yytext());}
NewArray {return new Symbol(sym.zNewArray, yycolumn, yyline, yytext());}
({Integer})+"."({Integer})*({Exponent})? {return new Symbol(sym.zconstante_double, yycolumn, yyline, yytext());}
Float {return new Symbol(sym.zFloat, yycolumn, yyline, yytext());}
getByte {return new Symbol (sym.zgetbyte, yycolumn, yyline, yytext());} 
setByte {return new Symbol (sym.zsetbyte, yycolumn, yyline, yytext());}

"(" { return new Symbol(sym.para, yycolumn, yyline, yytext()); }
")" { return new Symbol(sym.parac, yycolumn, yyline, yytext()); }
"{" { return new Symbol(sym.lla, yycolumn, yyline, yytext()); }
"}" { return new Symbol(sym.llc, yycolumn, yyline, yytext()); }
"[" { return new Symbol(sym.coa, yycolumn, yyline, yytext()); }
"]" { return new Symbol(sym.coc, yycolumn, yyline, yytext()); }
"[]" {return new Symbol(sym.corcetes, yycolumn, yyline, yytext());}
"{}" {return new Symbol(sym.llaves, yycolumn, yyline, yytext());}
"()" {return new Symbol(sym.paren, yycolumn, yyline, yytext());}
"." {return new Symbol(sym.punto, yycolumn, yyline, yytext());}
"," {return new Symbol(sym.coma, yycolumn, yyline, yytext());}
";" {System.out.println("pyc"); return new Symbol(sym.pyc, yycolumn, yyline, yytext());}
"!" {return new Symbol(sym.admira, yycolumn, yyline, yytext());}
"||" {return new Symbol(sym.pipes, yycolumn, yyline, yytext());}
"&&" {return new Symbol(sym.andpers, yycolumn, yyline, yytext());}
"!=" {return new Symbol(sym.negar, yycolumn, yyline, yytext());}
"==" {return new Symbol(sym.dobleigual, yycolumn, yyline, yytext());}
"=" {return new Symbol(sym.igual, yycolumn, yyline, yytext());}
">=" {return new Symbol(sym.maigual, yycolumn, yyline, yytext());}
">" {return new Symbol(sym.mayor, yycolumn, yyline, yytext());}
"<=" {return new Symbol(sym.meigual, yycolumn, yyline, yytext());}
"<" {return new Symbol(sym.menor, yycolumn, yyline, yytext());}
"%" {return new Symbol(sym.porcentaje, yycolumn, yyline, yytext());}
"/" { System.out.println("dividido"); return new Symbol(sym.slash, yycolumn, yyline, yytext());}
"*" { System.out.println("por"); return new Symbol(sym.aster, yycolumn, yyline, yytext());}
"-" { System.out.println("menos"); return new Symbol(sym.guin, yycolumn, yyline, yytext());}
"+" { System.out.println("mas"); return new Symbol(sym.mas, yycolumn, yyline, yytext());}

{cbool} {return new Symbol(sym.cbool, yycolumn, yyline, yytext());}
{SpaceChar} { }
{Ident} {System.out.println("id"); return new Symbol(sym.id, yycolumn, yyline, yytext()); }
{Integer} {System.out.println(yytext()); return new Symbol(sym.zconst_int, yycolumn, yyline, yytext()); }
"//"{InputChar}* { System.out.println("Comentario");} //REVISAR
{LineChar} { }
"/*"~"*/"  { System.out.println("Comentario");}  //REVISAR
"/*"[^*]+~[^/]+  { System.out.println("error lexico");} //return new Symbol(sym.error, yycolumn, yyline, yytext());}
\"{SChar}*\" {return new Symbol(sym.zconst_string, yycolumn, yyline, yytext());}
//<<EOF>> { System.out.println("FIN"); } //REVISAR
}
. { System.out.println("error lexico"); } //return new Symbol(sym.error); }