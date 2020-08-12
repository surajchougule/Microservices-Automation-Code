package apiTest;

import java.util.HashMap;
import java.util.Map;

public class RequestHeaders {
	
	public static Map<String,String> addRequestHeaders(String[] key, String[] value) {
	 Map<String,String> requestHeaders=new HashMap<String,String>();
	 for(int i=0;i<key.length;i++) {
		 requestHeaders.put(key[i],value[i]);
	 }
	 return requestHeaders;
 	}
}
