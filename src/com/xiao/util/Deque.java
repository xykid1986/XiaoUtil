package com.xiao.util;

public class Deque<E> {
	
	private int size;
	private int left;
	private int right;
	private static int DEFAULT_SIZE = 1000;
	private int maxSize;
	private E[] a;
	
	public Deque(){
		this(DEFAULT_SIZE);
	}
	
	@SuppressWarnings("unchecked")
	public Deque(int maxSize){
		this.maxSize = maxSize;
		a = (E[])(new Object[this.maxSize]);
	}
	
	public void addRight(E obj){
		if(size>=maxSize)
			throw new IllegalStateException("Deque is full: "+obj);
		if(left==right&&a[left]==null){
			a[left] = obj;
		}
		else{
			if(++right>=maxSize)
				right=0;
			a[right]=obj;
		}
		size++;
	}
	
	public void addLeft(E obj){
		if(size>=maxSize)
			throw new IllegalStateException("Deque is full: "+obj);
		if(left==right&&a[left]==null){
			a[left]=obj;
		}
		else{
			if(--left<0){
				left=maxSize-1;
			}
			a[left]=obj;
		}
		size++;
	}
	
	public void removeLeft(){
		if(size<=0)
			throw new IllegalStateException("Deque is empty");
		if(left==right&&a[left]!=null)
			a[left]=null;
		else{
			a[left]=null;
			if(++left>=a.length)
				left=0;
		}
		size--;
	}
	
	public void removeRight(){
		if(size<=0)
			throw new IllegalStateException("Deque is empty");
		if(left==right&&a[left]!=null)
			a[left]=null;
		else{
			a[right]=null;
			if(--right<0)
				right=a.length-1;
		}
		size--;
	}
	
	/**
	 * Display the queue in left to right order
	 */
	public void display(){
		System.out.println("size: "+size);
		if(left<right){
			for(int i=left;i<=right;i++)
				System.out.print(a[i]+" ");
		}
		else if(left>right){
			for(int i=left;i<maxSize;i++)
				System.out.print(a[i]+" ");
			for(int i=0;i<=right;i++)
				System.out.print(a[i]+" ");
		}
		else if(a[left]!=null){
			System.out.println(a[left]+" ");
		}
		System.out.println();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Deque<Character> d = new Deque<Character>();
		int counter=0;
		for(int i=0;i<13;i++)
			d.addLeft((char)('A'+counter++));
		for(int i=0;i<13;i++)
			d.addRight((char)('A'+counter++));
		d.display();
		
		for(int i=0;i<13;i++)
			d.removeRight();
		d.display();
		for(int i=0;i<3;i++)
			d.removeRight();
		d.display();
		for(int i=0;i<13;i++)
			d.removeLeft();
		d.display();
		
	}
}
