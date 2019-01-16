package d;

public class MyArrayList implements MyList {

	private int size;
	private String[] strs = new String[10];

	private void expandStrs() {
		if (size == strs.length) {
			String[] s = new String[strs.length + strs.length / 2];
			for (int i = 0; i < strs.length; i++) {
				s[i] = strs[i];
			}
		}
	}

	// ��β�����Ԫ��
	@Override
	public void add(String string) {
		expandStrs();
		strs[size] = string;
		size++;
	}

	// ��ȡָ��������Ԫ��
	@Override
	public String get(int index) {
		if (index < size && index >= 0) {
			return strs[index];
		} else {
			return null;
		}
	}

	// �����б�ĳ���
	@Override
	public int size() {
		return size;
	}

	// ָ��λ�������Ԫ��
	@Override
	public void add(int index, String string) {
		if (index <= size && index >= 0) {
			expandStrs();
			for (int i = size; i > index; i--) {
				strs[i] = strs[i - 1];
			}
			strs[index] = string;
			size++;
		}
	}

	@Override
	public void remove(int index) {
		if (index == size - 1) {
			strs[index] = null;
		} else {
			for (int i = index; i < size - 1; i++) {
				strs[i] = strs[i + 1];
			}
			strs[size - 1] = null;
		}
		size--;
		subSize();
	}

	private void subSize() {
		if (size <= strs.length / 2) {
			int length = strs.length * 2 / 3;
			String[] strings = new String[length];
			for (int i = 0; i < length; i++) {
				strings[i] = strs[i];
			}
			strs = strings;
		}
	}
}
