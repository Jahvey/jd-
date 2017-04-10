package day2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class getRes2 {
	
	public static void WriteCSV1(File csv, StringBuffer sBuffer) {

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
	
	
	public static void  ReadCSV1(FileReader fr){
		 try {  
	          //user_id,sku_id
			 BufferedReader reader = new BufferedReader(fr);
	            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉 
	            String line = null;  
	           
	            String lastUser="1";
	            
	      
	          StringBuffer all=new StringBuffer();
	          
	           String string="";
	           int count=0;
	           Map<String, List<String>> map=new HashMap<>();
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
								// all.append(string+","+list);
								 all.append(string+","+list.get(list.size()>=3?2:0));
								WriteCSV1(new File("res114.csv"),all);
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
	            
	            
//	            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
//	            	
//	            	   System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue().toString());
//	            	   
//	            }
	            
	            
	            System.out.println(map.entrySet().size());
	            System.out.println("count:="+count);
	            
	            
	            
	        } catch (Exception e) {  
	            e.printStackTrace();
	        	
	        	//System.out.println("File Complete!!");
	        	
	        }  
		
		
		
	}
	
	
	


	public static void main(String[] args) {
try {
			
			//user_id,sku_id
			ReadCSV1(new FileReader("res21.csv"));
	
//	List<Integer> list = new ArrayList<Integer>();
//    for(int i=0;i<10;i++)
//        list.add(i);
//    System.out.println(list);
//    list.clear();
//    System.out.println(list);
			
			//String a="1";
			//System.out.println(a=="1");
			/* float a=Float.parseFloat("0.0");
				int b=0;
				if(a==b)
				{
					System.out.println("aljkfdlj");
				}else {
					System.out.println("Erro");
				}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
