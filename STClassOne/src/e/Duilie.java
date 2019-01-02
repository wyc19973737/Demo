package e;

import java.util.ArrayList;
import java.util.List;

public class Duilie<E> implements IZhanAndDuilie<E>{

	private List<E> list=new ArrayList<>();
	
	@Override
	public E get() {
		E e=list.get(0);
		list.remove(0);
		return e;
	}

	@Override
	public void set(E e) {
		list.add(e);
	}

}
