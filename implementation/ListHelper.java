package implementation;

public class ListHelper
{
	public void addRecursive(Node node, String data) {
    	if (node == null) {
        	data += ":1";
        	node = new Node(data, null);
        } else {
        	String[] getData = node.getData().split(":");
        	int instance = 0;
        	if (getData[0].equals(data)) {
        		instance = Integer.getInteger(getData[1]) + 1;
        		String newData = data + ":" + instance;
        		node.setData(newData);
        	} else if (node.getNext() == null) {
        		data += ":1";
        		Node newNode = new Node(data, null);
        		node.setNext(newNode);
        	} else {
        		addRecursive(node.getNext(), data);
        	}
        }
    }
	
	public boolean removeHelp(Node root, String data) {
		boolean check = false;
		
		Node curr = root;
        Node prev = null;
        
        if (curr != null) {
        	String[] getData = curr.getData().split(":");
        	
        	if (getData[0].equals(data)) {
    			root = curr.getNext();
    		} else {
    			while (curr != null && curr.getData().split(":")[0] != data) {
    				prev = curr;
    				curr = curr.getNext();
    			}
    			
    			if (curr != null) {
    				prev.setNext(curr.getNext());
    				check = true;
    			}
    		}
        }
		
		return check;
	}
	
	public int searchRecursive(Node node, String data) {
    	int instance = 0;
    	
    	if (node != null) {
    		String[] getData = node.getData().split(":");
    		if (getData[0].equals(data)) {
    			instance = Integer.parseInt(getData[1]);
    		} else {
    			searchRecursive(node.getNext(), data);
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
    			searchRecursive(node.getNext(), data);
    		}
    	}
    	
        return check;
    }
	
	public String printHelp(Node root) {
		String list = "";
    	Node node = root;
    	
    	while (node.getData() != null) {
    		list += node.getData() + "\n";
    		node = node.getNext();
    	}
        
        return list;
	}
	
	public String printRangeHelp(Node root, String lower, String upper) {
		String list = "";
    	Node node = root;
    	
    	while (node.getData() != null) {
    		String[] getData = node.getData().split(":");
    		
    		if (getData[0].compareToIgnoreCase(lower) >= 1 && 
    				getData[0].compareToIgnoreCase(upper) <= 1) {
    			list += node.getData() + "\n";
        		node = node.getNext();
    		}
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
    			String[] getDataOther = otherList[i].split(":");
    			
    			if (getDataArray[0].equals(getDataOther[0])) {
    				int amount = Integer.parseInt(getDataArray[1]);
    				
    				for (int k = 0; k < amount; k++) {
    					multiSet.add(getDataArray[0]);
    				}
    				
    			}
    			
    		}
    	}
	}
	
	public void differenceList(RmitMultiset multiSet, String[] currList, String[] otherList) {
		for (int i = 0; i < currList.length; i++) {
    		boolean check = false;
    		String[] getDataArray = currList[i].split(":");
    		
    		for (int j = 0; j < otherList.length; j++) {
    			String[] getDataOther = otherList[j].split(":");
    			
    			if (getDataOther.equals(otherList)) {
    				check = true;
    			}
    		}
    		
    		if (check == false) {
    			int amount = Integer.parseInt(getDataArray[1]);
    			
    			for (int k = 0; k < amount; k++) {
    				multiSet.add(getDataArray[0]);
    			}
    		}
    	}
	}
	
}
