package implementation;

import java.util.List;

/**
 * Array implementation of a multiset.  See comments in RmitMultiset to
 * understand what each overriden method is meant to do.
 *
 * @author Jeffrey Chan & Yongli Ren, RMIT 2020
 */
public class ArrayMultiset extends RmitMultiset
{
	private String[] array = null;
	private final static int INITIAL_LENGTH = 1;
	private SortStrings sorter = new SortStrings();
	private ListHelper helper = new ListHelper();
	
    @Override
	public void add(String elem) {
    	int instance = 1;
    	boolean check = false;
    	
    	// Checks if multiset array contains no values yet
    	if (array == null) {
    		array = new String[INITIAL_LENGTH];
    		array[INITIAL_LENGTH - 1] = elem + ":" + Integer.toString(instance); 
    	} else {
    		int length = array.length + 1;
    		String[] newArray = new String[length];
    		
    		// Checks if element exists
    		// If it does, adds an additional instance to the element
    		for (int i = 0; i < array.length; i++) {
    			String[] arrayString = array[i].split(":");
    			if (arrayString[0].equals(elem)) {
    				instance = Integer.parseInt(arrayString[1]) + 1;
    				array[i] = elem + ":" + instance;
    				check = true;
    			}
    		}
    		
    		// If the desired element does not exist within the list
    		if (check == false) {
    			// New array takes all elements from current list
    			for (int i = 0; i < length - 1; i++) {
        			newArray[i] = array[i];
        		}
        		
    			// Adds new desired element to new list
        		newArray[length - 1] = elem + ":" + instance;
        		
        		// Replace current list with new list
        		array = new String[length];
        		
        		for (int j = 0; j < length; j++) {
        			array[j] = newArray[j];
        		}
    		}
    	}
    } // end of add()


    @Override
	public int search(String elem) {
    	int instance = searchFailed;
    	
    	for (int i = 0; i < array.length; i++) {
    		String[] arrayString = array[i].split(":");
    		if (arrayString[0].equals(elem)) {
    			instance = Integer.parseInt(arrayString[1]);
    		}
    	}
    	
        return instance;
    } // end of search()


    @Override
    public List<String> searchByInstance(int instanceCount) {
    	ListClass<String> listing = new ListClass<String>(array, instanceCount);
    	
        return listing;
    } // end of searchByInstance


    @Override
	public boolean contains(String elem) {
        boolean check = false;
    	
        for (int i = 0; i < array.length; i++) {
        	String[] arrayString = array[i].split(":");
        	if (arrayString[0].equals(elem)) {
        		check = true;
        	}
        }
        
        // Placeholder, please update.
        return check;
    } // end of contains()


    @Override
	public void removeOne(String elem) {
    	boolean check = false;
    	
        if (array != null) {
        	// Checks if element is within array first
        	for (int i = 0; i < array.length; i++) {
        		String[] getData = array[i].split(":");
        		if (getData[0].equals(elem)) {
        			// Removes instance from desired element
        			if (Integer.parseInt(getData[1]) > 1) {
        				int newInstance = Integer.parseInt(getData[1]) - 1;
        				array[i] = getData[0] + ":" + newInstance;
        			} else {
        				array[i] = null;
            			check = true;
        			}
        		}
        	}
        	
        	// If element only has one instance
        	if (check == true) {
        		int length = array.length - 1;
            	int iterate = 0;
            	
            	String[] newArray = new String[length];
            	
            	// New array takes all elements from current list
            	// Does not include desired element to be removed
                for (int j = 0; j < array.length; j++) {
                	if (array[j] != null) {
                		newArray[iterate] = array[j];
                		iterate++;
                	}
                }
                
                // Adds all elements minus the removed element back
                // into current array
                array = new String[length];
                
                for (int k = 0; k < array.length; k++) {
                	array[k] = newArray[k];
                }
        	}
        	
        }
    } // end of removeOne()


    @Override
	public String print() {
    	
        String sorted = sorter.sortArray(array);
        
        return sorted;
        
    } // end of OrderedPrint


    @Override
	public String printRange(String lower, String upper) {
    	String sorted = sorter.sortArrayRange(array, lower, upper);

        return sorted;
    } // end of printRange()


    @Override
	public RmitMultiset union(RmitMultiset other) {
    	RmitMultiset newArrayMultiSet = new ArrayMultiset();
    	String[] arrayValues = this.print().split("\n");
    	String[] otherValues = other.print().split("\n");
    	
    	helper.unionList(newArrayMultiSet, arrayValues, otherValues);
    	
        return newArrayMultiSet;
    } // end of union()


    @Override
	public RmitMultiset intersect(RmitMultiset other) {
    	RmitMultiset newArrayMultiSet = new ArrayMultiset();

    	String[] arrayValues = this.print().split("\n");
    	String[] otherValues = other.print().split("\n");
    	
    	helper.intersectList(newArrayMultiSet, arrayValues, otherValues);
    	
        return newArrayMultiSet;
    } // end of intersect()


    @Override
	public RmitMultiset difference(RmitMultiset other) {
    	RmitMultiset newArrayMultiSet = new ArrayMultiset();
    	String[] arrayValues = this.print().split("\n");
    	String[] otherValues = other.print().split("\n");
    	
    	helper.differenceList(newArrayMultiSet, arrayValues, otherValues);
    	
        return newArrayMultiSet;
    } // end of difference()

} // end of class ArrayMultiset
