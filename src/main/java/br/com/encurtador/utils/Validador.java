package br.com.encurtador.utils;

public class Validador {
	
	public static boolean valideStringVazia(String s) {
		if(s == null || s.isEmpty()) {
			return true;
		}
		return false;
	}

}
