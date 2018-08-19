import java.util.*;
import java.text.*;
public class Transaction {
	private String tid;
	private String dt;
	private String type;
	private double amt;
	private static int rand;
	
	public Transaction(){}
	public Transaction(String s,double d){
		setTid();
		dt=DateFormat.getInstance().format(new Date());
		amt=d;
		type=s.toUpperCase();
	}
	public void setTid(){
			tid="2018YASHA000000"+String.valueOf(rand);
		rand++;
	}
	public double getAmt(){
		return amt;
	}
	public String getTid(){
		return tid;
	}
	public String getDt(){
		return dt;
	}
	public String getType(){
		return type;
	}
}
