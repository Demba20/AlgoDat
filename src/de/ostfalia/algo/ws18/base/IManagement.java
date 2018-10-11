package de.ostfalia.algo.ws18.base;

/**
 * @author M. Gruendel
 */
public interface IManagement {
	
	/**
	 * Liefert die Anzahl der Datensaetze.
	 * @return Anzahl der Datensaetze: int.
	 */
	public int size();
	
	
	/**
	 * Fuegt ein Mitglied dem Datensatz hinzu.
	 * @param member - hinzuzufuegendes Mitglied: IMember.
	 * @return true, wenn das Mitglied hinzugefuegt werden konnte, sonst
	 * false.
	 */
	public boolean insert(IMember member);

	
	/**
	 * Sucht nach einem Datensatz mit dem angegebenen Schluessel.
	 * @param key - Schluesselwert: long 
	 * @return - den gefundenen Datensatz, oder null, wenn der Schluesselwert
	 * nicht gefunden werden konnte: IMember.
	 */
	public IMember search(long key);

	
	/**
	 * Sucht nach dem ersten Datensatz mit dem angegebenen Namen und Vornamen.
	 * @param name - Nachname des Mitglieds: String.
	 * @param firstName - Vorname des Mitglieds: String. 
	 * @return - den gefundenen Datensatz, oder null, wenn der Schluesselwert
	 * nicht gefunden werden konnte: IMember. 
	 */
	public IMember search(String name, String firstName);
	
	
	/**
	 * Liefert die Anzahl der Datensaetze mit der angegebenen Sportart zurueck.
	 * @param kindOfSport die gesuchte Sportart: KindOfSport.
	 * @return - Anzahl der Datensaetze mit der angegebenen Sportart: int.
	 */
	public int size(KindOfSport kindOfSport);
	
	
	/**
	 * Liefert die Datensaetze mit der angegebenen Sportart zurueck.
	 * @param kindOfSport die gesuchte Sportart: KindOfSport.
	 * @return - die Datensaetze mit der angegebenen Sportart: IMember[].
	 */
	public IMember[] discipline(KindOfSport kindOfSport);
	
	/**
	 * Liefert alle Datensaetze als Array von IMember zurueck.
	 * @return - alle Datensaetze als Array: IMember[].
	 */
	public IMember[] toArray();
	
	/**
	 * Liefert die Anzahl grundlegenden Operationen bei der zuvor aufgerufenen
	 * Zugriffsmethode zurueck.
	 * @return - Anzahl grundlegenden Operationen bei der zuvor aufgerufenen
	 * Zugriffsmethode: int.
	 */
	public int numberOfOperations();
	
	
	/**
	 * Nur fuer Aufgabe 3: liefert die Hoehe des binaeren Suchbaums zurueck. 
	 * @return - die Hoehe des binaeren Suchbaums: int.
	 */
	public default int height() {
		return 0;
	};
	
}
