package banksystem.account;

public class Joint extends Account {
	private double openingBalance;
	private String jointHolderName;
	private String holderRelationship;

//constructor
	public Joint(String createdDate, String customerName, double openingBalance, String jointHolderName,
			String holderRelationship) {
		super(createdDate, customerName);
		this.openingBalance = openingBalance;
		this.balance = openingBalance;
		this.jointHolderName = jointHolderName;
		this.holderRelationship = holderRelationship;
	}

//getters and setters
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
		return "Joint [" + accountNo + ";" + createdDate + ";" + customerName + ";" + openingBalance + ";"
				+ jointHolderName + ";" + holderRelationship + ";" + balance + "]";
	}

	public static Joint parseJoint(String str) throws Exception {
		str = str.replace("Joint [", "").replace("]", "");
		String[] fields = str.split(";");
		if (fields.length > 0) {
			Joint joint = new Joint(fields[1], fields[2], Double.parseDouble(fields[3]), fields[4], fields[5]);
			joint.accountNo = Long.parseLong(fields[0]);
			joint.balance = Double.parseDouble(fields[6]);
			return joint;
		} else {
			throw new Exception("");
		}
	}

}
