package a;

public class Test {

	public static void main(String[] args) {
		MyList list = new MyLinkedList();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		list.add("ddd");
		list.add(4, "eee");
		list.remove(2);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

}
