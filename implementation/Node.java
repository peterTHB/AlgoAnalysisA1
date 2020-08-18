package implementation;

public class Node
{
	private String data;
	private Node left, right, next;
	
	public Node(String data, Node left, Node right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	public Node(String data, Node next) {
		this.data = data;
		this.next = next;
	}
	
	public String getData() {
		return this.data;
	}
	
	public Node getLeft() {
		return this.left;
	}
	
	public Node getRight() {
		return this.right;
	}
	
	public Node getNext() {
		return this.next;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public void setLeft(Node left) {
		this.left = left;
	}
	
	public void setRight(Node right) {
		this.right = right;
	}
	
	public void setNext(Node next) {
		this.next = next;
	}
}
