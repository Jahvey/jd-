package org.wh.mahout;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;


public class UserCF {
	
	 final static int NEIGHBORHOOD_NUM = 2;
	    final static int RECOMMENDER_NUM = 3;
	    
	    
		
		public static void  WriteCSV(File csv,StringBuffer sBuffer){
			// File csv = new File("F:/writers.csv"); // CSV数据文件 
			 
		      BufferedWriter bw;
			try {
				bw = new BufferedWriter(new FileWriter(csv, true));
				// 添加新的数据行 
				
				String[] tmps=sBuffer.toString().split(",");
				
				bw.write(new String(tmps[tmps.length-3]+","+tmps[tmps.length-2]+","+tmps[tmps.length-1]));  
				//bw.write(new String(sBuffer));  
			    	bw.newLine();  
				   bw.close();  
			      
			      
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 附加 
		      
			
			
		}
		public  static void UserCFAlg(String file)throws IOException, TasteException{
			
			// String file = "data/writers2.csv";
		        DataModel model = new FileDataModel(new File(file));
		        UserSimilarity user = new EuclideanDistanceSimilarity(model);
		        NearestNUserNeighborhood neighbor = new NearestNUserNeighborhood(NEIGHBORHOOD_NUM, user, model);
		        Recommender r = new GenericUserBasedRecommender(model, neighbor, user);
		        LongPrimitiveIterator iter = model.getUserIDs();

		        
		        StringBuffer last =new StringBuffer() ;//这就是你要的数据了 
		        String tmp;
		        while (iter.hasNext()) {
		            long uid = iter.nextLong();
		            List<RecommendedItem> list = r.recommend(uid, RECOMMENDER_NUM);
		            
		          
		            
		        	
		            
		            //tmp=uid+",";
		            last.append(uid+",");
		            if(!list.isEmpty()){
		            	
		            	//tmp+=list.get(0).getItemID()+","+list.get(0).getValue();
		            	last.append(list.get(0).getItemID()+","+list.get(0).getValue());
		            	
		            	WriteCSV(new File("result4.csv"),last);
		            	last.setLength(0);
		            }
		            
		            System.out.printf("uid:%s", uid);
		            
		            //tmp=uid;
		            for (RecommendedItem ritem : list) {
		                System.out.printf("(%s,%f)", ritem.getItemID(), ritem.getValue());
		                
		            }
		            System.out.println();
		            
		          
		            
		        }
			
		}
		
		

	    public static void main(String[] args) throws IOException, TasteException {
	    	UserCFAlg("data/writers4.csv");
	    }

}
