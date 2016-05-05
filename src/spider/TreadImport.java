package spider;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import com.mysql.jdbc.Connection;

public class TreadImport {

    static DBTool db1 = null; 
    static String sql="";
    public void multiThreadImport(final String name, final int ThreadNum){
        final CountDownLatch cdl= new CountDownLatch(ThreadNum);
        long starttime=System.currentTimeMillis();
        for(int k=1;k<=ThreadNum;k++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                        for(int i=1;i<=5;i++){
                        	
                        	System.out.println(name+i);
                        }
                        cdl.countDown();
                }
            }).start();
        }
        

        try {
            cdl.await();
            long spendtime=System.currentTimeMillis()-starttime;
            System.out.println( ThreadNum+"个线程花费时间:"+spendtime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        


    }

    public static void main(String[] args) throws Exception {
        TreadImport ti=new TreadImport();
        ti.multiThreadImport("A",1);
        ti.multiThreadImport("B",5);
        ti.multiThreadImport("C",8);
        ti.multiThreadImport("D",10);
        ti.multiThreadImport("F",20);
        System.out.println("笔记本CPU数:"+Runtime.getRuntime().availableProcessors());
    }

}