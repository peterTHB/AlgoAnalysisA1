package implementation;

public class SortStrings
{
	public String sortArray(String[] array) {
		String list = "";
		
		for (int i = 0; i < array.length; i++) {
			if (array[i + 1] != null || i != array.length) {
				String[] string1 = array[i].split(":");
				String[] string2 = array[i + 1].split(":");
				
				if (Integer.parseInt(string2[1]) > Integer.parseInt(string1[1])) {
					String spot1 = array[i];
					String spot2 = array[i + 1];
					
					array[i] = spot2;
					array[i + 1] = spot1;
				}
			}
        }
		
		for (int j = 0; j < array.length; j++) {
			list += array[j] + "\n";
		}
		
		return list;
	}
	
	public String sortArrayRange(String[] array, String lower, String upper) {
		String list = "";
		String[] newArray = new String[array.length];
		
		for (int i = 0; i < array.length; i++) {
			if (array[i].compareToIgnoreCase(lower) >= 1 && 
					array[i].compareToIgnoreCase(upper) <= 1) {
				newArray[i] = array[i];
			} else {
				newArray[i] = "";
			}
		}
		
		for (int j = 0; j < newArray.length; j++) {
			if (!newArray[j].equals("")) {
				list += newArray[j] + "\n";
			}
		}
		
		return list;
	}
	
	public void sortLLAlpha(Node node) {
		if (node != null) {
			String[] getData = node.getData().split(":");
			
			if (node.getNext() != null) {
				String[] getNext = node.getNext().getData().split(":");
				
				// if the next is bigger than current
				if (getData[0].compareToIgnoreCase(getNext[0]) > 1) {
					String temp = node.getNext().getData();
					node.getNext().setData(node.getData());
					node.setData(temp);
				}
				sortLLAlpha(node.getNext());
			}
		}
	}
	
	public void sortLLInst(Node node) {
		if (node != null) {
			String[] getData = node.getData().split(":");
			
			if (node.getNext() != null) {
				String[] getNext = node.getNext().getData().split(":");
				
				// if the next is bigger than current
				if (Integer.parseInt(getData[1]) < Integer.parseInt(getNext[1])) {
					String temp = node.getNext().getData();
					node.getNext().setData(node.getData());
					node.setData(temp);
				}
				sortLLInst(node.getNext());
			}
		}
	}
}
