package spider;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
/**
 * 爬取页面 生成txt文件
 * @param fileName
 * @return
 */
public class Robot2 {
    private final  Logger logger = LoggerFactory.getLogger(this.getClass());
	private FileManger fm=new FileManger();

	
	public String getData() {
		// TODO Auto-generated method stub
		final WebClient webClient=new WebClient(BrowserVersion.FIREFOX_45);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());  
		webClient.waitForBackgroundJavaScriptStartingBefore(1000);
		HtmlPage page = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String str="";
		String[] strs=new String[]{"28002420","28002421","28002422","28002424","28002425","28002427","28002428","28002429","28002433","28002436","28002437","28002438","28002440","28002447","28002448","28002450","28002451","28002454","28002456","28002457","28002458","28002459","28002464","28002465","28002470","28002471","28002473","28002474","28002475","28002476","28002477","28002478","28002479","28002480","28002481","28002482","28002484","28002485","28002486","28002537","28002538","28002539","28002545","28002546","28002725","28002726","28002727","28002728","28002729","28002730","28002731","28002732","28002733","28002734","28002735","28002736","28002737","28002738","28002739","28002740"};

		String no="";
       List<String> nolists=new ArrayList();
       

		logger.info("开始抓取数据：",df.format(new Date()));
		try {
			
			for(int i=0;i<strs.length;i++){
				System.out.println(strs[i]);
				no=strs[i];
				String url="http://hqdigi2.eastmoney.com/EM_Quote2010NumericApplication/index.aspx?type=s&sortType=C&sortRule=-1&pageSize=150&page=1&jsName=quote_123&style="+strs[i]+"&token=44c9d251add88e27b65ed86506f6e5da&_g=0.3105378430336714";
//				System.out.println(url);
				page = webClient.getPage(url);
				String cash=page.asText().toString();

				//生成txt
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
				String fn=strs[i];
				fm.doFile(cash, fn);
				
				
//				str=cash+str;
			}
			
	        
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(), e);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(), e);

		}
		logger.info("抓取数据完毕：",df.format(new Date()));


		webClient.close();
		
		
		
		return str;
		
		
	}
	
	public static void main(String[] args) throws InterruptedException {  
		Robot2 rt=new Robot2();
		rt.getData();
  } 
	


}
