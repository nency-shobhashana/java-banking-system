package banksystem.customer;

import java.util.*;

import banksystem.transaction.TransactionRepo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;

public class CustomerPanel {

	public static String customerFileName = "./src/banksystem/textDatabase/customer.txt";
	public static Scanner sc = new Scanner(System.in);

	// customerPanel main method
	public void main() throws IOException {
		filling();
	}

	// method to display customer detail of given customer number
	public static void viewCustomer(ArrayList<Customer> customer, String num) throws IOException {
		FileInputStream fstream = new FileInputStream(customerFileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String line;
		String fields[] = new String[7];
		while ((line = br.readLine()) != null) {
			fields = line.split(",");
			if (line.contains(num)) {
				System.out.println("Name: " + fields[0] + "\n" + "Address: " + fields[6] + "\n" + "Gender: " + fields[2]
						+ "\n" + "Phone number: " + fields[3] + "\n" + "Email: " + fields[4] + "\n" + "DOB: "
						+ fields[5]);
			}
		}
		br.close();
		fstream.close();
	}

	// method for update customer file
	public static void replaceInFile(File file, String num, String str) throws IOException {

		File tempFile = File.createTempFile("buffer", ".tmp");
		FileWriter fw = new FileWriter(tempFile);

		Reader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		while (br.ready()) {
			fw.write(br.readLine().replaceAll(num, str));
			fw.write("\n");
		}

		fw.close();
		br.close();
		fr.close();

		// Finally replace the original file.
		Files.move(tempFile.toPath(), file.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
		System.out.println("Updated Successfully.");
	}

	// method to create customer
	public static void filling() throws IOException {
		File myObj = new File(customerFileName);
		if (myObj.createNewFile()) {
			System.out.println("File created: " + myObj.getName());
		}

		System.out.println();
		System.out.println("Customer Panel");
		System.out.println();

		while (true) {
			System.out.println("Choose from below options : \n1: Create Customer\n2: View/Update Customer Details \n0: for back");
			System.out.println();
			int type = sc.nextInt();
			sc.nextLine();
			if(type == 0) {
				return;
			}
			if (type == 1) {
				System.out.println("Enter the full name: ");
				String name = sc.nextLine();
				System.out.println("Enter the address: ");
				String address = sc.nextLine();
				System.out.println("Enter the gender: ");
				String gender = sc.nextLine();
				System.out.println("Enter the DOB: ");
				String dob = sc.nextLine();
				System.out.println("Enter the phone number: ");
				String phone = sc.nextLine();
				System.out.println("Enter the email: ");
				String email = sc.nextLine();
				Customer customer = new Customer(name, address, gender, phone, email, dob);
				System.out.println("Customer No is:" +customer.getCustomerNo());

				PrintWriter out = null;
				FileWriter fstream = null;
				BufferedWriter bw = null;
				try {
					fstream = new FileWriter(customerFileName, true);
					bw= new BufferedWriter(fstream);
					out = new PrintWriter(bw);
					out.print(customer.writeString());
				} catch (IOException e) {
					System.err.println(e);
				} finally {
					if (out != null) {
						out.close();
					}
					if (bw != null) {
						bw.close();
					}
					if (fstream != null) {
						fstream.close();
					}
				}

			} else if (type == 2) {
				System.out.println("Choose \n1: View\n2: Update");
				int choice = sc.nextInt();
				if (choice == 1) {
					System.out.println("Enter the customer number: ");
					String num = sc.next();
					ArrayList<Customer> customer = new ArrayList<Customer>();
					viewCustomer(customer, num);
				} else if (choice == 2) {
					ArrayList<Customer> customer = new ArrayList<Customer>();
					System.out.println("Enter the customer number: ");
					String num = sc.next();
					System.out.println(
							"Choose from below options: \n1: Change name\n2: Change address\n3: Change Phone number\n4: Change email");
					int select = sc.nextInt();
					sc.nextLine();
					switch (select) {
					case 1:
						System.out.println("Enter the old name: ");
						String name1 = sc.nextLine();
						System.out.println("Enter the new name: ");
						String name2 = sc.nextLine();
						replaceInFile(myObj, name1, name2);
						break;
					case 2:
						System.out.println("Enter the old address: ");
						String address1 = sc.nextLine();
						System.out.println("Enter the new address: ");
						String address2 = sc.nextLine();
						replaceInFile(myObj, address1, address2);
						break;
					case 3:
						System.out.println("Enter the old phone number: ");
						String phone1 = sc.nextLine();
						System.out.println("Enter the new phone number: ");
						String phone2 = sc.nextLine();
						replaceInFile(myObj, phone1, phone2);
						break;
					case 4:
						System.out.println("Enter the old email: ");
						String email1 = sc.nextLine();
						System.out.println("Enter the new email: ");
						String email2 = sc.nextLine();
						replaceInFile(myObj, email1, email2);
						break;
					default:
						break;
					}
				} else {
					break;
				}
			} else {
				break;
			}
		}
	}
}
