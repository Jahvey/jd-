package Sort_Dispatched;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class CSV_Sort_Result {

	public static Map<String, Map<String, Float>> GetMaps(File file) throws Exception {

		Map<String, Map<String, Float>> map = new HashMap<>();

		BufferedReader reader = new BufferedReader(new FileReader(file));

		String string;
		while ((string = reader.readLine()) != null) {
			String[] tmps = string.split(",");
			System.out.println(tmps[0] + "|" + tmps[1] + "|" + tmps[2]);

			if (tmps.length == 3) {
				map.put(tmps[tmps.length - 3],
						(Map<String, Float>) new HashMap<>().put(tmps[tmps.length - 2], tmps[tmps.length - 1]));
			}
			System.out.println(map.keySet().size() + ":" + map.values().size());
			// System.out.println(map.values().iterator().next().get("75056"));

		}

		return map;

	}


	public static void Sort(File file) throws Exception {

		BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

		Map<String, Map<String, Float>> maps = GetMaps(file);
		Iterator it = maps.entrySet().iterator();

		Iterator<Map.Entry<String, Float>> iter;
		String tmp;
		while (it.hasNext()) {

			String user_id = (String) it.next();

			Map<String, Float> nameMap = maps.get(user_id);

			iter = nameMap.entrySet().iterator();

			tmp = user_id;
			while (iter.hasNext()) {
				Map.Entry<String, Float> me = iter.next();
				String sku_id = me.getKey();
				float rate = me.getValue();
				tmp += " (" + sku_id + "," + rate + ") ";

			}

			System.out.print(tmp);
			tmp = "";

		}
		System.out.println();

	}

	public static void main(String[] args) {
		try {

			File f = new File("res.txt");

			Sort(f);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
