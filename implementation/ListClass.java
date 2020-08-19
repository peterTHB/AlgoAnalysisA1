package implementation;

import java.util.*;

@SuppressWarnings("serial")
public class ListClass<Type> extends LinkedList<Type> implements Iterable<Type>
{
	private Node current;
	
	public ListClass(String[] array, int count) {
		addArray(array, count);
	}
	
	public ListClass(Node node, int count, int check) {
		if (check == 0) {
			addBST(node, count);
		} else {
			addNode(node, count);
		}
	}
	
	private void addArray(String[] array, int count) {
		for (int i = 0; i < array.length; i++) {
			String[] currString = array[i].split(":");
					
			if (Integer.parseInt(currString[1]) == count) {
				current = new Node(array[i], null);
				current.getNext();
			}
		}
	}
	
	private void addNode(Node node, int count) {
		while (node != null) {
			String[] currString = node.getData().split(":");
			
			if (Integer.parseInt(currString[1]) == count) {
				current = new Node(node.getData(), null);
				current = current.getNext();
			}
		}
	}
	
	private void addBST(Node node, int count) {
		if (node != null) {
			String[] currString = node.getData().split(":");
			if (Integer.parseInt(currString[1]) == count) {
				current = new Node(node.getData(), null);
				current = current.getNext();
			}
			addBST(node.getLeft(), count);
			addBST(node.getNext(), count);
		}
	}
	
	public Iterator<Type> iterator() {
		Iterator<Type> it = new Iterator<Type>() {

			@Override
			public boolean hasNext()
			{
				boolean check = false;
				
				if (current != null) {
					check = true;
				}
				
				return check;
			}

			@SuppressWarnings("unchecked")
			@Override
			public Type next()
			{
				Type check = null;
				
				if (hasNext()) {
					check = (Type) current.getNext().getData();
				}
				
				return check;
			}

		};
		
		return it;
	}
}
