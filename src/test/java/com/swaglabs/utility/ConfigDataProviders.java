package com.swaglabs.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProviders {

	Properties pro;

	public ConfigDataProviders() {

		File source = new File("./Configuration/config.properties");

		try {

			FileInputStream fis = new FileInputStream(source);
			pro = new Properties();
			pro.load(fis);

		} catch (Exception e) {
			System.out.println("Uh...Oh.. I am unable to load to config file " + e.getMessage());
		}
	}

	public String getBrowser() {
		return pro.getProperty("browser");
	}

	public String getStagingUrl() {
		return pro.getProperty("testurl");
	}
}
