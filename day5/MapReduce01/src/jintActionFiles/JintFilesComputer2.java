package jintActionFiles;
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


public class JintFilesComputer2 {
	
	
	private static final Logger log=Logger.getLogger(JintFilesComputer2.class);
	private static Text Label =null;
	private static Text count =new Text("count");
	private static boolean iswriteLabel=false;
	
	
	
	
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
	
	 public static class MyMapper extends Mapper<Object, Text, Text, Text>{     
	     
	        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {     

	        	
	        	try {
	        		
	        		String line=value.toString();
					if (TestFirstLineIsLabel(line)) {

					
					 String[] e = line.split(",");    
		             
					   context.write(new Text(e[0].split("\\.")[0]+","+e[1]+","+e[2]+","+e[3]+","+e[4]),new Text("1")); 
					}else{
						
						Label=new Text(line);//save File Label
						
					}
		                
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
	        	
	        	
	        	
	        	
	        }    
	    }    
	     
	    public static class MyReducer extends Reducer<Text,Text,Text,Text> {    
	     
	        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {    

	        	int sum=0;
	        	
	        	for (Text val : values) { 
					//log.info("value:="+val);
					if(!val.toString().equals("count")){
						sum+= Integer.parseInt(val.toString());	
					}
	            	
	            }
	        	
	        	//log.info(key+"\t"+sum);
				context.write(key,new Text(sum+""));    

	                
//
//	            if (Label!=new Text("")&&!iswriteLabel) {
//					context.write(Label, count);
//					Label=new Text("");
//					iswriteLabel=true;
//				}else{
//					for (Text val : values) { 
//						log.info("value:="+val);
//						if(!val.toString().equals("count")){
//							sum+= Integer.parseInt(val.toString());	
//						}
//		            	
//		            }
//					context.write(key,new Text(sum+""));    
//				}
	            
      
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
	        job.setJarByClass(JintFilesComputer2.class);    
	        job.setMapperClass(MyMapper.class);    
	        job.setCombinerClass(MyReducer.class);    
	        job.setReducerClass(MyReducer.class);    
	        job.setOutputKeyClass(Text.class);    
	        job.setOutputValueClass(Text.class);    
	        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));    
	        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));    
	        System.exit(job.waitForCompletion(true) ? 0 : 1);    
	    }    

}
