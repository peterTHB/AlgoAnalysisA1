package implementation;

/*
 * Auxiliary class to assist in misc. methods
 * for all multiset implementations.
 * Mostly used, however, in linked list 
 * implementations.
 */
public class ListHelper
{
	// Method to add nodes to both linked list classes
	// Used in both linked list multisets
	public Node addHelp(Node node, String data) {
		Node temp = new Node(data + ":1", null);
    	Node curr = node;
    	Node prev = null;
    	
    	if (node == null) {
    		node = temp;
    	} else {
    		boolean check = false;
    		
    		while (curr != null) {
    			String[] getData = curr.getData().split(":");
    			
    			if (getData[0].equals(data)) {
    				int instance = Integer.parseInt(getData[1]) + 1;
    				curr.setData(data + ":" + instance);
    				check = true;
    			}
    			prev = curr;
    			curr = curr.getNext();
        	}
    		if (check == false) {
    			prev.setNext(temp);
    		}
    	}
    	
    	return node;
	}	
	
	// Method to get length of a linked list class
	// Used in both linked list multisets
	public int getLength(Node root) {
		int length = 0;
		
		while (root != null) {
			length++;
			root = root.getNext();
		}
		
		return length;
	}
    	
	// Method to remove an element from a linked list
	// Used in both linked list multisets
	public Node removeHelp(Node root, String data) {
		Node curr = root;
		Node prev = null;
        
        while (curr.getNext() != null) {
        	String[] getData = curr.getData().split(":");
        	
        	if (getData[0].equals(data)) {
        		if (Integer.parseInt(getData[1]) > 1) {
        			int newInstance = Integer.parseInt(getData[1]) - 1;
        			curr.setData(getData[0] + ":" + newInstance);
        		} else if (Integer.parseInt(getData[1]) == 1) {
        			if (curr.getNext() != null) {
        				if (prev != null) {
        					prev.setNext(curr.getNext());
        				} else {
        					curr = curr.getNext();
        				}
        			} else {
        				prev.setNext(null);
        			}
        		} 
    		} 
        	prev = curr;
			curr = curr.getNext();
        }
		
		return root;
	}
	
	// Method to search through a linked list multiset
	// Used in both linked list multisets
	public int searchRecursive(Node node, String data) {
    	int instance = 0;
    	
    	if (node != null) {
    		String[] getData = node.getData().split(":");
    		if (getData[0].equals(data)) {
    			instance = Integer.parseInt(getData[1]);
    		} else {
    			instance = searchRecursive(node.getNext(), data);
    		}
    	}
    	
    	return instance;
    }
	
	// Method to search through list and determine if desired
	// element is in linked list
	// Used in both linked list multisets
	public boolean containsRecur(Node node, String data) {
    	boolean check = false;

    	if (node != null) {
    		String[] getData = node.getData().split(":");
    		if (getData[0].equals(data)) {
    			check = true;
    		} else {
    			check = containsRecur(node.getNext(), data);
    		}
    	}
    	
        return check;
    }
	
	// Method to simply print out all elements in linked list
	// Used in both linked list multisets
	public String printHelp(Node root, int length) {
		String list = "";
    	Node node = root;
    	
    	while (node != null) {
    		list += node.getData() + "\n";
    		node = node.getNext();
    	}
        
        return list;
	}
	
	// Method to print out elements that are within range
	// Used in both linked list multisets
	public String printRangeHelp(Node root, String lower, String upper) {
		String list = "";
    	Node node = root;
    	
    	while (node != null) {
    		String[] getData = node.getData().split(":");
    		
    		if (getData[0].compareToIgnoreCase(lower) >= 0 && 
    				getData[0].compareToIgnoreCase(upper) <= 0) {
    			list += node.getData() + "\n";
    		}
    		node = node.getNext();
    	}
    	
        return list;
	}
	
	// Method to preform the union operation between two multisets
	// Used by all multiset implementations
	public void unionList(RmitMultiset multiSet, String[] currList, String[] otherList) {
		// Adds currList multiset's elements to new multiset
		for (int i = 0; i < currList.length; i++) {
    		String[] getDataArray = currList[i].split(":");
    		
    		String data = getDataArray[0];
    		int amount = Integer.parseInt(getDataArray[1]);
    		
    		for (int j = 0; j < amount; j++) {
    			multiSet.add(data);
    		}
    	}
    	
		// Adds otherList multiset's elements to new multisets
    	for (int i = 0; i < otherList.length; i++) {
    		String[] getDataOther = otherList[i].split(":");
    		
    		String data = getDataOther[0];
    		int amount = Integer.parseInt(getDataOther[1]);
    		
    		for (int j = 0; j < amount; j++) {
    			multiSet.add(data);
    		}
    	}
	}
	
	// Method to preform the intersect operation between two multisets
	// Used by all multiset implementations
	public void intersectList(RmitMultiset multiSet, String[] currList, String[] otherList) {
		
		// Uses elements and length from the currList multiset
		for (int i = 0; i < currList.length; i++) {
    		String[] getDataArray = currList[i].split(":");
    		
    		// Uses elements and length from the otherList multiset
    		for (int j = 0; j < otherList.length; j++) {
    			String[] getDataOther = otherList[j].split(":");
    			
    			// Checks if element from currList is equivalent to element
    			// from otherList
    			if (getDataArray[0].equals(getDataOther[0])) {
    				int amountArray = Integer.parseInt(getDataArray[1]);
    				int amountOther = Integer.parseInt(getDataOther[1]);
    				
    				// Checks for the least amount of instances
    				// If currList element has less instances
    				if (amountArray < amountOther) {
    					for (int k = 0; k < amountArray; k++) {
        					multiSet.add(getDataArray[0]);
        				}
    				// If otherList element has less instances
    				} else if (amountOther < amountArray) {
    					for (int l = 0; l < amountOther; l++) {
        					multiSet.add(getDataOther[0]);
        				}
    				// If elements in both lists have the same 
    				// amount of instances
    				} else {
    					for (int k = 0; k < amountArray; k++) {
        					multiSet.add(getDataArray[0]);
        				}
    				}
    				
    			}
    			
    		}
    	}
	}
	
	// Method to preform the difference operation between two multisets
	// Used by all multiset implementations
	public void differenceList(RmitMultiset multiSet, String[] currList, String[] otherList) {
		// Uses length and elements from currList
		for (int i = 0; i < currList.length; i++) {
    		int getOtherInstance = 0;
    		int amount = 0;
    		String[] getDataArray = currList[i].split(":");
    		
    		// Checks if element exists in otherList
    		for (int j = 0; j < otherList.length; j++) {
    			String[] getDataOther = otherList[j].split(":");
    			
    			if (getDataOther[0].equals(getDataArray[0])) {
    				getOtherInstance = Integer.parseInt(getDataOther[1]);
    			}
    		}
    		
    		// If element does exist in otherList
    		if (getOtherInstance != 0) {
    			amount = Integer.parseInt(getDataArray[1]) - getOtherInstance;
    			// Adds element and instance difference to new list
    			if (amount > 0) {
    				for (int k = 0; k < amount; k++) {
        				multiSet.add(getDataArray[0]);
        			}
    			}
    		// If element doesn't exist in otherList
    		} else {
    			// Add currList element and instance to new list
    			amount = Integer.parseInt(getDataArray[1]);
    			for (int k = 0; k < amount; k++) {
    				multiSet.add(getDataArray[0]);
    			}
    		}
    	}
	}
	
}
