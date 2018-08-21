//package BankJavaProj;
import java.util.*;
import java.text.*;

public class Bank {
	//private static Calendar cal2;
	public static Account createAccount(ArrayList<Account> account){
		Scanner g=new Scanner(System.in);
		int i, ind=0, flag1=0, flag2=0, flag3=0, flag4=0, flag5=0;
		double dAmt, wAmt;
		String nm, add, usn=null, pass, dep, pan, acNo, accType;
		Customer c=null; long mob;		
		Account acc=new Account();
		Date curDate;
		boolean yb=true;
		boolean yn=true;
		//do{
			//try{
				//do{
					System.out.println("Enter your Name:");
					nm=g.nextLine();
					System.out.println("Enter your Address:");
					add=g.nextLine();
					System.out.println("Enter your Mobile Number:");
					mob=g.nextLong();
					g.nextLine();
					System.out.println("Enter your PAN Number:");
					pan=g.nextLine();
					c=new Customer(nm,mob,add,pan);
					System.out.println("Set User Name:");
					usn=g.nextLine();
					yb=false;
//					if((nm.equals("")||add.equals("")||pan.equals("")||usn.equals(""))){
//						System.out.println("Give Valid Input.");
//						yn=true;
//					}
//					else yn=false;
//				}while(yn);
//			}
//			catch(InputMismatchException e){
//				System.out.println("Invalid Input. Please Enter Valid Input.");
//				yb=true;
//			}
		//}while(yb);
		for(i=0;i<account.size();i++) if(account.get(i).getUserName().equals(usn)==true){ flag5=1; break;}
		while(flag5==1){
			System.out.println("User Name already exist! Try other.");
			//do{
				System.out.println("Set User Name:");
				usn=g.nextLine();
				//if(usn.equals("")){System.out.println("Give Valid UserName");yn=true;}
				//else yn=false;
			//}while(yn);
			flag5=0;
			for(i=0;i<account.size();i++) if(account.get(i).getUserName().equals(usn)){ flag5=1; break;}
		}
		//do{
			System.out.println("Set Password:");
			pass=g.nextLine();
//			if(pass.equals("")){
//				System.out.println("Enter valid password");
//				yn=true;
//			}
			//else yn=false;
		//}while(yn);
		//do{
			System.out.println("Enter your Account Type - Savings or Current.");
			accType=g.nextLine();
//			if(!accType.equalsIgnoreCase("Savings")&&!accType.equalsIgnoreCase("Current")){
//				System.out.println("Enter valid account type.");
//				yn=true;
//			}
//			else yn=false;
//		}while(yn);
		if(accType.equalsIgnoreCase("Current")){
			acc = new CurrentAccount();
			((CurrentAccount)acc).setAccNo();
			((CurrentAccount)acc).setC(c);
			((CurrentAccount)acc).setUserName(usn);
			((CurrentAccount)acc).setPassword(pass);
			((CurrentAccount)acc).setAccType(accType);
			System.out.println(acc.getAccType().toUpperCase()+" Account Successfully Created!!!");
			System.out.println("Your account Number is "+((CurrentAccount)acc).getAccNo());
			curDate=new Date();
			String DateToStr = DateFormat.getInstance().format(curDate);
			System.out.println("To activate your account, please deposit Rs. 500/-");
			System.out.println("Do you want to deposit (Y/N)?");
			dep=g.nextLine();
			if(dep.equalsIgnoreCase("Y")){
				acc.setAct(dep);
				System.out.println("Congratulations!! Your account activated successfully.");
				acc.deposit(500);
			}
		}
		else if(accType.equalsIgnoreCase("Savings")){
			acc=new SavingAccount();
			((SavingAccount)acc).setAccNo();
			((SavingAccount)acc).setC(c);
			((SavingAccount)acc).setUserName(usn);
			((SavingAccount)acc).setPassword(pass);
			((SavingAccount)acc).setAccType(accType);
			System.out.println(acc.getAccType().toUpperCase()+" Account Successfully Created!!!");
			System.out.println("Your account Number is "+((SavingAccount)acc).getAccNo());
			curDate=new Date();
			String DateToStr = DateFormat.getInstance().format(curDate);
			System.out.println("To activate your account, please deposit Rs. 500/-");
			System.out.println("Do you want to deposit (Y/N)?");
			dep=g.nextLine();
			if(dep.equalsIgnoreCase("Y")){
				acc.setAct(dep);
				System.out.println("------>Congratulations!! Your account activated successfully.<-------");
				//cal2=Calendar.getInstance();
				acc.deposit(500);
			}
		}
		else System.out.println("Invalid Input.");
		return acc;
	}
	
	public static void accountOperation(ArrayList<Account> account) {
		Scanner g = new Scanner(System.in);
		String usn, pass, dep, acNo;
		double dAmt = 0, wAmt = 0;
		Account acc, creditor=null;
		int i, AcInput, min;
		int flag1=0, flag3=0, flag4=0, flag5=0;
		int curday,accday,curmonth,accmonth,curyear,accyear,curhour,acchour,curminute,accminute;
		Calendar cal, cal3 = null, cal2=null;
		boolean excptn;
		double f=0.05, bal;
		System.out.println("Please enter your User Name:");
		usn=g.nextLine();
		System.out.println("Please enter your Password:");
		pass=g.nextLine();
		for(i=0;i<account.size();i++){			
			if(account.get(i).validate(usn, pass)){	
				//----Check For If A/C Activation Status Is False?-----
				if(account.get(i).Activate()==false){
					System.out.println(account.get(i).getC().getName()+", You need to activate your account to perform transactions.");
					System.out.println("Do you want to deposit Rs.500 for activation (Y/N)?");
					dep=g.nextLine();
					if(dep.equals("Y")){
						account.get(i).setAct(dep);
						account.get(i).setBalance(500);
						System.out.println("Congratulations!! Your account activated successfully.");
						//cal2=Calendar.getInstance();
						System.out.println("Re-Login to perform transactions");
						flag4=1;
						break;
					}
				}
				//----If A/C Is Activated-----
				else if(account.get(i).Activate()==true){
					acc = account.get(i);
					cal2=acc.getCal();
					System.out.println("Successfully Logged in");
					while(true){
						System.out.println();
						System.out.println("Welcome to the Portal, "+account.get(i).getC().getName()+".");
						//System.out.printf("Your Balance is Rs.%.2f\n", account.get(i).getBalance());
						flag3=1;
						// ---Account Menu----
						System.out.println("##########################################");
						System.out.println("   ========= Customer Portal =========");
						System.out.println("##########################################\n");
						System.out.println("1. Deposit Amount.");
						System.out.println("2. Withdraw Amount.");
						System.out.println("3. Transfer Amount.");
						System.out.println("4. Show Mini Statement.");
						System.out.println("5. View Loans.");
						System.out.println("6. Close Loans.");
						System.out.println("7. Update Details.");
						System.out.println("8. View Details.");
						System.out.println("9. Remove Account");
						System.out.println("10. Log Out.");
						System.out.println("---------------------------");
						System.out.print("Enter Choice Number: ");
						AcInput = g.nextInt();
						g.nextLine();
						cal3=Calendar.getInstance();
						bal=acc.getBalance();
						//System.out.println("Line 181 - "+cal3.get(Calendar.MINUTE));
						if((cal3.get(Calendar.MINUTE)-cal2.get(Calendar.MINUTE))>=2){
							min=(cal3.get(Calendar.MINUTE)-cal2.get(Calendar.MINUTE));
							acc.setBalance(bal*(double)(1+(double)(min/2)*f));
							cal2=cal3;
						}
						System.out.println();
						switch(AcInput){
							case 1:
								do{
										
									try{
										System.out.println("Enter the Deposit Amount");
										dAmt = g.nextDouble();
										g.nextLine();
										excptn = false;
									}catch(InputMismatchException e){
										System.out.println("Invalid Input. Try Again.");
										g.nextLine();
										excptn = true;
									}

								}while(excptn);
								account.get(i).deposit(dAmt);
								break;
							case 2:
								do{
									try{
										System.out.println("Amount to be Withdrawn");
										wAmt = g.nextDouble();
										g.nextLine();
										excptn = false;
									}catch(InputMismatchException e){
										System.out.println("Invalid Input. Try Again.");
										g.nextLine();
										excptn = true;
									}

								}while(excptn);
							
								acc.withDraw(wAmt);
								break;
							case 3:
								//Try Catch Must.
								System.out.println("Beneficiary A/C number :");
								acNo = g.nextLine();
								for( Account a: account){
									if( a.getAccNo().equals(acNo)){
										creditor = a;
										break;
									}
									creditor = null;
								}
								System.out.println("Amount:");
								wAmt = g.nextDouble();
								acc.Transfer(wAmt, acNo, creditor);								
								break;
							case 4:
								acc.displayTransReport();
								break;
							case 5:
								acc.viewLoan();
								break;
							case 6:
								acc.settleLoan();
								//acc.updateDetails();
								break;
							case 7:
								acc.updateDetails();
								break;
							case 8:
								acc.getDetails();
								break;
							case 9:
								cal=Calendar.getInstance();
								//DateToStr = DateFormat.getInstance().format(new Date());
								curday = cal.get(Calendar.DAY_OF_MONTH);//Integer.parseInt(DateToStr.substring(2,4));
								accday = acc.getCal().get(Calendar.DAY_OF_MONTH);//Integer.parseInt(acc.getDt().substring(2,4));
								//System.out.println("cur "+Integer.parseInt(DateToStr.substring(0,2))+" Acc "+Integer.parseInt(acc.getDt().substring(0,2)));
								curmonth = cal.get(Calendar.MONTH);//Integer.parseInt(DateToStr.substring(0,1));
								accmonth = acc.getCal().get(Calendar.MONTH);//Integer.parseInt(acc.getDt().substring(0,1));
								
								curyear = cal.get(Calendar.YEAR);//Integer.parseInt(DateToStr.substring(5,7));
								accyear = acc.getCal().get(Calendar.YEAR);//Integer.parseInt(acc.getDt().substring(5,7));
								
								curhour = cal.get(Calendar.HOUR_OF_DAY);//Integer.parseInt(DateToStr.substring(8,10));
								acchour = acc.getCal().get(Calendar.HOUR_OF_DAY);//Integer.parseInt(acc.getDt().substring(8,10));
								
								curminute = cal.get(Calendar.MINUTE);//Integer.parseInt(DateToStr.substring(11,13));
								accminute = acc.getCal().get(Calendar.MINUTE);//Integer.parseInt(acc.getDt().substring(11,13));
								
								if(accyear - curyear ==0){
									if(accmonth - curmonth ==0){
										if(accday - curday ==0){
											if(acchour - curhour ==0){
												if(curminute - accminute < 2){
													System.out.println("You need to wait 6 months after opening the account to close it.");
												}
												else {
													System.out.println("You can collect your balance at the Bank by showing required documents.");
													account.remove(i);
													flag5=1;
												}
											}
											else {
												System.out.println("You can collect your balance at the Bank by showing required documents.");
												account.remove(i);
												flag5=1;
											}
										}
										else {
											System.out.println("You can collect your balance at the Bank by showing required documents.");
											account.remove(i);
											flag5=1;
										}
									}
									else {
										System.out.println("You can collect your balance at the Bank by showing required documents.");
										account.remove(i);
										flag5=1;
									}
								}
								else {
									System.out.println("You can collect your balance at the Bank by showing required documents.");
									account.remove(i);
									flag5=1;
								}
							case 10:
								if(flag5==1){
									System.out.println("Account Successfully Removed!!");
									System.out.println("Thank you for banking with us.");
								}
								System.out.println("Successfully Logged out!!");
								flag1=1;
								break;
							default:
								System.out.println("Invalid Input. Enter correct Choice.");
								break;
						}
						if(flag1==1) 
							break;
					}
					break;
				}
			}
		}
		
		if((flag3==0)&&(flag4==0)) 
			System.out.println("Oops!! Wrong User Name or Password.\nContact admin to recover your credentials.");
		else{
			
		}
	}
	
	public static void ApplyLoan( ArrayList<Account> account, Loan loanSegment ){
		Scanner in = new Scanner(System.in);
		String resp, usn, pass;
		boolean isValidUsr = false;
		Account usrAcc = null;
		int mxAttempt=3, attempt=0, diff=3;
		System.out.println();
		System.out.println("------------------------------");
		System.out.println("    Welcome To Loan Portal    ");
		System.out.println("------------------------------");

		System.out.print("\nDo You have an Account With Us (Y/N) ? ");
		resp = in.nextLine();

		while( diff > 0 ){
			attempt++;
			diff = mxAttempt - attempt;

			if( resp.equalsIgnoreCase("Y")){
				attempt = 0;
				diff = 3;
				while( diff > 0 ){
					attempt++;
					diff = mxAttempt - attempt;

					System.out.print("User Name : ");
					usn = in.nextLine();
					System.out.print("Password : ");
					pass = in.nextLine();

					for(Account acc: account ){
						if( acc.validate(usn, pass) ){
							isValidUsr = true;
							usrAcc = acc;							
							break;
						}
					}

					if( isValidUsr && usrAcc.getAct().equals("Y") ){
						loanSegment.interfaceApp(usrAcc);
						diff = 0;
					}else if(isValidUsr){
						System.out.println("Your A/C is not activated. Pls. Activate your A/C. \nRedirecting to main menu...");
						diff = 0;
					}else{
						System.out.println("Wrong UserName or Password... "+ diff +" Attempts left.\n\n");
						if( diff == 0 ){
							System.out.println("Redirecting to main menu...\n\n");
							break;
						}
					}
				}

			}else if( resp.equalsIgnoreCase("N") ){
				System.out.println("Please Create An Account First Before Proceding Further.\nRedirecting to main menu...");
				diff = 0;
				break;
			}else{
				System.out.println("Invalid Input!! "+ diff +" Attempts Left.");
				if( diff == 0){
					System.out.println("Redirecting to main menu...\n\n");
					break;
				}else{
					System.out.print("\nResponse (Y/N) :");
					resp = in.nextLine();
				}
			}
		}

	}

	// ToDo - (1)
	public static void bkLoanScheme( Loan LoanSegment, int choice ){
		Scanner g=new Scanner(System.in);
		int i,j,k,ch,flag=0,sav,per;
		double[][][] sch = LoanSegment.getScheme();
		double[][][] in = LoanSegment.getInterest();
		double[][][] mla = LoanSegment.getMaxLoanAmt();
		double[] n = new double[3];
		double[][][] prevsch = LoanSegment.getScheme();
		double[][][] previn = LoanSegment.getInterest();
		double[][][] prevmla = LoanSegment.getMaxLoanAmt();
		System.out.println("Matrices are encoded in the following way:");
		System.out.println("First index indicates Account type - 0 for Savings and 1 for Current");
		System.out.println("Second index indicates Loan type - 0 for Personal Loan, 1 for Housing Loan and 2 for Auto Loan");
		System.out.println("Third index represents the corresponding matrix");
		System.out.println("For example, scheme[0][1][1] means Savings account wants Housing Loan and balance limit");
		if(choice==1){
			System.out.println("Scheme Matrix");
			for(i=0;i<2;i++){
				for(j=0;j<3;j++){
					for(k=0;k<3;k++){
						System.out.print(LoanSegment.getScheme()[i][j][k]+" ");
					}
					System.out.println();
				}
				System.out.println();
				System.out.println();
			}
			System.out.println("Interest Matrix");
			for(i=0;i<2;i++){
				for(j=0;j<3;j++){
					for(k=0;k<3;k++){
						System.out.print(LoanSegment.getInterest()[i][j][k]+" ");
					}
					System.out.println();
				}
				System.out.println();
				System.out.println();
			}
			System.out.println("MaxLoanAmount Matrix");
			for(i=0;i<2;i++){
				for(j=0;j<3;j++){
					for(k=0;k<3;k++){
						System.out.print(LoanSegment.getMaxLoanAmt()[i][j][k]+" ");
					}
					System.out.println();
				}
				System.out.println();
				System.out.println();
			}
		}
		else if(choice==2){
			while(true){
				System.out.println("Which matrix do you want to change?");
				System.out.println("1. Scheme Matrix.\n2. Interest Matrix.\n3. MaxLoanAmount Matrix.\n4. Exit");
				ch=g.nextInt();
				g.nextLine();
				switch(ch){
				case 1:
					System.out.println("Which one do you want to change?");
					System.out.println("1. Savings");
					System.out.println("2. Current");
					sav=g.nextInt();
					g.nextLine();
					if(sav==1){
						System.out.println("Which segment do you want to choose?");
						System.out.println("1. Personal");
						System.out.println("2. Housing");
						System.out.println("3. Auto");
						per=g.nextInt();
						g.nextLine();
						System.out.println("Input Format - \n num1\n num2\n num3 ");
						if(per==1){
							for(i=0;i<3;i++) {
								n[i]=g.nextDouble();
								g.nextLine();
							}
							sch[0][0] = n.clone();
						}
						else if(per==2){
							for(i=0;i<3;i++) {
								n[i]=g.nextDouble();
								g.nextLine();
							}
							sch[0][1] = n.clone();
						}
						else if(per==2){
							for(i=0;i<3;i++) {
								n[i]=g.nextDouble();
								g.nextLine();
							}
							sch[0][2] = n.clone();
						}
						else System.out.println("Invalid Input.");
					}
					else if(sav==2){
						System.out.println("Which segment do you want to choose?");
						System.out.println("1. Personal");
						System.out.println("2. Housing");
						System.out.println("3. Auto");
						per=g.nextInt();
						g.nextLine();
						System.out.println("Input Format - \n num1\n num2\n num3 ");
						if(per==1){
							for(i=0;i<3;i++) {
								n[i]=g.nextDouble();
								g.nextLine();
							}
							sch[1][0] = n.clone();
						}
						else if(per==2){
							for(i=0;i<3;i++) {
								n[i]=g.nextDouble();
								g.nextLine();
							}
							sch[1][1] = n.clone();
						}
						else if(per==3){
							for(i=0;i<3;i++) {
								n[i]=g.nextDouble();
								g.nextLine();
							}
							sch[1][2] = n.clone();
						}
						else System.out.println("Invalid Input.");
					}
					else System.out.println("Invalid Input.");
					LoanSegment.setScheme(sch);
					break;
				case 2:
					System.out.println("Which one do you want to change?");
					System.out.println("1. Savings");
					System.out.println("2. Current");
					sav=g.nextInt();
					g.nextLine();
					if(sav==1){
						System.out.println("Which segment do you want to choose?");
						System.out.println("1. Personal");
						System.out.println("2. Housing");
						System.out.println("3. Auto");
						per=g.nextInt();
						g.nextLine();
						System.out.println("Input Format - \n num1\n num2\n num3 ");
						if(per==1){
							for(i=0;i<3;i++) {
								n[i]=g.nextDouble();
								g.nextLine();
							}
							in[0][0] = n.clone();
						}
						else if(per==2){
							for(i=0;i<3;i++) {
								n[i]=g.nextDouble();
								g.nextLine();
							}
							in[0][1] = n.clone();
						}
						else if(per==3){
							for(i=0;i<3;i++) {
								n[i]=g.nextDouble();
								g.nextLine();
							}
							in[0][2] = n.clone();
						}
						else System.out.println("Invalid Input.");
					}
					else if(sav==2){
						System.out.println("Which segment do you want to choose?");
						System.out.println("1. Personal");
						System.out.println("2. Housing");
						System.out.println("3. Auto");
						per=g.nextInt();
						g.nextLine();
						System.out.println("Input Format - \n num1\n num2\n num3 ");
						if(per==1){
							for(i=0;i<3;i++) {
								n[i]=g.nextDouble();
								g.nextLine();
							}
							in[1][0] = n.clone();
						}
						else if(per==2){
							for(i=0;i<3;i++) {
								n[i]=g.nextDouble();
								g.nextLine();
							}
							in[1][1] = n.clone();
						}
						else if(per==3){
							for(i=0;i<3;i++) {
								n[i]=g.nextDouble();
								g.nextLine();
							}
							in[1][2] = n.clone();
						}
						else System.out.println("Invalid Input.");
					}
					else System.out.println("Invalid Input.");
					LoanSegment.setInterest(in);     
					break;
				case 3:
					System.out.println("Which one do you want to change?");
					System.out.println("1. Savings");
					System.out.println("2. Current");
					sav=g.nextInt();
					g.nextLine();
					if(sav==1){
						System.out.println("Which segment do you want to choose?");
						System.out.println("1. Personal");
						System.out.println("2. Housing");
						System.out.println("3. Auto");
						per=g.nextInt();
						g.nextLine();
						System.out.println("Input Format - \n num1\n num2\n num3 ");
						if(per==1){
							for(i=0;i<3;i++) {
								n[i]=g.nextDouble();
								g.nextLine();
							}
							mla[0][0] = n.clone();
						}
						else if(per==2){
							for(i=0;i<3;i++) {
								n[i]=g.nextDouble();
								g.nextLine();
							}
							mla[0][1] = n.clone();
						}
						else if(per==3){
							for(i=0;i<3;i++) {
								n[i]=g.nextDouble();
								g.nextLine();
							}
							mla[0][2] = n.clone();
						}
						else System.out.println("Invalid Input.");
					}
					else if(sav==2){
						System.out.println("Which segment do you want to choose?");
						System.out.println("1. Personal");
						System.out.println("2. Housing");
						System.out.println("3. Auto");
						per=g.nextInt();
						g.nextLine();
						System.out.println("Input Format - \n num1\n num2\n num3 ");
						if(per==1){
							for(i=0;i<3;i++) {
								n[i]=g.nextDouble();
								g.nextLine();
							}
							mla[1][0] = n.clone();
						}
						else if(per==2){
							for(i=0;i<3;i++) {
								n[i]=g.nextDouble();
								g.nextLine();
							}
							mla[1][1] = n.clone();
						}
						else if(per==3){
							for(i=0;i<3;i++) {
								n[i]=g.nextDouble();
								g.nextLine();
							}
							mla[1][2] = n.clone();
						}
						else System.out.println("Invalid Input.");
					}
					else System.out.println("Invalid Input.");
					LoanSegment.setMaxLoanAmt(mla);
					break;
				case 4:
					flag=1;
					break;
				default:
					System.out.println("Invalid Input.");
				}
				if(flag==1) break;
			}
		}
		else System.out.println("Invalid Input.");
	}

	// ToDo - (2)
	public static void bkLoanReport( Loan LoanSegment, int choice ){
		int i,sum=0,repay=0;
		LoanReport lr;
		switch(choice){
		case 1:
			System.out.println("All Corporate Loans that are sanctioned.");
			for(i=0;i<LoanSegment.getLr().size();i++){
				if(LoanSegment.getLr().get(i).getCType().equalsIgnoreCase("Current")){
					lr=LoanSegment.getLr().get(i);
					//System.out.println("Name: "+lr.getName()+", Account No:"+lr.getAccNo()+", Loan Type:"+lr.getLoanType()+", Loan Id:"+lr.getLoanId()+", Date:"+lr.getDt()+", Status:"+lr.getStatus()+", RePay Amount"+lr.getRepayAmt());
					sum+=lr.getLoanAmt();
					repay+=lr.getRepayAmt();
					//System.out.println("Total Amount ");
				}
			}
			System.out.println("Total Amount given as Loans: "+sum);
			System.out.println("Total Amount we gonna get: "+repay);
			break;
		case 2:
			System.out.println("All Savings Loans that are sanctioned.");
			for(i=0;i<LoanSegment.getLr().size();i++){
				if(LoanSegment.getLr().get(i).getCType().equalsIgnoreCase("Savings")){
					lr=LoanSegment.getLr().get(i);
					//System.out.println("Name: "+lr.getName()+", Account No:"+lr.getAccNo()+", Loan Type:"+lr.getLoanType()+", Loan Id:"+lr.getLoanId()+", Date:"+lr.getDt()+", Status:"+lr.getStatus()+", RePay Amount"+lr.getRepayAmt());
					sum+=lr.getLoanAmt();
					repay+=lr.getRepayAmt();
					//System.out.println("Total Amount ");
				}
			}
			System.out.println("Total Amount given as Loans: "+sum);
			System.out.println("Total Amount we gonna get: "+repay);
			break;
		case 3:
			System.out.println("All the Loans that are sanctioned.");
			for(i=0;i<LoanSegment.getLr().size();i++){
				//if(LoanSegment.getLr().get(i).getCType().equalsIgnoreCase("Current")){
					lr=LoanSegment.getLr().get(i);
					//System.out.println("Name: "+lr.getName()+", Account No:"+lr.getAccNo()+", Loan Type:"+lr.getLoanType()+", Loan Id:"+lr.getLoanId()+", Date:"+lr.getDt()+", Status:"+lr.getStatus()+", RePay Amount"+lr.getRepayAmt());
					sum+=lr.getLoanAmt();
					repay+=lr.getRepayAmt();
					//System.out.println("Total Amount ");
				//}
			}
			System.out.println("Total Amount given as Loans: "+sum);
			System.out.println("Total Amount we gonna get: "+repay);
			break;
		default:
			System.out.println("Invalid Input.");
		}
	}

	public static void main(String[] args) throws Exception{
		Scanner g=new Scanner(System.in);
		char ch;
		ArrayList<Account> account = new ArrayList<Account>();
		Loan LoanSegment = new Loan();	
		double[][][] scheme = new double[2][3][3];
		double[][][] interest = new double[2][3][3];
		double[][][] maxLoanAmt = new double[2][3][3];
		boolean bkLoop = true, masterLoop = true;
		String busn, bpass;
		
		scheme[0][0][0] = 500000 ; scheme[0][0][1] = 1000000; scheme[0][0][2] = 1500000;
		scheme[0][1][0] = 200000; scheme[0][1][1] = 700000; scheme[0][1][2] = 1500000;
		scheme[0][2][0] = 400000; scheme[0][2][1] = 1200000; scheme[0][2][2] = 1800000;

		scheme[1][0][0] = 1500000; scheme[1][0][1] = 2500000; scheme[1][0][2] = 5000000;
		scheme[1][1][0] = 2000000; scheme[1][1][1] = 4000000; scheme[1][1][2] = 6000000;
		scheme[1][2][0] = 1500000; scheme[1][2][1] = 2500000; scheme[1][2][2] = 3500000;
		LoanSegment.setScheme(scheme);

		interest[0][0][0] = 10.01 ; interest[0][0][1] = 9.85; interest[0][0][2] = 8.62;
		interest[0][1][0] = 9.85; interest[0][1][1] = 8.62; interest[0][1][2] = 8.21;
		interest[0][2][0] = 13.25; interest[0][2][1] = 12.89; interest[0][2][2] = 12.12;

		interest[1][0][0] = 8.01; interest[1][0][1] = 7.85; interest[1][0][2] = 6.62;
		interest[1][1][0] = 8.85; interest[1][1][1] = 7.62; interest[1][1][2] = 7.21;
		interest[1][2][0] = 10.25; interest[1][2][1] = 9.89; interest[1][2][2] = 9.12;
		LoanSegment.setInterest(interest);

		maxLoanAmt[0][0][0] = 250000 ; maxLoanAmt[0][0][1] = 800000; maxLoanAmt[0][0][2] = 1000000;
		maxLoanAmt[0][1][0] = 100000; maxLoanAmt[0][1][1] = 350000; maxLoanAmt[0][1][2] = 750000;
		maxLoanAmt[0][2][0] = 200000; maxLoanAmt[0][2][1] = 600000; maxLoanAmt[0][2][2] = 900000;

		maxLoanAmt[1][0][0] = 750000; maxLoanAmt[1][0][1] = 1250000; maxLoanAmt[1][0][2] = 2500000;
		maxLoanAmt[1][1][0] = 1000000; maxLoanAmt[1][1][1] = 2000000; maxLoanAmt[1][1][2] = 3000000;
		maxLoanAmt[1][2][0] = 750000; maxLoanAmt[1][2][1] = 1300000; maxLoanAmt[1][2][2] = 1800000;
		LoanSegment.setMaxLoanAmt(maxLoanAmt);

		int choice, flag2=0;
		int attempt=0, mxAttempt=3, diff=3;
		Account acc ;

		while(masterLoop){
			System.out.println();
			System.out.println("######################################################");
			System.out.println("----------------Welcome to SYS Bank!!!----------------");
			System.out.println("######################################################");

			System.out.println("\n        A. Bank Portal     B.Customer Portal");
			System.out.println("------------------------------------------------------");
			System.out.println("Type X to close the portal.");
			System.out.print("\nEnter your choice : ");
			ch = g.next().charAt(0);
			g.nextLine();
			bkLoop=true;
			if( ch=='A' || ch=='a'){				
				while(bkLoop){
					System.out.println("Enter User Name:"); // Username : sys
					busn=g.nextLine();						//Password: sys
					System.out.println("Enter Password:");
					bpass=g.nextLine();
					if((busn.equals("sys"))&&(bpass.equals("sys"))){
					System.out.println("\n\n--------------Bank Portal---------------");
					System.out.println("1. Loan Scheme.");
					System.out.println("2. Get Loan Report.");
					System.out.println("3. Get %-change in loan.");
					System.out.println("4. Exit.");

					System.out.print("\nEnter your choice : ");
					choice = g.nextInt();
					g.nextLine();

					attempt=0; mxAttempt=3; diff=3;

					switch( choice ){
						case 1:
							while( diff != 0 ){
								System.out.println("1. View Loan Scheme.");
								System.out.println("2. Change Loan Scheme.");
								System.out.println("3. Exit.");
								System.out.print("Enter Your Choice : ");
								choice = g.nextInt();
								g.nextLine();

								if(  choice>0 && choice<3 ){
									bkLoanScheme( LoanSegment, choice ); // ToDo - (1)
									diff = 0;
								}else if (choice == 3){
									diff = 0;
								}else{
									diff--;
									if( diff != 0)
										System.out.println("Invalid Input. Try Again. "+diff+" Attempts remains.");
								}
							}
							System.out.println("Redirecting to Bank Portal...");
							break;
						case 2:
							while( diff != 0 ){
								System.out.println("1. Get Corporate Loan Report.");
								System.out.println("2. Get Savings Loan Report.");
								System.out.println("3. Get All Loan Reports.");
								System.out.println("4. Exit.");
								System.out.print("Enter Your Choice : ");
								choice = g.nextInt();
								g.nextLine();
								if(  choice>0 && choice<4 ){
									bkLoanReport( LoanSegment, choice ); // ToDo - (2)
									diff = 0;
								}else if( choice == 4 ){
									diff = 0;
								}else{
									diff--;
									if( diff != 0)
										System.out.println("Invalid Input. Try Again. "+diff+" Attempts remains.");
								}
							}
							System.out.println("Redirecting to Bank Portal...");
							break;
						case 3:
							break;
						case 4:
							bkLoop = false;
							break;
						default:
							System.out.println("Invalid Input. Try Again...");
							break;
					}
					System.out.println("\n");
				}
					else{
						System.out.println("Invalid User Name or Password");
						bkLoop=false;
					}
				}
				System.out.println("**************************************************\n");
			}else if( ch=='B' || ch=='b' ){
				System.out.println("\n\n------------Customer Portal-------------\n");
				while(true){
					System.out.println("Enter your choice:");
					System.out.println("1. New Account Opening?");
					System.out.println("2. Existing User?");
					System.out.println("3. Apply for Loan?");
					//System.out.println("4. Clear Loans.");
					System.out.println("4. Exit");
					choice = g.nextInt();
					g.nextLine();
					
					switch(choice){
						case 1:
							acc=createAccount(account);
							account.add(acc);
							break;				
						case 2:
							accountOperation(account);
							break;				
						case 3:	
							ApplyLoan(account, LoanSegment);
							break;				
//						case 4:
//							removeLoan( account );				
//							break;
						case 4:
							flag2=1;				
							break;
						default:
							System.out.println("Invalid Input.");
					}
					System.out.println("**********************************************************************************");
					if(flag2==1) 
						break;
				}

			}else if( ch=='X' || ch =='x' ){
				System.out.println("\n\n>>>>>>>>> Thank You for banking with Us. _/\\_ <<<<<<<<<");
				masterLoop = false;
			}else{
				System.out.println("Invalid Input. Try Again...");
			}
		}
		
	}
}
