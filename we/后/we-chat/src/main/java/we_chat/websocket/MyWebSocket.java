package we_chat.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import we_chat.dao.ChattingDao;
import we_chat.pojo.ChatContentObj;
import we_chat.pojo.Chatting;
import we_chat.pojo.SocketHint;
import we_chat.tool.Uuid;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint(value = "/websocket/{name}/{id}") //{name}这个是创建连接的时候就把名字传过来了
public class MyWebSocket {
    //用来记录webscoketsessionId(通过页面传的)和该websocketsession进行绑定
    private static Map<String, Session> map = new HashMap<>();

    private String name;  //存放昵称的变量

    //用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private String id;

    /**********************解决非控制器中注入dao层开始(原来是没法注入的,还需要在springboot入口类中改一下)**************************************/
    private static ApplicationContext applicationContext;

    //需要注入的接口,注意还需要在使用的构造方法中进行获取动态代理后的实现类注入在这里
    private ChattingDao chattingDao;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        MyWebSocket.applicationContext = applicationContext;
    }
    /**********************解决非控制器中注入dao层结束*********************************************************************************/

    /**
     *      * 连接建立成功调用的方法
     *     
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("name") String name, @PathParam("id") String id) throws JsonProcessingException { //@PathParam("name") String name 参数绑定页面发送过来的昵称变量名
        this.session = session; //赋值给上面的session,这个好像没有用
        this.name = name; //赋值给上面的name
        this.id = id;
        map.put(id, session); //这里使用的是session.getId()作为键和session对象(value)之间的对应关系
        webSocketSet.add(this); //当前建立连接的对象加入上面的CopyOnWriteArraySet(线程安全的无序的集合)集合中

        System.out.println("有新连接加入:" + name + ",当前在线人数为" + webSocketSet.size());
        SocketHint socketHint = new SocketHint();
        socketHint.setMessage("恭喜" + name + ",成功连接上WebSocket(当前用户的id：" + id + ")-->当前在线人数为：" + webSocketSet.size());
        ObjectMapper objectMapper = new ObjectMapper();
        String string = objectMapper.writeValueAsString(socketHint);
        //向浏览器发送信息
        //this.session.getAsyncRemote().sendText(string);
    }

    /**
     *      * 连接关闭调用的方法
     *     
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this); //从set中删除
        System.out.println("有一连接关闭！当前在线人数为" + webSocketSet.size());
    }

    /**
     *      * 收到客户端消息后回调的方法(也就是客户端发送消息方法)
     * 这个注解可以接受客户端直接发送过来的内容消息
     *      *
     *      * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("昵称:" + name + "============" + "来自客户端的消息:" + message);
        //从客户端传过来的数据是json数据，所以这里使用jackson进行转换为SocketMsg对象
        ObjectMapper objectMapper = new ObjectMapper(); //获取jackson的ObjectMapper对象进行转换
        Map<Object, Object> objmap; //用map集合进行接收json转换后的对象
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String strdate = simpleDateFormat.format(date);
        try {
            chattingDao = applicationContext.getBean(ChattingDao.class); //动态代理的实现类赋给需要用到的变量上

            objmap = objectMapper.readValue(message, Map.class); //把页面传过来的json字符串转换成后面这个map的集合中
            Chatting chatting = new Chatting();
            chatting.setChUid((String) objmap.get("FId"));
            chatting.setChFid((String) objmap.get("Id"));
            chatting.setChContent((String) objmap.get("Content"));
            chatting.setChTime(strdate);
            chatting.setChImage((String) objmap.get("Uimage"));
            chatting.setChName((String) objmap.get("Uname"));

            ChatContentObj obj = new ChatContentObj();  //返回的对象;返回发送者的对象
            obj.setName(name); //获取用户名返回
            obj.setContent((String) objmap.get("Content")); //获取消息内容
            obj.setImage((String) objmap.get("Uimage")); //获取头像地址
            obj.setDate(strdate);
            obj.setCategory("发送");
            String string = objectMapper.writeValueAsString(obj);

            ChatContentObj obj2 = new ChatContentObj();  //返回的对象;返回接收者的对象
            obj2.setName(name); //获取用户名返回
            obj2.setContent((String) objmap.get("Content")); //获取消息内容
            obj2.setImage((String) objmap.get("Uimage")); //获取头像地址
            obj2.setDate(strdate);
            obj2.setCategory("接收");
            String string2 = objectMapper.writeValueAsString(obj2); //使用jackson的writeValueAsString序列化成json字符串


            if (objmap.get("Type").equals(1)) { //取出map集合中的type判断是否为群聊和单聊,1为单聊
                //单聊.需要找到发送者和接受者
                String jid = (String) objmap.get("Id"); //接收者id
                if (jid != null) { //如果type为1进来了的话接收者的id又不存在就发送给发送者提示
                    for (String key : map.keySet()) {
                        if (key.equals(objmap.get("Id"))) {  //判断当前传进来的接收方id是否和连接的时候id一样，一样就进来,不一样的话就提示发送方
                            if (key.equals(objmap.get("Id"))) { //进来后判断连接时候的id是否和传进来的接收者id一样,一样就进来
                                //map.get(key).getAsyncRemote().sendText(string); //发送消息内容和昵称给接收者;
                                if (map.get(key).isOpen()) { //判断当前用户连接是否还在,为true代表还在;
                                    chattingDao.insterAll(Uuid.getUUID(), chatting.getChUid(), chatting.getChFid(), chatting.getChContent(), chatting.getChTime(), chatting.getChImage(), "接收", chatting.getChName());
                                    map.get(key).getBasicRemote().sendText(string2); //发送消息内容和昵称给接收者
                                } else { //连接对象不存在的话给发送方发送提示
                                    for (String key1 : map.keySet()) { //循环找出发送方
                                        if (key1.equals(objmap.get("FId"))) { //一样的话就进来发送给发送方
                                            map.get(key1).getBasicRemote().sendText("系统消息：对方不在线;发了消息对方收不到"); //发送消息内容和昵称给发送者
                                        }
                                    }
                                }
                            }
                            for (String key1 : map.keySet()) {  //给接收者发送了消息后,再循环找到连接时候的发送方id和当前传进来的id一样
                                if (key1.equals(objmap.get("FId"))) { //一样的话就进来发送给发送方
                                    if (map.get(key1).isOpen()) { //判断当前用户连接是否还在,为true代表还在
                                        for (String key3 : map.keySet()) {
                                            if (key3.equals(objmap.get("Id"))) {
                                                if (map.get(key3).isOpen()) {
                                                    chattingDao.insterAll(Uuid.getUUID(), chatting.getChUid(), chatting.getChFid(), chatting.getChContent(), chatting.getChTime(), chatting.getChImage(), "发送", chatting.getChName());
                                                }
                                            }
                                        }
                                        map.get(key1).getBasicRemote().sendText(string); //发送消息内容和昵称给发送者
                                    } else { //连接对象不存在的话给发送方发送提示
                                        for (String key2 : map.keySet()) {  //循环找出发送方
                                            if (key2.equals(objmap.get("FId"))) { //一样的话就进来发送给发送方
                                                map.get(key2).getBasicRemote().sendText("系统消息：对方不在线;发了消息对方收不到"); //发送消息内容和昵称给发送者
                                            }
                                        }
                                    }
                                }
                            }
                        } else {  //由于当前的map中没有和传过来的接收方id一样就提示发送方
                            List<Object> list = new ArrayList<>();
                            for (String key2 : map.keySet()) { //循环找map中有没有和传过来的接收方id,有就添加到上面集合中
                                if (key2.equals(objmap.get("Id"))) {
                                    list.add(key2);
                                }
                            }

                            if (list.size() == 0) { //如果list集合的长度不等于0的话就代表有,就不发送提示信息给发送者;等于0就发送提示信息
                                for (String key3 : map.keySet()) {
                                    if (key3.equals(objmap.get("FId"))) {
                                        //发送给发送者.
                                        map.get(key3).getBasicRemote().sendText("系统消息：对方不在线");
                                    }
                                }
                            }
                        }
                    }
                } else {  //发送给发送者提示;如果type为1进来了的话接收者的id又不存在就发送给发送者提示
                    for (String key : map.keySet()) {
                        if (key.equals(objmap.get("FId"))) {
                            //发送给发送者.
                            map.get(key).getAsyncRemote().sendText("系统消息：没有传接收方ID过来");
                        }
                    }
                }
            } else { //type为0的话就代表群发消息
                //群发消息
                broadcast(string);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

    //发生错误时调用
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    //群发自定义消息
    public void broadcast(String message) {
        for (MyWebSocket item : webSocketSet) { //循环这个集合中的连接webscoket后保存的对象
            //同步异步说明参考：http://blog.csdn.net/who_is_xiaoming/article/details/53287691
            //this.session.getBasicRemote().sendText(message); 同步发送
            item.session.getAsyncRemote().sendText(message);//异步发送消息.
        }
    }
}