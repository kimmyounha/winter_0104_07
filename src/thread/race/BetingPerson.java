package thread.race;


public class BetingPerson {
	private int orderNum;
	private String name;
	private int betingIndex;
	
	public int getBetingIndex() {
		return betingIndex;
	}
	public void setBetingIndex(int betingIndex) {
		this.betingIndex = betingIndex;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}