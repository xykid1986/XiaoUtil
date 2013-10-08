package com.xiao.util;

import java.util.*;

/**
 * Binary search tree
 * @author Xiao
 *
 * @param <E> generic type
 */
public class BST<E extends Comparable<? super E>> {
	
	/**
	 * A helper class for binary search tree
	 * @author Xiao
	 *
	 * @param <E>
	 */
	private static class Node<E extends Comparable<? super E>>{
		
		private E element;
		private Node<E> left;
		private Node<E> right;
		
		Node(E e){
			this.element = (E)e;
		}
		
		private boolean isLeaf(){
			return left==null&&right==null;
		}
		
		@Override public String toString(){
			return element.toString();
		}

	}
	
	private int size;
	Node<E> root;
	
	/**
	 * 
	 */
	public BST(){
		
	}
	
	/**
	 * Return the size of the binary search tree
	 * @return size of binary search tree
	 */
	public int size(){
		return size;
	}
	
	/**
	 * Add an element
	 * @param e
	 */
	
	public void add(E e){
		Node<E> newNode = new Node<E>(e);
		size++;
		if(root == null)
			root = newNode;
		else{
			Node<E> current = root;
			Node<E> parent = null;
			while(true){
				parent = current;
				if(((E)e).compareTo(current.element)>0){
					current=current.right;
					if(current==null){
						parent.right=newNode;
						return;
					}	
				}
				else{
					current=current.left;
					if(current==null){
						parent.left=newNode;
						return;
					}
				}
			}
		}
	}
	
	
	public void delete(E e){
		Node<E> parent = null;
		Node<E> current = root;
		boolean isLeft = true;
		while(current!=null){
			
			if(((E)e).compareTo(current.element)>0){
				parent = current;
				current = current.right;
				isLeft = false;
			}else if(((E)e).compareTo(current.element)<0){
				parent = current;
				current = current.left;
				isLeft = true;
			}else{
				break;
			}
		}
		
		if(current==null)
			return;
		
		
		if(current.isLeaf()){
			if(current==root)
				root = null;
			else if(isLeft)
				parent.left = null;
			else
				parent.right = null;
		}
		else if(current.left!=null&&current.right==null){
			if(current==root)
				root = root.left;
			else if(isLeft)
				parent.left = current.left;
			else
				parent.right = current.left;
		}
		else if(current.left==null&&current.right!=null){
			if(current==root)
				root = root.right;
			else if(isLeft)
				parent.left = current.right;
			else
				parent.right = current.right;
		}else{ //when both left and right of current are not null
			Node<E> successor = findSuccessor(current);
			//successor.left = current.left;
			successor.right = current.right;
			if(current==root)
				root = successor;
			else if(isLeft)
				parent.left = successor;
			else
				parent.right = successor;
		}
	}
	
	//two points: 1. find the successor(in left or right subtree). 
	//2. if successor is not children of the deleteNode, assign the left(or right) subtree of deleteNode 
	//to successor, assign the subtree if successor to its parent. if successor is child of deleteNode,
	//those assignments are not necessary.
	private Node<E> findSuccessor(Node<E> delete){
		Node<E> parent = delete;
		Node<E> current = delete.left;
		while(current.right!=null){
			parent = current;
			current = current.right;
		}
		if(parent != delete){
			parent.right = current.left;
			current.left = delete.left;
		}
		   
		//System.out.println(" found su "+current+" su parent "+parent);
		return current;
		
		
	}
	//=============================search========================
	/**
	 * @param e
	 * @return
	 */
	
	public E get(E e){
		Node<E> current = root;
		while(current!=null){
			if(((E)e).compareTo(current.element)>0)
				current = current.right;
			else if(((E)e).compareTo(current.element)<0)
				current = current.left;
			else
				return current.element;
		}
		return null;
	}
	
	
	public boolean find(E e){
		Node<E> current = root;
		while(current!=null){
			if(((E)e).compareTo(current.element)>0)
				current = current.right;
			else if(((E)e).compareTo(current.element)<0)
				current = current.left;
			else
				return true;
		}
		return false;
	}
	//=============================traverse=======================
	public void inorder(Node<E> n){
		if(n==null)
			return;
		else{
			inorder(n.left);
			System.out.print(n+" ");
			inorder(n.right);
		}
	}
	//=========================display==============================
	public void displayTree(){
		System.out.println("========================================================================");
		boolean done = false;
		LinkedList<Node<E>> out = new LinkedList<Node<E>>();
		int level = 6;
        out.add(root);
        while(!done){
        	done=true;
        	LinkedList<Node<E>> in = new LinkedList<Node<E>>();//for children
        	
        	while(out.size()>0){
        		for(int i=0;i<Math.pow(2, level);i++){
        		    System.out.print(" ");
        	    }
        		Node<E> current = out.poll();
        		if(current!=null){
        			System.out.print(current);
        			in.add(current.left);
        			in.add(current.right);
        			if(current.left!=null||current.right!=null)
        			    done = false;
        		}else{
        			System.out.print("--");
        			in.add(null);
        			in.add(null);
        		}
        		for(int i=0;i<Math.pow(2, level)-2;i++){
        		     System.out.print(" ");
        	    }
        	}
        	
        	System.out.println();
            level--;
            while(in.size()>0)
            	out.add(in.poll());
        }
       System.out.println("========================================================================");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BST<Integer> bst = new BST<Integer>();
		bst.add(10);
		bst.add(14);
		bst.add(13);
		bst.add(12);
		bst.add(11);
		bst.add(22);
		//bst.add(20);
		bst.add(8);
		bst.add(6);
		bst.add(9);
		bst.add(23);
		/*
		BST<Character> bst = new BST<Character>();
		bst.add('h');
		bst.add('a');
		bst.add('j');
		bst.add('o');
		bst.add('t');
		bst.add('k');
		*/
		bst.displayTree();
		//System.out.println(bst.find(21));
		//bst.inorder(bst.root);
		//bst.delete(12);
		//bst.delete(23);
		//bst.inorder(bst.root);
		bst.delete(10);
		bst.displayTree();

	}

}
