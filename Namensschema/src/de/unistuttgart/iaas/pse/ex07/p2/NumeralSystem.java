package de.unistuttgart.iaas.pse.ex07.p2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Diese Klasse bietet Methoden, die das Umrechnen von Zahlen in den Zahlensystemen von 1 bis 16
 * ermöglichen
 * 
 * @author Langer, 3255917, st149962@stud.uni-stuttgart.de
 * @author Zouboulis, 3230893, st148316@stud.uni-stuttgart.de
 * @author Honecker, 2813091, st148147@stud.uni-stuttgart.de  
 */
public class NumeralSystem {


	public static void main(String[] args) {
		printMenu();	
	}

	/**
	 * Diese Methode liest die Nutzereingaben ein
	 */
	public static void printMenu(){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String dez;
		int base1 = 0, base2 = 0;

		//Die Nutzereingaben

		System.out.println("welche Basis hat der Ausgangswert? ");
		String s1 = scanner.nextLine();
		if(s1.equals("0")){
			System.exit(0);
		}
		if(!pruefeInputBase(s1)){
			System.err.println("Bitte geben Sie einen gültigen Wert ein!");
			printMenu();
		}


		System.out.println("Welchen Wert wollen Sie umrechnen? ");
		dez = scanner.nextLine();
		dez = dez.toUpperCase();		

		System.out.println("In welche Basis soll der Wert umgerechnet werden? ");
		String s2 = scanner.nextLine();

		if(pruefeInputBase(s1) && pruefeInputBase(s2)){
			base1 = Integer.valueOf(s1);
			base2 = Integer.valueOf(s2);

			if(pruefeInputDez(dez, base1, base2)){
				System.out.println(zuweisung(dez, base1, base2));
			} else{
				System.err.println("Bitte geben Sie einen gültigen Wert ein!");
				printMenu();
			}
		} else{

			System.err.println("Bitte geben Sie einen gültigen Wert ein!");
			printMenu();
		}
	}




	/**
	 * Diese Methode startet auf Grundlage der Nutzereingaben die
	 * dementsprechenden Methoden an
	 * 
	 * @param dez (String) Nutzereingabe für die umzurechnende Zahl
	 * @param base1 (int) Nutzereingabe für das Ausgangszahlensystem
	 * @param base2 (int) Nutzereingabe für das Zielzahlensystem
	 * @return (String) der errechnete Wert
	 */
	public static String zuweisung(String dez, int base1, int base2) {

		if(base1 == base2){
			return dez;
		}

		if(base2 == 10){
			return toDez(dez, base1) + "";
		}

		if(base1 == 10){
			return toFinal(Integer.valueOf(dez), base2) + "";
		}

		return toFinal(toDez(dez, base1), base2);
	}

	/**
	 * Wandelt eine Zahl beliebigem Zahlensystems in das Dezimalsystem um
	 * 
	 * @param dez (String) Nutzereingabe für die umzurechnende Zahl
	 * @param ersteBase (int) Nutzereingabe für das Ausgangszahlensystem
	 * @return (int) der Wert im Dezimalsystem 
	 */
	public static int toDez(String dez, int ersteBase){

		dez = umkehren(dez);

		int t;
		int summe = 0;

		for(int i = 0; i < dez.length(); i++){
			if(Character.isLetter(dez.charAt(i))){
				t = inZahl(dez.charAt(i));
			} else {
				t = dez.charAt(i) - 48;
			}			
			summe = (int) (summe + (t * Math.pow(ersteBase, i)));
		}
		return summe;

	}

	/**
	 * Wandelt eine Zahl aus dem Dezimalsystem in ein beliebiges Zahlensystem um
	 * 
	 * @param b (int) die Zahl im Dezimalsystem
	 * @param zielBase (int) das Zielzahlensystem
	 * @return (String) der Wert im Zielzahlensystem
	 */
	public static String toFinal(int b, int zielBase){

		int h = -1;
		String s = "";

		while(h != 0){
			h = b / zielBase;	

			if(b % zielBase > 9){
				s = s + inZeichen(b % zielBase);
			} else{
				s = s + b % zielBase + "";
			}

			b = h;
		}

		return umkehren(s);

	}

	/**
	 * Überprüft die Nutzereingabe des Ausgangswertes auf Richtigkeit
	 * 
	 * @param dez (String) Nutzereingabe für die umzurechnende Zahl
	 * @param base1 (int) Nutzereingabe für das Ausgangszahlensystem
	 * @param base2 (int) Nutzereingabe für das Zielzahlensystem
	 * @return (boolean) Ist die Nutzeringabe korrekt
	 */
	public static boolean pruefeInputDez(String dez, int base1, int base2) {

		char[] ch = dez.toCharArray();
		Integer[] in = new Integer[dez.length()];

		for(int i = 0; i < dez.length(); i++){
			if(Character.isLetter(ch[i])){
				in[i] = inZahl(dez.charAt(i));
			} else{
				in[i] = ch[i] - 48;
			}
		}
		Arrays.sort(in);
		Arrays.sort(in, Collections.reverseOrder());

		if(in[0] < base1){
			return true;
		} else{
			return false;
		}
	}


	/**
	 * Überprüft die Nutzereingabe der Basis auf Richtigkeit
	 * 
	 * @param s (String) Die Nutzereingabe 
	 * @return (boolean) Ist die Nutzereingabe korrekt
	 */
	public static boolean pruefeInputBase(String s){

		for(int i = 0; i < s.length(); i++){
			if(Character.isLetter(s.charAt(0))){
				return false;
			}
		}

		if(Integer.valueOf(s) > 1 && Integer.valueOf(s) < 17){
			return true;
		} else{
			return false;
		}

	}

	/**
	 * Dreht einen String um 
	 * 
	 * @param s (String) der umzuwandelnde String
	 * @return (String) der umgewandelte String
	 */
	public static String umkehren(String s){

		String s1 = "";

		for ( int j = s.length()-1; j >= 0; j-- ){
			s1 += s.charAt(j);
		}

		return s1;
	}

	/**
	 * Wandelt ein Zeichen in die entsprechende Zahl um
	 *  
	 * @param c (char) das umzuwandelnde Zeichen
	 * @return (int) das umgewandelte Zeichen
	 */
	public static int inZahl(char c){

		int i = (int) (c - 55);

		return i;

	}

	/**
	 * Wandelt eine Zahl größer 9 in das entsprechende Zeichen um
	 * 
	 * @param i (int) die umzuwandelnde Zahl
	 * @return (String) die umgewandelte Zahl
	 */
	public static String inZeichen(int i) {

		i = i - 10;

		char ch = (char) (i + 65);

		return ch+"";
	}

}
