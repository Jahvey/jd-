package day2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class getRes1 {
	
	public static void WriteCSV(File csv, StringBuffer sBuffer) {

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
	
	
	public static void  ReadCSV(FileReader fr){
		 try {  
	          //user_id,sku_id
			 BufferedReader reader = new BufferedReader(fr);
	            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉 
	            String line = null;  
	           
	            String lastUser="1";
	            
	      
	          StringBuffer all=new StringBuffer();
	          
	           
	            while((line=reader.readLine())!=null){
	                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分 
	               
	             
	             
	             
	             if(item!=null){
	            	 
	            	 
	            	 if(lastUser!="1"){//first read or not 
	            		
	            		 int count=0;
	            		 if (lastUser.equals(item[count])) {
  		
	            			 all.append(item[count]+"|"+item[count+1]);
	            			 all.append("\n");
	            			

							}else {
								//all.append("\n");
								System.out.println(all); 
								WriteCSV(new File("res111.csv"),all);
								all.setLength(0);
								
								lastUser=item[count];
								
							}
	            	 }else {
	            		 lastUser=item[0];
		            	 all.append(item[0]+","+item[1]);
		            	
					}
	            	 
	            	 
	            	 

        	 
	             }
  
	            }  
	            
	            System.out.println("Finished !!!");
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
		
		
		
	}
	

	public static void main(String[] args) {
try {
			
			//user_id,sku_id
			ReadCSV(new FileReader("res21.csv"));
			
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
