package day4.JointFiles.test;

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

public class testWrite2 {
	
	
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
	
	
	public static void ReadText(String csv,String output) {
		String islabel="user_id,sku_id,time,model_id,type,cate,brand";
		String string2= null; 
		StringBuffer all=new StringBuffer();
        
        String string="";
        int count=0;
     
        Map<String, String> map=new HashMap<>();
        List<String> list=new ArrayList<>();
        
		try {  
		    FileReader fr = new FileReader(csv);  
		    BufferedReader br = new BufferedReader(fr);  
		    String str = null;  
		    while ((str = br.readLine()) != null) {  
		        string2= str;  
		        System.out.println(string2); 
		        
		        
		        String item[] = string2.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分 
		        
		        list.add(item[1]);
				
				string=item[0];
		        
		        
		        
		        System.out.println(string+" "+list);
				map.put(string, toString(list, '|'));
				all.append(string+","+toString(list, '|'));
				
			
				WriteText(new File(output),islabel,all);
				all.setLength(0);
				list.clear();
		    }  
		    
		    br.close();  
		    fr.close();  
		} catch (Exception e) {  
		    e.printStackTrace();  
		}  
		
		
	}
	
	
	public static <T> String toString(List<T> list,char ch) {
        Iterator<T> it = list.iterator();
	   
        if (! it.hasNext())
        	return "";
    

        StringBuilder sb = new StringBuilder();
     
        for (;;) {
            T e = it.next();
           
            sb.append(e);
            if (! it.hasNext()){
            	return sb.toString();
            	
            	
            }
             
            	
         
            sb.append(ch);
        }
    }
	
public static void main(String[] args) {
	ReadText("data/1.txt","data/ww1.txt");
	
	
}

}
