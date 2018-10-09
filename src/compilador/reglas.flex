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
//%cupsym sym
%cup
//%full
//%char


%init{
%init}
%{


   
   private Symbol symbol(int type){
      return new Symbol(type, yyline, yycolumn);
   }
   private Symbol symbol(int type, Object value){
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
Print { return symbol(sym.zprint); }
ReadInteger { return symbol(sym.zreadinteger); }
ReadLine { return symbol(sym.zreadline); }
Malloc { return symbol(sym.zmalloc); }
void { return symbol(sym.zvoid); }
int {return symbol(sym.zint);}
double {return symbol(sym.zdouble);}
bool {return symbol(sym.zbool);}
string {System.out.println("stringtipo");return symbol(sym.zstring);}
class {return symbol(sym.zclass);}
interface {return symbol(sym.zinterface);}
null {return symbol(sym.znull);}
this {return symbol(sym.zthis);}
extends {return symbol(sym.zextends);}
implements {return symbol(sym.zimplements);}
for {return symbol(sym.zfor);}
while {return symbol(sym.zwhile);}
if {return symbol(sym.zif);}
else {return symbol(sym.zelse);}
return {return symbol(sym.zreturn);}
break {return symbol(sym.zbreak);}
New {return symbol(sym.zNew);}
NewArray {return symbol(sym.zNewArray);}
({Integer})+"."({Integer})*({Exponent})? {return symbol(sym.zconstante_double);}
Float {return symbol(sym.zFloat);}
getByte {return symbol (sym.zgetbyte);} 
setByte {return symbol (sym.zsetbyte);}

"(" { return symbol(sym.para); }
")" { return symbol(sym.parac); }
"{" { return symbol(sym.lla); }
"}" { return symbol(sym.llc); }
"[" { return symbol(sym.coa); }
"]" { return symbol(sym.coc); }
"[]" {return symbol(sym.corcetes);}
"{}" {return symbol(sym.llaves);}
"()" {return symbol(sym.paren);}
"." {return symbol(sym.punto);}
"," {return symbol(sym.coma);}
";" {System.out.println("pyc"); return symbol(sym.pyc);}
"!" {return symbol(sym.admira);}
"||" {return symbol(sym.pipes);}
"&&" {return symbol(sym.andpers);}
"!=" {return symbol(sym.negar);}
"==" {return symbol(sym.dobleigual);}
"=" {return symbol(sym.igual);}
">=" {return symbol(sym.maigual);}
">" {return symbol(sym.mayor);}
"<=" {return symbol(sym.meigual);}
"<" {return symbol(sym.menor);}
"%" {return symbol(sym.porcentaje);}
"/" { System.out.println("dividido"); return symbol(sym.slash);}
"*" { System.out.println("por"); return symbol(sym.aster);}
"-" { System.out.println("menos"); return symbol(sym.guin);}
"+" { System.out.println("mas"); return symbol(sym.mas);}

{cbool} {return symbol(sym.cbool);}
{SpaceChar} { }
{Ident} {System.out.println("id"); return symbol(sym.id); }
{Integer} {System.out.println(yytext()); return symbol(sym.zconst_int); }
"//"{InputChar}* { System.out.println("Comentario");} //REVISAR
{LineChar} { }
"/*"~"*/"  { System.out.println("Comentario");}  //REVISAR
"/*"[^*]+~[^/]+  { return symbol(sym.error);}
\"{SChar}*\" {return symbol(sym.zconst_string);}
//<<EOF>> { System.out.println("FIN"); } //REVISAR
}
. { System.out.println("error lexico"); return symbol(sym.error); }