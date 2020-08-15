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
    	if (array == null) {
    		array = new String[length];
    		array[length - 1] = elem; 
    	} else {
    		length++;
    		
    		String[] newArray = new String[length];
    		
    		for (int i = 0; i < length - 1; i++) {
    			newArray[i] = array[i];
    		}
    		
    		newArray[length - 1] = elem;
    		
    		array = new String[length];
    		
    		for (int j = 0; j < length; j++) {
    			array[j] = newArray[j];
    		}
    	}
    } // end of add()


    @Override
	public int search(String elem) {
    	int instance = 0;
    	
    	for (int i = 0; i < array.length; i++) {
    		if (array[i].equals(elem)) {
    			instance++;
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
        	if (array[i].equals(elem)) {
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
    	int position = 0;
    	int total = 0;
    	String printed = "";
    	SortStrings sorter = new SortStrings();
    	
    	String[] newArray = new String[array.length];
    	
    	for (int i = 0; i < array.length; i++) {
    		newArray[i] = array[i];
    	}
    	
        for (int i = 0; i < array.length; i++) {
        	if (array[i] != null) {
        		for (int j = position; j < array.length; j++) {
            		// if string doesn't exist yet
            		if (array[position] != null) {
            			if (array[i].equals(array[position])) {
            				total++;
            				array[position] = null;
            			}
            		}
            	}
        		printed = array[i] + Integer.toString(total) + "\n";
        	}
        	position++;
        }
        
        String sorted = sorter.Sort(printed);
        
        return sorted;
    } // end of OrderedPrint


    @Override
	public String printRange(String lower, String upper) {

        // Placeholder, please update.
        return new String();
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
