package spider;

import java.io.IOException;
import java.net.MalformedURLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

public class ThreadTool implements Runnable {

	private String name;  
    private DataManger dm=new DataManger();
    private final  Logger logger = LoggerFactory.getLogger(this.getClass());


    public ThreadTool(String name) {  
       this.name=name;  
    }  
    @Override  
    public void run() {  
            
//		Robot rt=new Robot();
//		rt.getData();
//		rt.getSigleData();
//		
//		
//		
//		DataManger dm=new DataManger();
//		dm.insertData();
//		dm.insertSigleData();
    	
    	
    	System.out.println("A"+"123");

    	
    	
        try {  
            Thread.sleep((int) Math.random() * 10);  
        } catch (InterruptedException e) {  
            e.printStackTrace(); 
			logger.error(e.getMessage(), e);

        }  
     }  
          
    
}  

  