public class SavingAccount extends Account {
	private double threshold;
	
	public void setThreshold(double th) {
		threshold = th;
	}
	
	public double getThreshold() {
		return threshold;
	}
	
	@Override
	public void withDraw(double d){
		if( balance-d < 0) 
			System.out.println("Sorry!! Insufficient Funds.");
		else {
			balance-=d;
			addTransaction(2,d);
			System.out.printf("Amount Rs %.2f successfully withdrawn.",d);
		}
	}
	@Override
	public void setAccNo(){
		accNo = "2018FREEZE00"+"SAV"+"00" + String.valueOf(rand);
		rand++;
	}
}