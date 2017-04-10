import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WriteTable {
	
	public static void WriteUser() throws UnsupportedEncodingException, Exception {
		
		
		
	}
	
	

	public static void WriteUser1() throws UnsupportedEncodingException, Exception {
		// 1000个一提交
		int COMMIT_SIZE = 25000;
		// 一共多少个
		int COUNT = 100000;

		long a = System.currentTimeMillis();
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/adonai?useUnicode=true&characterEncoding=UTF-8&rewriteBatchedStatements=true";
			String user = "root";
			String password = "";

			conn = DriverManager.getConnection(url, user, password);
			long starTime = System.currentTimeMillis();

			conn.setAutoCommit(false);
			// String sql = "LOAD DATA LOCAL INFILE '" + filepath + "' INTO
			// TABLE " + tableName + " " + " FIELDS TERMINATED BY '&%$'";
			PreparedStatement pstmt = conn.prepareStatement("load data local infile '" + new File("G:\\data\\user.csv")
					+ "' into table " + new String("user") + " fields terminated by ','");
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i <= COUNT; i++) {
				sb.append(i + "," + i + "abc" + "\n");
				if (i % COMMIT_SIZE == 0) {
					InputStream is = new ByteArrayInputStream(sb.toString().getBytes());
					((com.mysql.jdbc.Statement) pstmt).setLocalInfileInputStream(is);
					pstmt.execute();
					conn.commit();
					sb.setLength(0);
				}
			}
			InputStream is = new ByteArrayInputStream(sb.toString().getBytes());
			((com.mysql.jdbc.Statement) pstmt).setLocalInfileInputStream(is);
			pstmt.execute();
			conn.commit();

			long endTime = System.currentTimeMillis();
			System.out.println("program runs " + (endTime - starTime) + "ms");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

		// 在最好的一行加上:
		System.out.println("\r插入数据条数：" + COUNT + ",提交的阀值：" + COMMIT_SIZE + ",执行耗时 : "
				+ (System.currentTimeMillis() - a) / 1000f + " 秒 ");
		// renderNull();
	}

	public static void main(String[] args) throws UnsupportedEncodingException, Exception {
		WriteUser();
	}

}
