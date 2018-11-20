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

   static ArrayList<t_simbolo> simbolo = new ArrayList<>();

   public void sponch (String simbolo, String tipo) {
    t_simbolo o_simbolo = new t_simbolo(simbolo, tipo);
    this.simbolo.add(o_simbolo);
}
   
   private Symbol symhbol(int type){
      return new Symbol(type, yyline, yycolumn);
   }
   private Symbol symbhol(int type, Object value){
       return new Symbol(type, yyline, yycolumn, value);
   }



// <editor-fold defaultstate="collapsed" desc="Verificacion de longitud de id"> 
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
// </editor-fold>
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
Print {/*System.out.println("zprint"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext());*/ return new Symbol(sym.zprint, yycolumn, yyline, yytext()); }
ReadInteger { /*System.out.println("zreadinteger"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext());*/ return new Symbol(sym.zreadinteger, yycolumn, yyline, yytext()); }
ReadLine { /*System.out.println("zreadline"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext());*/ return new Symbol(sym.zreadline, yycolumn, yyline, yytext()); }
Malloc { System.out.println("zmalloc"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.zmalloc, yycolumn, yyline, yytext()); }
void { System.out.println("zvoid"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.zvoid, yycolumn, yyline, yytext()); }
int {/*System.out.println("zint"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext());*/ return new Symbol(sym.zint, yycolumn, yyline, yytext());}
double {System.out.println("zdouble"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.zdouble, yycolumn, yyline, yytext());}
bool {System.out.println("zbool"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.zbool, yycolumn, yyline, yytext());}
string {/*System.out.println("zstring"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext());*/return new Symbol(sym.zstring, yycolumn, yyline, yytext());}
class {sponch (yytext(), "zclass") ;/*System.out.println("zclass"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext());*/ return new Symbol(sym.zclass, yycolumn, yyline, yytext());}
interface {System.out.println("zinterface"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.zinterface, yycolumn, yyline, yytext());}
null {System.out.println("znull"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.znull, yycolumn, yyline, yytext());}
this {System.out.println("zthis"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.zthis, yycolumn, yyline, yytext());}
extends {System.out.println("zextends"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.zextends, yycolumn, yyline, yytext());}
implements { System.out.println("zimplements"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext());return new Symbol(sym.zimplements, yycolumn, yyline, yytext());}
for {System.out.println("zfor"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.zfor, yycolumn, yyline, yytext());}
while {System.out.println("while"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.zwhile, yycolumn, yyline, yytext());}
if {System.out.println("zif"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.zif, yycolumn, yyline, yytext());}
else {System.out.println("zelse"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.zelse, yycolumn, yyline, yytext());}
return {/*System.out.println("zreturn"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext());*/return new Symbol(sym.zreturn, yycolumn, yyline, yytext());}
break {System.out.println("zbreak"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext());return new Symbol(sym.zbreak, yycolumn, yyline, yytext());}
New { System.out.println("zNew"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.zNew, yycolumn, yyline, yytext());}
NewArray {System.out.println("zNewArray"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.zNewArray, yycolumn, yyline, yytext());}
({Integer})+"."({Integer})*({Exponent})? {System.out.println("zconstante_double"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.zconstante_double, yycolumn, yyline, yytext());}
//Float {System.out.println("zfloat"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.zFloat, yycolumn, yyline, yytext());}
GetByte {System.out.println("zgetbyte"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol (sym.zgetbyte, yycolumn, yyline, yytext());} 
SetByte {System.out.println("zsetbyte"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol (sym.zsetbyte, yycolumn, yyline, yytext());}

"(" {/*System.out.println("para"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext());*/ return new Symbol(sym.para, yycolumn, yyline, yytext()); }
")" {/*System.out.println("parac"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext());*/ return new Symbol(sym.parac, yycolumn, yyline, yytext()); }
"{" {/* System.out.println("lla"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext());*/ return new Symbol(sym.lla, yycolumn, yyline, yytext()); }
"}" {/* System.out.println("llc"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext());*/ return new Symbol(sym.llc, yycolumn, yyline, yytext()); }
"[" { System.out.println("coa"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.coa, yycolumn, yyline, yytext()); }
"]" { System.out.println("coc"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.coc, yycolumn, yyline, yytext()); }
"[]" {System.out.println("corcetes"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.corcetes, yycolumn, yyline, yytext());}
//"{}" {System.out.println("llaves"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.llaves, yycolumn, yyline, yytext());}
//"()" {/*System.out.println("paren"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext());*/ return new Symbol(sym.paren, yycolumn, yyline, yytext());}
"." {System.out.println("punto"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.punto, yycolumn, yyline, yytext());}
"," {/*System.out.println("coma"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext());*/ return new Symbol(sym.coma, yycolumn, yyline, yytext());}
";" {/*System.out.println("pyc"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext());*/ return new Symbol(sym.pyc, yycolumn, yyline, yytext());}
"!" {System.out.println("admira"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.admira, yycolumn, yyline, yytext());}
"||" {System.out.println("pipes"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.pipes, yycolumn, yyline, yytext());}
"&&" {System.out.println("andpers"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.andpers, yycolumn, yyline, yytext());}
"!=" {System.out.println("negar"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.negar, yycolumn, yyline, yytext());}
"==" {System.out.println("dobleigual"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.dobleigual, yycolumn, yyline, yytext());}
"=" {/*System.out.println("igual"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext());*/ return new Symbol(sym.igual, yycolumn, yyline, yytext());}
">=" {System.out.println("maigual"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.maigual, yycolumn, yyline, yytext());}
">" {System.out.println("mayor"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.mayor, yycolumn, yyline, yytext());}
"<=" {System.out.println("meigual"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext());return new Symbol(sym.meigual, yycolumn, yyline, yytext());}
"<" {System.out.println("menor"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.menor, yycolumn, yyline, yytext());}
"%" {System.out.println("porcentaje"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext());return new Symbol(sym.porcentaje, yycolumn, yyline, yytext());}
"/" { System.out.println("slash"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.slash, yycolumn, yyline, yytext());}
"*" { System.out.println("aster"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.aster, yycolumn, yyline, yytext());}
"-" { System.out.println("guin"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.guin, yycolumn, yyline, yytext());}
"+" { System.out.println("mas"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.mas, yycolumn, yyline, yytext());}

{cbool} {System.out.println("cbool"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); return new Symbol(sym.cbool, yycolumn, yyline, yytext());}
{SpaceChar} { }
{Ident} {sponch (yytext(), "String") ;/*System.out.println("id" +" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext());*/ return new Symbol(sym.id, yycolumn, yyline, yytext()); }
{Integer} {/*System.out.println("zconst_int"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext());*/ return new Symbol(sym.zconst_int, yycolumn, yyline, yytext()); }
"//"{InputChar}* { /*System.out.println("Comentario")*/;} //REVISAR
{LineChar} { }
"/*"~"*/"  { /*System.out.println("Comentario")*/;}  //REVISAR
"/*"[^*]+~[^/]+  { System.out.println("error lexico"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext());} //return new Symbol(sym.error, yycolumn, yyline, yytext());}
\"{SChar}*\" {/*System.out.println("zconst_string"+" Linea: " + yyline + " Columna: " + yycolumn +" Lexema: "+ yytext()); */return new Symbol(sym.zconst_string, yycolumn, yyline, yytext());}
//<<EOF>> { System.out.println("FIN"); } //REVISAR
}
. { System.out.println("error lexico"); } //return new Symbol(sym.error); }