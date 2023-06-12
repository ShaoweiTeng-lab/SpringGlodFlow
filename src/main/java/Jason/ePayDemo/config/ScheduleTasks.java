package Jason.ePayDemo.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduleTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Scheduled(fixedRate = 5000) // fixedDelay = 60000 表示當前方法執行完畢 60000ms(1分鐘) 後，Spring scheduling會再次呼叫該方法
    public void testFixDelay() {
        System.out.println( "五秒過去 "+dateFormat.format(new Date()));
    }
    @Scheduled(fixedRate = 60000) // fixedRate = 60000 表示當前方法開始執行 60000ms(1分鐘) 後，Spring scheduling會再次呼叫該方法
    public void testFixedRate() {
        System.out.println(dateFormat.format(new Date()));
    }
    @Scheduled(initialDelay = 180000, fixedRate = 5000) // initialDelay = 180000 表示延遲 180000 (3秒) 執行第一次任務, 然後每 5000ms(5 秒) 再次呼叫該方法
    public void testInitialDelay() {
        System.out.printf( dateFormat.format(new Date()));
    }
    @Scheduled(cron = "0 0/1 * * * ?") // cron接受cron表示式，根據cron表示式確定定時規則
    public void testCron() {
        System.out.println(dateFormat.format(new Date()));
    }
}
