package banksystem.transaction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import banksystem.transaction.payment.BillPayment;
import banksystem.transaction.payment.MobilePayment;

public class TransactionRepo {
	
	private static BufferedReader bufferedReader;
	private static BufferedWriter bufferedWriter;
	
	
	private static BufferedReader getBufferedReader() throws FileNotFoundException {
		if(bufferedReader == null) {
			FileInputStream fstream= new FileInputStream("E:\\Lambton\\projects\\java-banking-system\\src\\banksystem\\textDatabase\\transaction.txt");
			bufferedReader = new BufferedReader(new InputStreamReader(fstream));
		}
		return bufferedReader;
	}
	
	private static BufferedWriter getBufferedWriter() throws FileNotFoundException {
		if(bufferedWriter == null) {
			FileOutputStream fstream= new FileOutputStream("E:\\Lambton\\projects\\java-banking-system\\src\\banksystem\\textDatabase\\transaction.txt", true);
			bufferedWriter =  new BufferedWriter(new OutputStreamWriter(fstream));
		}
		return bufferedWriter;
	}
	
	public static long getLastTransId() {
		long lastTransactionId = 0;
		try {
			String line;
	        BufferedReader br = getBufferedReader();
	        
	        while((line = br.readLine())!=null) {
	        	String transactionType = line.split(" ")[0];
	        	BaseTransaction transaction = null;
	        	if(transactionType.equals("Deposit")) {
	                transaction = Deposit.parseDeposit(line);
	            }
	            if(transactionType.equals("Withdraw")) {
	            	transaction = Withdraw.parseWithdraw(line);
	            }
	            if(transactionType.equals("Transfer")) {
	            	transaction = Transfer.parseTransfer(line);
	            }
	            if(transactionType.equals("BillPayment")) {
	            	transaction = BillPayment.parseBillPayment(line);
	            }
	            if(transactionType.equals("MobilePayment")) {
	            	transaction = MobilePayment.parseMobilePayment(line);
	            }
	            lastTransactionId = transaction.getTranID();
	        }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lastTransactionId;
	}

	public static ArrayList<BaseTransaction> getLastTenTransaction(long accountNo) throws Exception{
		ArrayList<BaseTransaction> transactions = new ArrayList<BaseTransaction>();
		String line;
		int noOfTransaction = 0;
        BufferedReader br = getBufferedReader();
        while((line = br.readLine())!=null && noOfTransaction < 10) {
            String transactionType = line.split(" ")[0];
            if(transactionType.equals("Deposit")) {
                Deposit transaction = Deposit.parseDeposit(line);
                transactions.add(transaction);
            }
            if(transactionType.equals("Withdraw")) {
            	Withdraw transaction = Withdraw.parseWithdraw(line);
                transactions.add(transaction);
            }
            if(transactionType.equals("Transfer")) {
            	Transfer transaction = Transfer.parseTransfer(line);
                transactions.add(transaction);
            }
            if(transactionType.equals("BillPayment")) {
            	BillPayment transaction = BillPayment.parseBillPayment(line);
                transactions.add(transaction);
            }
            if(transactionType.equals("MobilePayment")) {
            	MobilePayment transaction = MobilePayment.parseMobilePayment(line);
                transactions.add(transaction);
            }
            noOfTransaction++;
        }
        return transactions;
        
	}

	public static ArrayList<Deposit> getLastTenDepositTransaction() throws Exception{
		ArrayList<Deposit> transactions = new ArrayList<Deposit>();
		String line;
		int noOfTransaction = 0;
        BufferedReader br = getBufferedReader();
        while((line = br.readLine())!=null && noOfTransaction < 10) {
            String transactionType = line.split(" ")[0];
            if(transactionType.equals("Deposit")) {
                Deposit transaction = Deposit.parseDeposit(line);
                transactions.add(transaction);
                noOfTransaction++;
            }
        }
        return transactions;
	}

	public static ArrayList<Withdraw> getAllWithdrawTransaction()  throws Exception{
		ArrayList<Withdraw> transactions = new ArrayList<Withdraw>();
		String line;
		int noOfTransaction = 0;
        BufferedReader br = getBufferedReader();
        while((line = br.readLine())!=null && noOfTransaction < 10) {
            String transactionType = line.split(" ")[0];
            if(transactionType.equals("Withdraw")) {
            	Withdraw transaction = Withdraw.parseWithdraw(line);
                transactions.add(transaction);
                noOfTransaction++;
            }
        }
        return transactions;
	}

	public static ArrayList<Transfer> getAllTransferTransaction() throws Exception{
		ArrayList<Transfer> transactions = new ArrayList<Transfer>();
		String line;
		int noOfTransaction = 0;
        BufferedReader br = getBufferedReader();
        while((line = br.readLine())!=null && noOfTransaction < 10) {
            String transactionType = line.split(" ")[0];
            if(transactionType.equals("Transfer")) {
            	Transfer transaction = Transfer.parseTransfer(line);
                transactions.add(transaction);
                noOfTransaction++;
            }
        }
        return transactions;
	}

	public static ArrayList<BillPayment> getAllBillPaymentTransaction() throws Exception{
		ArrayList<BillPayment> transactions = new ArrayList<BillPayment>();
		String line;
		int noOfTransaction = 0;
        BufferedReader br = getBufferedReader();
        while((line = br.readLine())!=null && noOfTransaction < 10) {
            String transactionType = line.split(" ")[0];
            if(transactionType.equals("BillPayment")) {
            	BillPayment transaction = BillPayment.parseBillPayment(line);
                transactions.add(transaction);
                noOfTransaction++;
            }
        }
        return transactions;
	}

	public static ArrayList<MobilePayment> getAllMobilePaymentTransaction() throws Exception{
		ArrayList<MobilePayment> transactions = new ArrayList<MobilePayment>();
		String line;
		int noOfTransaction = 0;
        BufferedReader br = getBufferedReader();
        while((line = br.readLine())!=null && noOfTransaction < 10) {
            String transactionType = line.split(" ")[0];
            if(transactionType.equals("MobilePayment")) {
            	MobilePayment transaction = MobilePayment.parseMobilePayment(line);
                transactions.add(transaction);
                noOfTransaction++;
            }
        }
        return transactions;
	}

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
