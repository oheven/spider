package spider;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jetty.util.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	
	
	
	public static void main(String[] args) {  
//        new Thread(new ThreadTool("A")).start();  
//        new Thread(new ThreadTool("B")).start();  
//		Thread t1 = new Thread(new ThreadTool("A"));    
//		Thread t2 = new Thread(new ThreadTool("B"));    
//		t1.start();    
//		t1.join(); // ��t1ִ����������ִ��  
//		t2.start();    
//		t2.join(); // �������ִ���У������ܱ�����  
		
		Robot rt=new Robot();
		rt.getSigleData();
		rt.getData();
		DataManger dm=new DataManger();
		dm.insertData();
		dm.insertSigleData();

		
		System.out.println("1212121212");
        
    } 
}
