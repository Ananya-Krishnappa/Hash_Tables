package com.bridgelabz;

import java.util.ArrayList;
import java.util.Objects;

public class MyHashTable<K, V> {
	// bucketArray is used to store array of chains
	private ArrayList<MyMapNode<K, V>> bucketArray;

	// Current capacity of array list
	private int numBuckets;

	// Current size of array list
	private int size;

	// Constructor (Initializes capacity, size and
	// empty chains.
	public MyHashTable() {
		bucketArray = new ArrayList<>();
		numBuckets = 10;
		size = 0;

		// Create empty chains
		for (int i = 0; i < numBuckets; i++)
			bucketArray.add(null);
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	private final int hashCode(K key) {
		return Objects.hashCode(key);
	}

	/**
	 * This implements hash function to find index for a key
	 * 
	 * @param key
	 * @return
	 */
	private int getBucketIndex(K key) {
		int hashCode = hashCode(key);
		int index = hashCode % numBuckets;
		// key.hashCode() could be negative.
		index = index < 0 ? index * -1 : index;
		return index;
	}

	/**
	 * Method to remove a given key
	 * 
	 * @param key
	 * @return
	 */
	public V remove(K key) {
		// Apply hash function to find index for given key
		int bucketIndex = getBucketIndex(key);
		int hashCode = hashCode(key);
		// Get head of chain
		MyMapNode<K, V> head = bucketArray.get(bucketIndex);

		// Search for key in its chain
		MyMapNode<K, V> prev = null;
		while (head != null) {
			// If Key found
			if (head.key.equals(key) && hashCode == head.hashCode)
				break;

			// Else keep moving in chain
			prev = head;
			head = head.next;
		}

		// If key was not there
		if (head == null)
			return null;

		// Reduce size
		size--;

		// Remove key
		if (prev != null)
			prev.next = head.next;
		else
			bucketArray.set(bucketIndex, head.next);

		return head.value;
	}

	/**
	 * Returns value for a key
	 * 
	 * @param key
	 * @return
	 */
	public V get(K key) {
		// Find head of chain for given key
		int bucketIndex = getBucketIndex(key);
		int hashCode = hashCode(key);
		MyMapNode<K, V> head = bucketArray.get(bucketIndex);
		// Search key in chain
		while (head != null) {
			if (head.key.equals(key) && head.hashCode == hashCode)
				return head.value;
			head = head.next;
		}
		// If key not found
		return null;
	}

	/**
	 * Adds a key value pair to hash
	 * 
	 * @param key
	 * @param value
	 */
	public void add(K key, V value) {
		// Find head of chain for given key
		int bucketIndex = getBucketIndex(key);
		int hashCode = hashCode(key);
		MyMapNode<K, V> head = bucketArray.get(bucketIndex);

		// Check if key is already present
		while (head != null) {
			if (head.key.equals(key) && head.hashCode == hashCode) {
				head.value = value;
				return;
			}
			head = head.next;
		}

		// Insert key in chain
		size++;
		head = bucketArray.get(bucketIndex);
		MyMapNode<K, V> newNode = new MyMapNode<K, V>(key, value, hashCode);
		newNode.next = head;
		bucketArray.set(bucketIndex, newNode);

		// If load factor goes beyond threshold, then
		// double hash table size
		if ((1.0 * size) / numBuckets >= 0.7) {
			ArrayList<MyMapNode<K, V>> temp = bucketArray;
			bucketArray = new ArrayList<>();
			numBuckets = 2 * numBuckets;
			size = 0;
			for (int i = 0; i < numBuckets; i++)
				bucketArray.add(null);

			for (MyMapNode<K, V> headNode : temp) {
				while (headNode != null) {
					add(headNode.key, headNode.value);
					headNode = headNode.next;
				}
			}
		}
	}

	/**
	 * Iterates through the hash table and prints the key and value
	 */
	public void show() {
		for (int i = 0; i < bucketArray.size(); i++) {
			MyMapNode<K, V> head = bucketArray.get(i);
			while (head != null) {
				System.out.println(head.key + " - " + head.value);
				head = head.next;
			}
		}
	}
}
