package banksystem.account;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import banksystem.transaction.TransactionRepo;

public class AccountPanel {
	public static Scanner sc = new Scanner(System.in);
	public static String accountFileName = AccountPanel.class.getResource("../textDatabase/account.txt").getPath();
	// accountPanel main method
	public void main() throws IOException {

		System.out.println();
		System.out.println("Account Panel");
		System.out.println();

		while (true) {
			System.out.println("Choose from below option:");
			System.out.println("1. savings account");
			System.out.println("2. current account");
			System.out.println("3. fixed deposit account");
			System.out.println("4. joint account");
			System.out.println(" press  0  number to exit");
			int type = sc.nextInt();
			sc.nextLine();
			switch (type) {
			case 0:
				return;
			case 1:
				savings();
				break;
			case 2:
				current();
				break;
			case 3:
				fixeddeposit();
				break;
			case 4:
				joint();
				break;
			default:
				System.out.println("Please choose valid option");
				break;
			}

		}
	}

	// method for saving account
	private void savings() throws IOException {
		FileWriter fileWriter = new FileWriter(accountFileName, true);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		while (true) {
			double openingBalance, annualInterest;
			String createdDate = new Date().toString();
			System.out.println("Enter the Customer no: ");
			String customerNo = sc.nextLine();
			System.out.println("Enter  amount for opening account: ");
			openingBalance = sc.nextDouble();
			System.out.println("Enter annual interest: ");
			annualInterest = sc.nextDouble();
			Savings savingsAccount = new Savings(createdDate, customerNo, openingBalance, annualInterest);
			printWriter.println(savingsAccount.toString());
			System.out.println("Account Id is:" +savingsAccount.getAccountNo());

			System.out.println("press 0 to stop or any number to add more");
			if (sc.nextInt() == 0)
				break;
			sc.nextLine();
		}
		printWriter.close();
		fileWriter.close();
	}

	// method for current account
	private void current() throws IOException {
		FileWriter fileWriter = new FileWriter(accountFileName, true);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		while (true) {
			double openingBalance;
			System.out.println("Enter the Customer no: ");
			String customerNo = sc.nextLine();
			System.out.println("enter opening balance:");
			openingBalance = sc.nextDouble();
			String createdDate = new Date().toString();
			Current currentAccount = new Current(createdDate, customerNo, openingBalance);
			printWriter.println(currentAccount.toString());
			System.out.println("Account Id is:" +currentAccount.getAccountNo());
			System.out.println("press 0 to stop or any number to add more");
			if (sc.nextInt() == 0)
				break;
			sc.nextLine();
		}
		printWriter.close();
		fileWriter.close();
	}

	// method for fixed deposit account
	private void fixeddeposit() throws IOException {
		FileWriter fileWriter = new FileWriter(accountFileName, true);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		while (true) {
			int noOfDeposit;
			double interestRate;
			double timePeriod;
			String createdDate = new Date().toString();
			System.out.println("Enter the Customer no: ");
			String customerNo = sc.nextLine();
			System.out.println("Enter the amount:");
			noOfDeposit = sc.nextInt();
			System.out.println("interest rate:");
			interestRate = sc.nextDouble();
			System.out.println("Enter Time period (in months): ");
			timePeriod = sc.nextDouble();
			Fixeddeposit fixeddepositAccount = new Fixeddeposit(createdDate, customerNo, noOfDeposit, interestRate,
					timePeriod);
			printWriter.println(fixeddepositAccount.toString());
			System.out.println("Account Id is:" +fixeddepositAccount.getAccountNo());
			System.out.println("press 0 to stop or any number to add more");
			if (sc.nextInt() == 0)
				break;
			sc.nextLine();
		}
		printWriter.close();
		fileWriter.close();
	}

	// method for joint account
	private void joint() throws IOException {
		FileWriter fileWriter = new FileWriter(accountFileName, true);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		while (true) {
			double openingBalance;
			String jointHolderName, holderRelationship;
			String createdDate = new Date().toString();
			System.out.println("Enter the Customer no: ");
			String customerNo = sc.nextLine();
			System.out.println("enter opening balance:");
			openingBalance = sc.nextDouble();
			sc.nextLine();
			System.out.println("Enter the name of joint account holder");
			jointHolderName = sc.nextLine();
			System.out.println("Enter relationship with joint account holder");
			holderRelationship = sc.nextLine();
			Joint jointAccount = new Joint(createdDate, customerNo, openingBalance, jointHolderName,
					holderRelationship);
			System.out.println("Account Id is:" +jointAccount.getAccountNo());
			printWriter.println(jointAccount.toString());
			System.out.println("press 0 to stop or any number to add more");
			if (sc.nextInt() == 0)
				break;
			sc.nextLine();
		}
		printWriter.close();
		fileWriter.close();
	}

}
