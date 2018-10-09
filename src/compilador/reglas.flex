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


%init{
%init}
%{

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
Print { return new Symbol(sym.zprint,yyline,yycolumn,yytext()); }
ReadInteger { return new Symbol(sym.zreadinteger,yyline,yycolumn,yytext()); }
ReadLine { return new Symbol(sym.zreadline,yyline,yycolumn,yytext()); }
Malloc { return new Symbol(sym.zmalloc,yyline,yycolumn,yytext()); }
void { return new Symbol(sym.zvoid,yyline,yycolumn,yytext()); }
int {return new Symbol(sym.zint,yyline,yycolumn,yytext());}
double {return new Symbol(sym.zdouble,yyline,yycolumn,yytext());}
bool {return new Symbol(sym.zbool,yyline,yycolumn,yytext());}
string {System.out.println("stringtipo");return new Symbol(sym.zstring,yyline,yycolumn,yytext());}
class {return new Symbol(sym.zclass,yyline,yycolumn,yytext());}
interface {return new Symbol(sym.zinterface,yyline,yycolumn,yytext());}
null {return new Symbol(sym.znull,yyline,yycolumn,yytext());}
this {return new Symbol(sym.zthis,yyline,yycolumn,yytext());}
extends {return new Symbol(sym.zextends,yyline,yycolumn,yytext());}
implements {return new Symbol(sym.zimplements,yyline,yycolumn,yytext());}
for {return new Symbol(sym.zfor,yyline,yycolumn,yytext());}
while {return new Symbol(sym.zwhile,yyline,yycolumn,yytext());}
if {return new Symbol(sym.zif,yyline,yycolumn,yytext());}
else {return new Symbol(sym.zelse,yyline,yycolumn,yytext());}
return {return new Symbol(sym.zreturn,yyline,yycolumn,yytext());}
break {return new Symbol(sym.zbreak,yyline,yycolumn,yytext());}
New {return new Symbol(sym.zNew,yyline,yycolumn,yytext());}
NewArray {return new Symbol(sym.zNewArray,yyline,yycolumn,yytext());}
({Integer})+"."({Integer})*({Exponent})? {return new Symbol(sym.zconstante_double,yyline,yycolumn,yytext());}
Float {return new Symbol(sym.zFloat,yyline,yycolumn,yytext());}
getByte {return new Symbol (sym.zgetbyte,yyline,yycolumn,yytext() );} 
setByte {return new Symbol (sym.zsetbyte,yyline,yycolumn,yytext() );}

"(" { return new Symbol(sym.para,yyline,yycolumn,yytext()); }
")" { return new Symbol(sym.parac,yyline,yycolumn,yytext()); }
"{" { return new Symbol(sym.lla,yyline,yycolumn,yytext()); }
"}" { return new Symbol(sym.llc,yyline,yycolumn,yytext()); }
"[" { return new Symbol(sym.coa,yyline,yycolumn,yytext()); }
"]" { return new Symbol(sym.coc,yyline,yycolumn,yytext()); }
"[]" {return new Symbol(sym.corcetes,yyline,yycolumn,yytext());}
"{}" {return new Symbol(sym.llaves,yyline,yycolumn,yytext());}
"()" {return new Symbol(sym.paren,yyline,yycolumn,yytext());}
"." {return new Symbol(sym.punto,yyline,yycolumn,yytext());}
"," {return new Symbol(sym.coma,yyline,yycolumn,yytext());}
";" {System.out.println("pyc"); return new Symbol(sym.pyc,yyline,yycolumn,yytext());}
"!" {return new Symbol(sym.admira,yyline,yycolumn,yytext());}
"||" {return new Symbol(sym.pipes,yyline,yycolumn,yytext());}
"&&" {return new Symbol(sym.andpers,yyline,yycolumn,yytext());}
"!=" {return new Symbol(sym.negar,yyline,yycolumn,yytext());}
"==" {return new Symbol(sym.dobleigual,yyline,yycolumn,yytext());}
"=" {return new Symbol(sym.igual,yyline,yycolumn,yytext());}
">=" {return new Symbol(sym.maigual,yyline,yycolumn,yytext());}
">" {return new Symbol(sym.mayor,yyline,yycolumn,yytext());}
"<=" {return new Symbol(sym.meigual,yyline,yycolumn,yytext());}
"<" {return new Symbol(sym.menor,yyline,yycolumn,yytext());}
"%" {return new Symbol(sym.porcentaje,yyline,yycolumn,yytext());}
"/" { System.out.println("dividido"); return new Symbol(sym.slash,yyline,yycolumn,yytext());}
"*" { System.out.println("por"); return new Symbol(sym.aster,yyline,yycolumn,yytext());}
"-" { System.out.println("menos"); return new Symbol(sym.guin,yyline,yycolumn,yytext());}
"+" { System.out.println("mas"); return new Symbol(sym.mas,yyline,yycolumn,yytext());}

{cbool} {return new Symbol(sym.cbool, yyline, yycolumn, yytext());}
{SpaceChar} { }
{Ident} {System.out.println("id"); return new Symbol(sym.id, yyline, yycolumn, yytext()); }
{Integer} {System.out.println(yytext()); return new Symbol(sym.zconst_int,yyline,yycolumn,yytext()); }
"//"{InputChar}* { System.out.println("Comentario");} //REVISAR
{LineChar} { }
"/*"~"*/"  { System.out.println("Comentario");}  //REVISAR
"/*"[^*]+~[^/]+  { return new Symbol(sym.error,yyline,yycolumn,yytext());}
\"{SChar}*\" {return new Symbol(sym.zconst_string);}
//<<EOF>> { System.out.println("FIN"); } //REVISAR
}
. { System.out.println("error lexico"); return new Symbol(sym.error,yyline,yycolumn,yytext()); }