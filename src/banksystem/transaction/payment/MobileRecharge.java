package banksystem.transaction.payment;

public class MobileRecharge extends BillPayment {
	
	private Long mobileNo;

	public MobileRecharge(double amount, String tranDetail, long accountId, String date, String type,
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

}
