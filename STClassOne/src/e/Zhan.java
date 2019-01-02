package e;

import java.util.ArrayList;
import java.util.List;

public class Zhan<E> implements IZhanAndDuilie<E>{

	private List<E> list=new ArrayList<>();
	
	@Override
	public E get() {
		E e=list.get(list.size()-1);
		list.remove(list.size()-1);
		return e;
	}

	@Override
	public void set(E e) {
		list.add(e);
	}
	
	
}
