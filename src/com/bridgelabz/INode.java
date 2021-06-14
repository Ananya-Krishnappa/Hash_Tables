package com.bridgelabz;

public interface INode<K> {
	public K getKey();

	public void setKey(K key);

	public INode<K> getNext();

	public void setNext(INode<K> next);

}
