package spider;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

public class DataManger {
	static String sql = null;  
    static DBTool db1 = null;  
//    static ResultSet ret = null;  
    static int result=0;
    static Robot rt=new Robot();
    private final  Logger logger = LoggerFactory.getLogger(this.getClass());
    static FileManger fm=new FileManger();
    
    
    //插入t_fund_flow
    public int insertData(){
    	//读取文件字符串
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String temp=df.format(new Date());
    	String fileName=temp+"-hy.txt";
		File f=new File("f:\\SF\\"+fileName);
		if (f.exists()) {
            System.out.print("文件存在");
        } else {
            System.out.print("文件不存在,hehehehhe");
            return -1;
        }
		
		
		
    	String cash="";
		try {
			cash = fm.readTxtFile(f);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(cash);
    	//处理字符串
    	String[] cashs=cash.split(",");
		List<String> cashlist=new ArrayList();
		for(int i=1;i<cashs.length;i++){
			if(cashs[i].equals("\"1")||cashs[i]=="\"1"||cashs[i].equals("data:[\"1")||cashs[i]=="data:[\"1" || cashs[i].equals("var qTYszDLA={pages:2")||cashs[i]==" var qTYszDLA={pages:2"){
				continue;
			}
			
			cashlist.add(cashs[i]);
		}	
//    	List<String> cashlist=this.rt.getData();
    	
    	logger.debug("打印字符串：",cashlist);
		ArrayList no=new ArrayList();
		ArrayList trade=new ArrayList();
		List<Double> zdf=new ArrayList();
		List<Double> zlin=new ArrayList();
		List<Double> zlinp=new ArrayList();
		List<Double> cddin=new ArrayList();
		List<Double> cddinp=new ArrayList();
		List<Double> dddin=new ArrayList();
		List<Double> ddinp=new ArrayList();
		List<Double> zddin=new ArrayList();
		List<Double> zdinp=new ArrayList();
		List<Double> xddin=new ArrayList();
		List<Double> xdinp=new ArrayList();
		ArrayList big=new ArrayList();
		ArrayList bigno=new ArrayList();

		
		for(int i=0;i<cashlist.size();i++){
			if((i+1)%15==1){
				no.add(cashlist.get(i));
			}else if((i+1)%15==2){
				trade.add(cashlist.get(i));
			}else if((i+1)%15==3){
				zdf.add(Double.parseDouble(cashlist.get(i)));
			}else if((i+1)%15==4){
				zlin.add(Double.parseDouble(cashlist.get(i)));
			}else if((i+1)%15==5){
				zlinp.add(Double.parseDouble(cashlist.get(i)));
			}else if((i+1)%15==6){
				cddin.add(Double.parseDouble(cashlist.get(i)));
			}else if((i+1)%15==7){
				cddinp.add(Double.parseDouble(cashlist.get(i)));
			}else if((i+1)%15==8){
				dddin.add(Double.parseDouble(cashlist.get(i)));
			}else if((i+1)%15==9){
				ddinp.add(Double.parseDouble(cashlist.get(i)));
			}else if((i+1)%15==10){
				zddin.add(Double.parseDouble(cashlist.get(i)));
			}else if((i+1)%15==11){
				zdinp.add(Double.parseDouble(cashlist.get(i)));
			}else if((i+1)%15==12){
				xddin.add(Double.parseDouble(cashlist.get(i)));
			}else if((i+1)%15==13){
				xdinp.add(Double.parseDouble(cashlist.get(i)));
			}else if((i+1)%15==14){
				big.add(cashlist.get(i));
			}else if((i+1)%15==0){
				bigno.add(cashlist.get(i).substring(0, 6));
			}
		}

    	//循环插入数据库
    	for(int i=0;i<trade.size();i++){
        	sql = "insert into t_fund_flow values ('"+no.get(i)+"','"+trade.get(i)+"',"+zdf.get(i)+","+zlin.get(i)+","+zlinp.get(i)+","+cddin.get(i)+","+cddinp.get(i)+","+dddin.get(i)+","+ddinp.get(i)+","+zddin.get(i)+","+zdinp.get(i)+","+xddin.get(i)+","+xdinp.get(i)+",'"+big.get(i)+"','"+bigno.get(i)+"',"+"sysdate())";//SQL语句  
        	logger.info("打印sql：",sql.toString());

        	db1 = new DBTool(sql);//创建DBTool对象  
        	System.out.println(sql);

  	      try {  
  	    	  result = db1.pst.executeUpdate(sql);//执行语句，得到结果集  
  	          
  	      } catch (SQLException e) {  
  	          e.printStackTrace(); 
  	          logger.error(e.getMessage(),e);
  	      }
    	}
    	
         db1.close();//关闭连接  	
		return result;
    	
    }
    
    
    public  int insertSigleData(){
    	
    	//读取文件字符串
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String temp=df1.format(new Date());
    	String fileName=temp+"-single.txt";
		File f=new File("f:\\SF\\"+fileName);
		if (f.exists()) {
            System.out.print("文件存在");
        } else {
            System.out.print("文件不存在,hehehehhe");
            return -1;
        }
    	String cash="";
		try {
			cash = fm.readTxtFile(f);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	String[] cashs=cash.split(",");
    	List<String> cashlist=new ArrayList();
    	for(int i=1;i<cashs.length;i++){
    		if(cashs[i].equals("var LUZddZQC={data:[\"2")||cashs[i]=="var LUZddZQC={data:[\"2" || cashs[i].equals("var LUZddZQC={data:[\"1")||cashs[i]=="var LUZddZQC={data:[\"1"|| cashs[i].equals("\"2")||cashs[i]=="\"2"|| cashs[i].equals("\"1")||cashs[i]=="\"1"){
    			continue;
    		}
    		
    		cashlist.add(cashs[i]);
    	}
//    	List<String> cashlist=this.rt.getSigleData();
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    	//每组数据包含15个字段
    	ArrayList no=new ArrayList();
    	ArrayList trade=new ArrayList();
    	ArrayList price=new ArrayList();		
    	ArrayList zdf=new ArrayList();
    	ArrayList zlin=new ArrayList();
    	ArrayList zlinp=new ArrayList();
    	ArrayList cddin=new ArrayList();
    	ArrayList cddinp=new ArrayList();
    	ArrayList dddin=new ArrayList();
    	ArrayList ddinp=new ArrayList();
    	ArrayList zddin=new ArrayList();
    	ArrayList zdinp=new ArrayList();
    	ArrayList xddin=new ArrayList();
    	ArrayList xdinp=new ArrayList();


    	
    	for(int i=0;i<cashlist.size();i++){
    		if((i+1)%15==1){
    			no.add(cashlist.get(i));
    		}else if((i+1)%15==2){
    			trade.add(cashlist.get(i));
    		}else if((i+1)%15==3){
    			price.add(cashlist.get(i));
    		}else if((i+1)%15==4){
    			zdf.add(cashlist.get(i));
    		}else if((i+1)%15==5){
    			zlin.add(cashlist.get(i));
    		}else if((i+1)%15==6){
    			zlinp.add(cashlist.get(i));
    		}else if((i+1)%15==7){
    			cddin.add(cashlist.get(i));
    		}else if((i+1)%15==8){
    			cddinp.add(cashlist.get(i));
    		}else if((i+1)%15==9){
    			dddin.add(cashlist.get(i));
    		}else if((i+1)%15==10){
    			ddinp.add(cashlist.get(i));
    		}else if((i+1)%15==11){
    			zddin.add(cashlist.get(i));
    		}else if((i+1)%15==12){
    			zdinp.add(cashlist.get(i));
    		}else if((i+1)%15==13){
    			xddin.add(cashlist.get(i));
    		}else if((i+1)%15==14){
    			xdinp.add(cashlist.get(i));
    		}
    	}

    	logger.debug("------开始插入数据：",df.format(new Date()));

    	for(int i=0;i<trade.size();i++){
        	sql = "insert into t_fund_flow_s values ('"+no.get(i)+"','"+trade.get(i)+"',"+price.get(i)+","+zdf.get(i)+","+zlin.get(i)+","+zlinp.get(i)+","+cddin.get(i)+","+cddinp.get(i)+","+dddin.get(i)+","+ddinp.get(i)+","+zddin.get(i)+","+zdinp.get(i)+","+xddin.get(i)+","+xdinp.get(i)+","+"sysdate())";//SQL语句  
        	logger.debug(sql.toString());
        	System.out.println(sql);
        	db1 = new DBTool(sql);//创建DBTool对象  

  	      try {  
  	    	  result = db1.pst.executeUpdate(sql);//执行语句，得到结果集  
  	          
  	      } catch (SQLException e) {  
  	          e.printStackTrace();  
  	          logger.error(e.getMessage(), e);
  	      }
    	}
    	logger.debug("------插入数据完成：",df.format(new Date()));

         db1.close();//关闭连接  	
		return result;
    	
    }
    
    //行业和股票对应
    public  int insertTradeCom(){
		String[] strs=new String[]{"28002420","28002421","28002422","28002424","28002425","28002427","28002428","28002429","28002433","28002436","28002437","28002438","28002440","28002447","28002448","28002450","28002451","28002454","28002456","28002457","28002458","28002459","28002464","28002465","28002470","28002471","28002473","28002474","28002475","28002476","28002477","28002478","28002479","28002480","28002481","28002482","28002484","28002485","28002486","28002537","28002538","28002539","28002545","28002546","28002725","28002726","28002727","28002728","28002729","28002730","28002731","28002732","28002733","28002734","28002735","28002736","28002737","28002738","28002739","28002740"};

    	
    	//读取文件字符串
		
		for(int i=0;i<strs.length;i++){
			String no=strs[i];
	    	String fileName=strs[i]+".txt";
	    	File f=new File("f:\\SF\\"+fileName);
			if (f.exists()) {
	            System.out.print("文件存在");
	        } else {
	            System.out.print("文件不存在,hehehehhe");
	            return -1;
	        }
	    	String cash="";
			try {
				cash = fm.readTxtFile(f);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	
	    	String[] cashs=cash.split(",");
	    	List<String> cashlist=new ArrayList();
	    	for(int j=1;j<cashs.length;j++){
//	    		if(cashs[i].equals("var LUZddZQC={data:[\"2")||cashs[i]=="var LUZddZQC={data:[\"2" || cashs[i].equals("var LUZddZQC={data:[\"1")||cashs[i]=="var LUZddZQC={data:[\"1"|| cashs[i].equals("\"2")||cashs[i]=="\"2"|| cashs[i].equals("\"1")||cashs[i]=="\"1"){
//	    			continue;
//	    		}
	    		
	    		cashlist.add(cashs[j]);
	    	}
//	    	List<String> cashlist=this.rt.getSigleData();
	    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    	//每组数据包含15个字段
	    	ArrayList gpno=new ArrayList();
	    	ArrayList com_name=new ArrayList();		
//	    	ArrayList zdf=new ArrayList();
//	    	ArrayList zlin=new ArrayList();
//	    	ArrayList zlinp=new ArrayList();
//	    	ArrayList cddin=new ArrayList();
//	    	ArrayList cddinp=new ArrayList();
//	    	ArrayList dddin=new ArrayList();
//	    	ArrayList ddinp=new ArrayList();
//	    	ArrayList zddin=new ArrayList();
//	    	ArrayList zdinp=new ArrayList();
//	    	ArrayList xddin=new ArrayList();
//	    	ArrayList xdinp=new ArrayList();


	    	
	    	for(int j=0;j<cashlist.size();j++){
	    		if((j+1)%33==1){
	    			gpno.add(cashlist.get(j));
	    		}else if((j+1)%33==2){
	    			com_name.add(cashlist.get(j));
	    		}
	    	}
	    	System.out.println(gpno.toString());
	    	System.out.println(com_name.toString());


	    	logger.debug("------开始插入数据：",df.format(new Date()));

	    	for(int j=0;j<gpno.size();j++){
	    		sql="insert into t_hy_gp values("+no+",'"+gpno.get(j)+"','"+com_name.get(j)+"')";
	    		logger.debug(sql.toString());
	        	System.out.println(sql);
	        	db1 = new DBTool(sql);//创建DBTool对象  

	  	      try {  
	  	    	  result = db1.pst.executeUpdate(sql);//执行语句，得到结果集  
	  	          
	  	      } catch (SQLException e) {  
	  	          e.printStackTrace();  
	  	          logger.error(e.getMessage(), e);
	  	      }
	    	}
	    	logger.debug("------插入数据完成：",df.format(new Date()));

		}
		

//         db1.close();//关闭连接  	
		return result;
    	
    }
    
    
    
	public static void main(String[] args) throws InterruptedException {  
		DataManger rt=new DataManger();
		rt.insertTradeCom();
  } 
  
}
