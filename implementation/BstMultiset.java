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
	private Node root = null;
	private ListHelper helper = new ListHelper();

    @Override
	public void add(String item) {
    	root = addRecursive(root, item);
    } // end of add()
    
    private Node addRecursive(Node root, String data) {
    	String[] splitData;
    	
    	if (root == null) {
    		data += ":1";
    		root = new Node(data, null, null);
    	} else {
    		splitData = root.getData().split(":");
    		
    		// positive is lower, negative is higher
        	if (data.compareToIgnoreCase(splitData[0]) == 0) {
        		if (!data.equals(splitData[0])) {
        			root.setLeft(addRecursive(root.getLeft(), data));
        		} else {
        			String[] newData = root.getData().split(":");
        			int instance = Integer.parseInt(newData[1]) + 1; 
            		root.setData(newData[0] + ":" + instance);
        		}
        	// if data is greater 
        	} else if (data.compareToIgnoreCase(splitData[0]) > 0) {
        		root.setRight(addRecursive(root.getRight(), data));
        	// if data is less
        	} else if (data.compareToIgnoreCase(splitData[0]) < 0) {
        		root.setLeft(addRecursive(root.getLeft(), data));
        	}
    	}
    	return root;
    }


    @Override
	public int search(String item) {
    	int instance = 0;
    	
        instance = searchRecursive(root, item);
        
        return instance;
    } // end of search()
    
    
    private int searchRecursive(Node root, String data) {
    	int instance = searchFailed;
    	
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
    	ListClass<String> listing = new ListClass<String>(root, instanceCount, 0);
    	
        return listing;
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
    			if (Integer.parseInt(getData[1]) > 1) {
    				int newInstance = Integer.parseInt(getData[1]) - 1;
    				root.setData(getData[0] + ":" + newInstance);
    			} else {
    				root.setData(null);
    			}
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
    		data += printRecursive(root.getLeft());
    		data += root.getData() + "\n";
    		data += printRecursive(root.getRight());
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
    	
    	if (root != null) {
    		String[] getData = root.getData().split(":");
    		
    		data += printRangeRecur(root.getLeft(), lower, upper);
    		if (getData[0].compareToIgnoreCase(lower) >= 0 && 
    				getData[0].compareToIgnoreCase(upper) <= 0) {
        		data += root.getData() + "\n";
    		}
    		data += printRangeRecur(root.getRight(), lower, upper);
    	}
    	
    	return data;
    }


    @Override
	public RmitMultiset union(RmitMultiset other) {
    	RmitMultiset newBstMultiSet = new BstMultiset();
    	String[] bstValues = this.print().split("\n");
    	String[] otherValues = other.print().split("\n");
    	
    	helper.unionList(newBstMultiSet, bstValues, otherValues);
    	
        return newBstMultiSet;
    } // end of union()


    @Override
	public RmitMultiset intersect(RmitMultiset other) {
    	RmitMultiset newBstMultiSet = new BstMultiset();
    	String[] bstValues = this.print().split("\n");
    	String[] otherValues = other.print().split("\n");
    	
    	helper.intersectList(newBstMultiSet, bstValues, otherValues);
    	
        return newBstMultiSet;
    } // end of intersect()


    @Override
	public RmitMultiset difference(RmitMultiset other) {
    	RmitMultiset newBstMultiSet = new BstMultiset();
    	String[] bstValues = this.print().split("\n");
    	String[] otherValues = other.print().split("\n");
    	
    	helper.differenceList(newBstMultiSet, bstValues, otherValues);
    	
        return newBstMultiSet;
    } // end of difference()

} // end of class BstMultiset
