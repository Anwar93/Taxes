package application;

import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Account {

	private final SimpleStringProperty name = new SimpleStringProperty();
	private  SimpleIntegerProperty total= new SimpleIntegerProperty();
	int buys=0;
	String date;
	ArrayList<Purchase> purchases=new ArrayList<Purchase>();
	
	public Account(String name) {
		this.name.set(name);
	}
	
	public void pay(int amount) {

		this.total.set(this.total.subtract(amount).get());

		
	}
	
	public void purchase(int amount) {
		this.total.set(total.add(amount).get());	
		purchases.add(new Purchase(amount));
	}
	
	public void zero() {
		this.total.set(0);
		purchases.clear();
	}
	
	public void setTotal(int total) {
		this.total.set(total);
	}
	
	public void setName(String name) {
		this.name.set(name);
	}

	public String getName() {
		return name.get();
	}

	public int getTotal() {
		return total.get();
	}

	public ArrayList<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(ArrayList<Purchase> purchases) {
		this.purchases = purchases;
	}
		
}
