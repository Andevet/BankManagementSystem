package main;

public class Client {
	private String name;
	private int clientRank;
	public Client(String name,int clientRank) {
		setName(name);
		setClientRank(clientRank);
	}

	public Client(Client original) {
    this.name = original.name;
    this.clientRank = original.clientRank;
}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getClientRank() {
		return clientRank;
	}
	public void setClientRank(int clientRank) {
		this.clientRank = clientRank;
	}


}
