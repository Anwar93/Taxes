package application;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Purchase {

	
	private final SimpleStringProperty date = new SimpleStringProperty("date");
	private final SimpleIntegerProperty amount= new SimpleIntegerProperty();;
	
	public Purchase(int amount) {
		this.amount.set(amount);
		this.date.set(getCurrentDate());
	}

	public String getDate() {
		return date.get();
	}

	public int getAmount() {
		return amount.get();
	}
	
	public void setDate(String date) {
		this.date.set(date);;
	}

	public void setAmount(int amount) {
		this.amount.set(amount);
	}
	
	public String getCurrentDate() {
		
		Date date=new Date();
		SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(date);
	}
	
}
