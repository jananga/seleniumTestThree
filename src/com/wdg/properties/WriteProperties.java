package com.wdg.properties;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

 class WriteProperties {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		writeProperty();

		// CreateTpUser("13297", "1234");
	}

	public static void writeProperty() {

		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream("D:\\github\\SeleniumTestTwo\\properties\\config.properties");

			// set the properties value
			prop.setProperty("adminUsername", "admin");
			prop.setProperty("adminPassword", "admin");
			prop.setProperty("tpUsername", "13297");
			prop.setProperty("tpPassword", "1234");
			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	@SuppressWarnings("finally")
	public static boolean CreateTpUser(String username, String password) {

		Properties prop = new Properties();
		boolean success = false;

		try (OutputStream output = new FileOutputStream("properties/config.properties");) {

			// set the properties value
			prop.setProperty("adminUsername", "13297");
			prop.setProperty("adminPassword", "1234");
			prop.put("tpUsername", username);
			prop.put("tpPassword", password);
			prop.store(output, null);
			success = true;
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			return success;

		}
	}
}
