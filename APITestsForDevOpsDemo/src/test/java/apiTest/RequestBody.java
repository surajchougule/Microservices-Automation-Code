package apiTest;

import java.util.HashMap;
import java.util.Map;

public class RequestBody {
	
	public static Map<String,String> addRequestParams(String[] key, String[] value) {
	 Map<String,String> json=new HashMap<String,String>();
	 for(int i=0;i<key.length;i++) {
	 json.put(key[i],value[i]);
	 }
	 return json;
 	}
}
