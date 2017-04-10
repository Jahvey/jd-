package compute;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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


public class Compute {
	
	
	 public static class MyMapper extends Mapper<Object, Text, Text, Text>{     
	     
	        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {     

	        	 String[] e = value.toString().split(",");    
                context.write(new Text(e[0]),new Text(e[1]+","+e[2])); 
	        	
	        	
	        }    
	    }    
	     
	    public static class MyReducer extends Reducer<Text,Text,Text,Text> {    
	     
	        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {    
	       
	            
	        	//声明  
	        	Map<String,Float> hashMap = new HashMap<String,Float>();  
	        	//向Map中添加数据  
	            for (Text val : values) { 
	            	String str[]=val.toString().split(",");
	            	String  sku_id=str[0];
	            	Float rate=Float.parseFloat(str[1]);
	            	hashMap.put(sku_id, rate);
	            	
	            }    
	            
	            
	          //转换  
	            ArrayList<Entry<String, Float>> arrayList = new ArrayList<Map.Entry<String,Float>>(hashMap.entrySet()); 
	          //排序  
	            Collections.sort(arrayList, new Comparator<Map.Entry<String, Float>>(){  
	                public int compare(Map.Entry<String, Float> map1,Map.Entry<String,Float> map2) {  
	                    return ((map2.getValue() - map1.getValue() == 0) ? 0  : (map2.getValue() - map1.getValue() > 0) ? 1 : -1);  
	                }  
	            });  
	            
	          //输出  
	            for (Entry<String, Float> entry : arrayList) {  
	                System.out.println(key+"\t"+entry.getKey() + "\t" + entry.getValue());  
	            }  
	     
	            context.write(key,new Text(arrayList.get(0).getKey()+","+arrayList.get(0).getValue()));    
      
	            }    
	    }    
	     
	    public static void main(String[] args) throws Exception {    
	        Configuration conf = new Configuration();    
	        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();    
	        if (otherArgs.length < 2) {    
	            System.err.println("Usage: EventCount <in> <out>");    
	            System.exit(2);    
	        }    
	        Job job = Job.getInstance(conf, "result compute");    
	        job.setJarByClass(Compute.class);    
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
