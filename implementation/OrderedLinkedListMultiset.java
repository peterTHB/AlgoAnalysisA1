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
	private ListHelper helper = new ListHelper();
	private SortStrings sorter = new SortStrings();
	
    @Override
	public void add(String item) {
    	int i = 0;
    	
    	// Helper method to add new node
    	head = helper.addHelp(head, item);
    	
    	// Get length of list to help sort list
    	length = helper.getLength(head);
        
    	// Sorts list into alphabetical order
        while (i < length - 1) {
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
        head = helper.removeHelp(head, item);
        
        length = helper.getLength(head);
    } // end of removeOne()
    

    @Override
	public String print() {
    	String list = "";
    	int i = 0;
    	int j = 0;

    	// Sorts list into instance order
    	while (i < length - 1) {
    		sorter.sortLLInst(head);
    		i++;
    	}
    	
    	list = helper.printHelp(head, length);
    	
    	// Sorts list into alphabetical order after
    	// list has been compiled to be printed
    	while (j < length - 1) {
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
    	RmitMultiset newOllMultiSet = new OrderedLinkedListMultiset();
    	String[] ollValues = this.print().split("\n");
    	String[] otherValues = other.print().split("\n");
    	
    	helper.unionList(newOllMultiSet, ollValues, otherValues);
    	
        return newOllMultiSet;
    } // end of union()


    @Override
	public RmitMultiset intersect(RmitMultiset other) {
    	RmitMultiset newOllMultiSet = new OrderedLinkedListMultiset();

    	String[] ollValues = this.print().split("\n");
    	String[] otherValues = other.print().split("\n");
    	
    	helper.intersectList(newOllMultiSet, ollValues, otherValues);
    	
        return newOllMultiSet;
    } // end of intersect()


    @Override
	public RmitMultiset difference(RmitMultiset other) {
    	RmitMultiset newOllMultiSet = new OrderedLinkedListMultiset();
    	String[] ollValues = this.print().split("\n");
    	String[] otherValues = other.print().split("\n");
    	
    	helper.differenceList(newOllMultiSet, ollValues, otherValues);
    	
        return newOllMultiSet;
    } // end of difference()

} // end of class OrderedLinkedListMultiset
