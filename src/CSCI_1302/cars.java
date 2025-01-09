package CSCI_1302;

public class cars {

	int stockNumber;
	int year;
	String VIN;
	
	String make;
	String model;
	boolean sold;
	
	
	cars()
	{
		
	}
	
	
	cars(int stockNumber, int year, String VIN, String make, String model, boolean sold)
	{
		this.stockNumber = stockNumber;
		this.year = year; 
		this.VIN = VIN;
		this.make = make; 
		this.model = model;
		this.sold = sold;
		
	}
	
	
	
	
	public int getStockNumber() {
		return stockNumber;
	}




	public void setStockNumber(int stockNumber) {
		this.stockNumber = stockNumber;
	}




	public int getYear() {
		return year;
	}




	public void setYear(int year) {
		this.year = year;
	}




	public String getVIN() {
		return VIN;
	}




	public void setVIN(String vIN) {
		VIN = vIN;
	}




	public String getMake() {
		return make;
	}




	public void setMake(String make) {
		this.make = make;
	}




	public String getModel() {
		return model;
	}




	public void setModel(String model) {
		this.model = model;
	}

	public boolean getSold()
	{
	   return sold;
	}
	
	public void setSold(boolean sold)
	{
		this.sold = sold;
	}
	
	


	public String toString()
	{
		return "Car Info: \n" + "Stock Number: " + stockNumber +"\n" +"Make:" + make + "\n" + "Model:" + model + "\n" + "Year:" + year + "\n" + "VIN:" + VIN + "\n" + "Sold:" + sold + "\n";
	}
	
	
}
