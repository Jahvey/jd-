

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class mosaicFile5 {


	public static void WriteCSV2(File csv, StringBuffer sBuffer) {

		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(csv, true));
	

			bw.write(new String(sBuffer));
			bw.newLine();
			bw.close();

		} catch (IOException e) {

			e.printStackTrace();
		} // 附加

	}
	
	
	public static void  ReadCSV2(FileReader fr){
		 try {  
	          //key,values
			 BufferedReader reader = new BufferedReader(fr);
	            reader.readLine();
	            String line = null;  
	           
	            String lastUser="1";
	            
	      
	          StringBuffer all=new StringBuffer();
	          
	           String string="";
	           int count=0;
	         //  Map<String, List<String>> map1=new HashMap<>();
	           Map<String, String> map=new HashMap<>();
	           List<String> list=new ArrayList<>();
	            while((line=reader.readLine())!=null){
	                String item[] = line.split(",");

	             
	             if(item!=null){
	            	// System.out.println(item[0]+" "+item[1]);

	            	 if (lastUser=="1") {
						list.add(item[1]);
						lastUser=item[0];
						string=item[0];
					}else {
						
						
						if (string.equals(item[0])&&list.size()>=1) {
							list.add(item[1]);
						}else {
							
							
							if (!string.equals(item[0])) {
								System.out.println(string+" "+list);
								map.put(string, toString(list, '|'));
								//all.append(string+","+toString(list, '|'));
								all.append(string.split("\\.")[0]+","+list.get(0));
								//WriteCSV2(new File("ress2.csv"),all);
								WriteCSV2(new File("ress3.csv"),all);
								all.setLength(0);
								
								//map.put(string, list);
								string=item[0];
								list.clear();
								//System.out.println(list.size());
								count++;
								list.add(item[1]);
							}
							
						}
						
					}
	            	 

        	 
	             }
  
	            }  
	            
	            System.out.println("Finished !!!");
	            
	            
	            for (Map.Entry<String, String> entry : map.entrySet()) {
	            	
	            	   System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
	            	   
	            }
	            
	            
	            System.out.println(map.entrySet().size());
	            System.out.println("count:="+count);
	            
	            
	            
	        } catch (Exception e) {  
	            e.printStackTrace();
	        	
	        	//System.out.println("File Complete!!");
	        	
	        }  
		
		
		
	}
	
	
	
	   public static <T> String toString(List<T> list,char ch) {
	        Iterator<T> it = list.iterator();
		   
	        if (! it.hasNext())
	        	return "";
	    

	        StringBuilder sb = new StringBuilder();
	       // sb.append('[');
	        for (;;) {
	            T e = it.next();
	            //sb.append(e == this ? "(this Collection)" : e);
	            sb.append(e);
	            if (! it.hasNext())
	               // return sb.append(']').toString();
	            	return sb.toString();
	           // sb.append('*').append(' ');
	            sb.append(ch);
	        }
	    }
	
	
	public static void main(String[] args) throws Exception{
		
			ReadCSV2(new FileReader("1.csv"));
			//ReadCSV2(new FileReader("protential_users.csv"));
		
	
		
	}

}
