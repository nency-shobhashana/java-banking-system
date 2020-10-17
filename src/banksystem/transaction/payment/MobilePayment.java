package banksystem.transaction.payment;

import banksystem.transaction.TransactionRepo;

public class MobilePayment extends BillPayment {
	
	private Long mobileNo;

	public MobilePayment(double amount, String tranDetail, long accountId, String date, String type,
			String provider, String consumerNo, Long mobileNo) {
		super(amount, tranDetail, accountId, date, type, provider, consumerNo);
		this.mobileNo = mobileNo;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Override
	public long doTransaction() {
		return TransactionRepo.mobilePaymentTransaction(this);
	}
	
	@Override
	public String toString() {
		return "MobilePayment [" + getType() + ";" + getProvider() + ";" + mobileNo + ";" + tranID + ";" + amount + ";"
				+ tranDetail + ";" + accountId + ";" + date + "]";
	}

	public static MobilePayment parseMobilePayment(String str) throws Exception {
		str = str.replace("MobilePayment [", "").replace("]", "");
		String[] fields = str.split(";");
		if(fields.length > 0) {
			MobilePayment mobilePayment = new MobilePayment(Double.parseDouble(fields[4]), fields[5], Long.parseLong(fields[6]), fields[7], fields[0], fields[1], null, Long.parseLong(fields[2]));
			mobilePayment.tranID = Long.parseLong(fields[1]);
			return mobilePayment;
		} else {
			throw new Exception("");
		}
	}
	
	

}
