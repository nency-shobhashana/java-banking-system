package banksystem.transaction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import banksystem.account.Account;
import banksystem.account.Current;
import banksystem.account.Fixeddeposit;
import banksystem.account.Joint;
import banksystem.account.Savings;
import banksystem.customer.Customer;
import banksystem.transaction.payment.BillPayment;
import banksystem.transaction.payment.MobilePayment;

public class TransactionRepo {
	public static String fileName = "./src/banksystem/textDatabase/transaction.txt";
	public static String accountFileName = "./src/banksystem/textDatabase/account.txt";

	private static BufferedReader bufferedReader;
	private static BufferedWriter bufferedWriter;
	private static FileReader freader = null;
	private static FileWriter fWriter = null;

	// method for get bufferReader
	private static BufferedReader getBufferedReader() throws IOException {
		if (bufferedReader != null) {
			bufferedReader.close();
		}
		if (freader != null) {
			freader.close();
		}
		freader = new FileReader(fileName);
		bufferedReader = new BufferedReader(freader);
		return bufferedReader;
	}

	// method for get bufferWriter
	private static BufferedWriter getBufferedWriter() throws IOException {
		if (bufferedWriter != null) {
			bufferedWriter.close();
		}
		if (fWriter != null) {
			fWriter.close();
		}
		fWriter = new FileWriter(fileName, true);
		bufferedWriter = new BufferedWriter(fWriter);
		return bufferedWriter;
	}

	// method for last account number from file
	public static long getLastAccountNo() {
		long lastAccountNo = 0;
		try {
			String line;
			FileInputStream fstream = new FileInputStream(accountFileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			while ((line = br.readLine()) != null) {
				String accountType = line.split(" ")[0];
				Account account = null;
				if (accountType.equals("Current")) {
					account = Current.parseCurrent(line);
				}
				if (accountType.equals("Savings")) {
					account = Savings.parseSavings(line);
				}
				if (accountType.equals("Joint")) {
					account = Joint.parseJoint(line);
				}
				if (accountType.equals("Fixeddeposit")) {
					account = Fixeddeposit.parseFixeddeposit(line);
				}
				lastAccountNo = account.getAccountNo();
			}
			br.close();
			fstream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lastAccountNo;
	}

	// method for get account object of given account number from file
	public static Account getAccount(long accountNo) {
		FileInputStream fstream;
		BufferedReader br;
		try {
			fstream = new FileInputStream(accountFileName);
			br = new BufferedReader(new InputStreamReader(fstream));
			String line;
			while ((line = br.readLine()) != null) {
				String accountType = line.split(" ")[0];
				Account account = null;
				if (accountType.equals("Current")) {
					account = Current.parseCurrent(line);
				}
				if (accountType.equals("Savings")) {
					account = Savings.parseSavings(line);
				}
				if (accountType.equals("Joint")) {
					account = Joint.parseJoint(line);
				}
				if (accountType.equals("Fixeddeposit")) {
					account = Fixeddeposit.parseFixeddeposit(line);
				}
				if (account != null && account.getAccountNo() == accountNo) {
					br.close();
					fstream.close();
					return account;
				}
			}
			br.close();
			fstream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// method for upadte account balance after any transactions
	public static void updateAccount(Account account) {
		ArrayList<Account> accounts = new ArrayList<Account>();
		try {
			FileReader fileReader = new FileReader(accountFileName);
			BufferedReader br = new BufferedReader(fileReader);
			String line;
			while ((line = br.readLine()) != null) {
				String accountType = line.split(" ")[0];
				Account tempAccount = null;
				if (accountType.equals("Current")) {
					tempAccount = Current.parseCurrent(line);
				}
				if (accountType.equals("Savings")) {
					tempAccount = Savings.parseSavings(line);
				}
				if (accountType.equals("Joint")) {
					tempAccount = Joint.parseJoint(line);
				}
				if (accountType.equals("Fixeddeposit")) {
					tempAccount = Fixeddeposit.parseFixeddeposit(line);
				}
				if (tempAccount.getAccountNo() == account.getAccountNo()) {
					tempAccount = account;
				}
				accounts.add(tempAccount);
			}
			br.close();
			fileReader.close();
			FileWriter fileWriter = new FileWriter(accountFileName);
			BufferedWriter bw = new BufferedWriter(fileWriter);

			for (Account acc : accounts) {
				bw.write(acc.toString());
				bw.newLine();
			}
			bw.close();
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// method for last transaction ID from file
	public static long getLastTransId() {
		long lastTransactionId = 0;
		try {
			String line;
			BufferedReader br = getBufferedReader();

			while ((line = br.readLine()) != null) {
				String transactionType = line.split(" ")[0];
				BaseTransaction transaction = null;
				if (transactionType.equals("Deposit")) {
					transaction = Deposit.parseDeposit(line);
				}
				if (transactionType.equals("Withdraw")) {
					transaction = Withdraw.parseWithdraw(line);
				}
				if (transactionType.equals("Transfer")) {
					transaction = Transfer.parseTransfer(line);
				}
				if (transactionType.equals("BillPayment")) {
					transaction = BillPayment.parseBillPayment(line);
				}
				if (transactionType.equals("MobilePayment")) {
					transaction = MobilePayment.parseMobilePayment(line);
				}
				lastTransactionId = transaction.getTranID();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lastTransactionId;
	}

	// method to show last ten transactions of given account number
	public static ArrayList<BaseTransaction> getLastTenTransaction(long accountNo) throws Exception {
		ArrayList<BaseTransaction> transactions = new ArrayList<BaseTransaction>();
		String line;
		BufferedReader br = getBufferedReader();
		while ((line = br.readLine()) != null) {
			String transactionType = line.split(" ")[0];
			BaseTransaction transaction = null;
			if (transactionType.equals("Deposit")) {
				transaction = Deposit.parseDeposit(line);
			}
			if (transactionType.equals("Withdraw")) {
				transaction = Withdraw.parseWithdraw(line);
			}
			if (transactionType.equals("Transfer")) {
				transaction = Transfer.parseTransfer(line);
			}
			if (transactionType.equals("BillPayment")) {
				transaction = BillPayment.parseBillPayment(line);
			}
			if (transactionType.equals("MobilePayment")) {
				transaction = MobilePayment.parseMobilePayment(line);
			}
			if(transaction != null && transaction.getAccountNo() == accountNo) {
				transactions.add(transaction);
			}
			if (transactions.size() > 10) {
				transactions.remove(0);
			}
		}
		return transactions;

	}

	// method to show last ten deposit transactions
	public static ArrayList<Deposit> getLastTenDepositTransaction() throws Exception {
		ArrayList<Deposit> transactions = new ArrayList<Deposit>();
		String line;
		BufferedReader br = getBufferedReader();
		while ((line = br.readLine()) != null) {
			String transactionType = line.split(" ")[0];
			if (transactionType.equals("Deposit")) {
				Deposit transaction = Deposit.parseDeposit(line);
				transactions.add(transaction);
			}
			if (transactions.size() > 10) {
				transactions.remove(0);
			}
		}
		return transactions;
	}

	// method to show last ten withdraw transactions
	public static ArrayList<Withdraw> getLastTenWithdrawTransaction() throws Exception {
		ArrayList<Withdraw> transactions = new ArrayList<Withdraw>();
		String line;
		BufferedReader br = getBufferedReader();
		while ((line = br.readLine()) != null) {
			String transactionType = line.split(" ")[0];
			if (transactionType.equals("Withdraw")) {
				Withdraw transaction = Withdraw.parseWithdraw(line);
				transactions.add(transaction);
			}
			if (transactions.size() > 10) {
				transactions.remove(0);
			}
		}
		return transactions;
	}

	// method to show last ten transfer transactions
	public static ArrayList<Transfer> getLastTenTransferTransaction() throws Exception {
		ArrayList<Transfer> transactions = new ArrayList<Transfer>();
		String line;
		BufferedReader br = getBufferedReader();
		while ((line = br.readLine()) != null) {
			String transactionType = line.split(" ")[0];
			if (transactionType.equals("Transfer")) {
				Transfer transaction = Transfer.parseTransfer(line);
				transactions.add(transaction);
			}
			if (transactions.size() > 10) {
				transactions.remove(0);
			}
		}
		return transactions;
	}

	// method to show last ten utility bill payments transactions
	public static ArrayList<BillPayment> getLastTenBillPaymentTransaction() throws Exception {
		ArrayList<BillPayment> transactions = new ArrayList<BillPayment>();
		String line;
		BufferedReader br = getBufferedReader();
		while ((line = br.readLine()) != null) {
			String transactionType = line.split(" ")[0];
			if (transactionType.equals("BillPayment")) {
				BillPayment transaction = BillPayment.parseBillPayment(line);
				transactions.add(transaction);
			}
			if (transactions.size() > 10) {
				transactions.remove(0);
			}
		}
		return transactions;
	}

	// method to show last ten mobile payment transactions
	public static ArrayList<MobilePayment> getLastTenMobilePaymentTransaction() throws Exception {
		ArrayList<MobilePayment> transactions = new ArrayList<MobilePayment>();
		String line;
		BufferedReader br = getBufferedReader();
		while ((line = br.readLine()) != null) {
			String transactionType = line.split(" ")[0];
			if (transactionType.equals("MobilePayment")) {
				MobilePayment transaction = MobilePayment.parseMobilePayment(line);
				transactions.add(transaction);
			}
			if (transactions.size() > 10) {
				transactions.remove(0);
			}
		}
		return transactions;
	}

	// save deposit transaction in file
	public static long depositTransaction(Deposit deposit) {
		try {
			BufferedWriter bw = getBufferedWriter();
			bw.write(deposit.toString());
			bw.write("\n");
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return deposit.getTranID();
	}
	
	// save withdraw transaction in file
	public static long withdrawTransaction(Withdraw withdraw) {
		try {
			BufferedWriter bw = getBufferedWriter();
			bw.write(withdraw.toString());
			bw.write("\n");
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
		return withdraw.getTranID();
	}

	// save transfer transaction in file
	public static long transferTransaction(Transfer transfer) {
		try {
			BufferedWriter bw = getBufferedWriter();
			bw.write(transfer.toString());
			bw.write("\n");
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
		return transfer.getTranID();
	}

	// save bill payment transaction in file
	public static long billPaymentTransaction(BillPayment billPayment) {
		try {
			BufferedWriter bw = getBufferedWriter();
			bw.write(billPayment.toString());
			bw.write("\n");
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
		return billPayment.getTranID();
	}

	// save mobile bill transaction in file
	public static long mobilePaymentTransaction(MobilePayment mobilePayment) {
		try {
			BufferedWriter bw = getBufferedWriter();
			bw.write(mobilePayment.toString());
			bw.write("\n");
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
		return mobilePayment.getTranID();
	}
}
