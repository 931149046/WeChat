package we_chat.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import we_chat.dao.*;
import we_chat.pojo.Detail;
import we_chat.pojo.User;
import we_chat.service.UserService;
import we_chat.tool.Initial;
import we_chat.tool.Uuid;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Transactional
@Service
public class UserServiceImp implements UserService {
    @Autowired
    public UserDao userDao;

    @Autowired
    public DetailDao detailDao;

    @Autowired
    public TheDao theDao;

    @Autowired
    public ChattingDao chattingDao;

    @Autowired
    public ChatDao chatDao;


    @Override
    public User getNumber(String number) { //根据微信号查询
        return userDao.getNumber(number);
    }

    @Override
    public List<User> getById(String id) { //根据id进行查询
        return userDao.getById(id);
    }

    @Override
    public Map<Object, Object> getNumAndPassword(String number, String password, HttpServletRequest request) {
        Map<Object, Object> map = new HashMap<>();

        User user = userDao.getNumAndPassword(number, password);
        User user1 = userDao.getNumber(number);
        if (user != null && !(user.equals(""))) {
            HttpSession sessoin = request.getSession();//这就是session的创建
            sessoin.setMaxInactiveInterval(30 * 60);//以秒为单位，即在没有活动30分钟后，session将失效
            sessoin.setAttribute("username", user.getuName());//给session添加属性属性name： username,属性 value：查询到的用户名
            sessoin.setAttribute("password", user.getuPassword());//添加属性 name: password; value: 查询到的密码
            System.out.println(sessoin.getId());  //打印sesisonid

            map.put("msg", "登录成功");
            map.put("sessionId", sessoin.getId());
            map.put("uid", user1.getuId());
            map.put("uname", user1.getuName());
            return map;
        } else {
            map.put("msg", "微信号或密码错误");
            map.put("sessionId", 0);
            return map;
        }
    }

    @Override
    public void insert(String name, String image, String number, String password) {

        String uuid = Uuid.getUUID();
        userDao.insert(uuid, name, image, number, password);

        //保存详细信息在用户详细表中
        detailDao.insertDeat(Uuid.getUUID(), name, number, null, image, uuid);

        //创建用户的时候添加自己为好友
        String letter = name.substring(0, 1); //截取第一个字符
        String newLetter = Initial.getPinYinHeadChar(letter); //把截取到的第一个字符进行转换
        theDao.insert(Uuid.getUUID(), name, image, newLetter, uuid, uuid);
    }

    @Override
    public void updateImage(MultipartFile file, String id) throws IOException {

        User user = userDao.getByUid(id); //查询头像地址
        // 获取数据库中保存的地址
        String fileName = user.getuImage();
        String str = fileName.substring(26); //截取图片名字
        // 设置文件存储路径
        String filePath = "F:\\we\\we\\userImage\\";
        //拼装存在磁盘的地址
        String diskPath = filePath + str;
        //磁盘路径放在file文件操作类中
        File dest = new File(diskPath);
        dest.delete();
        // 获取文件名
        String filename = file.getOriginalFilename();
        System.out.println("上传的文件名为：" + filename);
        // 获取文件的后缀名
        String suffixName = filename.substring(filename.lastIndexOf("."));
        System.out.println("文件的后缀名为：" + suffixName);
        //注意replaceAll前面的是正则表达式,把横杠的找到替换掉
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //新地址前部分
        String http = "http://172.16.11.230:8088/";
        //数据库最终新地址
        String newstr = http + uuid + suffixName;

        //根据用户id更新图片地址
        userDao.updateImage(newstr, id);

        //更新the表图片
        theDao.updateThe(newstr, id);

        //更新detail表图片
        detailDao.updateDetail(newstr, id);

        //更新chattting表
        chattingDao.updateChatting(newstr, id);

        //更新chat表
        Detail detail = detailDao.findByTId(id);
        chatDao.updateChat(newstr, detail.getdId());

        // 设置文件存储路径
        String dPath = "F:\\we\\we\\userImage\\";
        //拼装存在磁盘的地址
        String dskPath = dPath + uuid + suffixName;
        //磁盘路径放在file文件操作类中
        File dsk = new File(dskPath);
        // 检测是否存在目录
        if (!dsk.getParentFile().exists()) {
            dsk.getParentFile().mkdirs();// 新建文件夹
        }
        file.transferTo(dsk);// 文件写入
    }
}
