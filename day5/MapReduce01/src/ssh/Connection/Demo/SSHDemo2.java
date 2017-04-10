/**
 * 
 */
package ssh.Connection.Demo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * @author Administrator
 *
 */
public class SSHDemo2 {



	private static final Logger logger = Logger.getLogger(SSHDemo2.class); 
	
	public static void TestWriteFile(String ip,int port,String remoteUser,String remotePass)throws Exception{
		Connection con = new Connection(ip, port);
		//连接
		con.connect();
		//远程服务器的用户名密码
		boolean isAuthed = con.authenticateWithPassword(remoteUser,remotePass);
		//建立SCP客户端
		SCPClient scpClient = con.createSCPClient();
		//服务器端的文件下载到本地的目录下

		scpClient.get("/root/data/shell.sh", "C:/");
		//将本地文件上传到服务器端的目录下
		scpClient.put("C:/shell.sh", "/root/tools");
		
		/** 
		 
		//建立一个SFTP客户端        
		SFTPv3Client sftpClient = new SFTPv3Client(con);
		//远程新建目录
		sftpClient.mkdir("newRemoteDir", 6);
		//远程删除目录
		sftpClient.rmdir("RemoteDir");
		//远程新建文件          
		sftpClient.createFile("newRemoteFile");
		//远程打开文件，可进行读写
		sftpClient.openFileRW("remoteFile");
		
		*/
		//建立会话
		Session session = null;
		session = con.openSession();
		//利用会话可以操作远程服务器
		//例如：删除远程目录下的文件
		//session.execCommand("rm -f".concat("remotePath").concat("qrCodeFileMode"));
		session.execCommand("cd /root/data");
		session.execCommand("ll");
		
		//显示执行命令后的信息
		InputStream stdout = new StreamGobbler(session.getStdout());
		BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
		 
		while (true) {
		String line = br.readLine();
		    if (line == null) {
		       logger.info("远程服务器返回信息:空");
		           break;
		    }
		    logger.info("远程服务器返回信息:" + line);
		}
		//获得推出状态
		System.out.println("ExitCode: " + session.getExitStatus());
		session.close();
		con.close();
		
		
	}
	
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		TestWriteFile("120.25.237.116", 22,"root", "Adonai8888");
		
	}

}
