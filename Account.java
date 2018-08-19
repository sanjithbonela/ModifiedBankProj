
import java.text.*;
import java.util.*;
public class Account {
	protected String accNo;
	protected String userName;
	protected String password;
	protected String act="false";
	protected Customer c;
	protected static int rand=1;
	protected double balance=0;
	//protected int cThreshold;
	//protected int sThreshold;
	//private float SancLoanAmt;
	protected String dt;
	protected Calendar cal;
	protected ArrayList<Transaction> t=new ArrayList<Transaction>();
	protected String accType;
	
	public void setAccNo(){
		accNo = "2018FREEZE00000" + String.valueOf(rand);
		rand++;
	}
	public void setUserName(String s){
		userName=s;
	}
	public void setPassword(String p){
		password=p;
		cal = Calendar.getInstance();
		dt=DateFormat.getInstance().format(new Date());
	}
	public void setC(Customer n){
		c=new Customer(n.getName(), n.getMobNo(), n.getAddress(), n.getPAN());
	}
	public void setAct(String s){
		act=s;
	}
	public void setBalance(double d){
		balance=d;
	}
	public void setAccType( String s ){
		accType = s;
	}

	//	public void setCThreshold(int i){
//		cThreshold = i;
//	}
//	public void setSThreshold(int i){
//		sThreshold = i;
//	}
	
	public void deposit(double d){
		addTransaction(1,d);
		balance+=d;
		System.out.printf("Amount Rs %.2f successfully deposited.\n",d);
	}
	
	public void withDraw(double d){
//	/*	if( accType.equalsIgnoreCase("Current") ){
//			CurrentAccount obj1 = new CurrentAccount();
//		}
//			/*if( balance-d < cThreshold) 
//				System.out.println("Sorry!! Insufficient Funds.");
//			else {
//				balance-=d;
//				addTransaction(2,d);
//				if( balance < 0)
//					System.out.println("You have Overdrafted Rs."+(balance*(-1)) );
//				System.out.printf("Amount Rs %.2f successfully withdrawn.",d);
//			}*/
//		}else if(accType.equalsIgnoreCase("Savings")){
//			SavingAccount obj2 = new SavingAccount();
//		}
//			/*if( balance-d < sThreshold) 
//				System.out.println("Sorry!! Insufficient Funds.");
//			else {
//				balance-=d;
//				addTransaction(2,d);
//				System.out.printf("Amount Rs %.2f successfully withdrawn.",d);
//			}*/
//		}*/
	}
	
	public void Transfer(double d, String bid, Account creditor){
		
		if( accType.equalsIgnoreCase("Current") ){
			if( balance-d < -100000) 
				System.out.println("Sorry!! Insufficient Funds.");
			else {
				balance-=d;
				addTransaction(3,d);
				if( balance < 0)
					System.out.println("You have Overdrafted Rs."+(balance*(-1)) );
				System.out.printf("Amount Rs %.2f successfully withdrawn.",d);
				creditor.deposit(d);
				System.out.println("Amount Successfully Transferred to "+bid);
			}
			
		}else if(accType.equalsIgnoreCase("Savings")){
			if( balance-d <= 0) 
				System.out.println("Sorry!! Insufficient Funds.");
			else {
				balance-=d;
				addTransaction(3,d);
				System.out.printf("Amount Rs %.2f successfully withdrawn.",d);
				creditor.deposit(d);
				System.out.println("Amount Successfully Transferred to "+bid);
			}
		}
		
	}
	public double getBalance(){
		return balance;
	}
	public String getAct(){
		return act;
	}
	public String getAccNo(){
		return accNo;
	}
	public String getUserName(){
		return userName;
	}
	public String getPassword(){
		return password;
	}
	public Customer getC(){
		return c;
	}
	public String getAccType(){
		return accType;
	}
	public String getDt(){
		return dt;
	}
	public Calendar getCal(){
		return cal;
	}
//	public int getCThreshold(){
//		return cThreshold;
//	}
//	public int getSThreshold(){
//		return sThreshold;
//	}
	
	public void addTransaction(int n, double amt){
		Transaction tr = null;
		
		if(n==1) {
			tr=new Transaction("Deposit",amt);
		}
		else if(n==2) {
			tr=new Transaction("Withdraw",amt);
		}
		else if(n==3) {
			tr=new Transaction("Transfer",amt);
		}
		if(t.size()<15) t.add(tr);
		else{
			t.remove(0);
			t.add(tr);
		}
	}
	public void updateDetails(){
		System.out.println("1. Update your Address?");
		System.out.println("2. Update your Phone No?");
		System.out.println("3. Update your Address & Phone No?");
		Scanner g = new Scanner(System.in);
		long l;
		int choice = g.nextInt();
		g.nextLine();
		switch(choice){
			case 1: 
				System.out.println("Enter your new Address");
				c.setAddress(g.nextLine());
				break;
			case 2:	
				System.out.println("Enter your new Phone No");
				l=g.nextLong();
				g.nextLine();
				c.setMobNo(l);
				break;
			case 3:
				System.out.println("Enter your new Phone No");
				l=g.nextLong();
				g.nextLine();
				c.setMobNo(l);
				System.out.println("Enter your new Address");
				c.setAddress(g.nextLine());
				break;
			default:
				System.out.println("Invalid Choice.");
		}
	}
	public void getDetails(){
		int i;
		System.out.println("Name: "+getC().getName());
		System.out.println("Mobile: "+getC().getMobNo());
		System.out.println("Address: "+getC().getAddress());
		System.out.println("PAN: "+getC().getPAN());
		System.out.println("Account Number: "+getAccNo());
		System.out.println("Account Type: "+getAccType());
		System.out.println("Balance: "+getBalance());
	}
	public ArrayList<Transaction> getTList(){
		return t;
	}
	public void displayTransReport(){
		int i;
		for(i=0;i<t.size();i++){
			System.out.println("Transaction ID:"+t.get(i).getTid()+", Transaction Type:"+t.get(i).getType()+", Amount:"+t.get(i).getAmt()+", Date:"+t.get(i).getDt());
		} 
	}
	public boolean Activate(){
		if(getAct().equals("Y")) return true;
		return false;
	}
	public boolean validate(String usn, String pass){
		if(userName.equals(usn)&& password.equals(pass)) return true;
		return false;
	}

}