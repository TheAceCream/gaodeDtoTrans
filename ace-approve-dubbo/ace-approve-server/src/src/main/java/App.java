import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by Administrator on 2017/6/12.
 */
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:context/applicationContext.xml");
        context.start();

        System.out.println("Dubbo provider start...");

        try {
            System.in.read();	// 按任意键退出
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }
}
