package implementation;

import java.util.List;

/**
 * Ordered linked list implementation of a multiset.  See comments in RmitMultiset to
 * understand what each overriden method is meant to do.
 *
 * @author Jeffrey Chan & Yongli Ren, RMIT 2020
 */
public class OrderedLinkedListMultiset extends RmitMultiset
{

	private Node head;
	private int length = 0;
	private LinkedListHelps helper = new LinkedListHelps();
	private SortStrings sorter = new SortStrings();
	
    @Override
	public void add(String item) {
    	int i = 0;
        helper.addRecursive(head, item);
        length++;
        
        while (i < length) {
        	sorter.sortLLAlpha(head);
        	i++;
        }
    } // end of add()


    @Override
	public int search(String item) {
        int instance = searchFailed;
        
        instance = helper.searchRecursive(head, item);

        return instance;
    } // end of search()


    @Override
	public List<String> searchByInstance(int instanceCount) {
    	ListClass<String> listing = new ListClass<String>(head, instanceCount, 1);
    	
        return listing;
    } // end of searchByInstance


    @Override
	public boolean contains(String item) {
        boolean check = false;

        check = helper.containsRecur(head, item);
        
        return check;
    } // end of contains()


    @Override
	public void removeOne(String item) {
        boolean check = false;
        check = helper.removeHelp(head, item);
        
        if (check == true) {
        	length--;
        }
    } // end of removeOne()
    

    @Override
	public String print() {
    	String list = "";
    	int i = 0;
    	int j = 0;
    	
    	while (i < length) {
    		sorter.sortLLInst(head);
    		i++;
    	}
    	list = helper.printHelp(head);
    	while (j < length) {
    		sorter.sortLLAlpha(head);
    		j++;
    	}
    	
        return list;
    } // end of OrderedPrint


    @Override
	public String printRange(String lower, String upper) {
    	String list = "";

    	list = helper.printRangeHelp(head, lower, upper);
    	
        return list;
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

} // end of class OrderedLinkedListMultiset
