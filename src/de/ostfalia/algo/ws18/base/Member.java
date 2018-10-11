package de.ostfalia.algo.ws18.base;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Member implements IMember {

	private String nachname;
	private String vorname;
	private LocalDate geburtsdatum;
	private Gender geschlecht;
	private KindOfSport sportArt;

	public static void main(String[] args) throws FileNotFoundException {
		
	}

	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String member) {
		String[] parts = member.split(",");

		this.nachname = parts[0].trim();
		this.vorname = parts[1].trim();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.geburtsdatum = LocalDate.parse(parts[2].trim(), dtf);
		this.geschlecht = Gender.valueOf(parts[3].trim());
		this.sportArt = KindOfSport.valueOf(parts[4].trim());

	}

	@Override
	public int compareTo(IMember o) {
		if(this.equals(o))
		return 1;
		return 0;
	}

	@Override
	public long getKey() {
		char cName = nachname.charAt(0);
		long cNameL = Character.getNumericValue(cName) - 9;
		char cVname = vorname.charAt(0);
		long cVnameL = Character.getNumericValue(cVname) - 9;
		String str = String.valueOf(cNameL);
		if (cVnameL < 10)
			str += "0" + String.valueOf(cVnameL);
		else str += String.valueOf(cVnameL);
		
		String[]datePart = String.valueOf(geburtsdatum).split("-");
		String year = datePart[0].trim();
		String month = datePart[1].trim();
		String day = datePart[2].trim();
		str += day + month + year;
		long l = Long.parseLong(str);
		return l;
	}

	@Override
	public String getName() {
		return nachname;
	}

	@Override
	public String getFirstName() {
		return vorname;
	}

	@Override
	public Gender getGender() {
		return geschlecht;
	}

	@Override
	public LocalDate getDate() {
		return geburtsdatum;
	}

	@Override
	public KindOfSport getKindOfSport() {
		// TODO Auto-generated method stub
		return sportArt;
	}

	@Override
	public String toString() {
		return (getKey() + ", " + nachname + ", " + vorname + ", " + geburtsdatum + ", " + geschlecht + ", " + sportArt)
				.toString();
	}

}
