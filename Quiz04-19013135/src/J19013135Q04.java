abstract class Printer {
	public static int USB = 0;
	public static int PARALLEL = 1;
	
	private String modelName; // �� �̸�
	private String manufacturer; // ������
	private int type; // USB �Ǵ� ���� ������
	
	protected int numberOfPrinted; // �μ���  ���� �ż�
	protected int numberOfPaper; // ���� ���� �ż�
	
	abstract public boolean print();
	
	public String getModelName()
	{
		return modelName;
	}
	public String getManufacturer()
	{
		return manufacturer;
	}
	public int getType() {
		return type;
	}
	public int getNumberOfPrinted()
	{
		return numberOfPrinted;
	}
	public void setNumberOfPaper(int numberOfPaper)
	{
		this.numberOfPaper = numberOfPaper;
	}
	public int getNumberOfPaper()
	{
		return numberOfPaper;
	}
	public Printer(String modelName, String manufacturer, int type, int numberOfPaper)
	{
		this.modelName = modelName;
		this.manufacturer = manufacturer;
		this.type = type;
		this.numberOfPaper = numberOfPaper;
		this.numberOfPrinted = 0;
	}
}

class InkjetPrinter extends Printer {
	static final int amountOfInkToPrint = 5;
	private int amountOfInk;
	public InkjetPrinter(String modelName, String manufacturer, int type, int numberOfPaper, int amountOfInk) {
		super(modelName, manufacturer, type, numberOfPaper);
		this.amountOfInk = amountOfInk;
	}

	public int getAmountOfInk() {
		return amountOfInk;
	}
	@Override
	public boolean print() {
		if (amountOfInk <= 0) {
			System.out.println("��ũ�� �����մϴ�.");
			return false;
		}
		if (numberOfPaper <= 0) {
			System.out.println("�μ� ������ �����մϴ�.");
			return false;
		}
		System.out.println("��ũ�� ������ ���");
		amountOfInk -= amountOfInkToPrint; // ��ũ �ܷ� ����
		numberOfPaper--; // ���� ���� �ż� ����
		numberOfPrinted++; // �μ��� ���� �ż� ����
		return true;
	}
}

class LaserPrinter extends Printer {
	static final int amountOfTonerToPrint = 10;
	private int amountOfToner;
	public LaserPrinter(String modelName, String manufacturer, int type, int numberOfPaper, int amountOfToner) {
		super(modelName, manufacturer, type, numberOfPaper);
		this.amountOfToner = amountOfToner;
	}

	public int getAmountOfToner() {
		return amountOfToner;
	}
	
	@Override
	public boolean print() {
		if (amountOfToner <= 0) {
			System.out.println("��ʰ� �����մϴ�.");
			return false;
		}
		if (numberOfPaper <= 0) {
			System.out.println("�μ� ������ �����մϴ�.");
			return false;
		}
		System.out.println("������ ������ ���");
		amountOfToner -= amountOfTonerToPrint; // ��� �ܷ� ����
		numberOfPaper--; // ���� ���� �ż� ����
		numberOfPrinted++; // �μ��� ���� �ż� ����
		return true;
	}

}

public class J19013135Q04 {
	public static void main (String[] args) { 
		LaserPrinter lp = new LaserPrinter("SCX-6x45", "�Ｚ����", Printer.PARALLEL, 3, 20);
		InkjetPrinter ip = new InkjetPrinter("Officejet V40", "HP", Printer.USB, 5, 10);
		for (int i=0; i<5; i++) {
			lp.print();
			System.out.print("������������>>");
			System.out.print("�μ����:" + lp.getNumberOfPaper());
			System.out.print(" ��¸ż�:" + lp.numberOfPrinted);
			System.out.println(" ����ܷ�:" + lp.getAmountOfToner());
			ip.print();
			System.out.print("��ũ��������>>");
			System.out.print("�μ����:" + ip.getNumberOfPaper());
			System.out.print(" ��¸ż�:" + ip.numberOfPrinted);
			System.out.println(" ��ũ�ܷ�:" + ip.getAmountOfInk());
		}
	}
}