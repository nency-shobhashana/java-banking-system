package banksystem.transaction.payment;

import banksystem.account.Account;
import banksystem.transaction.BaseTransaction;
import banksystem.transaction.TransactionRepo;

public class BillPayment extends BaseTransaction {

	private String type;
	private String provider;
	private String consumerNo;

	// constructor
	public BillPayment(double amount, String tranDetail, long accountNo, String date, String type, String provider,
			String consumerNo) {
		super(amount, tranDetail, accountNo, date);
		this.type = type;
		this.provider = provider;
		this.consumerNo = consumerNo;
	}

	// constructor for auto-generate
	public BillPayment(long tranId, double amount, String tranDetail, long accountNo, String date, String type,
			String provider, String consumerNo) {
		super(tranId, amount, tranDetail, accountNo, date);
		this.type = type;
		this.provider = provider;
		this.consumerNo = consumerNo;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getConsumerNo() {
		return consumerNo;
	}
	public void setConsumerNo(String consumerNo) {
		this.consumerNo = consumerNo;
	}

	// method for calculate amount and save transaction
	@Override
	public long doTransaction() {
		Account account = getAccount();
		if (isAccountValid(account)) {
			double remainBalance = account.getBalance() - amount;
			if (remainBalance > 0) {
				account.setBalance(account.getBalance() - amount);
				TransactionRepo.updateAccount(account);
				return TransactionRepo.billPaymentTransaction(this);
			} else {
				System.out.println("Not sufficent balance in account; Your account balance is " + account.getBalance());
				return -1;
			}
		} else {
			System.out.println("Account no is invalid or does not exist");
			return -1;
		}
	}

	@Override
	public String toString() {
		return "BillPayment [" + tranID + ";" + accountNo + ";" + amount + ";" + type + ";" + provider + ";"
				+ consumerNo + ";" + tranDetail + ";" + date + "]";
	}

	// convert string to Utilizes Bill payment transaction class
	public static BillPayment parseBillPayment(String str) throws Exception {
		str = str.replace("BillPayment [", "").replace("]", "");
		String[] fields = str.split(";");
		if (fields.length > 0) {
			BillPayment billPayment = new BillPayment(Long.parseLong(fields[0]), Double.parseDouble(fields[2]),
					fields[6], Long.parseLong(fields[1]), fields[7], fields[3], fields[4], fields[5]);
			return billPayment;
		} else {
			throw new Exception("");
		}
	}

}
