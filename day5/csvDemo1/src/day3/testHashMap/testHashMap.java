/**
 * 
 */
package day3.testHashMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Administrator
 *
 */
public class testHashMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Map<String, String> map=new HashMap<>();
		
		map.put("a", "b");
		map.put("a", "b");
		map.put("a", "b");
		map.put("a", "c");
		map.put("b", "b");
		System.out.println(map.size());
		
		Iterator<Entry<String, String>> entryKeyIterator = map.entrySet().iterator();  
	       while (entryKeyIterator.hasNext()) {  
	            Entry<String, String> e = entryKeyIterator.next();  
	            String value=e.getValue();  
	            System.out.println("key:="+e.getKey()+" ,value:="+e.getValue());
	 } 
		
		
		
		
	}

}
