package csvDemo1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;  
public class TextRead {

 
	
	
	public static void  WriteCSV(File csv,StringBuffer sBuffer){
		// File csv = new File("F:/writers.csv"); // CSV数据文件 
		 
	      BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(csv, true));
			// 添加新的数据行 
		     // bw.write("\"李四\"" + "," + "\"1988\"" + "," + "\"1992\"");  
		    bw.write(new String(sBuffer));  
			bw.newLine();  
		      bw.close();  
		      
		      
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 附加 
	      
		
		
	}
	
	
	public static void  ReadCSV(FileReader fr){
		 try {  
	            //BufferedReader reader = new BufferedReader(new FileReader("comment_201602_1.csv"));//换成你的文件名 
			 BufferedReader reader = new BufferedReader(fr);
	            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉 
	            String line = null;  
	            int count=0;
	            
	            StringBuffer last;
	            while((line=reader.readLine())!=null){
	                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分 
	               count=3;
	               //String last = item[item.length-1];//这就是你要的数据了 
	             last =new StringBuffer() ;//这就是你要的数据了 
	                if (item[count-1]!=null&&item[count-2]!=null&&item[count-3]!=null) {
	                	
	                	if (Double.parseDouble(item[count-1])!=0.0) {
	                		last.append(item[count-3]+","+item[count-2]+","+item[count-1]);
		                	WriteCSV(new File("writers4.csv"),last);
		                	 System.out.println(last); 
		                	
		                	 //int value = Integer.parseInt(last);//如果是数值，可以转化为数值 
		                	 
		                	 last=null;
						}
	                	
						
	                	
					}else  {
						
							System.out.println("Write finished!!");
					
					}
	                
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
		
		
		
	}
	

 
    public static void main(String[] args) throws FileNotFoundException {  
    	ReadCSV(new FileReader("comments_04_2.csv"));
    }  
 

	
	

}
