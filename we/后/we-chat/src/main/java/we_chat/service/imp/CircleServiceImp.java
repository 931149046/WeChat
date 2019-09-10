package we_chat.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import we_chat.dao.CircleDao;
import we_chat.dao.TheDao;
import we_chat.dao.UserDao;
import we_chat.pojo.Circle;
import we_chat.pojo.CircleNew;
import we_chat.pojo.The;
import we_chat.pojo.User;
import we_chat.service.CircleService;
import we_chat.tool.Initial;
import we_chat.tool.Uuid;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class CircleServiceImp implements CircleService {
    @Autowired
    public CircleDao circleDao;

    @Autowired
    public TheDao theDao;

    @Autowired
    public UserDao userDao;

    public String Image;

    int count = 1;


    @Override
    public void inserts(String id, String content, String address) {
        List<The> the = theDao.findByUdId(id); //查对应的好友
        for (The t : the) { //循环好友,给每个好友都添加一个说说内容
            String uuid = Uuid.getUUID();
            if (t.getCiId() == null || t.getCiId().equals("")) { //判断the表中ci_id存在不
                theDao.insertCid(uuid, t.getuId(), id); //不存在就添加一个uuid根据发uid（查询哪些好友的字段）和udid(每个好友对应的用户的id)
                circleDao.inserts(Uuid.getUUID(), content, address, this.Image, Initial.date(), t.getuId(), id, uuid);
            } else {
                System.out.println(Uuid.getUUID() + content + address + this.Image + Initial.date() + "==" + t.getuId() + "==" + id + "===" + t.getCiId());
                circleDao.inserts(Uuid.getUUID(), content, address, this.Image, Initial.date(), t.getuId(), id, t.getCiId());
            }
        }

    }


    @RequestMapping("/FileImage")
    public String FileImage(@RequestParam("file") MultipartFile file) throws IOException {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        System.out.println("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("文件的后缀名为：" + suffixName);

        //注意replaceAll前面的是正则表达式,把横杠的找到替换掉
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        //新地址前部分
        String http = "http://172.16.11.230:8088/";

        //数据库最终新地址
        String newstr = http + uuid + suffixName;

        System.out.println("最终路径" + newstr);

        // 设置文件存储路径
        String filePath = "F:\\we\\we\\userImage\\";

        //拼装存在磁盘的地址
        String diskPath = filePath + uuid + suffixName;

        System.out.println("磁盘路径" + diskPath);

        this.Image = newstr; //存在上面成员变量中,用来保存头像地址的

        //磁盘路径放在file文件操作类中
        File dest = new File(diskPath);

        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();// 新建文件夹
        }
        file.transferTo(dest);// 文件写入
        return "上传成功";
    }

    @Override
    public List<CircleNew> getByUid(String uid) {

        List<CircleNew> circleNewList = new ArrayList<>();
        // List<Circle> circles = circleDao.findByUIdOrJId(uid, uid);
        List<Circle> circles = circleDao.findByUId(uid);
        for (Circle ci : circles) {
            CircleNew circleNew = new CircleNew();
            circleNew.setId(ci.gettId());
            circleNew.setContent(ci.getCiContent());
            circleNew.setAddress(ci.getCiAddress());
            circleNew.setCimage(ci.getCiConimage());
            circleNew.setTime(ci.getCiTime());
            The the = theDao.getByciId(ci.gettId());
            User user = userDao.getByUid(the.getUdId());
            circleNew.setImage(user.getuImage());
            circleNew.setName(user.getuName());
            circleNewList.add(circleNew);
        }
        return circleNewList;
    }
}
