abstract class Printer {
	public static int USB = 0;
	public static int PARALLEL = 1;
	
	private String modelName; // 모델 이름
	private String manufacturer; // 제조사
	private int type; // USB 또는 병렬 프린터
	
	//수정: numberOfPaper, numberOfPrinted 필드를 private으로 변경
	private int numberOfPrinted; // 인쇄한  종이 매수
	private int numberOfPaper; // 남은 종이 매수
	
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
	public int getNumberOfPaper()
	{
		return numberOfPaper;
	}
	//수정: class Printer 에 setNumberofPrinted() 멤버 메소드를 추가 구현해야 한다.
	public void setNumberofPrinted()//setNumberofPrinted가 실행되면 print 개수 증가
	{
		this.numberOfPrinted++;
	}
	
	public void setNumberOfPaper(int numberOfPaper)
	{
		this.numberOfPaper = numberOfPaper;
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
	private int numberOfPaper = getNumberOfPaper();//인쇄용지 변수를 불러온다
	@Override
	public boolean print() {
		if (amountOfInk <= 0) {
			System.out.println("잉크가 부족합니다.");
			return false;
		}
		if (numberOfPaper <= 0) {
			System.out.println("인쇄 용지가 부족합니다.");
			return false;
		}
		System.out.println("잉크젯 프린터 출력");
		amountOfInk -= amountOfInkToPrint; // 잉크 잔량 감소
		numberOfPaper--; // 남은 종이 매수 감소
		setNumberofPrinted(); // 인쇄한 종이 매수 증가
		setNumberOfPaper(numberOfPaper);//인쇄용지 개수 반환
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
	private int numberOfPaper = getNumberOfPaper();
	@Override
	public boolean print() {
		if (amountOfToner <= 0) {
			System.out.println("토너가 부족합니다.");
			return false;
		}
		if (numberOfPaper <= 0) {
			System.out.println("인쇄 용지가 부족합니다.");
			return false;
		}
		System.out.println("레이저 프린터 출력");
		amountOfToner -= amountOfTonerToPrint; // 토너 잔량 감소
		numberOfPaper--; // 남은 종이 매수 감소
		setNumberofPrinted(); // 인쇄한 종이 매수 증가
		setNumberOfPaper(numberOfPaper);//인쇄용지 개수 반환
		return true;
	}

}

public class J19013135Q04 {
	public static void main (String[] args) { 
		LaserPrinter lp = new LaserPrinter("SCX-6x45", "삼성전자", Printer.PARALLEL, 3, 20);
		InkjetPrinter ip = new InkjetPrinter("Officejet V40", "HP", Printer.USB, 5, 10);
		
		for (int i=0; i<5; i++) {
			lp.print();
			System.out.print("레이저프린터>>");
			System.out.print("인쇄용지:" + lp.getNumberOfPaper());
			System.out.print(" 출력매수:" + lp.getNumberOfPrinted());//Print로 부터 변수를 가져온다.
			System.out.println(" 토너잔량:" + lp.getAmountOfToner());
			ip.print();
			System.out.print("잉크젯프린터>>");
			System.out.print("인쇄용지:" + ip.getNumberOfPaper());
			System.out.print(" 출력매수:" + ip.getNumberOfPrinted());//Print로 부터 변수를 가져온다.
			System.out.println(" 잉크잔량:" + ip.getAmountOfInk());
		}
	}
}