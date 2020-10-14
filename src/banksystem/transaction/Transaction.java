package banksystem.transaction;

import java.util.Date;
import java.util.*;


import banksystem.transaction.payment.BillPayment;
import banksystem.transaction.payment.MobileRecharge;

public class Transaction {

	public Scanner sc = new Scanner(System.in);
	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public void doTraction() {
		
		System.out.println();
		System.out.println("Transaction Panel");
		System.out.println();

		while (true) {
			System.out.println("Choose from below transaction option:");
			System.out.println("1. Deposit money");
			System.out.println("2. Withdraw money");
			System.out.println("3. Transfer money");
			System.out.println("4. Pay utility bills");
			System.out.println("0. Go back");
			int type = sc.nextInt();
			switch (type) {
			case 0:
				return;
			case 1:
				depositTransaction();
				break;
			case 2:
				withdrawTransaction();
				break;
			case 3:
				transferTransaction();
				break;
			case 4:
				payBillTransaction();
				break;
			default:
				System.out.println("Please choose valid option");
				break;
			}
		}
	}

	private void depositTransaction() {
		String tranDetail, date, personName, type;
		double amount;
		long accountId;
		
		System.out.println("Enter the accountId: ");
		accountId=sc.nextLong();
        System.out.println("Enter the amount: ");
        amount=sc.nextDouble();
        System.out.println("Enter the transaction Detail: ");
        tranDetail=sc.nextLine();
        System.out.println("Enter the person name: ");
        personName=sc.nextLine();
        System.out.println("Enter the type (cash/cheque): ");
        type=sc.next();
        date = new Date().toString();
        
        Deposit deposit=new Deposit(amount, tranDetail, accountId, date, personName, type);
        long transactionId = deposit.doTransaction();
        
        if(transactionId > 0) {
        	System.out.println("Transaction done successfully. Transaction Id: " + transactionId);
        } else {
        	System.out.println("Transaction failed.");
        }
	}

	private void withdrawTransaction() {
		String tranDetail = "Withdraw money by customer";
		String date, checkNo;
		double amount;
		long accountId;
		
		System.out.println("Enter the accountId: ");
		accountId=sc.nextLong();
        System.out.println("Enter the check number: ");
        checkNo=sc.nextLine();
        System.out.println("Enter the amount: ");
        amount=sc.nextDouble();
        date = new Date().toString();
        
        Withdraw withdraw=new Withdraw(amount, tranDetail, accountId, date, checkNo);
        long transactionId = withdraw.doTransaction();
        
        if(transactionId > 0) {
        	System.out.println("Transaction done successfully. Transaction Id: " + transactionId);
        } else {
        	System.out.println("Transaction failed.");
        }

	}

	private void transferTransaction() {
		String date, tranDetail;
		double amount;
		long accountId, destinationAccountId;
		
		System.out.println("Enter the accountId: ");
		accountId=sc.nextLong();
        System.out.println("Enter the accountId where you want to transfer: ");
        destinationAccountId=sc.nextLong();
        System.out.println("Enter the amount: ");
        amount=sc.nextDouble();
        System.out.println("Enter the transaction Detail: ");
        tranDetail=sc.nextLine();
        date = new Date().toString();
        
        Transfer transfer=new Transfer(amount, tranDetail, accountId, date, destinationAccountId);
        long transactionId = transfer.doTransaction();
        
        if(transactionId > 0) {
        	System.out.println("Transaction done successfully. Transaction Id: " + transactionId);
        } else {
        	System.out.println("Transaction failed.");
        }

	}

	private void payBillTransaction() {
		System.out.println();

		while (true) {
			System.out.println("Pay utilities bills:");
			System.out.println("1. Hydro");
			System.out.println("2. Water");
			System.out.println("3. DTH");
			System.out.println("4. Mobile Recharge");
			System.out.println("0. Go back");
			int type = sc.nextInt();
			switch (type) {
			case 0:
				return;
			case 1:
				hydro();
				break;
			case 2:
				water();
				break;
			case 3:
				dth();
				break;
			case 4:
				mobileRecharge();
				break;
			default:
				System.out.println("Please choose valid option");
				break;
			}
		}
	}
	
	
	private void hydro() {
		String type = "Hydro";
		String tranDetail = "Hydro bill paid";
		String date, provider, consumerNo;
		double amount;
		long accountId;
		
		System.out.println("Enter the accountId: ");
		accountId=sc.nextLong();
        System.out.println("Enter the provider name: ");
        provider=sc.nextLine();
        System.out.println("Enter the consumer number: ");
        consumerNo=sc.nextLine();
        System.out.println("Enter the amount: ");
        amount=sc.nextDouble();
        date = new Date().toString();
        
        BillPayment billPayment=new BillPayment(amount, tranDetail, accountId, date, provider, consumerNo, type);
        long transactionId = billPayment.doTransaction();
        
        if(transactionId > 0) {
        	System.out.println("Transaction done successfully. Transaction Id: " + transactionId);
        } else {
        	System.out.println("Transaction failed.");
        }
	}
	
	private void water() {
		String type = "Water";
		String tranDetail = "Water bill paid";
		String date, provider, consumerNo;
		double amount;
		long accountId;
		
		System.out.println("Enter the accountId: ");
		accountId=sc.nextLong();
        System.out.println("Enter the provider name: ");
        provider=sc.nextLine();
        System.out.println("Enter the consumer number: ");
        consumerNo=sc.nextLine();
        System.out.println("Enter the amount: ");
        amount=sc.nextDouble();
        date = new Date().toString();
        
        BillPayment billPayment=new BillPayment(amount, tranDetail, accountId, date, provider, consumerNo, type);
        long transactionId = billPayment.doTransaction();
        
        if(transactionId > 0) {
        	System.out.println("Transaction done successfully. Transaction Id: " + transactionId);
        } else {
        	System.out.println("Transaction failed.");
        }
	}
	
	private void dth() {
		String type = "DTH";
		String tranDetail = "DTH bill paid";
		String date, provider, consumerNo;
		double amount;
		long accountId;
		
		System.out.println("Enter the accountId: ");
		accountId=sc.nextLong();
        System.out.println("Enter the provider name: ");
        provider=sc.nextLine();
        System.out.println("Enter the consumer number: ");
        consumerNo=sc.nextLine();
        System.out.println("Enter the amount: ");
        amount=sc.nextDouble();
        date = new Date().toString();
        
        BillPayment billPayment=new BillPayment(amount, tranDetail, accountId, date, provider, consumerNo, type);
        long transactionId = billPayment.doTransaction();
        
        if(transactionId > 0) {
        	System.out.println("Transaction done successfully. Transaction Id: " + transactionId);
        } else {
        	System.out.println("Transaction failed.");
        }
	}
	
	private void mobileRecharge() {
		String type = "Mobile";
		String consumerNo = "";
		String tranDetail = "Mobile Recharge done";
		String date, provider;
		double amount;
		long accountId, mobileNo;
		
		System.out.println("Enter the accountId: ");
		accountId=sc.nextLong();
        System.out.println("Enter the mobile number to recharge: ");
        mobileNo=sc.nextLong();
        System.out.println("Enter the provider name: ");
        provider=sc.nextLine();
        System.out.println("Enter the amount: ");
        amount=sc.nextDouble();
        date = new Date().toString();
        
        MobileRecharge mobileRecharge=new MobileRecharge(amount, tranDetail, accountId, date, provider, consumerNo, type, mobileNo);
        long transactionId = mobileRecharge.doTransaction();
        
        if(transactionId > 0) {
        	System.out.println("Transaction done successfully. Transaction Id: " + transactionId);
        } else {
        	System.out.println("Transaction failed.");
        }
	}

}
