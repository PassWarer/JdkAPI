package org.java.util;

public class MyArrayList<AnyType> implements Iterable<AnyType>{
	
	private static final int DEFAULT_CAPACITY = 0;//默认容量
	private int theSize;//现在的List大小
	private AnyType[] theItems;
	
	public MyArrayList(){
		clear();
	}
	
	public void clear(){
		theSize = 0;
		ensureCapacity(DEFAULT_CAPACITY);
	}
	
	public int size(){
		return theSize;
	}
	
	public boolean isEmpty(){
		return size() == 0;
	}
	
	public void trimToSize(){
		ensureCapacity(size());
	}
	
	public AnyType get(int idx){
		if(idx<0 || idx >= size()){
			throw new ArrayIndexOutOfBoundsException();
		}
		return theItems[idx];
	}
	
	public AnyType set(int idx,AnyType newVal){
		if(idx<0 || idx>=size()){
			throw new ArrayIndexOutOfBoundsException();
		}
		AnyType old = theItems[idx];
		theItems[idx] = newVal;
		return old;
	}
	
	public void ensureCapacity(int newCapacity){
		if(newCapacity < size()){
			return;
		}
		
		AnyType[] old = theItems;
		theItems = (AnyType[]) new Object[newCapacity];
		for(int i = 0;i<size();++i){
			theItems[i] = old[i];
		}
	}
	
	public boolean add(AnyType x){
		add(size(),x);
		return true;
	}
	
	public void add(int idx,AnyType x){
		if(theItems.length == size()){
			ensureCapacity(size()*2+1);
		}
		for(int i = size();i>idx;--i){
			theItems[i] = theItems[i-1];
		}
		theItems[idx] = x;
		theSize++;
	}
	
	public AnyType remove(int idx){
		AnyType removedItem = theItems[idx];
		for(int i = idx;i<size();++i){
			theItems[i] = theItems[i+1];
		}
		theSize--;
		return removedItem;
	}
	
	public java.util.Iterator<AnyType> iterator(){
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements java.util.Iterator<AnyType>{
		private int current = 0;
		
		public boolean hasNext(){
			return current < size();
		}
		
		public AnyType next(){
			if(!hasNext()){
				throw new java.util.NoSuchElementException();
			}
			return theItems[current++];
		}
		
		public void remove(){
			MyArrayList.this.remove(--current);
		}
	}
	
	public String toString(){
		if(size() == 0){
			return "[]";
		}
		StringBuffer str = new StringBuffer("["+theItems[0].toString());
		for(int i =1;i<size();++i){
			str.append(","+theItems[i].toString());
		}
		str.append("]");
		return str.toString();
	}
}
