import java.util.*;
import java.text.*;
public class LoanReport {
	private String name;
	private String accNo;
	private String loanType;
	private int slab;
	private double interest;
	private double loanAmt;
	private String dt;
	private String loanId;
	private static int rand=0;
	
	public LoanReport(){}
	public LoanReport(String nm, String ano, String lt, int sl, double inter, double la){
		name=nm;
		accNo=ano;
		loanType=lt;
		slab=sl;
		interest=inter;
		loanAmt=la;
		dt=DateFormat.getInstance().format(new Date());
		setLoanId();
	}
	public void setName(String s){
		name=s;
	}
	public void setAccNo(String s){
		accNo=s;
	}
	public void setLoanType(String s){
		loanType=s;
	}
	public void setSlab(int a){
		slab=a;
	}
	public void setInterest(double f){
		interest=f;
	}
	public void setLoanAmt(double f){
		loanAmt=f;
	}
	public void setDt(){
		dt=DateFormat.getInstance().format(new Date());
	}
	public void setLoanId(){
		//if(dt.charAt(1)=='/')
			loanId="2018MONDAL000000"+String.valueOf(rand);
		//else
			//loanId=dt.substring(0,2)+dt.substring(3,5)+dt.substring(7,9)+"MONDAL000000"+String.valueOf(rand);	
		rand++;
	}
	public String getLoanId(){
		return loanId;
	}
	public String getName(){
		return name;
	}
	public String getAccNo(){
		return accNo;
	}
	public String getLoanType(){
		return loanType;
	}
	public String getDt(){
		return dt;
	}
	public int getSlab(){
		return slab;
	}
	public double getInterest(){
		return interest;
	}
	public double getLoanAmt(){
		return loanAmt;
	}
}