package de.ostfalia.algo.ws18.base;

import java.time.LocalDate;

/**
 * @author M. Gruendel
 */
public interface IMember extends Comparable<IMember> {
	
	/**
	 * Liefert den Schluesselwert zurueck.
	 * @return - den Schluesselwert: int.
	 */
	public long getKey();
	
	/**
	 * Liefert den Nachnamen des Mitglieds zurueck.
	 * @return - den Nachnamen des Mitglieds: String.
	 */
	public String getName();
	
	/**
	 * Liefert den Vornamen des Mitglieds zurueck.
	 * @return - den Vornamen des Mitglieds: String.
	 */
	public String getFirstName();
	
	/**
	 * Liefert das Geschlecht des Mitglieds zurueck.
	 * @return Geschlecht des Mitglieds: Gender.
	 */
	public Gender getGender();
	
	/**
	 * Liefert das Geburtsdatum des Mitglieds zurueck.
	 * @return Geburtsdatum des Mitglieds: LocalDate.
	 */
	public LocalDate getDate();
	
	/**
	 * liefert die Sportart zurueck.
	 * @return - die Sportart: KindOfSport;
	 */
	public KindOfSport getKindOfSport();
	
	/**
	 * Liefert den Datensatz inklusive Schluesselwert als String zurueck.<br><br>
	 * Beispiel: "82115101922, Hueber, Uta, 1922-10-15, F, HANDBALL"
	 * @return Datensatz inklusive Schluesselwert: String.
	 */
	public String toString();

}
