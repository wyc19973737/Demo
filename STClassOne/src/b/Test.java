package b;

public class Test {
	public static void main(String[] args) {
		Paper paper=new A4Paper();
		InkBox inkBox=new ColorInkBox();
		Printer printer=new Printer();
		printer.printer(paper,inkBox);
	}
}
