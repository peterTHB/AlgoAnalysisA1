package implementation;

import java.util.List;

/**
 * BST implementation of a multiset.  See comments in RmitMultiset to
 * understand what each overriden method is meant to do.
 *
 * @author Jeffrey Chan & Yongli Ren, RMIT 2020
 */
public class BstMultiset extends RmitMultiset
{
	Node root;

    @Override
	public void add(String item) {
    	addRecursive(root, item);
    } // end of add()
    
    private void addRecursive(Node root, String data) {
    	String getData;
    	String[] splitData;
    	
    	if (root == null) {
    		data += ":1";
    		root = new Node(data, null, null);
    	} else {
    		getData = root.getData();
    		splitData = getData.split(":");
    		
    		// positive is lower, negative is higher
        	if (data.compareToIgnoreCase(splitData[0]) == 0) {
        		if (!data.equals(splitData[0])) {
        			addRecursive(root.getLeft(), data);
        		} else {
        			String[] newData = root.getData().split(":");
            		newData[1] += 1;
            		root.setData(newData[0] + newData[1]);
        		}
        	// if data is greater 
        	} else if (data.compareToIgnoreCase(splitData[1]) > 1) {
        		addRecursive(root.getRight(), data);
        	// if data is less
        	} else if (data.compareToIgnoreCase(splitData[1]) < 1) {
        		addRecursive(root.getLeft(), data);
        	}
    	}
    }


    @Override
	public int search(String item) {
    	int instance = 0;
    	
        instance = searchRecursive(root, item);
        
        return instance;
    } // end of search()
    
    private int searchRecursive(Node root, String data) {
    	int instance = -1;
    	
    	if (root != null) {
        	String[] getData = root.getData().split(":");
        	
        	if (getData[0].equals(data)) {
        		instance = Integer.getInteger(getData[1]);
        	} else {
        		if (root.getLeft() != null) {
        			instance = searchRecursive(root.getLeft(), data);
        		} else if (root.getRight() != null) {
        			instance = searchRecursive(root.getRight(), data);
        		}
        	}
        }
    	return instance;
    }


    @Override
	public List<String> searchByInstance(int instanceCount) {

        // Placeholder, please update.
        return null;
    } // end of searchByInstance    


    @Override
	public boolean contains(String item) {
        boolean check = false;
        
        check = containsRecursive(root, item);
        
        return check;
    } // end of contains()
    
    private boolean containsRecursive(Node root, String data) {
    	boolean check = false;
    	
    	if (root != null) {
        	String[] getData = root.getData().split(":");
        	
        	if (getData[0].equals(data)) {
        		check = true;
        	} else {
        		if (root.getLeft() != null) {
        			check = containsRecursive(root.getLeft(), data);
        		} else if (root.getRight() != null) {
        			check = containsRecursive(root.getRight(), data);
        		}
        	}
        }
    	
    	return check;
    }

    
    @Override
	public void removeOne(String item) {
        removeRecursive(root, item);
    } // end of removeOne()

    
    private void removeRecursive(Node root, String data) {
    	if (root != null) {
    		String[] getData = root.getData().split(":");
    		
    		if (getData[0].equals(data)) {
    			root.setData(null);
    		} else {
    			if (root.getLeft() != null) {
        			removeRecursive(root.getLeft(), data);
        		} else if (root.getRight() != null) {
        			removeRecursive(root.getRight(), data);
        		}
    		}
    	}
    }
    
    
    @Override
	public String print() {
    	String list = "";
    	
    	list = printRecursive(root);
        
        return list;
    } // end of OrderedPrint

    
    private String printRecursive(Node root) {
    	String data = "";
    	
    	if (root != null) {
    		printRecursive(root.getLeft());
    		data += root.getData() + "\n";
    		printRecursive(root.getRight());
    	}
    	
    	return data;
    }
    

    @Override
	public String printRange(String lower, String upper) {
    	String list = "";
    	
        list = printRangeRecur(root, lower, upper);
    	
        return list;
    } // end of printRange()
    
    
    private String printRangeRecur(Node root, String lower, String upper) {
    	String data = "";
    	
    	String[] getData = root.getData().split(":");
    	
    	if (root != null) {
    		if (getData[0].compareTo(lower) >= 1 && getData[0].compareTo(upper) <= 1) {
    			printRecursive(root.getLeft());
        		data += root.getData() + "\n";
        		printRecursive(root.getRight());
    		}
    	}
    	
    	return data;
    }


    @Override
	public RmitMultiset union(RmitMultiset other) {

        // Placeholder, please update.
        return null;
    } // end of union()


    @Override
	public RmitMultiset intersect(RmitMultiset other) {

        // Placeholder, please update.
        return null;
    } // end of intersect()


    @Override
	public RmitMultiset difference(RmitMultiset other) {

        // Placeholder, please update.
        return null;
    } // end of difference()

} // end of class BstMultiset
