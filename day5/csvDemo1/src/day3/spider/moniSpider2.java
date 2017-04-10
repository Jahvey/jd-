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

public class moniSpider2 {
	

	public static void spider2() throws Exception{
		
		/**
		 * 
		 wlanuserip=0bc386d9e643d188b011a0d00c9b5c40&wlanacname=5fcbc245a7ffdfa4&ssid=&nasip=2c0716b583c8ac3cbd7567a84cfde5a8&mac=53ba540bde596b811a6d5617a86fa028&t=wireless-v2&url=2c0328164651e2b4f13b933ddf36628bea622dedcc302b30
		 * */
		
		//获取包含登录信息的目的cookies
				String cookies=""; 
				String url="http://210.77.16.21/eportal/index.jsp?wlanuserip=4a9f35519b4b7d08718143c453d73074&wlanacname=5fcbc245a7ffdfa4&ssid=&nasip=2c0716b583c8ac3cbd7567a84cfde5a8&mac=908f22a9769994760e32c47fc30aab32&t=wireless-v2&url=ee3511094c924eb9bc48b089c9e5fb23";
				URL url1=new URL(url); 
				HttpURLConnection conn1=(HttpURLConnection)url1.openConnection();//打开一个连接
				conn1.setRequestMethod("POST");
				conn1.setDoOutput(true);
				conn1.setDoInput(true);
				conn1.setUseCaches(false);//不用缓存
				conn1.setInstanceFollowRedirects(false);//禁止跳转
		//String requst="http://210.77.16.21/eportal/index.jsp?wlanuserip=0bc386d9e643d188b011a0d00c9b5c40&wlanacname=5fcbc245a7ffdfa4&ssid=&nasip=2c0716b583c8ac3cbd7567a84cfde5a8&mac=53ba540bde596b811a6d5617a86fa028&t=wireless-v2&url=2c0328164651e2b4f13b933ddf36628bea622dedcc302b30";
				conn1.setRequestProperty("Referer",url);
				OutputStream out=conn1.getOutputStream();//与目标页面建立输出流
				//String str="zjh="+"你的学号"+"&mm="+"你的密码";
				String str="username="+"hyzx2509"+"&pwd="+"ucas";
				out.write(str.getBytes()); //写入用户信息
				conn1.connect();	//连接
				List<String> list=conn1.getHeaderFields().get("Set-Cookie");//得到cookie列表
				for(String cookie:list)//保存cookies
				{
					cookies=cookie.substring(0,cookie.indexOf(";")+1)+cookies;
				}
				
				//抓取目的网页成绩信息
				
				URL url2=new URL(url); 
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
				
				/**
				rs=rs.replace("&nbsp;","");//去除网页空白字符	
				Document doc=Jsoup.parse(rs);//将网页装入document对象
				Elements scoreclass = doc.getElementsByClass("odd");
				for (Element link : scoreclass) {
					
					Elements sc=link.select("td[align]");
					
					System.out.println(sc.text());
					
				}
				
				*/
		
	}
	
	public static void main(String[] args) {
		try {
			spider2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
