package banksystem.transaction;

public class Deposit extends BaseTransaction {
	
	private String personName;
	private String type;
	
	public Deposit(double amount, String tranDetail, long accountId, String date, String personName,
			String type) {
		super(amount, tranDetail, accountId, date);
		this.personName = personName;
		this.type = type;
	}

	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public long doTransaction() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	

}
