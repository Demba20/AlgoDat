package de.ostfalia.algo.ws18.s1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import de.ostfalia.algo.ws18.base.IManagement;
import de.ostfalia.algo.ws18.base.IMember;
import de.ostfalia.algo.ws18.base.KindOfSport;

public class Management implements IManagement {
	private int size = 0;

	public static void main(String[] args) throws IOException {
		new Management("Materialien/Mitglied10000.txt");

	}

	public Management(String[] copyOf) {
		// TODO Auto-generated constructor stub
	}

	public Management() {
		// TODO Auto-generated constructor stub
	}

	public Management(String fileName) throws IOException {
		fileName = "Materialien/Mitglied10000.txt";
		FileReader fr;
		try {
			fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				br.readLine();
				size++;
				System.out.println(line);
			}
			System.out.println(size);
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean insert(IMember member) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IMember search(long key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMember search(String name, String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size(KindOfSport kindOfSport) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IMember[] discipline(KindOfSport kindOfSport) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMember[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numberOfOperations() {
		// TODO Auto-generated method stub
		return 0;
	}

}
