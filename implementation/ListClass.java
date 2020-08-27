package implementation;

import java.util.*;

/*
 * Personal list class to implement a linked list class
 * to help iterate through a list.
 * Contains the ability to use all multisets.
 */
@SuppressWarnings("serial")
public class ListClass<Type> extends LinkedList<Type> implements Iterable<Type>
{
	private Node head;
	
	// Constructor call for array
	public ListClass(String[] array, int count) {
		head = new Node(null, null);
		addArray(array, count);
	}
	
	// Constructor call for all node classes
	public ListClass(Node node, int count, int check) {
		head = new Node(null, null);
		Node current = head;
		if (check == 0) {
			addBST(current, node, count);
		} else {
			addNode(current, node, count);
		}
	}
	
	// Method to add array elements to linked list implementation
	private void addArray(String[] array, int count) {
		Node current = head;

		for (int i = 0; i < array.length; i++) {
			String[] currString = array[i].split(":");
			
			if (Integer.parseInt(currString[1]) == count) {
				current.setData(array[i].split(":")[0]);
				current.setNext(new Node(null, null));
				current = current.getNext();
			}
		}
	}
	
	// Method to add linked list nodes to linked list implementation
	private void addNode(Node current, Node enter, int count) {
		while (enter.getNext() != null) {
			String[] currString = enter.getData().split(":");
			
			if (Integer.parseInt(currString[1]) == count) {
				current.setData(enter.getData().split(":")[0]);
				current.setNext(new Node(null, null));
				current = current.getNext();
			}
			enter = enter.getNext();
		}
	}
	
	// Method to add binary search tree nodes to linked list 
	// implementation
	private void addBST(Node current, Node enter, int count) {
		if (enter != null) {
			String[] currString = enter.getData().split(":");
			if (Integer.parseInt(currString[1]) == count) {
				current.setData(enter.getData().split(":")[0]);
				
				current.setNext(new Node(null, null));
				
				current = current.getNext();
				
				addBST(current, enter.getLeft(), count);
				addBST(current, enter.getRight(), count);
			}
		}
	}
	
	public Iterator<Type> iterator() {
		Iterator<Type> it = new Iterator<Type>() {

			Node current = head;
			
			@Override
			public boolean hasNext()
			{
				return current.getNext() != null;
			}

			@SuppressWarnings("unchecked")
			@Override
			public Type next()
			{
				Type check = null;
				
				if (hasNext()) {
					check = (Type) current.getData();
					current = current.getNext();
				}
				
				return check;
			}

		};
		
		return it;
	}
}
