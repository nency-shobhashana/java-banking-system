package banksystem.transaction.payment;

import banksystem.transaction.BaseTransaction;

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
		// TODO Auto-generated method stub
		return 0;
	}



}
