package we_chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import we_chat.websocket.MyWebSocket;

@SpringBootApplication
public class WeChatApplication {

    public static void main(String[] args) {
        //SpringApplication.run(WeChatApplication.class, args); //这是原来的入口类的启动方法

       /* //这是改造后的主要是为了让非控制器层进行注入
        SpringApplication springApplication = new SpringApplication(WeChatApplication.class);
        ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
        //需要注入到MyWebSocket类上
        MyWebSocket.setApplicationContext(configurableApplicationContext);*/

       //让非控制器层进行注入;这是链式编程的写法,简化了上面的多余代码
        MyWebSocket.setApplicationContext(SpringApplication.run(WeChatApplication.class, args));
    }

}
