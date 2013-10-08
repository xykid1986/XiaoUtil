package com.xiao.util;


public class ListNode<E> {
	private ListNode<E> next;
	private ListNode<E> previous;
	private E data;
	public ListNode(E obj){
		this.data = (E)obj;
	}
	public ListNode<E> getPrevious(){
		return this.previous;
	}
	public ListNode<E> getNext(){
		return this.next;
	}
	public E getData(){
		return this.data;
	}
	public void setNext(ListNode<E> next) {
		this.next = next;
	}
	public void setPrevious(ListNode<E> previous) {
		this.previous = previous;
	}
	public void setData(E data) {
		this.data = data;
	}
}
