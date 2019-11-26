package project;

public class MartDTO {
	private String num;
	private String place;
	private String name;
	private String company;
	private String date;
	private int price;
	private int amount;
	private int money;
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "MartDTO [num=" + num + ", place=" + place + ", name=" + name + ", company=" + company + ", date=" + date
				+ ", price=" + price + ", amount=" + amount + "]";
	}
	public MartDTO(String num, String place, String name, String company, String date, int price, int amount,int money) {
		super();
		this.num = num;
		this.place = place;
		this.name = name;
		this.company = company;
		this.date = date;
		this.price = price;
		this.amount = amount;
		this.money = money;
	}
	public MartDTO() {
	
	}
	
	
}
