/**
 * 
 */
package day3.spider;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Administrator
 *
 */
public class moniSpider1 {

	
	public static void spider() throws Exception{
		
		//获取包含登录信息的目的cookies
				String cookies=""; 
				URL url1=new URL("http://newjw.cduestc.cn/loginAction.do"); 
				HttpURLConnection conn1=(HttpURLConnection)url1.openConnection();//打开一个连接
				conn1.setRequestMethod("POST");
				conn1.setDoOutput(true);
				conn1.setDoInput(true);
				conn1.setUseCaches(false);//不用缓存
				conn1.setInstanceFollowRedirects(false);//禁止跳转			
				conn1.setRequestProperty("Referer","http://newjw.cduestc.cn/");
				OutputStream out=conn1.getOutputStream();//与目标页面建立输出流
				//String str="zjh="+"你的学号"+"&mm="+"你的密码";
				String str="zjh="+"1341310728"+"&mm="+"TLGGKY";
				out.write(str.getBytes()); //写入用户信息
				conn1.connect();	//连接
				List<String> list=conn1.getHeaderFields().get("Set-Cookie");//得到cookie列表
				for(String cookie:list)//保存cookies
				{
					cookies=cookie.substring(0,cookie.indexOf(";")+1)+cookies;
				}
				
				//抓取目的网页成绩信息
				
				URL url2=new URL("http://newjw.cduestc.cn/bxqcjcxAction.do?pageSize=200"); 
				HttpURLConnection conn2=(HttpURLConnection)url2.openConnection();
				conn2.setRequestMethod("GET");
				conn2.setDoOutput(true);
				conn2.setDoInput(true);
				conn2.setUseCaches(false);//不用缓存
				conn2.setInstanceFollowRedirects(false);//禁止跳转
				conn2.setRequestProperty("Cookie",cookies);//设置cookie		
				conn2.connect();	
				InputStreamReader in=new InputStreamReader(conn2.getInputStream(),"gbK");//打开网络输入流
				LineNumberReader reader=new LineNumberReader(in);
			    String line;
				String rs="";
				while((line=reader.readLine())!=null)//得到网页内容rs
				{
					
				 rs+=line;					
				
				}
				
				
				System.out.println(rs);
				rs=rs.replace("&nbsp;","");//去除网页空白字符	
				Document doc=Jsoup.parse(rs);//将网页装入document对象
				Elements scoreclass = doc.getElementsByClass("odd");
				for (Element link : scoreclass) {
					
					Elements sc=link.select("td[align]");
					
					System.out.println(sc.text());
					
				}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			spider();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
