package main;

import java.lang.reflect.Array;
import java.util.*;

import bankException.AmountExcption;
import bankException.CheckedException;
import bankException.DuplicationException;
import bankException.GeneralException;
import bankException.LimitInputException;
import interfaces.AccountInterface;
import interfaces.Profitable;

public abstract class Account implements AccountInterface {
	private static int ID = 0;
	private static final int BankNumber = 555;
	private List<String> listManager;
	private int accountNumber;
	private Date creationDate;
	private int balance;
	private String managerName;
	private Client[] clients;
	private int clientsCounter;
	private final float rateDifference = 0.1f;


	public Account(String managerName, int inputAccountNumber, boolean autoId) throws GeneralException {
		try {
			setID(autoId);
		} catch (DuplicationException e) {
			setAccountNumber(inputAccountNumber);
		}
		setManagerName(managerName);
		setDate();
		setBalance(20);
		setClients(clients);
		clients = new Client[2];
		this.clientsCounter = 0;
	}





	public void addClient(Client client) {
		// בדיקה אם יש צורך להגדיל את המערך
		if (clientsCounter >= clients.length) {
			Client[] newClients = new Client[clients.length * 2];// הגדלת המערך
			 System.arraycopy(clients, 0, newClients, 0, clients.length);
			 clients = newClients;
		}
		// הוספת הלקוח החדש למערך
		clients[clientsCounter++] = client;
}
    public void newClient(String nameOfTheClient, int clientRank) {
		if (clients.length == this.clientsCounter) {
			resizeArray();
		}
		Client newClient = new Client(nameOfTheClient, clientRank);
		clients[this.clientsCounter++] = newClient;
	}

    private void resizeArray() {
		clients = Arrays.copyOf(clients, clients.length + 2);

	}
	public float getRateDifference() {
		return rateDifference;
	}

	public static int getID() {
		return ID;
	}
	public static int getBankNumber() {
		return BankNumber;
	}
	public Date getDate() {
		return creationDate;
	}

	public int getAccountNumber() {
		return this.accountNumber;
	}
	public int getBalance() {
		return balance;
	}

	public String getManagerName() {
		return managerName;
	}

	public Client[] getClients() {
		return clients;
	}

	public int getClientCounter() {
		return clientsCounter;
	}

	public  void setID(boolean autoId) throws CheckedException {
		if (autoId) {
			this.accountNumber = ++ID;
		} else {
			throw new CheckedException(0);
		}
	}

	public void setDate() {
		this.creationDate = new Date();
	}

	public void setAccountNumber(int accountNumber) throws LimitInputException {
		if (accountNumber <= 0) {
			throw new LimitInputException("number above 0");
		}
		this.accountNumber = accountNumber;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public void setClients(Client[] clients) {
		this.clients = clients;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("The account has been approved by the manager, ").append(getManagerName()).append(" on the date - ");
		sb.append(getDate().getDay()).append("/").append(getDate().getMonth()).append("/").append(getDate().getYear());
		sb.append(" \n-> Account number - ").append(getAccountNumber()).append("\n -> The balance on this account is: ").append(getBalance()).append(".\n");
		sb.append("There are ").append(getClientCounter()).append(" owner's on this account.\nThere Detail's are:\n______________\n");
		for (int i = 0; i < getClientCounter(); i++) {
			sb.append("The ").append(i + 1).append(" Client data:\n");
			sb.append(" -> Client Name: ").append(getClients()[i].getName()).append(".\n");
			sb.append(" ->Client rank: ").append(getClients()[i].getClientRank()).append("/\n______________\n");
		}
		return sb.toString();

	}



}



