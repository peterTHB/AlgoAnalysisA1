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
	private SortStrings sorter = new SortStrings();
	
    @Override
	public void add(String item) {
    	
    	root = addRecursive(root, item);
    	
    } // end of add()
    
    // Recursive function to add a new node
    private Node addRecursive(Node root, String data) {
    	String[] splitData;
    	
    	// Checks if root node is null
    	if (root == null) {
    		data += ":1";
    		root = new Node(data, null, null);
    	} else {
    		splitData = root.getData().split(":");
    		
    		// Checks if element is either equal, greater than,
    		// or less than the element in the current node
        	if (data.compareToIgnoreCase(splitData[0]) == 0) {
        		// If element is the same ignoring case sensitivity,
        		// add to the left node
        		if (!data.equals(splitData[0])) {
        			root.setLeft(addRecursive(root.getLeft(), data));
        		// Else, increase element instance by one
        		} else {
        			String[] newData = root.getData().split(":");
        			int instance = Integer.parseInt(newData[1]) + 1; 
            		root.setData(newData[0] + ":" + instance);
        		}
        	// If data is greater 
        	} else if (data.compareToIgnoreCase(splitData[0]) > 0) {
        		root.setRight(addRecursive(root.getRight(), data));
        	// If data is less
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
    
    // Recursive function to search through the binary tree
    private int searchRecursive(Node root, String data) {
    	int instance = searchFailed;
    	
    	// Checks if root node is null
    	if (root != null) {
        	String[] getData = root.getData().split(":");
        	
        	// First checks if element is equal to the node's element
        	if (getData[0].equals(data)) {
        		instance = Integer.getInteger(getData[1]);
        	// Else, goes into its children nodes
        	} else {
        		// Goes into left child node
        		if (root.getLeft() != null) {
        			instance = searchRecursive(root.getLeft(), data);
        		// Goes into right child node
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
    
    // Recursive method to check if the list contains
    // the specified element
    private boolean containsRecursive(Node root, String data) {
    	boolean check = false;
    	
    	// Checks if root node is null
    	if (root != null) {
        	String[] getData = root.getData().split(":");
        	
        	// First checks if element is present in tree
        	if (getData[0].equals(data)) {
        		check = true;
        	// Else, proceeds to go into children nodes	
        	} else {
        		// Goes into left child node
        		if (root.getLeft() != null) {
        			check = containsRecursive(root.getLeft(), data);
        		// Goes into right child node
        		} else if (root.getRight() != null) {
        			check = containsRecursive(root.getRight(), data);
        		}
        	}
        }
    	
    	return check;
    }

    
    @Override
	public void removeOne(String item) {

        removeRecursive(root, null, item);

    } // end of removeOne()

    // Recursive function to remove element from tree
    private void removeRecursive(Node root, Node prev, String data) {
    	// Checks if current root is not null in both memory
    	// and element
    	if (root != null && root.getData() != null) {
    		String[] getData = root.getData().split(":");
    		
    		// Checks if root's element is equal to desired element
    		if (getData[0].equals(data)) {
    			// Decrease element's instance by one if it is greater than one
    			if (Integer.parseInt(getData[1]) > 1) {
    				int newInstance = Integer.parseInt(getData[1]) - 1;
    				root.setData(getData[0] + ":" + newInstance);
    			// Else if element has only one instance, proceed to remove it
    			} else {
    				// Checks if the children nodes are able to be accessed
    				// If only one children node can be accessed
    				if (root.getLeft() != null && root.getRight() == null || 
    						root.getLeft() == null && root.getRight() != null) {
    					setLeftRight(root, prev);
    				// If both children node can be accessed
    				} else if (root.getLeft() != null && root.getRight() != null) {
    					String searchedData = searchNode(root.getRight());
    					root.setData(searchedData);
    					removeRecursive(root.getLeft(), root, data);
    					removeRecursive(root.getRight(), root, data);
    				// If root node is not a parent node at all
    				} else {
    					root = null;
    				}
    			}
    		// Else, keep searching through tree through children nodes	
    		} else {
    			if (root.getLeft() != null) {
        			removeRecursive(root.getLeft(), root, data);
        		} else if (root.getRight() != null) {
        			removeRecursive(root.getRight(), root, data);
        		}
    		}
    	}
    }
    
    // Method to set previous node to the current node's children node
    private void setLeftRight(Node root, Node prev) {
    	if (prev.getLeft() != null) {
			if (prev.getLeft().getData().equals(root.getData())) {
				prev.setLeft(root.getLeft());
			}
		} else if (prev.getRight() != null) {
			if (prev.getRight().getData().equals(root.getData())) {
				prev.setRight(root.getRight());
			}
		}
    }
    
    // Method to find the smallest possible node
    // on the left side of the current node to 
    // replace the removed node
    private String searchNode(Node search) {
    	String obtain = null;
    	
    	if (search != null) {
    		if (search.getLeft() == null) {
    			obtain = search.getData();
    		} else {
    			searchNode(search.getLeft());
    		}
    	}
    	
    	return obtain;
    }
    
    @Override
	public String print() {
    	String list = "";
    	String newList = "";
    	
    	list = printRecursive(root);
    	
    	String[] dataList = list.split("\n");
    	
    	newList = sorter.sortArray(dataList);
        
        return newList;
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
    
    // Recursive method to help print between a range of values
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
