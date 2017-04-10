package day4.JointFiles.bugForFree;

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

public class MergeWriteFiles2 {

	public static void WriteText(File csv, String FirstLabel, StringBuffer sBuffer) {

		BufferedWriter bw;
		try {

			boolean isWriteLable = false;
			bw = new BufferedWriter(new FileWriter(csv, true));
			if (isWriteLable == false && csv.length() == 0) {
				bw.write(FirstLabel.split(",")[0]+","+FirstLabel.split(",")[1]+","+FirstLabel.split(",")[2]+","+FirstLabel.split(",")[3]+","+FirstLabel.split(",")[4]);
				bw.newLine();
				System.out.println("Label [" + FirstLabel + "] Write Complete!!");
				isWriteLable = true;
			}

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
			String islabel = br.readLine();
			if (!TestFirstLineIsLabel(islabel)) {
			} // Test Label Info or not

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

				WriteText(new File(output), islabel, all);
				all.setLength(0);
				list.clear();
			}

			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
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

	// 1.用JAVA自带的函数
	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			System.out.println(str.charAt(i));
			if (!Character.isDigit(str.charAt(i))) {
				return false;

			}
		}
		return true;
	}

	public static void TestReadText() throws Exception {// bug
		MergeFiles("data", new String[] { "1.txt" }, "out.csv");
		System.out.println("TestReadText Complete!!");

	}

	public static void TestDemo1() throws Exception {
		String tmp = "user_id,sku_id,time,model_id,type,cate,brand";
		String tmp1 = "100001.0,114568,2016-02-17 10:30:02,0,6,5,479";
		String[] splits = tmp1.split(",");
		// String []splits=tmp.split(",");
		if (isNumeric(splits[0].split("\\.")[0]) && isNumeric(splits[0].split("\\.")[1])) {
			System.out.println("is num!!");

		} else {
			System.out.println("not num!!!");
		}

	}

	public static boolean TestFirstLineIsLabel(String line) throws Exception {

		String[] splits = line.split(",");

		if (isNumeric(splits[0].split("\\.")[0]) && isNumeric(splits[0].split("\\.")[1])) {
			System.out.println("is num!!");
			return true;

		} else {
			System.out.println("not num!!!");
			return false;
		}

	}

	public static void MergeFiles(String path, String[] files, String outfile) throws Exception {
		String tmp = "";
		if (new File(path + File.separator + outfile).exists()) {
			new File(path + File.separator + outfile).delete();
			System.out.println("Delete output files [" + path + File.separator + outfile + "] success!!");
		}
		for (int i = 0; i < files.length; i++) {
			tmp = path + File.separator + files[i];
			System.out.println(tmp);

			ReadText(tmp, path + File.separator + outfile);

			tmp = "";

		}

		System.out.println("MergeFiles Complete!!");

	}

	public static void main(String[] args) throws Exception {
		//MergeFiles("data", new String[] { "1.txt", "2.txt", "3.txt" }, "ww2.csv");
		MergeFiles("G:"+File.separator+"大数据竞赛"+File.separator+"JData-master"+File.separator+"data_ori",//
				new String[] { "JData_Action_201602.csv", "JData_Action_201603.csv", "JData_Action_201604.csv" }, "MergeActionFiles.csv");
		// MergeFiles("data",new String[]{"1.txt"},"ww2.csv");
		// TestReadText();
		// ReadText("data/1.txt","data/ww1.txt");

		// TestDemo1();

		// ReadCSV2(new FileReader("res21.csv"));

	}

}
