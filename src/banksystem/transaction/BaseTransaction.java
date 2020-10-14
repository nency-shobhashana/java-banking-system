package banksystem.transaction;

public abstract class BaseTransaction {
	protected long tranID = 1000;
    protected double amount;
    protected String tranDetail;
    protected long accountId;
    protected String date;
    
    public abstract long doTransaction();
    
    // constructor
	public BaseTransaction(double amount, String tranDetail, long accountId, String date) {
		super();
		this.tranID = 0;
		this.amount = amount;
		this.tranDetail = tranDetail;
		this.accountId = accountId;
		this.date = date;
	}

	// getter and setter
	public long getTranID() {
		return tranID;
	}

	public double getAmount() {
		return amount;
	}
	public void setTranAmount(double amount) {
		this.amount = amount;
	}

	public String getTranDetail() {
		return tranDetail;
	}
	public void setTranDetail(String tranDetail) {
		this.tranDetail = tranDetail;
	}
	
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		return "transcation [tranID=" + tranID + ", Amount=" + amount + ", tranDetail=" + tranDetail + "]";
	}
	
	protected boolean isAccountIdValid() {
		
		return true;
	}
	
	
}
