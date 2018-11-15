/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.util.*;
import type.*;

public class Symb {

	Type type;
	Name ownerClass;
	boolean pub = false;

	public Symb(Type t, Name c, boolean p){
		type = t;
		ownerClass = c;
		pub = p;
	}

	public Type getType(){
		return type;
	}

	public Name getOwner(){
		return ownerClass;
	}


	public boolean isPublic(){
		return pub;
	}

	public String toString(){
		return "("+type+", "+ownerClass+", "+pub+")";
	}
}
