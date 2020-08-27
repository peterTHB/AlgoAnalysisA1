package implementation;

import java.util.List;

/**
 * Dual linked list implementation of a multiset.  See comments in RmitMultiset to
 * understand what each overriden method is meant to do.
 *
 * @author Jeffrey Chan & Yongli Ren, RMIT 2020
 */
public class DualLinkedListMultiset extends RmitMultiset
{
	private Node headAlpha;
	private Node headInst;
	private int length = 0;
	private ListHelper helper = new ListHelper();
	private SortStrings sorter = new SortStrings();
	
    @Override
	public void add(String item) {
    	int i = 0;
    	int j = 0;
    	
    	// Helper class to add a new node
        headAlpha = helper.addHelp(headAlpha, item);
        headInst = helper.addHelp(headInst, item);
        
        length++;
        
        // Sorts first list into alphabetical order
        while (i < length - 1) {
        	sorter.sortLLAlpha(headAlpha);
        	i++;
        }
        
        // Sorts second list into instance order
        while (j < length - 1) {
        	sorter.sortLLInst(headInst);
        	j++;
        }
    } // end of add()


    @Override
	public int search(String item) {
    	int instance = searchFailed;
    	
    	instance = helper.searchRecursive(headInst, item);
        
        return instance;
    } // end of search()


    @Override
	public List<String> searchByInstance(int instanceCount) {
    	ListClass<String> listing = new ListClass<String>(headInst, instanceCount, 0);
    	
        return listing;
    } // end of searchByInstance    


    @Override
	public boolean contains(String item) {
    	boolean check = false;

        check = helper.containsRecur(headInst, item);
        
        return check;
    } // end of contains()


    @Override
	public void removeOne(String item) {
    	
        headAlpha = helper.removeHelp(headAlpha, item);
        headInst = helper.removeHelp(headInst, item);
        
        length = helper.getLength(headInst);
        
    } // end of removeOne()


    @Override
	public String print() {
    	String list = "";
    	
    	list = helper.printHelp(headInst, length);

        return list;
    } // end of OrderedPrint


    @Override
	public String printRange(String lower, String upper) {
    	String list = "";

    	list = helper.printRangeHelp(headAlpha, lower, upper);
    	
        return list;
    } // end of printRange()


    @Override
	public RmitMultiset union(RmitMultiset other) {
    	RmitMultiset newDllMultiSet = new DualLinkedListMultiset();
    	String[] dllValues = this.print().split("\n");
    	String[] otherValues = other.print().split("\n");
    	
    	helper.unionList(newDllMultiSet, dllValues, otherValues);
    	
        return newDllMultiSet;
    } // end of union()


    @Override
	public RmitMultiset intersect(RmitMultiset other) {
    	RmitMultiset newDllMultiSet = new DualLinkedListMultiset();
    	
    	String[] dllValues = this.print().split("\n");
    	String[] otherValues = other.print().split("\n");
    	
    	helper.intersectList(newDllMultiSet, dllValues, otherValues);

        return newDllMultiSet;
    } // end of intersect()


    @Override
	public RmitMultiset difference(RmitMultiset other) {
    	RmitMultiset newDllMultiSet = new DualLinkedListMultiset();
    	String[] dllValues = this.print().split("\n");
    	String[] otherValues = other.print().split("\n");
    	
    	helper.differenceList(newDllMultiSet, dllValues, otherValues);
    	
        return newDllMultiSet;
    } // end of difference()

} // end of class DualLinkedListMultiset
