package c;

public class MyLinkedList implements MyList {

	private int size;
	private Node firstNode = new Node();

	private class Node {
		String value;
		Node prev;
		Node next;
	}

	private Node node(int index) {
		Node node = firstNode;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
	}

	@Override
	public void add(String string) {
		if (size == 0) {
			firstNode.value = string;
			firstNode.next = new Node();
		} else {
			Node node = new Node();
			node.value = string;
			node(size - 1).next = node;
			node.prev = node(size - 1);
		}
		size++;
	}

	@Override
	public String get(int index) {
		if (index <= size) {
			return node(index).value;
		}
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void add(int index, String string) {
		if (index <= size) {
			Node node = node(index);
			Node node2 = new Node();
			node2.value = string;
			node2.next = node;
			if(index!=size) {
				node.prev = node2;
			}
			if (index == 0) {
				firstNode = node2;
			} else {
				node(index - 1).next = node2;
				node2.prev = node(index - 1);
			}
			size++;
		}
	}

	@Override
	public void remove(int index) {
		Node node = firstNode;
		if (index == 0) {
			firstNode = node.next;
			firstNode.prev = null;
		} else {
			node = node(index - 1);
			node.next = node.next.next;
			node.next.prev=node;
		}
		size--;
	}

}
