package de.unistuttgart.iaas.pse.ex07.p2;

import java.util.Scanner;

/**
 * test
 * 
 * 
 * testbranch
 * 
 * @author juliu
 *
 */
public class NumeralSystem {


	public static void main(String[] args) {

		printMenu();
	}

	public static void printMenu(){
		Scanner scanner = new Scanner(System.in);
		String dez;
		int base1, base2;

		//Die Nutzereingaben
		System.out.println("Welchen Wert wollen Sie umrechnen? ");
		dez = scanner.nextLine();

		System.out.println("welche Basis hat der Wert? ");
		base1 = scanner.nextInt();

		System.out.println("In welche Basis soll der Wert umgerechnet werden? ");
		base2 = scanner.nextInt();

		System.out.println(conv2Dez(dez, base1, base2));
	}

	public static String conv2Dez(String dez, int ersteBase, int zielBase){

		int x;
		//Wenn Eingabe numerischer Wert, dann direkt weiter, wenn nicht, dann umrechnen
		if(!dez.matches("[-+]?\\d*\\.?\\d+")){
			x = ueber10(dez, ersteBase);
		} else{
			x = Integer.parseInt(dez);
		}

		int z = 1;
		String endTerm = "";

		// Solange das Ergebnis der Division nicht null ist, wird weiter gerechnet und das Ergebnis
		//als String gespeichert
		while(z > 0){
			z = (x / zielBase);

			if(x % zielBase > 9){
				endTerm = endTerm + ueber10(x % zielBase);
			} else{
				endTerm = endTerm + x % zielBase;
			}

			x = z;
		}

		String umgekehrt = "";

		//Wird umgewandelt, da bisher von links nach rechts gerechnet wurde
		for ( int j = endTerm.length()-1; j >= 0; j-- ){
			umgekehrt += endTerm.charAt(j);
		}

		return umgekehrt;

	}

	public static int ueber10(String s , int b){

		String s1 = "";

		//String wird umgekehrt
		for ( int j = s.length()-1; j >= 0; j-- ){
			s1 += s.charAt(j);
		}

		char[] ch = s1.toCharArray();

		int r;
		int z = 0;

		//Wert wird umgerechnet
		for(int i = 0; i < s.length(); i++){

			r = (int) ((((int) ch[i] - 55)) * Math.pow(b, i));
			//			System.out.println("RRR: " +r+ "  " + ch[i]);
			//			System.out.println((int) ch[i] - 55 + "  " + Math.pow(b, i));
			z = z + r;

		}

		return z;
	}

	public static String ueber10(int i) {

		//Werte über 10 werden in Buchstaben umgerechnet
		i = i - 10;

		char ch = (char) (i + 65);

		return ch+"";
	}




}
