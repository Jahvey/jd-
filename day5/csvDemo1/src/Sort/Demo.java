package Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Demo {
	public static void fun(){
		
		//声明  
		Map<String,Float> hashMap = new HashMap<String,Float>();  
		//向Map中添加数据  
		//.....  
		/**
		 * 100000,111620,0.060599886
100000,125225,0.0833
100000,95194,1.0
		 * */
		hashMap.put("111620", Float.valueOf("0.060599886"));
		hashMap.put("125225", Float.valueOf("0.0833"));
		hashMap.put("95194", Float.valueOf("1.0"));
		hashMap.put("951967", Float.valueOf("0.998"));
		
		//转换  
		ArrayList<Entry<String, Float>> arrayList = new ArrayList<Map.Entry<String,Float>>(hashMap.entrySet());  
		//排序  
		Collections.sort(arrayList, new Comparator<Map.Entry<String, Float>>(){  
		    public int compare(Map.Entry<String, Float> map1,  
		            Map.Entry<String,Float> map2) {  
		        return ((map2.getValue() - map1.getValue() == 0) ? 0  
		                : (map2.getValue() - map1.getValue() > 0) ? 1  
		                        : -1);  
		    }  
		});  
		//输出  
		for (Entry<String, Float> entry : arrayList) {  
		    System.out.println(entry.getKey() + "\t" + entry.getValue());  
		} 
		System.out.println("==========");
		System.out.println(arrayList.get(0).getKey()+"\t"+arrayList.get(0).getValue());
		
	}
	
	public static void main(String[] args) {
		fun();
		
		/**
		 * 
		 * 
95194	1.0
951967	0.998
125225	0.0833
111620	0.060599886

		 * */
		
	}

}
