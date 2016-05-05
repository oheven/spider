package spider;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileManger extends FileOperation {
    private final  Logger logger = LoggerFactory.getLogger(this.getClass());

	public  void doFile(String filetxt,String fileName) {  
		File f=new File("f:\\SF\\"+fileName+".txt");

		try {
			super.createFile(f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		
		
		try {
			super.writeTxtFile(filetxt, f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(),e);

		}
      
  } 
}
