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
	private int length = 1;
	
    @Override
	public void add(String elem) {
    	int instance = 1;
    	int position = -1;
    	// Add string:instance
    	if (array == null) {
    		array = new String[length];
    		array[length - 1] = elem + ":" + Integer.toString(instance); 
    	} else {
    		length++;
    		
    		String[] newArray = new String[length];
    		
    		for (int i = 0; i < length; i++) {
    			String[] arrayString = array[i].split(":");
    			if (arrayString[0] == elem) {
    				position = i;
    				instance = Integer.parseInt(arrayString[1]) + 1;
    			}
    		}
    		
    		if (position != -1) {
    			array[position] = elem + ":" + instance;
    		} else {
	        	for (int i = 0; i < length - 1; i++) {
	    			newArray[i] = array[i];
	    		}
	    		
	    		newArray[length - 1] = elem + ":" + instance;
	    		
	    		array = new String[length];
	    		
	    		for (int j = 0; j < length; j++) {
	    			array[j] = newArray[j];
	    		}
    		}
    	}
    } // end of add()


    @Override
	public int search(String elem) {
    	int instance = 0;
    	
    	for (int i = 0; i < array.length; i++) {
    		String[] arrayString = array[i].split(":");
    		if (arrayString[0].equals(elem)) {
    			instance = Integer.parseInt(arrayString[1]);
    		}
    	}
    	
    	if (instance == 0) {
    		instance = searchFailed;
    	}
    	
        return instance;
    } // end of search()


    @Override
    public List<String> searchByInstance(int instanceCount) {
    	
    	
        // Placeholder, please update.
        return null;
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
    	int position = 0;
    	
        if (array != null) {
        	for (int i = 0; i < array.length; i++) {
        		if (array[i].equals(elem)) {
        			position = i;
        		}
        	}
        	
        	length--;
        	
        	array[position] = null;
        	
        	String[] newArray = new String[length];
        	
        	int iterate = 0;
            
            for (int j = 0; j < newArray.length; j++) {
            	if (array[j] != null) {
            		newArray[iterate] = array[j];
            		iterate++;
            	}
            }
            
            array = new String[length];
            
            for (int k = 0; k < array.length; k++) {
            	array[k] = newArray[k];
            }
        }
        
    } // end of removeOne()


    @Override
	public String print() {
    	SortStrings sorter = new SortStrings();
        
        String sorted = sorter.sortArray(array);
        
        return sorted;
    } // end of OrderedPrint


    @Override
	public String printRange(String lower, String upper) {
    	SortStrings sorter = new SortStrings();
    	
    	String sorted = sorter.sortArrayRange(array, lower, upper);
    	
        // Placeholder, please update.
        return sorted;
    } // end of printRange()


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

} // end of class ArrayMultiset
