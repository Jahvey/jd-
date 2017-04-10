package Sort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;


public class GetResult {
	
	
	public static List<Recommand> GetLists(File file) throws Exception {

	
		List<Recommand> lists=new ArrayList<>();
		//Recommand r=new Recommand(user_id, sku_id, rate)
		

		BufferedReader reader = new BufferedReader(new FileReader(file));

		String string;
		while ((string = reader.readLine()) != null) {
			String[] tmps = string.split(",");
			//System.out.println(tmps[0] + "|" + tmps[1] + "|" + tmps[2]);

			if (tmps.length == 3) {
				lists.add(new Recommand(tmps[0], tmps[1], Float.parseFloat(tmps[2])));
			}
						

		}

		return lists;

	}
	
	
	public static void GetResults1(File file){
		try {
			List<Recommand> recommands=GetLists(file);

			Collections.sort(recommands);
			for (int i = 0; i < recommands.size(); i++) {
				System.out.println(recommands.get(i).getUser_id()+"**"+recommands.get(i).getSku_id()+"**"+recommands.get(i).getRate());
				
			}
			
			System.out.println(recommands.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public static void GetResults(File file){
		
		try {
			List<Recommand> recommands=GetLists(file);

			Collections.sort(recommands);
			for (int i = 0; i < recommands.size(); i++) {
				System.out.println(recommands.get(i).getUser_id()+"**"+recommands.get(i).getSku_id()+"**"+recommands.get(i).getRate());
				
			}
			
			System.out.println(recommands.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		GetResults(new File("res.txt"));
		
	}
	

}
