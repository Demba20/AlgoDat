package de.ostfalia.algo.ws18.s1.test;

import static org.junit.Assert.assertFalse;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import de.ostfalia.algo.ws18.base.IManagement;
import de.ostfalia.algo.ws18.base.IMember;
import de.ostfalia.algo.ws18.base.KindOfSport;
import de.ostfalia.algo.ws18.base.Member;
import de.ostfalia.algo.ws18.s1.Management;
import de.ostfalia.junit.annotations.AfterMethod;
import de.ostfalia.junit.annotations.TestDescription;
import de.ostfalia.junit.base.IMessengerRules;
import de.ostfalia.junit.base.ITraceRules;
import de.ostfalia.junit.conditional.PassTrace;
import de.ostfalia.junit.rules.MessengerRule;
import de.ostfalia.junit.rules.RuleControl;
import de.ostfalia.junit.rules.TraceRule;
import de.ostfalia.junit.runner.TopologicalSortRunner;

@RunWith(TopologicalSortRunner.class)
public class ManagementTestS1 {
	
	public RuleControl opt = RuleControl.NONE;
	public IMessengerRules messenger = MessengerRule.newInstance(opt);	
	public ITraceRules trace = TraceRule.newInstance(opt);
		

	@Rule
	public TestRule chain = RuleChain
							.outerRule(trace)	
							.around(messenger);
	
	/**
	 * Datensatz mit 10 Eintraegen als Testdaten fuer die JUnit-Tests.
	 */
	public String[] data10 = {"Hueber, Uta, 1922-10-15, F, HANDBALL",		//[0]
					   		  "Muller, Ursula, 1964-01-28, F, HANDBALL",	//[1]
					   		  "Fried, Heike, 1997-12-14, F, RUDERN",		//[2]
					   		  "Meyer, Tanja, 1946-04-16, F, HANDBALL",		//[3]
					   		  "Brauer, Mandy, 1933-07-21, F, FUSSBALL",		//[4]
					   		  "Weiss, Ulrich, 1987-06-09, M, FUSSBALL",		//[5]
					   		  "Bohm, Stephanie, 1931-10-22, F, HANDBALL",	//[6]
					   		  "Huber, Annett, 1936-11-19, F, RUDERN",		//[7]
					   		  "Hertz, Thomas, 1946-10-01, M, HANDBALL",		//[8]
					   		  "Scholz, Anja, 1933-01-12, F, RUDERN"};		//[9]
	
	/**
	 * Schluesselwerte fuer den Datensatz data (10 Eintraege).
	 */
	public long[] keys10 = {82115101922L, 132128011964L, 60814121997L, 132016041946L, 
				     	    21321071933L, 232109061987L, 21922101931L, 80119111936L,
				     	    82001101946L, 190112011933L};
	
	int[] all = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
	int[] ofs = {-1, +1};

	@Before
	public void setUp() throws Exception {		
	}

	/**
	 * <ul>
	 * 	<li>Testfall: Standard-Konstruktoraufruf Management().<br> 
	 * 		Nach Aufruf des Standard-Konstruktors duerfen sich keine Datensaetze
	 *      in der Mitgliederverwaltung befinden.</li>
	 *	<li>Erwartet: Anzahl der Datensaetze = 0.</li>
	 * </ul>
	 */
	@Test (timeout = 1000)
	@TestDescription("Testen des Kontruktors().")
	public void testKonstruktorOhneParameter() {
		trace.add("Konstruktoraufruf Management()");
		IManagement mgnt = new Management(); 		
		evaluate(mgnt);
	}
	
	/**
	 * <ul>
	 * 	<li>Testfall: Konstruktoraufruf Management(String[]) mit einem Datensatz.<br> 
	 * 		Nach Aufruf Konstruktors muss sich genau ein Datensatz (data10[0])
	 * 		in der Mitgliederverwaltung befinden.</li>
	 *	<li>Erwartet: data10[0] in der Mitgliederverwaltung.</li>
	 * </ul>
	 */
	@Test (timeout = 1000)
	@AfterMethod("testKonstruktorOhneParameter")
	@TestDescription("Testen des Kontruktors(String[]) mit einem Datensatz.")
	public void testKonstruktorEinDatensatz() {
		trace.add("Konstruktoraufruf Management(String[])");
		IManagement mgnt = new Management(Arrays.copyOf(data10, 1));
		evaluate(mgnt, 0);		
	}
	
	/**
	 * <ul>
	 * 	<li>Testfall: Konstruktoraufruf Management(String[]) mit 10 Datensaetzen.<br> 
	 * 		Nach Aufruf Konstruktors mussen sich alle 10 Datensaetze in der 
	 * 		Mitgliederverwaltung befinden.</li>
	 *	<li>Erwartet: data10[0] bis data10[9] in der Mitgliederverwaltung.</li>
	 * </ul>
	 */
	@Test (timeout = 1000)
	@AfterMethod("testKonstruktorEinDatensatz")
	@TestDescription("Testen des Kontruktors(String[]) mit 10 Datensaetzen.")
	public void testKonstruktorZehnDatensaetze() {
		trace.add("Konstruktoraufruf Management(String[])");
		IManagement mgnt = new Management(data10); 
		evaluate(mgnt, all);		
	}

	/**
	 * <ul>
	 * 	<li>Testfall: Standard-Konstruktoraufruf Management() und anschliessendes
	 * 		Einfuegen von 10 Datensaetzen.<br> 
	 * 		Alle Datensaetze muessen in die Mitgliederverwaltung eingefuegt 
	 * 		werden koennen.</li>
	 *	<li>Erwartet: data10[0] bis data10[9] in der Mitgliederverwaltung.</li>
	 * </ul>
	 */
	@Test (timeout = 1000)
	@AfterMethod("testKonstruktorZehnDatensaetze")
	@TestDescription("Testen der insert(IMember)-Methode mit 10 Datensaetzen.")
	public void testInsert() {
		trace.add("Konstruktoraufruf Management()");
		IManagement mgnt = new Management(); 
		evaluate(mgnt);
		
		String msg = "Falscher Rueckgabe beim Einfuegen errhalten.";
		for (int i = 0; i < data10.length; i++) {
			trace.add("Datensatz \"%s\" hinzufuegen.", data10[i]);
			IMember member = new Member(data10[i]);
			trace.addInfo("Aufruf von insert(%s).", member);
			trace.addInfo(PassTrace.ifTrue(msg, mgnt.insert(member)));
		}
		evaluate(mgnt, all);
	}

	/**
	 * <ul>
	 * 	<li>Testfall: Konstruktoraufruf Management(String[]) mit 10 Datensaetzen
	 * 		und anschliessendes Suchen nach den Schuesselwerten in der 
	 *		Mitgliederverwaltung.</li>
	 *	<li>Erwartet: Alle Schuesselwerten muessen in der Mitgliederverwaltung
	 *		gefunden werden. Die Methode search(long) muss den zugehoerigen
	 *		Datensatz zurueckliefern.</li>
	 * </ul>
	 */
	@Test (timeout = 1000)
	@AfterMethod("testKonstruktorZehnDatensaetze")
	@TestDescription("Testen der search(long)-Methode mit 10 Datensaetzen.")
	public void testSearchLong() {
		trace.add("Konstruktoraufruf Management(String[])");
		IManagement mgnt = new Management(data10); 
		evaluate(mgnt, all);
		
		String msg = "Falscher Datensatz bei der Suche nach %d geliefert.";
		for (int i = 0; i < data10.length; i++) {
			trace.add("Aufruf von search(%d)", keys10[i]);
			String exp  = concat(i);
			IMember got = mgnt.search(keys10[i]);
			trace.addInfo(PassTrace.ifEquals(msg, exp, got, keys10[i]));			
		}
		assertFalse("Methode search(long) liefert falschen Datensatz.",
				trace.hasOccurrences());
	}
	
	/**
	 * <ul>
	 * 	<li>Testfall: Konstruktoraufruf Management(String[]) mit 10 Datensaetzen
	 * 		und anschliessendes Suchen nach Schuesselwerten, die nicht in der
	 * 		Mitgliederverwaltung existieren.</li>
	 *	<li>Erwartet: Kein Schuesselwert darf in der Mitgliederverwaltung
	 *		gefunden werden. Methode search(long) muss null liefern.</li>
	 * </ul>
	 */
	@Test (timeout = 1000)
	@AfterMethod("testKonstruktorZehnDatensaetze")
	@TestDescription("Methode search(long) muss bei nicht vorhandenen Schluesseln null liefern.")
	public void testSearchNotExist() {
		trace.add("Konstruktoraufruf Management(String[])");
		IManagement mgnt = new Management(data10); 
		evaluate(mgnt, all);
		
		String msg = "Nicht vorhandener Schluessel %d geliefert ungueltigen Datensatz.";
		for (int i = 0; i < data10.length; i++) {
			for (int j = 0; j < ofs.length; j++) {
				long key = keys10[i] + ofs[j];
				trace.add("Aufruf von search(%d)", key);			
				IMember got = mgnt.search(key);
				trace.addInfo(PassTrace.ifEquals(msg, null, got, key));	
			}
		}
		assertFalse("Methode search(long) liefert falschen Datensatz.",
				trace.hasOccurrences());
	}

	/**
	 * <ul>
	 * 	<li>Testfall: Konstruktoraufruf Management(String[]) mit 10 Datensaetzen
	 * 		und anschliessendes Suchen nach dem Nach- und Vornamen in der 
	 *		Mitgliederverwaltung.</li>
	 *	<li>Erwartet: Alle Namen muessen in der Mitgliederverwaltung
	 *		gefunden. Die Methode search(String, String) muss den zugehoerigen
	 *		Datensatz zurueckliefern.</li>
	 * </ul>
	 */
	@Test (timeout = 1000)
	@AfterMethod("testKonstruktorZehnDatensaetze")
	@TestDescription("Testen der search(String, String)-Methode mit 10 Datensaetzen.")
	public void testSearchName() {
		trace.add("Konstruktoraufruf Management(String[])");
		IManagement mgnt = new Management(data10); 
		evaluate(mgnt, all);
		
		String msg  = "Falscher Datensatz bei der Suche nach \"%s, %s\" geliefert.";
		for (int i = 0; i < data10.length; i++) {
			String[] parts = data10[i].split(",");
			trace.add("Aufruf von search(\"%s, %s\")", parts[0], parts[1]);
			String exp  = concat(i);
			IMember got = mgnt.search(parts[0].trim(), parts[1].trim());			
			trace.addInfo(PassTrace.ifEquals(msg, exp, got, parts[0], parts[1]));				
		}
		assertFalse("Methode search(String, String) liefert falschen Datensatz.",
				trace.hasOccurrences());
	}
	
	/**
	 * <ul>
	 * 	<li>Testfall: Konstruktoraufruf Management(String[]) mit 10 Datensaetzen
	 * 		und anschliessendes Suchen nach dem Nach- und Vornamen in der 
	 *		Mitgliederverwaltung, wobei der Nachname nicht exsistiert.</li>
	 *	<li>Erwartet: Kein Namen darf in der Mitgliederverwaltung
	 *		gefunden werden. Die Methode search(String, String) muss null liefern.</li>
	 * </ul>
	 */
	@Test (timeout = 1000)
	@AfterMethod("testKonstruktorZehnDatensaetze")
	@TestDescription("Methode search(String, String) nuss bei unbekannen Namen null liefern.")
	public void testSearchUnknown() {
		trace.add("Konstruktoraufruf Management(String[])");
		IManagement mgnt = new Management(data10); 
		evaluate(mgnt, all);
		
		String msg  = "Ungueltiger Datensatz bei der Suche nach \"%s, %s\" geliefert.";
		for (int i = 0; i < data10.length; i++) {			
			String[] parts = data10[i].split(",");
			String unknown = parts[0] + "s";
			trace.add("Aufruf von search(\"%s, %s\")", unknown, parts[1]);			
			IMember got = mgnt.search(unknown.trim(), parts[1].trim());			
			trace.addInfo(PassTrace.ifEquals(msg, null, got, unknown, parts[1]));				
		}
		assertFalse("Methode search(String, String) liefert falschen Datensatz.",
				trace.hasOccurrences());
	}

	/**
	 * <ul>
	 * 	<li>Testfall: Konstruktoraufruf Management(String[]) mit 10 Datensaetzen
	 * 		und anschliessendes Ermitteln der Anzahl der Eintraege fuer die 
	 * 		Sportarten HANDBALL, RUDERN, FUSSBALL und REITEN in der 
	 * 		Mitgliederverwaltung.</li>
	 *	<li>Erwartet: Die Methode size(KindOfSport) muss folgende Werte liefern:
	 *		<ul>
	 *			<li>HANDBALL: 5</li>
	 *			<li>RUDERN: 3</li>
	 *			<li>FUSSBALL: 2</li>
	 *			<li>REITEN: 0</li>
	 *		</ul> 
	 *	</li>
	 * </ul>
	 */
	@Test (timeout = 1000)
	@AfterMethod("testKonstruktorZehnDatensaetze")
	@TestDescription("Testen der size(KindOfSport)-Methode mit 10 Datensaetzen.")
	public void testSizeKindOfSport() {
		trace.add("Konstruktoraufruf Management(String[])");
		IManagement mgnt = new Management(data10); 
		evaluate(mgnt, all);
		
		String msg = "Falsche Anzahl der Mitglieder fuer die Sportarten %s erhalten.";
		
		KindOfSport sport = KindOfSport.HANDBALL;
		trace.add("Aufruf von size(%s).", sport);
		trace.addInfo(PassTrace.ifEquals(msg, 5, mgnt.size(sport), sport));
		
		sport = KindOfSport.RUDERN;
		trace.add("Aufruf von size(%s).", sport);
		trace.addInfo(PassTrace.ifEquals(msg, 3, mgnt.size(sport), sport));
		
		sport = KindOfSport.FUSSBALL;
		trace.add("Aufruf von size(%s).", sport);
		trace.addInfo(PassTrace.ifEquals(msg, 2, mgnt.size(sport), sport));
		
		sport = KindOfSport.REITEN;
		trace.add("Aufruf von size(%s).", sport);
		trace.addInfo(PassTrace.ifEquals(msg, 0, mgnt.size(sport), sport));
		
		assertFalse("Methode size(KindOfSport) liefert falsches Ergebnis.",
				trace.hasOccurrences());
	}

	/**
	 * <ul>
	 * 	<li>Testfall: Konstruktoraufruf Management(String[]) mit 10 Datensaetzen
	 * 		und anschliessendes Ermitteln der Eintraege fuer die Sportarten 
	 * 		HANDBALL, RUDERN, FUSSBALL und REITEN in der Mitgliederverwaltung.</li>
	 *	<li>Erwartet:<br>
	 * 		Die Methode discipline(KindOfSport) muss folgende Datensaetze
	 *		liefern (angegeben ist jeweils der Index im Datensatz data10):
	 *		<ul>
	 *			<li>HANDBALL: [8], [6], [3], [1], [0]</li>
	 *			<li>RUDERN: [9], [7], [2]</li>
	 *			<li>FUSSBALL: [5], [4]</li>
	 *			<li>REITEN: nicht im Datensatz vorhanden</li>
	 *		</ul> 
	 *	</li>
	 * </ul>
	 */
	@Test (timeout = 1000)
	@AfterMethod("testKonstruktorZehnDatensaetze")
	public void testDiscipline() {
		trace.add("Konstruktoraufruf Management(String[])");
		IManagement mgnt = new Management(data10); 
		evaluate(mgnt, all);

		KindOfSport sport = KindOfSport.HANDBALL;
		trace.add("Aufruf von discipline(%s).", sport);
		evaluate(mgnt.discipline(sport), 8, 6, 3, 1, 0);

		sport = KindOfSport.RUDERN;
		trace.add("Aufruf von discipline(%s).", sport);
		evaluate(mgnt.discipline(sport), 9, 7, 2);
		
		sport = KindOfSport.FUSSBALL;
		trace.add("Aufruf von discipline(%s).", sport);
		evaluate(mgnt.discipline(sport), 5, 4);
		
		sport = KindOfSport.REITEN;
		trace.add("Aufruf von discipline(%s).", sport);
		evaluate(mgnt.discipline(sport));
		
		assertFalse("Methode discipline(KindOfSport) liefert falsches Ergebnis.",
				trace.hasOccurrences());		
	}
	
	//-----------------------------------------------------------------
	
	/**
	 * Ueberpruefung der durch die Methode toArray() der Mitgilderverwaltung
	 * zurueckgelieferten Datensaetze.
	 * @param mgnt - Mitgliederverwaltung: IManagement.
	 * @param elements - Indizes der erwarteten Testdatensaetze: int...
	 */
	private void evaluate(IManagement mgnt, int... elements) {
		evaluate(mgnt.toArray(), elements);
	}
	
	/**
	 * Ueberpruefung der uebergebenen Datensaetze anhand der Indizes der 
	 * erwarteten Testdatensaetze.
	 * @param members - Array mit Datensaetze: IMember[].
	 * @param elements - Indizes der erwarteten Testdatensaetze: int...
	 */
	private void evaluate(IMember[] members, int... elements) {
		trace.addInfo(PassTrace.ifEquals("Anzahl der Mitglieder nicht korrekt.", 
				elements.length, members.length));
		int length = Integer.min(elements.length, members.length);
		for (int i = 0; i < length; i++) {
			int index = elements[i];
			String exp = concat(index);
			trace.addInfo(PassTrace.ifEquals("Fehler im Datensatz %d.",
				      exp, members[i], i + 1));
		}	
		assertFalse("Fehlerhafte Datensaetze in der Mitgliederverwaltung.",
				trace.hasOccurrences());
	}
	
	/**
	 * Zusammenfuegen eines Schluesselwerts mit dem zuhegoerigen Datensatz
	 * entsprechend ihrem Index in den Testdatensaetzen.
	 * @param element - Index im Datensatz: int.
	 * @return Schluesselwert mit dem zuhegoerigen Datensatz: String. 
	 */
	private String concat(int element) {		
		return keys10[element] + ", " + data10[element];						
	}

}
