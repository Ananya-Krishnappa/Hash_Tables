package com.bridgelabz;

public class MyMapNode<K, V> implements INode<K> {
	K key;
	V value;
	MyMapNode<K, V> next;
	final int hashCode;

	public MyMapNode(K key, V value, int hashCode) {
		this.key = key;
		this.value = value;
		this.hashCode = hashCode;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public void setKey(K key) {
		this.key = key;

	}

	@Override
	public INode<K> getNext() {
		return next;
	}

	@Override
	public void setNext(INode<K> next) {
		this.next = (MyMapNode<K, V>) next;
	}

	@Override
	public String toString() {
		return "MyMapNode [key=" + key + ", value=" + value + ", next=" + next + "]";
	}

}
