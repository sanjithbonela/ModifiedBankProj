public class Customer {
	private String name;
	private long mobNo;
	private String address;
	private String PAN;
	public Customer(){}
	public Customer(String s, long l, String add, String p){
		name=s;
		mobNo=l;
		address=add;
		PAN=p;
	}
	
	public void setName(String s){
		name=s;
	}
	public void setMobNo(long l){
		mobNo=l;
	}
	public void setAddress(String a){
		address=a;
	}
	public void setPAN(String s){
		PAN=s;
	}
	public String getPAN(){
		return PAN;
	}
	public String getName(){
		return name;
	}
	public String getAddress(){
		return address;
	}
	public long getMobNo(){
		return mobNo;
	}
}