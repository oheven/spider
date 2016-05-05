package spider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

/**
 * �������ݿ�
 * @param chang
 * @return
 */
public class DBTool {
	
	public static final String url = "jdbc:mysql://127.0.0.1/data_dig?useUnicode=true&characterEncoding=utf8";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "dingmei";  
    public static final String password = "dingmei";  
  
    public Connection conn = null;  
    public PreparedStatement pst = null;  
  
    public DBTool(String sql) {  
        try {  
            Class.forName(name);//ָ����������  
            conn = DriverManager.getConnection(url, user, password);//��ȡ����  
            pst =  (PreparedStatement) conn.prepareStatement(sql);//׼��ִ�����  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void close() {  
        try {  
            this.conn.close();  
            this.pst.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
}
