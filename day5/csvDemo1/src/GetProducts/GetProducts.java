package GetProducts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * 
 * 
 * no bug!!!
 * */

public class GetProducts {
	
	
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
	          //dt,sku_id,comment_num,has_bad_comment,bad_comment_rate
			 BufferedReader reader = new BufferedReader(fr);
	            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉 
	            String line = null;  
	            int count=0;
	            
	            StringBuffer last;
	            while((line=reader.readLine())!=null){
	                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分 
	               count=5;
	               //String last = item[item.length-1];//这就是你要的数据了 
	             last =new StringBuffer() ;//这就是你要的数据了 
	             
	             if(item!=null){
	            	  if (Float.parseFloat(item[count-1])!=0) {
	 	            	 
	 	            	 last.append(item[count-4]+","+item[count-1]+","+item[count-2]+" "+item[count-3]);
	 	                	WriteCSV(new File("getproducts.csv"),last);
	 	                	 System.out.println(last); 
	 	                	
	 	                	 //int value = Integer.parseInt(last);//如果是数值，可以转化为数值 
	 	                	 
	 	                	 last.setLength(0);
	 					
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
			
			//dt,sku_id,comment_num,has_bad_comment,bad_comment_rate
			ReadCSV(new FileReader("comments.csv"));
			
			/* float a=Float.parseFloat("0.0");
				int b=0;
				if(a==b)
				{
					System.out.println("aljkfdlj");
				}else {
					System.out.println("Erro");
				}*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
