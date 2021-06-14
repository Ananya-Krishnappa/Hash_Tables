package com.bridgelabz;

public class Runner {
	public static void main(String[] args) {
		MyHashTable<String, Integer> myHashTable = new MyHashTable<>();
		String str = "Paranoids are not paranoid because they are paranoid but because they keep putting themselves deliberately into paranoid avoidable situations";
		String[] strArray = str.split(" ");
		for (int i = 0; i < strArray.length; i++) {
			Integer value = myHashTable.get(strArray[i]);
			value = (value == null) ? Integer.valueOf(1) : value + 1;
			myHashTable.add(strArray[i].toLowerCase(), value);
		}
		myHashTable.show();
	}
}
