package com.st.pojo;

import java.util.List;

public class TeXing {
	
	private List<String> teXing;
	
	public TeXing(List<String> teXing) {
		super();
		this.teXing = teXing;
	}

	public List<String> getTeXing() {
		int a=(int)Math.floor(Math.random()*4);
		if(a==0) {
			teXing.add("������");
			teXing.add("������");
		}else if(a==1) {
			teXing.add("������");
			teXing.add("�繥��");
		}else if(a==2) {
			teXing.add("������");
			teXing.add("�繥��");
		}else if(a==3) {
			teXing.add("������");
			teXing.add("�𹥻�");
		}
		return teXing;
	}

}
