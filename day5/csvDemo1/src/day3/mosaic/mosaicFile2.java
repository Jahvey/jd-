package day3.mosaic;

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

public class mosaicFile2 {


	public static void WriteCSV2(File csv, StringBuffer sBuffer) {

		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(csv, true));
			// 添加新的数据行

			bw.write(new String(sBuffer));
			bw.newLine();
			bw.close();

		} catch (IOException e) {

			e.printStackTrace();
		} // 附加

	}
	
	
	public static void  ReadCSV2(FileReader fr){
		 try {  
	          //user_id,sku_id
			 BufferedReader reader = new BufferedReader(fr);
	            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉 
	            String line = null;  
	           
	            String lastUser="1";
	            
	      
	          StringBuffer all=new StringBuffer();
	          
	           String string="";
	           int count=0;
	         //  Map<String, List<String>> map1=new HashMap<>();
	           Map<String, String> map=new HashMap<>();
	           List<String> list=new ArrayList<>();
	            while((line=reader.readLine())!=null){
	                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分 

	             
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
								all.append(string+","+toString(list, '|'));
								WriteCSV2(new File("res114.csv"),all);
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
		
			ReadCSV2(new FileReader("res21.csv"));
		
	
		
	}

}
