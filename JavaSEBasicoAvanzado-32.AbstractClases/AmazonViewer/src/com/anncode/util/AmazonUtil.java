package com.anncode.util;

import java.util.Scanner;

public class AmazonUtil {
	
	public static int validateUserResponseMenu(int min, int max) {
		//Leer la respuesta del usuario
		Scanner sc = new Scanner(System.in);
		int response;

		do {
			System.out.println("Ingresa una opción entre " + min + " y " + max + ":");
			while (!sc.hasNextInt()) {
				System.out.println("No ingresaste una opción válida");
				System.out.println("Intenta otra vez");
				sc.next();
			}
			response = sc.nextInt();
		} while (response < min || response > max);

		System.out.println("Tu Respuesta fue: " + response + "\n");
		return response;
	}

}
