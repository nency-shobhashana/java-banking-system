package banksystem;

import java.util.Scanner;

import banksystem.account.AccountPanel;
import banksystem.customer.CustomerPanel;
import banksystem.transaction.TransactionPanel;

public class bankingSystem {

	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws Exception {
		TransactionPanel transactionPanel = new TransactionPanel();
		AccountPanel accountPanel = new AccountPanel();
		CustomerPanel customerPanel = new CustomerPanel();

		System.out.println();
		System.out.println("Welcome to Banking System");
		System.out.println();

		while (true) {
			System.out.println("Choose from below for banking:");
			System.out.println("1. Create Customer");
			System.out.println("2. Create Account");
			System.out.println("3. Do Transactions");
			System.out.println("0. Exit");
			int type = sc.nextInt();
			switch (type) {
			case 0:
				return;
			case 1:
				customerPanel.main();
				break;
			case 2:
				accountPanel.main();
				break;
			case 3:
				transactionPanel.main();
				break;
			default:
				System.out.println("Please choose valid option");
				break;
			}
		}
	}

}
