package de.ostfalia.algo.ws18.s1.test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import de.ostfalia.algo.ws18.base.Gender;
import de.ostfalia.algo.ws18.base.IMember;
import de.ostfalia.algo.ws18.base.KindOfSport;
import de.ostfalia.algo.ws18.base.Member;
import de.ostfalia.junit.annotations.TestDescription;
import de.ostfalia.junit.base.IMessengerRules;
import de.ostfalia.junit.base.ITraceRules;
import de.ostfalia.junit.conditional.PassTrace;
import de.ostfalia.junit.rules.MessengerRule;
import de.ostfalia.junit.rules.RuleControl;
import de.ostfalia.junit.rules.TraceRule;
import de.ostfalia.junit.runner.TopologicalSortRunner;

@RunWith(TopologicalSortRunner.class)
public class MemberTest {

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
	public String[] data = {"Ackerman, Niklas, 1979-04-08, M, SCHWIMMEN",	//[0]
					 		"Bauer, Juliane, 1939-01-16, F, FECHTEN",		//[1]
					 		"Kirsch, Antje, 1960-07-13, F, TURNEN",			//[2]
					 		"Koertig, Dominik, 1933-09-28, M, HANDBALL",	//[3]
					 		"Trommler, Ines, 1924-10-29, F, TURNEN",		//[4]
					 		"Hirsch, Manuela, 1948-05-10, F, RUDERN",		//[5]
					 		"Schaefer, Stephanie, 1997-05-31, F, FECHTEN",	//[6]
					 		"Schultz, Katrin, 1952-08-18, F, REITEN",		//[7]
					 		"Fried, Leonie, 1965-09-24, F, TURNEN",			//[8]
					 		"Lemann, Philipp, 1936-10-30, M, RADSPORT"};	//[9]

	/**
	 * Datensatz mit 3 Eintraegen als Testdaten fuer die JUnit-Tests.
	 */
	public String[] test = {"Ackerman, Niklas, 1979-04-08, M, SCHWIMMEN",
				     		"Acker, Nadine, 1979-04-08, F, HANDBALL", 
				     		"Acker, Nadine, 1979-04-10, F, HANDBALL"};

	/**
	 * Schluesselwerte fuer den Datensatz data (10 Eintraege).
	 */
	public long[] keys = {11408041979L, 21016011939L, 110113071960L, 110428091933L, 
				   		  200929101924L, 81310051948L, 191931051997L, 191118081952L,
				   		  61224091965L, 121630101936L};

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * <ul>
	 * 	<li>Testfall: toString()-Methode der Klasse Member.<br> 
	 * 		Es werden alle 10 Datensaetze nacheinander druchlaufen.</li>
	 *	<li>Erwartet: Rueckgabe aller Attribute inkl. des berechneten Schluessels als String.</li>
	 *	<li>Beispiel  fuer data[0]: 11408041979, Ackerman, Niklas, 1979-04-08, M, SCHWIMMEN.</li>
	 * </ul>
	 */
	@Test (timeout = 1000)
	@TestDescription("Testen der toString()-Methode.")
	public void testToString() {
		for (int i = 0; i < keys.length; i++) {
			trace.add("Konstruktoraufruf Member(%s)", data[i]);
			IMember member = new Member(data[i]);
			String exp = keys[i]  + ", " + data[i];			
			String got = member.toString();
			trace.addInfo(PassTrace.ifEquals("Fehler im Datensatz %d",
					      exp, got, i + 1));
		}
		assertFalse("Fehler bei der Implementierung toString()-Methode.",
				trace.hasOccurrences());
	}

	/**
	 * <ul>
	 * 	<li>Testfall: getKey()-Methode der Klasse Member.</li>
	 *	<li>Erwartet: Rueckgabe des Schluessels als Long-Wert.</li>
	 *	<li>Beispiel fuer data[0]: 11408041979.</li>
	 * </ul>
	 */
	@Test (timeout = 1000)
	@TestDescription("Testen der getKey()-Methode.")
	public void testGetKey() {
		for (int i = 0; i < keys.length; i++) {
			trace.add("Konstruktoraufruf Member(%s)", data[i]);
			IMember member = new Member(data[i]);
			Long exp = keys[i];
			Long got = member.getKey();
			trace.addInfo(PassTrace.ifEquals("Fehlerhafter Schuessel im Datensatz %d",
					      exp, got, i + 1));
		}
		assertFalse("Fehler bei der Rueckgabe des Schluesselwertes.",
				trace.hasOccurrences());
	}

	/**
	 * <ul>
	 * 	<li>Testfall: getName()-Methode der Klasse Member.</li>
	 *	<li>Erwartet: Rueckgabe des Nachnamens als String.</li>
	 *	<li>Beispiel fuer data[0]: Ackerman.</li>
	 * </ul>
	 */
	@Test (timeout = 1000)
	@TestDescription("Testen der getName()-Methode.")
	public void testGetName() {
		for (int i = 0; i < keys.length; i++) {
			trace.add("Konstruktoraufruf Member(%s)", data[i]);
			IMember member = new Member(data[i]);
			String exp = data[i].split(",")[0];
			String got = member.getName();
			trace.addInfo(PassTrace.ifEquals("Fehlerhafter Nachname im Datensatz %d",
					      exp, got, i + 1));
		}
		assertFalse("Fehler bei der Rueckgabe des Nachnamens.",
				trace.hasOccurrences());
	}

	/**
	 * <ul>
	 * 	<li>Testfall: getFirstName()-Methode der Klasse Member.</li>
	 *	<li>Erwartet: Rueckgabe des Vornamens als String.</li>
	 *	<li>Beispiel fuer data[0]: Niklas.</li>
	 * </ul>
	 */
	@Test (timeout = 1000)
	@TestDescription("Testen der getFirstName()-Methode.")
	public void testGetFirstName() {
		for (int i = 0; i < keys.length; i++) {
			trace.add("Konstruktoraufruf Member(%s)", data[i]);
			IMember member = new Member(data[i]);
			String exp = data[i].split(",")[1];
			String got = member.getFirstName();
			trace.addInfo(PassTrace.ifEquals("Fehlerhafter Vornamen im Datensatz %d",
					      exp, got, i + 1));
		}
		assertFalse("Fehler bei der Rueckgabe des Vornamens.",
				trace.hasOccurrences());
	}
	
	/**
	 * <ul>
	 * 	<li>Testfall: getDate()-Methode der Klasse Member.</li>
	 *	<li>Erwartet: Rueckgabe des Geburtsdatum als LocalDate.</li>
	 *	<li>Beispiel fuer data[0]: 1979-04-08.</li>
	 * </ul>
	 */
	@Test (timeout = 1000)
	@TestDescription("Testen der geDate()-Methode.")
	public void testGetDate() {
		for (int i = 0; i < keys.length; i++) {
			trace.add("Konstruktoraufruf Member(%s)", data[i]);
			IMember member = new Member(data[i]);
			String exp = data[i].split(",")[2];
			LocalDate got = member.getDate();			
			trace.addInfo(PassTrace.ifEquals("Fehlerhaftes Geburtsdatum im Datensatz %d",
					      exp, got, i + 1));
		}
		assertFalse("Fehler bei der Rueckgabe des Geburtsdatums.",
				trace.hasOccurrences());
	}

	/**
	 * <ul>
	 * 	<li>Testfall: getGender()-Methode der Klasse Member.</li>
	 *	<li>Erwartet: Rueckgabe des Geschlechts als enum Gender.</li>
	 *	<li>Beispiel fuer data[0]: M.</li>
	 * </ul>
	 */
	@Test (timeout = 1000)
	@TestDescription("Testen der getGender()-Methode.")
	public void testGender() {
		for (int i = 0; i < keys.length; i++) {
			trace.add("Konstruktoraufruf Member(%s)", data[i]);
			IMember member = new Member(data[i]);
			String exp = data[i].split(",")[3];
			Gender got = member.getGender();			
			trace.addInfo(PassTrace.ifEquals("Fehlerhaftes Geschlecht im Datensatz %d",
					      exp, got, i + 1));
		}
		assertFalse("Fehler bei der Rueckgabe des Geschlechts.",
				trace.hasOccurrences());
	}

	/**
	 * <ul>
	 * 	<li>Testfall: getKindOfSport()-Methode der Klasse Member.</li>
	 *	<li>Erwartet: Rueckgabe der Sportart als enum KindOfSport.</li>
	 *	<li>Beispiel fuer data[0]: SCHWIMMEN.</li>
	 * </ul>
	 */
	@Test (timeout = 1000)
	@TestDescription("Testen der getKindOfSport()-Methode.")
	public void testGetKindOfSport() {
		for (int i = 0; i < keys.length; i++) {
			trace.add("Konstruktoraufruf Member(%s)", data[i]);
			IMember member = new Member(data[i]);
			String exp = data[i].split(",")[4];
			KindOfSport got = member.getKindOfSport();
			trace.addInfo(PassTrace.ifEquals("Fehlerhafte Sportart im Datensatz %d",
					      exp, got, i + 1));
		}
		assertFalse("Fehler bei der Rueckgabe der Sportart.",
				trace.hasOccurrences());
	}
	
	/**
	 * <ul>
	 * 	<li>Testfall: equals()-Methode der Klasse Member.</li>
	 *	<li>Erwartet: true, wenn die Member inhaltlich gleich sind, sonst false.</li>
	 * </ul>
	 */
	@Ignore
	@Test (timeout = 1000)
	@TestDescription("Testen der equals()-Methode.")
	public void testEqualsObject() {
		trace.add("Konstruktoraufruf Member(%s) fuer Mitglied %d", data[0], 1);
		IMember member1 = new Member(test[0]);
		trace.add("Konstruktoraufruf Member(%s) fuer Mitglied %d", data[0], 2);
		IMember duplikat = new Member(test[0]);
		trace.add("Konstruktoraufruf Member(%s) fuer Mitglied %d", data[1], 3);
		IMember member2 = new Member(test[1]);
		trace.add("Konstruktoraufruf Member(%s) fuer Mitglied %d", data[1], 4);
		IMember member3 = new Member(test[2]);
		
		trace.addInfo(PassTrace.ifTrue("[%s] gleich [%s]?",
				member1.equals(duplikat), member1, duplikat));		
		trace.addInfo(PassTrace.ifTrue("[%s] gleich [%s]",
				member1.equals(member2), member1, member2));
		trace.addInfo(PassTrace.ifFalse("[%s] gleich [%s]",
				member1.equals(member3), member1, member3));
		trace.addInfo(PassTrace.ifFalse("[%s] gleich [%s]",
				member2.equals(member3), member2, member3));
		
		assertFalse("equals()-Methode fehlerhaft implementiert.",
				trace.hasOccurrences());
	}

}
