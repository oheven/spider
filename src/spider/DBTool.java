package spider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

/**
 * 连接数据库
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
            Class.forName(name);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
            pst =  (PreparedStatement) conn.prepareStatement(sql);//准备执行语句  
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
