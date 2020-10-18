package banksystem.transaction.payment;

import banksystem.account.Account;
import banksystem.transaction.TransactionRepo;

public class MobilePayment extends BillPayment {

	private Long mobileNo;

	// constructor
	public MobilePayment(double amount, String tranDetail, long accountNo, String date, String type, String provider,
			String consumerNo, Long mobileNo) {
		super(amount, tranDetail, accountNo, date, type, provider, consumerNo);
		this.mobileNo = mobileNo;
	}

	// constructor for auto-generate
	public MobilePayment(long tranId, double amount, String tranDetail, long accountNo, String date, String type,
			String provider, String consumerNo, Long mobileNo) {
		super(tranId, amount, tranDetail, accountNo, date, type, provider, consumerNo);
		this.mobileNo = mobileNo;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
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
				return TransactionRepo.mobilePaymentTransaction(this);
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
		return "MobilePayment [" + tranID + ";" + accountNo + ";" + amount + ";" + getType() + ";" + getProvider() + ";"
				+ mobileNo + ";" + tranDetail + ";" + date + "]";
	}

	// convert string to Mobile Payment transaction class
	public static MobilePayment parseMobilePayment(String str) throws Exception {
		str = str.replace("MobilePayment [", "").replace("]", "");
		String[] fields = str.split(";");
		if (fields.length > 0) {
			MobilePayment mobilePayment = new MobilePayment(Long.parseLong(fields[0]), Double.parseDouble(fields[2]),
					fields[6], Long.parseLong(fields[1]), fields[7], fields[3], fields[4], null,
					Long.parseLong(fields[5]));
			return mobilePayment;
		} else {
			throw new Exception("");
		}
	}

}
