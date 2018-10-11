package de.ostfalia.algo.ws18.examples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author M. Gruendel
 */
public class Beispiele {
	
	
	private static void leseDatensaetze(String fileName) throws IOException {		
		File file = new File(fileName);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);		
		
		String line;			
		while ((line = br.readLine()) != null) {
			System.out.println(line);  	// Eingelesenen Datensatz ausgeben 
			//insert(new Member(line));
		}				
		br.close();
		fr.close();				
	}
	
	private static long[] leseSchluesselwerte(String fileName, int anzahl) throws IOException {		
		File file = new File(fileName);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		long[] keys = new long[anzahl];
		
		String line;	
		int index = 0;
		while ((line = br.readLine()) != null && anzahl-- > 0) {			
			keys[index++] = Long.parseLong(line);
		}				
		br.close();
		fr.close();		
		return keys;
	}
	
		
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	public static void main(String[] args) throws IOException {
		
		//--Datensaetze aus einer Datei einlesen-----------------------
		
		leseDatensaetze("Materialien/Mitglieder10000.txt");
		
		//--Datei mit Schluesselwerten einlesen------------------------
		
		long[] schluessel = leseSchluesselwerte("Materialien/Schluessel10000_5000.txt", 10000);
		System.out.println(schluessel.length);
		
		//--String in ein Datum umwandeln------------------------------
		
		String datum = "2018-09-24";
		LocalDate date = LocalDate.parse(datum, DateTimeFormatter.ISO_DATE);
		System.out.println(date);
	}

}
