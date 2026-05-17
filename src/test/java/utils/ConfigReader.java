package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	static Properties prop;

	public ConfigReader() {

		prop = new Properties();

		try {

			FileInputStream file = new FileInputStream("./src/main/resources/config.properties");

			prop.load(file);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	// Generic Method
	public static String getProperty(String key) {

		return prop.getProperty(key);
	}
}