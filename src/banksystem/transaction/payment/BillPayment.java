package banksystem.transaction.payment;

import banksystem.transaction.BaseTransaction;
import banksystem.transaction.TransactionRepo;

public class BillPayment extends BaseTransaction {
	
	private String type;
	private String provider;
	private String consumerNo;

	public BillPayment(double amount, String tranDetail, long accountId, String date, String type,
			String provider, String consumerNo) {
		super(amount, tranDetail, accountId, date);
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

	@Override
	public long doTransaction() {
		return TransactionRepo.billPaymentTransaction(this);
	}

	@Override
	public String toString() {
		return "BillPayment [" + type + ";" + provider + ";" + consumerNo + ";" 
				+ tranID + ";" + amount + ";" + tranDetail + ";" + accountId + ";"
				+ date + "]";
	}


	public static BillPayment parseBillPayment(String str) throws Exception {
		str = str.replace("BillPayment [", "").replace("]", "");
		String[] fields = str.split(";");
		if(fields.length > 0) {
			BillPayment billPayment = new BillPayment(Double.parseDouble(fields[4]), fields[5], Long.parseLong(fields[6]), fields[7], fields[0], fields[1], fields[2]);
			billPayment.tranID = Long.parseLong(fields[3]);
			return billPayment;
		} else {
			throw new Exception("");
		}
	}



}
