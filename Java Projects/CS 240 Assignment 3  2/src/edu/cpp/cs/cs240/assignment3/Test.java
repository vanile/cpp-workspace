package edu.cpp.cs.cs240.assignment3;

import java.util.Locale;
import java.util.Scanner;

public class Test {

	static HashTable<String, ArrayList<String>> hashTable =
			new HashTable<String, ArrayList<String>>();

	public void newTwitt(String hashtag, String twitt) {
		ArrayList<String> twitts = hashTable.get(hashtag);
		if (twitts == null) {
			twitts = new ArrayList<String>();
		}
		twitts.add(twitt);
		hashTable.put(hashtag, twitts);
	}

	public ArrayList<String> searchHashtag(String hashtag) {
		return hashTable.get(hashtag);
	}

	public static void main(String[] args) {
		Test system = new Test();
		system.newTwitt("MothersDay", "This is a great #MothersDay!");
		system.newTwitt("MothersDay", "#MothersDay is awesome!");
		system.newTwitt("MothersDay", "I bought this for my #MothersDay!");
		system.newTwitt("MothersDay", "What would you do for #MothersDay?");
		system.newTwitt("NBADraft", "#NBADraft is awesome!");
		system.newTwitt("NBADraft", "I bought this for my #NBADraft!");
		system.newTwitt("NBADraft", "What would you do for #NBADraft?");

		System.out.println(system.searchHashtag("MothersDay"));
		System.out.println(system.searchHashtag("NBADraft"));
		System.out.println("");
		System.out.println(hashTable.toString());
		String team = "Super dog";
		team = team.toLowerCase();
		team = team.replaceAll("\\p{Z}","");
		System.out.println(team);
	}
	

}
