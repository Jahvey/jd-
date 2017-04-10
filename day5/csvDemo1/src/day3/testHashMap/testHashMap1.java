/**
 * 
 */
package day3.testHashMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * @author Administrator
 *
 */
public class testHashMap1 {

	public static void Test(){
		
		HashMap<String, String> keySetMap = new HashMap<String, String>();    
		  
        HashMap<String, String> entrySetMap = new HashMap<String, String>();  
        
        /**
         * 
i=9000000
keyset spent times:313
entrySet spent times:169

i=4000000
keyset spent times:191
entrySet spent times:111

i=9999999
keyset spent times:400
entrySet spent times:342

         * */
        
  
        for (int i = 0; i < 9999999; i++) {     
  
            keySetMap.put("" + i, "keySet");     
  
        }     
  
        for (int i = 0; i < 9999999; i++) {     
  
            entrySetMap.put("" + i, "entrySet");     
  
        }     
  
        long startTimeOne = System.currentTimeMillis();     
  
        Iterator<String> keySetIterator = keySetMap.keySet().iterator();    
  
        while (keySetIterator.hasNext()) {     
  
            String key = keySetIterator.next();     
  
            String value = keySetMap.get(key);   
          //  System.out.println("key:="+key+" ,value:="+value);
  
           // System.out.println(value);     
  
        }     
  
        System.out.println("keyset spent times:"     
  
                + (System.currentTimeMillis() - startTimeOne));    
  
        long startTimeTwo = System.currentTimeMillis();     
  
        Iterator<Entry<String, String>> entryKeyIterator = entrySetMap    
  
                .entrySet().iterator();     
  
        while (entryKeyIterator.hasNext()) {     
  
            Entry<String, String> e = entryKeyIterator.next();   
          //  System.out.println("key:="+e.getKey()+" ,value:="+e.getValue());
  
          //  System.out.println(e.getValue());     
  
        }     
  
        System.out.println("entrySet spent times:"     
  
               + (System.currentTimeMillis() - startTimeTwo));     
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test();
		
	}

}
