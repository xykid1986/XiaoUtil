package com.xiao.util;


public class LList<E>{
	
	private ListNode<E> first;
	private ListNode<E> last;
	private long size;
	
	public void insertFirst(E i){
		ListNode<E> newlink = new ListNode<E>(i);
		if (size==0)
			last = newlink;
		else {
			first.setPrevious(newlink);
		    newlink.setNext(first);
		}
		first = newlink;	
		size++;
	}
	public void insertLast(E i){
		ListNode<E> newlink = new ListNode<E>(i);
		if (size==0) {
		    first = newlink;
		}
		else {
			newlink.setPrevious(last);
			last.setNext(newlink);	
		}
		last = newlink;
		size++;
	}
	public long size(){
		return this.size;
	}
	public ListNode<E> deleteFirst(){
		if(size<=0)
			return null;
		ListNode<E> temp = first;
		if (first.getNext()==null) 
			last = null;
		else first.getNext().setPrevious(null);
		first=first.getNext();
		size--;
		return temp;
	}
	public ListNode<E> deleteLast(){
		if(size<=0)
			return null;
		ListNode<E> temp = last;
		if (first.getNext()==null)
			first = null;
		else last.getPrevious().setNext(null);
		last = last.getPrevious();
		size--;
		return temp;
	}
	public void displayForward(){
		ListNode<E> temp = first;
		while(temp!=null){
			System.out.print(temp.getData()+" ");
			temp = temp.getNext();
		}
		System.out.println("");
	}
	public void displayBackward(){
		ListNode<E> temp = last;
		while(temp!=null){
			System.out.print(temp.getData()+" ");
			temp = temp.getPrevious();
		}
		System.out.println("");
	}
	public void delete(E obj){
		ListNode<E> current = first;
		while(current!=null){
			if(current.getData().equals(obj)){
				if(current==first){
					deleteFirst();
					return;
				}
				else if(current==last){
					deleteLast();
					return;
				}
				else{
					current.getPrevious().setNext(current.getNext());
					current.getNext().setPrevious(current.getPrevious());
					return;
				}
			}
			else
				current=current.getNext();
		}
	}
	public void deleteAll(E obj){
		ListNode<E> current = first;
		while(current!=null){
			if(current.getData().equals(obj)){
				if(current==first){
					deleteFirst();
				}
				else if(current==last){
					deleteLast();
				}
				else{
					current.getPrevious().setNext(current.getNext());
					current.getNext().setPrevious(current.getPrevious());
				}
				
			}
			current=current.getNext();
		}
	}
	public ListNode<E> find(E e){
		ListNode<E> current = first;
		while(current!=null){
			if(current.getData().equals(e)){
				return current;
			}
			current=current.getNext();
		}
		return null;
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LList<Integer> l = new LList<Integer>();
		for(int i=0;i<10;i++)
			l.insertFirst(i);
		l.displayForward();
		for(int i=0;i<10;i++)
			l.insertLast(i);
		l.displayForward();
		for(int i=0;i<5;i++)
			l.deleteLast();
		l.delete(5);
		l.deleteAll(4);
		l.displayForward();
		l.displayBackward();
	}
}
