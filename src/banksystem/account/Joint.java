package banksystem.account;

public class Joint extends Account {
	private double openingBalance;
	private String jointHolderName;
	private String holderRelationship;

//constructor
	public Joint(String createdDate, String customerNo, double openingBalance, String jointHolderName,
			String holderRelationship) {
		super(createdDate, customerNo);
		this.openingBalance = openingBalance;
		this.balance = openingBalance;
		this.jointHolderName = jointHolderName;
		this.holderRelationship = holderRelationship;
	}
	
	public Joint(long accountNo, String createdDate, String customerNo, double openingBalance, String jointHolderName,
			String holderRelationship) {
		super(accountNo, createdDate, customerNo);
		this.openingBalance = openingBalance;
		this.balance = openingBalance;
		this.jointHolderName = jointHolderName;
		this.holderRelationship = holderRelationship;
	}

	// getters and setters
	public double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}

	public String getJointHolderName() {
		return jointHolderName;
	}

	public String getHolderRelationship() {
		return holderRelationship;
	}

	public void setHolderRelationship(String holderRelationship) {
		this.holderRelationship = holderRelationship;
	}

	public void setJointHolderName(String jointHolderName) {
		this.jointHolderName = jointHolderName;
	}

	@Override
	public String toString() {
		return "Joint [" + accountNo + ";" + createdDate + ";" + customerNo + ";" + openingBalance + ";"
				+ jointHolderName + ";" + holderRelationship + ";" + balance + "]";
	}

	// convert string to Joint account class
	public static Joint parseJoint(String str) throws Exception {
		str = str.replace("Joint [", "").replace("]", "");
		String[] fields = str.split(";");
		if (fields.length > 0) {
			Joint joint = new Joint(Long.parseLong(fields[0]), fields[1], fields[2], Double.parseDouble(fields[3]), fields[4], fields[5]);
			joint.balance = Double.parseDouble(fields[6]);
			return joint;
		} else {
			throw new Exception("");
		}
	}

}
