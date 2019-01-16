package c;

public class MyArrayList implements MyList{
	
	private int size=0;
	private String[] strs=new String[10];
	
	
	//��β�����Ԫ��
	@Override
	public void add(String string) {
		if(size>strs.length-1) {
			String[] s=new String[strs.length+strs.length/2];
			for (int i = 0; i < strs.length; i++) {
				s[i]=strs[i];
			}
		}
		strs[size]=string;
		size++;
	}
	
	//��ȡָ��������Ԫ��
	@Override
	public String get(int index) {
		if(index<size) {
			return strs[index];
		}else {
			return null;
		}
	}

	//�����б�ĳ���
	@Override
	public int size() {
		return size;
	}

	//ָ��λ�������Ԫ��
	@Override
	public void add(int index, String string) {
		if(index<=size) {
			if(size>strs.length-1) {
				String[] s=new String[strs.length+strs.length/2];
				for (int i = 0; i < strs.length; i++) {
					s[i]=strs[i];
				}
			}
			for(int i=size;i>index;i--) {
				strs[i]=strs[i-1];
			}
			strs[index]=string;
			size++;
		}
	}

	@Override
	public void remove(int index) {
		if(index==size-1) {
			strs[index]=null;
		}else {
			for(int i=index;i<size-1;i++) {
				strs[i]=strs[i+1];
			}
			strs[size-1]=null;
		}
		size--;
	}

}
