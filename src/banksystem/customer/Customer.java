package banksystem.customer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import banksystem.transaction.BaseTransaction;
import banksystem.transaction.Deposit;
import banksystem.transaction.TransactionRepo;
import banksystem.transaction.Withdraw;

public class Customer {
	
	public static String customerFileName = "./src/banksystem/textDatabase/customer.txt";
	
	private static long lastCustomerNo = 0;
	protected long customerNo = 0;
	private String name;
	private String address;
	private String gender;
	private String phone;
	private String email;
	private String date;


	// constructor
	public Customer(String name, String address, String gender, String phone, String email, String date) {
		this.customerNo = generateCustomerNo();
		this.name = name;
		this.address = address;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.date = date;
	}
	
	// constructor for auto-generate
	public Customer(long customerNo, String name, String address, String gender, String phone, String email, String date) {
		this.customerNo = customerNo;
		this.name = name;
		this.address = address;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.date = date;
	}

	// method auto-generate customerNo
	private long generateCustomerNo() {
		if (lastCustomerNo == 0) {
			long customerNoFromfile = Customer.getLastCustomerNo();
			if (customerNoFromfile == 0) {
				lastCustomerNo = 100000000;
			} else {
				lastCustomerNo = customerNoFromfile;
			}
		}
		return ++lastCustomerNo;
	}

	// get last CustomerNo from file
	public static long getLastCustomerNo() {
		long lastCustomerNo = 0;
		try {
			String line;
			FileInputStream fstream = new FileInputStream(customerFileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			while ((line = br.readLine()) != null) {
				Customer customer = Customer.parseCustomer(line);
				lastCustomerNo = customer.getCustomerNo();
			}
			br.close();
			fstream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lastCustomerNo;

	}

	// getter and setter
	public long getCustomerNo() {
		return customerNo;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", address=" + address + ", gender=" + gender + ", phone=" + phone
				+ ", email=" + email + ", date=" + date + ", accNum=" + customerNo + "]";
	}

	// Write to string method
	public String writeString() {
		return name + "," + customerNo + "," + gender + "," + phone + "," + email + "," + date + "," +  address+ "\n";
	}
	
	// convert string to customer class
	private static Customer parseCustomer(String str) throws Exception {
		String[] fields = str.split(",");
		if (fields.length > 0) {
			Customer customer = new Customer(Long.parseLong(fields[1]), fields[0], fields[6], fields[2], fields[3], fields[4], fields[5]);
			return customer;
		} else {
			throw new Exception("");
		}
	}

}
