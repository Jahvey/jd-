/**
 * 
 */
package day4.JointFiles.bug;

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

/**
 * @author Administrator
 *
 */
public class jointFile1 {

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
	          //key,values
			 BufferedReader reader = new BufferedReader(fr);
	            ///reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉 
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
	   
	   
	   public static void  ReadText(FileReader fr,String outfile){
			 try {  
		          //key,values
				 BufferedReader reader = new BufferedReader(fr);
				 String islabel=reader.readLine();
				 if (!TestFirstLineIsLabel(islabel)) {}// Test Label Info or not

		            //reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉 
		            String line = islabel;  
		           
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
										
										//WriteText(new File(outfile),all);
										WriteText(new File(outfile),islabel,all);
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
		
		
	   
	   public static void WriteText(File csv, StringBuffer sBuffer) {

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
	   
	   
	   
	   public static void WriteText(File csv,String FirstLabel, StringBuffer sBuffer) {

			BufferedWriter bw;
			try {
			
				boolean isWriteLable=false;
				bw = new BufferedWriter(new FileWriter(csv, true));
				if (isWriteLable==false&&csv.length()==0) {
					bw.write(FirstLabel);
					bw.newLine();
					System.out.println("Label ["+FirstLabel+"] Write Complete!!");
					isWriteLable=true;
				}
				
				

				bw.write(new String(sBuffer));
				bw.newLine();
				bw.close();

			} catch (IOException e) {

				e.printStackTrace();
			} // 附加

		}
	
	   
	  // 1.用JAVA自带的函数
	   public static boolean isNumeric(String str){
	   for (int i = 0; i < str.length(); i++){
	      System.out.println(str.charAt(i));
	      if (!Character.isDigit(str.charAt(i))){
	       return false;
	      }
	   }
	   return true;
	   }
	   
	
	   public static void TestDemo1()throws Exception{
		   String tmp="user_id,sku_id,time,model_id,type,cate,brand";
		   String tmp1="100001.0,114568,2016-02-17 10:30:02,0,6,5,479";
		   String []splits=tmp1.split(",");
		  // String []splits=tmp.split(",");
		   if (isNumeric(splits[0].split("\\.")[0])&&isNumeric(splits[0].split("\\.")[1])) {
			   System.out.println("is num!!");
			
		}else {
			System.out.println("not num!!!");
		}
		   
		   
	   }
	   
	   public static boolean TestFirstLineIsLabel(String line)throws Exception{

		   String []splits=line.split(",");

		   if (isNumeric(splits[0].split("\\.")[0])&&isNumeric(splits[0].split("\\.")[1])) {
			   System.out.println("is num!!");
			   return true;
			
		}else {
			System.out.println("not num!!!");
			return false;
		}
		   
		   
	   }
	   
	   
 public static void MergeFiles(String path,String []files,String outfile)throws Exception{
		 String tmp="";
		 if (new File(path+File.separator+outfile).exists()) {
			 new File(path+File.separator+outfile).delete();
			 System.out.println("Delete output files ["+path+File.separator+outfile+"] success!!");
		}
	 	for (int i = 0; i < files.length; i++) {
			tmp=path+File.separator+files[i];
			System.out.println(tmp);
			
			ReadText(new FileReader(tmp),path+File.separator+outfile);
			
			tmp="";
			
		}
		   
		   System.out.println("MergeFiles Complete!!");
		   
	   }
	public static void main(String[] args) throws Exception{
		MergeFiles("data",new String[]{"1.txt","2.txt","3.txt"},"out.csv");
		
		//TestDemo1();
		
			//ReadCSV2(new FileReader("res21.csv"));
		
	
		
	}

}
