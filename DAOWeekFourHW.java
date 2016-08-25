package com.WeekFourFinalAssignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.hithursday.Student;

public class DAOWeekFourHW {

	static final String DB_URL = "jdbc:mysql://localhost:3306/?user=root&autoReconnect=true&useSSL=false";
	static final String USER = "root";
	static final String PASSWORD = "sesame";

	static Connection CONN = null;
	static Statement STMT = null;
	static PreparedStatement PREP_STMT = null;
	static ResultSet RES_SET = null;

	public static void connToDB() {

		try {
			System.out.println("Connecting to the database.");
			CONN = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Connected to DB.");

		} catch (SQLException e) {
			System.out.println("Connection failed!");
			e.printStackTrace();
		}
	}

	public static void readFromDB() {

		connToDB();

		ArrayList<CarListFields> myCars = new ArrayList<>();

		try {
			STMT = CONN.createStatement();
			RES_SET = STMT.executeQuery("SELECT * FROM von_cars.filetobeimported;");

			while (RES_SET.next()) {
				CarListFields carsInDB = new CarListFields();

				carsInDB.setSerialNumber(RES_SET.getInt("Serial Number"));
				carsInDB.setYear(RES_SET.getInt("Year"));
				carsInDB.setCarMake(RES_SET.getString("Make"));
				carsInDB.setCarModel(RES_SET.getString("Model"));
				carsInDB.setNumOfCylinders(RES_SET.getInt("Num of Cylinders"));
				carsInDB.setHorsePower(RES_SET.getInt("Horsepower"));
				carsInDB.setEngDisplacement(RES_SET.getDouble("Eng Displacement"));
				
				myCars.add(carsInDB);

			}
			
			for (CarListFields cars : myCars) {
				System.out.println(cars.toString());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}