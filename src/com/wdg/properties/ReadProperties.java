package com.wdg.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ReadProperties {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, String> hm = readProperty();

		System.out.println(hm);
	}

	@SuppressWarnings("finally")
	public static Map<String, String> readProperty() {
		Properties prop = new Properties();
		String filename = "D:\\github\\SeleniumTestTwo\\properties\\config.properties";
		Map<String, String> properties = new HashMap<String, String>();

		System.out.println("read property calles");
		
		try (InputStream input = new FileInputStream(filename)) {

			// load a properties file from class path, inside static method
			prop.load(input);
			System.out.println("prop values are : "+prop);
			
			
			Set<String> propKeys = prop.stringPropertyNames();
			
			System.out.println("Key Length : "+propKeys.size());
			for (String key : propKeys) {
				String value = prop.getProperty(key);
				properties.put(key, value);

				System.out.println("Value iteration : " + properties.get(key));
			}
			// get the property value and print it out

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {

			return properties;
		}
	}

}
