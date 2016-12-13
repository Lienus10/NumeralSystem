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
		//				System.out.println(toFinal(190, 12));
		//				System.out.println(inZeichen(13));
		printMenu();
	}

	public static void printMenu(){
		@SuppressWarnings("resource")
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

		System.out.println(zuweisung(dez, base1, base2));
		//		System.out.println(conv2Dez(dez, base1, base2));
	}


	public static String zuweisung(String dez, int base1, int base2) {

		if(base1 == base2){
			return dez;
		}

		if(base2 == 10){
			return toDez(dez, base1) + "";
		}

		if(base1 == 10){
			return toFinal(Integer.parseInt(dez), base2) + "";
		}

		return toFinal(toDez(dez, base1), base2);
	}

	public static int toDez(String dez, int ersteBase){

		dez = umkehren(dez);

		int t;
		int summe = 0;

		for(int i = 0; i < dez.length(); i++){
			if(Character.isLetter(dez.charAt(0))){
				t = inZahl(dez.charAt(i));
			} else {
				t = dez.charAt(i) - 48;
			}			
			summe = (int) (summe + (t * Math.pow(ersteBase, i)));
		}

		return summe;

	}


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


	public static String umkehren(String s){

		String s1 = "";

		for ( int j = s.length()-1; j >= 0; j-- ){
			s1 += s.charAt(j);
		}

		return s1;
	}


	public static int inZahl(char c){

		int i = (int) (c - 55);

		return i;

	}


	public static String inZeichen(int i) {

		i = i - 10;

		char ch = (char) (i + 65);

		return ch+"";
	}




}
