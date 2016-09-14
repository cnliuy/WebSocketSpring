package hello;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import pojo.Greeting;
import pojo.HelloMessage;


@Controller
public class GreetingController {
	
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        //Thread.sleep(3000); //simulated delay
        System.out.println("in  GreetingController() --- greeting method, message:"+message.getName());
        return new Greeting("Hello, " + message.getName() + "!"+message.getAge());
    }
}