package apiTest;

import java.io.FileInputStream;
import java.util.Properties;

public class GetConfigData {
	
	static Properties props = new Properties();
	
	public static void initializeProperties() {
				
		try {
			props.load(new FileInputStream("Configuration.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
				 	
	}
		
	public static String getURI(String serviceURI ) {
		
		initializeProperties();
		String URI = props.get(serviceURI).toString();
		return URI;
	}
	
	public static String getReportConfigPath(){
		 String reportConfigPath = props.getProperty("reportConfigPath");
		 if(reportConfigPath!= null) 
			 return reportConfigPath;
		 else 
			 throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath"); 
		}
	
	
	public static String getExcelFilePath(String pathExcelFile ) {
		
		initializeProperties();
		String path = props.get(pathExcelFile).toString();
		return path;
	}
}
