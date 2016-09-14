package chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import pojo.HelloMessage;


/**
 * 暂时没有用到
 * 
 * @deprecated
 * */
@Controller
public class ChatController {
	
    //用于转发数据(sendTo)  
    private SimpMessagingTemplate template; 
    
    @Autowired  
    public ChatController(SimpMessagingTemplate t) {  
        template = t;  
    } 
    
    
	/** 
	 * WebSocket聊天的相应接收方法和转发方法 
	 * 
	 * @param userChat 关于用户聊天的各个信息 
	 */  
	@MessageMapping("/userChat")  
	public void userChat(UserChatCommand userChat) {  	
		System.out.println("in  userChat -------------");
	    //找到需要发送的地址  
	    String dest = "/userChat/chat" + userChat.getCoordinationId();  
	    //发送用户的聊天记录  
	    this.template.convertAndSend(dest, userChat);  
	}  
	
	
	/** 
	 * WebSocket聊天的相应接收方法和转发方法 
	 * 
	 * @param String 关于用户name信息 
	 */  
	@MessageMapping("/userName")  
	public void userName( ) {  	
		//js  stompClient.send("/app/hello", {}, JSON.stringify({'name': name,'age': '123'}));
		System.out.println("in  userName -------------");
		HelloMessage   hm = new HelloMessage();
		hm.setAge("123");
		hm.setName("namw");
	    //找到需要发送的地址  
	    String dest = "/app/hello";  
	    //发送用户的聊天记录  
	    this.template.convertAndSend(dest, hm);  
	} 
	

}
