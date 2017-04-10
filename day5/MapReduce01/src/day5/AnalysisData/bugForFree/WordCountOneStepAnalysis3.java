package day5.AnalysisData.bugForFree;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.log4j.Logger;

import jintActionFiles.JintFilesComputer2;



/***
user_id,sku_id,time,model_id,type,cate,brand
300001.0,107171,2016-04-04 12:44:16,0,6,4,891
300002.0,107172,2016-04-04 12:44:16,,1,4,891
300003.0,107173,2016-04-04 12:44:16,216,6,4,891
300004.0,107174,2016-04-04 12:44:16,217,6,4,891
300005.0,107175,2016-04-04 12:45:16,0,6,4,891
300006.0,107176,2016-04-04 12:45:16,211,6,4,891
300007.0,107177,2016-04-04 12:45:16,29,6,4,891
300008.0,164218,2016-04-09 06:44:46,0,6,8,403
* */

public class WordCountOneStepAnalysis3 {
	
	
	
	
	
	private static final Logger log=Logger.getLogger(JintFilesComputer2.class);
	private static Text Label =null;
	private static Text count =new Text("count");
	private static boolean isERROR=false;
	private static StringBuffer all = new StringBuffer();
	private static int num=0;
	private static int num_error=0;
	
	/**
	 * 
	 * input
	100001,114561,2016-02-17 10:30:02,0,6	1
	100002,114562,2016-02-17 10:30:02,,1	1
	100003,114563,2016-02-17 10:30:02,216,6	1
	100004,114564,2016-02-17 10:30:02,217,6	1
	100005,118305,2016-02-03 17:50:47,0,6	1
	100006,118306,2016-02-03 17:50:47,,1	1
	200001,107171,2016-04-04 12:44:16,0,6	1
	200002,107172,2016-04-04 12:44:16,,1	1
	200003,107173,2016-04-04 12:44:16,216,6	1
	200004,107174,2016-04-04 12:44:16,217,6	1
	200005,107175,2016-04-04 12:45:16,0,6	1
	200006,107176,2016-04-04 12:45:16,211,6	1
	200007,107177,2016-04-04 12:45:16,29,6	1
	200008,164218,2016-04-09 06:44:46,0,6	1
	300001,107171,2016-04-04 12:44:16,0,6	1
	300002,107172,2016-04-04 12:44:16,,1	1
	300003,107173,2016-04-04 12:44:16,216,6	1
	300004,107174,2016-04-04 12:44:16,217,6	1
	300005,107175,2016-04-04 12:45:16,0,6	1
	300006,107176,2016-04-04 12:45:16,211,6	1
	300007,107177,2016-04-04 12:45:16,29,6	1
	300008,164218,2016-04-09 06:44:46,0,6	1


	*/
	/**
	 * 
	I:\output\out\part-r-00000
I:\output\ser_buyitems\out313
	 
	 * */
	
	/**
	 * 
	 output:
200001	161251,4|49160,3|149338,3|146525,3|2222,3
200002	8792,4|146525,4|71207,3|2866,3|129598,3|28502,3|140921,3|81462,3|58177,3
200003	16141,3|78797,3|64467,3|53857,3|145570,3|77296,3
200004	111569,3|107760,3
200005	72967,4|151327,3
	 * */
	
	 public static class MyMapper extends Mapper<Object, Text, Text, Text>{     
	     
	        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {     

	        	
	        	try {
	        		
	        		String line=value.toString();
				

					
					 String[] e = line.split(","); 
					 String type_id=e[4].split("\t")[0];//type_id  1.浏览（指浏览商品详情页）；2.加入购物车；3.购物车删除；4.下单；5.关注；6.点击
		             
					 if(type_id.equals("6")&&Integer.parseInt(e[4].split("\t")[1])>=3){
						 //300008  <164218,2016-04-09 06:44:46,1> 
						 //log.info("Map Stage::key values:=["+e[0]+"] val values:={"+e[1]+","+e[2]+","+e[4].split("\t")[1]+"}");
						  context.write(new Text(e[0]),new Text(e[1]+","+e[2]+","+e[4].split("\t")[1])); 
						 
					 }
					 
					  
	
		                
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
	        	
	        	
	        	
	        	
	        }    
	    }    
	     
	 
	 
	 
	 
	    public static class MyReducer extends Reducer<Text,Text,Text,Text> {    
	     
	        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {    
 //300008  <164218,2016-04-09 06:44:46,1> 
	        	//声明  
	        	Map<String,Integer> hashMap = new HashMap<String,Integer>();  
	        	//向Map中添加数据  
	            for (Text val : values) {
	            	//System.out.println(""+val);
	            	//log.info("key values:=["+key+"] val values:={"+val+"}");
	            	String str[]=val.toString().split(",");
	            	if (str.length==3) {
	            		String  sku_id=str[0];
		            	Integer rate=Integer.parseInt(str[2]);
		            	hashMap.put(sku_id, rate);
		            	num++;
					}else{
						num_error=num;
						System.out.println("对于key 【"+key+"】,错误的行数：=【"+num_error+"】行, ERROR value:= 【"+val+"】");
						isERROR=true;
						
					}
	            	
	            	
	            }    
	            
	            
	          //转换  
	            ArrayList<Entry<String, Integer>> arrayList = new ArrayList<Map.Entry<String,Integer>>(hashMap.entrySet()); 
	          //排序  
	            Collections.sort(arrayList, new Comparator<Map.Entry<String, Integer>>(){  
	                public int compare(Map.Entry<String, Integer> map1,Map.Entry<String,Integer> map2) {  
	                    return ((map2.getValue() - map1.getValue() == 0) ? 0  : (map2.getValue() - map1.getValue() > 0) ? 1 : -1);  
	                }  
	            });  
	            
//	          //输出  
//	            for (Entry<String, Integer> entry : arrayList) {  
//	                System.out.println(key+"\t"+entry.getKey() + "\t" + entry.getValue());  
//	                all.append(key+","+entry.getKey() + "," + entry.getValue());
//
//					WriteText(new File("I:\\output\\user_buyitems\\www1.txt"),  all);
//					all.setLength(0);
//	                
//	            }  
	     
	            if(!isERROR){
	            	// context.write(key,new Text(arrayList.get(0).getKey()+","+arrayList.get(0).getValue())); 
	            	//context.write(key,new Text(ListToString((List<Entry<String, Integer>>) arrayList, '|'))); 
	            	//context.write(key,new Text(arrayList.toString()));
	            	//context.write(key,new Text(arrayList.toString()));
	            	context.write(key,new Text(ListToString(arrayList,'|')));
	            	 
	            	
	            }else{
	            	//System.out.println("第"+num_error+"次有问题！！！");
	            	isERROR=false;
	            	num_error++;
	            	
	            }
	              


      
	            }

			 
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
	    
	

	    
	    
	
	
	public static <T> String toString(List<T> list, char ch) {
		Iterator<T> it = list.iterator();

		if (!it.hasNext())
			return "";

		StringBuilder sb = new StringBuilder();

		for (;;) {
			T e = it.next();

			sb.append(e);
			if (!it.hasNext()) {
				return sb.toString();

			}

			sb.append(ch);
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
	


	public static void ReadText(String csv, String output) {
		// String islabel="user_id,sku_id,time,model_id,type,cate,brand";

		// key,values
	

		String string2 = null;
		StringBuffer all = new StringBuffer();

		String string = "";
		int count = 0;

		Map<String, String> map = new HashMap<>();
		List<String> list = new ArrayList<>();

		try {
			FileReader fr = new FileReader(csv);
			BufferedReader br = new BufferedReader(fr);


			String str = null;
			while ((str = br.readLine()) != null) {
				string2 = str;
				System.out.println(string2);

				String item[] = string2.split(",");

				list.add(item[1]);

				string = item[0];

				System.out.println(string + " " + list);
				//map.put(string, toString(list, '|'));
				all.append(string.split("\\.")[0] + "," +item[1]+ "," +item[2]+ "," +item[3]+ "," +item[4]);

				WriteText(new File(output),  all);
				all.setLength(0);
				list.clear();
			}

			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 1.用JAVA自带的函数
	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			//System.out.println(str.charAt(i));
			if (!Character.isDigit(str.charAt(i))) {
				return false;

			}
		}
		return true;
	}
	
	
	public static boolean TestFirstLineIsLabel(String line) throws Exception {

		String[] splits = line.split(",");

		if (isNumeric(splits[0].split("\\.")[0]) && isNumeric(splits[0].split("\\.")[1])) {
			//System.out.println("is num!!");
			//log.info("Log Info:="+line+"is num!!!");
			return true;

		} else {
			//System.out.println("not num!!!");
			//log.info("Log Info:="+line+"is NOT num!!!");
			return false;
		}

	}
 
	     
	    public static void main(String[] args) throws Exception {    
	        Configuration conf = new Configuration();    
	        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();    
	        if (otherArgs.length < 2) {    
	            System.err.println("Usage: EventCount <in> <out>");    
	            System.exit(2);    
	        }    
	        Job job = Job.getInstance(conf, "wordcount compute!");    
	        job.setJarByClass(WordCountOneStepAnalysis3.class);    
	        job.setMapperClass(MyMapper.class);    
	       // job.setCombinerClass(MyReducer.class);    
	        job.setReducerClass(MyReducer.class);    
	        job.setOutputKeyClass(Text.class);    
	        job.setOutputValueClass(Text.class);    
	        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));    
	        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));    
	        System.exit(job.waitForCompletion(true) ? 0 : 1);    
	    }    

}
