package day5.AnalysisData.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TestListToString {

	/**
	 * 
305226	103132,5
305228	157597,3
305230	79520,4
305232	24048,10
	 * */
	public  static  void TestDemo1(){
		Map<String,Integer> hashMap = new HashMap<String,Integer>(); 
		hashMap.put("103132", 5);
		hashMap.put("157597", 3);
		hashMap.put("79520", 4);
		hashMap.put("24048", 10);
		 ArrayList<Entry<String, Integer>> arrayList = new ArrayList<Map.Entry<String,Integer>>(hashMap.entrySet()); 
		 System.out.println(ListToString((List<Entry<String, Integer>>) arrayList, '|'));
		
	}
	
	
	
	public static <T,V> String ListToString(List<Entry<T, V>> list,String tmp, char ch) {
        Iterator<Entry<T, V>> it = list.iterator();
        if (! it.hasNext())
            return "";

        StringBuilder sb = new StringBuilder();
        
        for (;;) {
        	Entry<T, V> e = it.next();
            sb.append(e.getKey()+tmp+e.getValue());
            if (! it.hasNext())
                return sb.toString();
            sb.append(ch);
        }
    }
	
	 public static <T,V> String ListToString(List<Entry<T, V>> list, char ch) {
	        Iterator<Entry<T, V>> it = list.iterator();
	        if (! it.hasNext())
	            return "";

	        StringBuilder sb = new StringBuilder();
	        
	        for (;;) {
	        	Entry<T, V> e = it.next();
	            sb.append(e.getKey()+","+e.getValue());
	            if (! it.hasNext())
	                return sb.toString();
	            sb.append(ch);
	        }
	    }
	
	
	public static void main(String[] args) {
		
		TestDemo1();
		//"1".toString();
		
		
		
	}
	
	  public static  String ListToString1(List<Entry<String, Integer>> list, char ch) {
			
	    	Iterator<Entry<String, Integer>> it = list.iterator();

			if (!it.hasNext())
				return "";

			StringBuilder sb = new StringBuilder();

			for (;;) {
				Entry<String, Integer> e = it.next();

				sb.append(e);
				if (!it.hasNext()) {
					return sb.toString();

				}

				sb.append(ch);
			}
	    	
	    	
	    	
			
		}   
}
