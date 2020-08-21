package implementation;

public class ListHelper
{
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
	
	public int getLength(Node root) {
		int length = 0;
		
		while (root != null) {
			length++;
			root = root.getNext();
		}
		
		return length;
	}
    	
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
        				prev.setNext(curr.getNext());
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
	
	public String printHelp(Node root, int length) {
		String list = "";
    	Node node = root;
    	
    	while (node != null) {
    		list += node.getData() + "\n";
    		node = node.getNext();
    	}
        
        return list;
	}
	
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
	
	public void unionList(RmitMultiset multiSet, String[] currList, String[] otherList) {
		for (int i = 0; i < currList.length; i++) {
    		String[] getDataArray = currList[i].split(":");
    		
    		String data = getDataArray[0];
    		int amount = Integer.parseInt(getDataArray[1]);
    		
    		for (int j = 0; j < amount; j++) {
    			multiSet.add(data);
    		}
    	}
    	
    	for (int i = 0; i < otherList.length; i++) {
    		String[] getDataOther = otherList[i].split(":");
    		
    		String data = getDataOther[0];
    		int amount = Integer.parseInt(getDataOther[1]);
    		
    		for (int j = 0; j < amount; j++) {
    			multiSet.add(data);
    		}
    	}
	}
	
	public void intersectList(RmitMultiset multiSet, String[] currList, String[] otherList) {
		for (int i = 0; i < currList.length; i++) {
    		String[] getDataArray = currList[i].split(":");
    		
    		for (int j = 0; j < otherList.length; j++) {
    			String[] getDataOther = otherList[j].split(":");
    			
    			if (getDataArray[0].equals(getDataOther[0])) {
    				int amountArray = Integer.parseInt(getDataArray[1]);
    				int amountOther = Integer.parseInt(getDataOther[1]);
    				
    				if (amountArray < amountOther) {
    					for (int k = 0; k < amountArray; k++) {
        					multiSet.add(getDataArray[0]);
        				}
    				} else if (amountOther < amountArray) {
    					for (int l = 0; l < amountOther; l++) {
        					multiSet.add(getDataOther[0]);
        				}
    				} else {
    					for (int k = 0; k < amountArray; k++) {
        					multiSet.add(getDataArray[0]);
        				}
    				}
    				
    			}
    			
    		}
    	}
	}
	
	public void differenceList(RmitMultiset multiSet, String[] currList, String[] otherList) {
		for (int i = 0; i < currList.length; i++) {
    		int getOtherInstance = 0;
    		int amount = 0;
    		String[] getDataArray = currList[i].split(":");
    		
    		for (int j = 0; j < otherList.length; j++) {
    			String[] getDataOther = otherList[j].split(":");
    			
    			if (getDataOther[0].equals(getDataArray[0])) {
    				getOtherInstance = Integer.parseInt(getDataOther[1]);
    			}
    		}
    		
    		if (getOtherInstance != 0) {
    			amount = Integer.parseInt(getDataArray[1]) - getOtherInstance;
    			
    			if (amount > 0) {
    				for (int k = 0; k < amount; k++) {
        				multiSet.add(getDataArray[0]);
        			}
    			}
    		} else {
    			amount = Integer.parseInt(getDataArray[1]);
    			for (int k = 0; k < amount; k++) {
    				multiSet.add(getDataArray[0]);
    			}
    		}
    	}
	}
	
}
