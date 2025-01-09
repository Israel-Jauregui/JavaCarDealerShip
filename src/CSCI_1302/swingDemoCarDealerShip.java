package CSCI_1302;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;


import static javax.swing.GroupLayout.Alignment.*;

import java.sql.*;


public class swingDemoCarDealerShip {

	public static void guiHome(Connection connect) {

			//this class serves as the hub for the program
		JFrame f = new JFrame();

		JButton b = new JButton("Add Car");
		JButton c = new JButton("Sell Car");
		JButton d = new JButton("View Cars");
		JButton e = new JButton("Home");
		//sets up the GUI
		
		
		b.setBounds(100, 100, 100, 40);
		c.setBounds(200, 100, 100, 40);
		d.setBounds(300, 100, 100, 40);
		e.setBounds(390, 425, 100, 40);
		f.add(e);

		JLabel L1 = new JLabel("Car Dealership App");
		L1.setBounds(200, 25, 200, 100);

		b.setBounds(100, 100, 100, 40);

		b.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				System.out.println("debug1");

				f.dispose();
				guiAddCars(connect);
				//action performs are used in order to execute code when a button is clicked
			}

		});

		c.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				System.out.println("debug1");

				f.dispose();
				guiSellCars(connect);
			}

		});

		d.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				System.out.println("debug1");

				f.dispose();
				try {
					guiViewCars(connect);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

		e.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				System.out.println("debug1");

				f.dispose();
				guiHome(connect);
			}

		});

		f.add(b);
		f.add(c);
		f.add(d);
		f.add(L1);

		System.out.println("debug"); //these are used to debug and are meant more for me the developer

		f.setSize(501, 500);

		f.setSize(500, 500);

		f.setLayout(null);
		f.setVisible(true);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes program when you exit out

	}

	public static void guiAddCars(Connection connect) {
		JFrame f = new JFrame();
		JButton e = new JButton("Home");
		JButton Add = new JButton("Add");

		JLabel L1 = new JLabel("Add Car");

		JTextField Make = new JTextField("Make");
		JTextField Vin = new JTextField("Vin");
		JTextField Model = new JTextField("Model");
		JTextField Year = new JTextField("Year");
		
		//sets up gui
		Make.setBounds(200, 100, 100, 50);
		Model.setBounds(200, 150, 100, 50);
		Year.setBounds(200, 200, 100, 50);
		Vin.setBounds(200, 250, 100, 50);

		Add.setBounds(200, 300, 100, 50);

		e.setBounds(390, 425, 100, 40);
		L1.setBounds(200, 25, 200, 100);
		f.add(e);
		f.add(L1);
		f.add(Make);
		f.add(Vin);
		f.add(Model);
		f.add(Year);
		f.add(Add);

		f.setSize(500, 500);

		f.setLayout(null);
		f.setVisible(true);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		e.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				System.out.println("debug1");

				f.dispose();
				guiHome(connect);
			}

		});
		
		Add.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String MakeString =   Make.getText();
				String ModelString = Model.getText();
				String YearString = Year.getText();
				String VinString =  Vin.getText();
				
				int yearInt = Integer.parseInt(YearString);
				//parses everything to push to a helper method later to add data into the database
				try {
					addCar(connect, VinString, MakeString, ModelString, yearInt );
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				f.dispose();
				guiHome(connect);
			}

		});

	}

	public static void guiSellCars(Connection connect) {
		JFrame f = new JFrame();
		JButton e = new JButton("Home");
		JButton sell = new JButton("Sell");
		JLabel L1 = new JLabel("Enter Stock Number");
		JTextField t1 = new JTextField("");
		//sets up gui
		e.setBounds(390, 425, 100, 40);
		L1.setBounds(193, 60, 150, 50);
		t1.setBounds(200, 100, 100, 50);
		sell.setBounds(200, 150, 100, 50);

		f.add(t1);
		f.add(e);
		f.add(L1);
		f.add(sell);

		f.setSize(500, 500);

		f.setLayout(null);
		f.setVisible(true);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		e.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				System.out.println("debug1");

				f.dispose();
				guiHome(connect);
			}

		});
		
		sell.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String idToSell = t1.getText();
				int idSell = Integer.parseInt(idToSell);
				
				SellCar(connect, idSell);
				
				
				f.dispose();
				guiHome(connect);
				
				//This method sells a car in the inventory / Database by passing a value to the sell method
			}

		});

	}

	public static void guiViewCars(Connection connect) throws SQLException {
		JFrame f = new JFrame();
		JButton e = new JButton("Home");

		JPanel panel = new JPanel();

		e.setBounds(390, 425, 100, 40);

		f.add(e);

		String column[] = { "Stock Number", "Make", "Model", "Year", "Sold", "Vin" };

		ArrayList<cars> tempdata = setList(connect);
		
		
		String data[][] = new String[tempdata.size()][6];
		
		for(int i = 0; i < data.length; i++)
		{
			
			String stockNumber =  "" + tempdata.get(i).getStockNumber();
			String yearNumber = "" + tempdata.get(i).getYear();
			
			String sold;
			if(tempdata.get(i).getSold())
			{
				sold = "true";
			}
			else {
				sold= "false";
			}
			
			data[i][0] = stockNumber;
			data[i][1] = tempdata.get(i).getMake();
			data[i][2] = tempdata.get(i).getModel();
			data[i][3] = yearNumber;
			data[i][4] = sold;
			data[i][5] = tempdata.get(i).getVIN();
			
			//java swing likes arrays so i converted a arraylist to a array here
		}
		
		

		JTable jt = new JTable(data, column);
		jt.setBounds(50, 50, 200, 300);

		JScrollPane sp = new JScrollPane(jt);
		panel.add(sp);
		f.add(panel);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		e.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				System.out.println("debug1");

				f.dispose();
				guiHome(connect);
			}

		});

		f.setSize(500, 500);
		f.setVisible(true);

	}
	
	public static void SellCar(Connection connect, int ID) {

		String carSoldSql = "UPDATE `cars`.`car_info` SET `car_sold` = ? WHERE (`car_id` = ? );";

		try {
			PreparedStatement update = connect.prepareStatement(carSoldSql);
			update.setObject(1, 1);
			update.setObject(2, ID);
			update.execute();
			//updates update the database with our new values
		}

		catch (Exception e) {
			System.out.println("Sell Car ERROR" + e);
		}


	}
	
	public static void addCar(Connection connect, String vin, String make, String model, int year) throws SQLException {
		ArrayList<cars> IdTracker = setList(connect);

		int carSizeAfter = IdTracker.size() + 1;
		String carAddSql = "INSERT INTO `cars`.`car_info` (`car_id`, `car_vin`, `car_make`, `car_model`, `car_year`, `car_sold`) VALUES (?, ?, ?, ?, ?, ?);";

		try {
			PreparedStatement update = connect.prepareStatement(carAddSql);
			update.setObject(1, carSizeAfter);
			update.setObject(2, vin);
			update.setObject(3, make);
			update.setObject(4, model);
			update.setObject(5, year);
			update.setObject(6, false);
			update.execute();
			
			// adds new cars via a prepared statement and updating by inserting into a new row that is not populated
		} catch (Exception e) {

		}
		// INSERT INTO `cars`.`car_info` (`car_id`, `car_vin`, `car_make`, `car_model`,
		// `car_year`, `car_sold`) VALUES ('2', 'test3', 'Toyota', 'CANRY', '2002',
		// '0');

	}
	
	public static ArrayList<cars> setList(Connection conn) throws SQLException {

		ArrayList<cars> carList = new ArrayList<cars>();
		Statement statement = conn.createStatement();
		ResultSet resultSet = statement
				.executeQuery("SELECT car_id, car_vin, car_make,car_model, car_year, car_sold FROM `cars`.`car_info`;");
		;

		while (resultSet.next()) {
			try {
				cars car = new cars();

				car.setStockNumber(resultSet.getInt("car_id"));
				car.setVIN(resultSet.getString("car_vin"));
				car.setYear(resultSet.getInt("car_year"));
				car.setMake(resultSet.getString("car_make"));
				car.setModel(resultSet.getString("car_model"));
				car.setSold(resultSet.getBoolean("car_sold"));

				carList.add(car);
				
				// simply gets all the data from the delaership SQL database and stores it into the java program into a arraylist
			}

			catch (Exception e) {
				System.out.println("Something went wrong! + \n" + e);
			}

		}

		return carList;

	}

	

	public static void main(String[] args) {
		
		// NOTE: THIS PROGRAM CAN ONLY RUN IF IT DETECTS A TABLE CAR.CAR_INFO WITH THE COLUMNS CAR_ID CAR_VIN CAR_YEAR CAR_MAKE CAR_MODEL CAR_SOLD
		// THIS PROGRAM DOES NOT CREATE THIS SQL SERVER BECAUSE IT ASSUMES THAT YOU ARE CONNECTED TO A SERVER THAT IS ALREADY SET UP  
		String url = "jdbc:mysql://localhost:3306/cars"; 
		String username = "root";
		String key = ""; //used to store user credintials

		
		//this program can not run without being connected to the database
		try {

			Connection connection;

			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection(url, username, key);

			if (connection != null) {
				System.out.println("Connected to Database");
				guiHome(connection);
//makes connection

			}
			

			

		}

		catch (Exception e) {

			System.out.println(e + " SQL SERVER NOT REACHABLE");

		}
		

		
		

	}

}
