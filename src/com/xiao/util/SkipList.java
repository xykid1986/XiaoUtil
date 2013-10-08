package com.xiao.util;

import java.util.Random;

public class SkipList<E extends Comparable<? super E>> {
	
	
	private static class Node<E extends Comparable<? super E>>{
		
		private E value;
		@SuppressWarnings("unchecked")
		private final Node<E>[] pointers = new Node[33];
		
		

		Node(E value){
			this.value = value;
		}
		
		Node(){
			
		}
		
		@Override public String toString(){
			if(value!=null)
				return value.toString();
			else
				return "null";
		}
	}
	
	private final Node<E> head = new Node<E>();
	private int levels;
	private Random rand = new Random();
	private int size;
	
	
	public void add(E e){
		this.size++;
		//make the new node, if new node's level is higher than the list level, increase the list level
		Node<E> newNode = new Node<E>(e);
		int newLevel = 0;
		while(rand.nextInt(2)>0) // 50% chance to go up a level
			newLevel++;
		if(newLevel>this.levels)
			this.levels=newLevel;
		
		//System.out.println(newNode+" level: "+newLevel);
		
		
	    //start with the highest level, with parent = head
        Node<E> parent = head;
	    Node<E> current = null;
			
		for(int i=this.levels;i>=0;i--){
				
			current = parent.pointers[i];
			//System.out.println("now in level "+i);
				
			//in the while loop, the position of the new node is found for every level
			//either the end is met 'current==null' or the 'current>newNode'
			while(current!=null){
				//System.out.println("now parent: "+parent+" current:"+current);
				if(current.value.compareTo(e)<=0){//current node is less than new node,move
					parent = current;
					current = current.pointers[i];
				}
				else{
					break;
				}	
			}//end while
			//if new node's level is no less than i, it should be added to this level.
			if(newLevel>=i){
				newNode.pointers[i] = current;
				parent.pointers[i] = newNode;
				//System.out.println("added between "+parent+" and "+newNode.pointers[i]+" at level "+i);
			}
		}// end for
		
        //System.out.println("==========================================");
	}
	
	
	
	public void delete(E e){
		Node<E> current = null;
		Node<E> parent = head;
		for(int i=this.levels;i>=0;i--){
			current = parent.pointers[i];
			while(current!=null){
				if(current.value.compareTo(e)==0){//find it
					parent.pointers[i] = current.pointers[i];
					current.pointers[i]=null;
					this.size--;
					break;
				}
				else if(current.value.compareTo(e)<0){
					parent = current;
					current = current.pointers[i];
				}
				else{
					break;
				}
			}
		}
	}
	
	
	public boolean find(E e){
		Node<E> current = head;
		for(int i=this.levels;i>=0;i--){
			while(current.pointers[i]!=null){
				if(((E) current.pointers[i].value).compareTo(e)==0)
					return true;
				else if(((E) current.pointers[i].value).compareTo(e)<0)
					current = current.pointers[i];
				else
					break;
			}
		}
		return false;
	}
	
	//Test the performance of SkipList
	public boolean findStat(E e){
		long counter = 0;
		Node<E> current = head;
		for(int i=this.levels;i>=0;i--){
			while(current.pointers[i]!=null){
				counter++;
				if(((E) current.pointers[i].value).compareTo(e)==0){
					System.out.println("Count: "+counter);
					return true;
				}
				else if(((E) current.pointers[i].value).compareTo(e)<0)
					current = current.pointers[i];
				else
					break;
			}
		}
		System.out.println("Count: "+counter);
		return false;
	}
	
	
	public void displayList(){
		
		for(int i=this.levels;i>=0;i--){
			Node<E> current = head;
			Node<E> bottom = head;
			System.out.print("level "+i+": ");
			while(bottom!=null){
				if(current.pointers[i]!=bottom.pointers[0]){
					System.out.print("-----");
				}
				else{
					System.out.print("-->"+current.pointers[i]);
					current = current.pointers[i];
				}
				bottom = bottom.pointers[0];
			}
			System.out.println();
		}
	}
	
	
	public void display(){
		
		for(int i=this.levels;i>=0;i--){
			Node<E> current = head;
			System.out.print("level"+i+": ");
			while(current.pointers[i]!=null){
				System.out.print(current.pointers[i]+"-->");
				current = current.pointers[i];
			}
			System.out.println();
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
	
	public int size(){
		return this.size;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SkipList<Integer> l = new SkipList<Integer>();
		/*
		l.add(17);
		
		l.add(14);
		
		l.add(19);
		
		l.add(12);
		
		l.add(3);
		l.add(20);
		l.add(37);
		
		l.add(11);
		
		l.displayList();
		
		l.delete(14);
		
		l.displayList();
		*/
		Random r = new Random();
		for(int i=0;i<1000000;i++)
			l.add(r.nextInt(3000000));
		//l.display();
		//l.displayList();
		System.out.println(l.findStat(10000));
	}

}
