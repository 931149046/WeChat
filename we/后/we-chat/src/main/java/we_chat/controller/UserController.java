package we_chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import we_chat.pojo.User;
import we_chat.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

//用户信息控制器
@CrossOrigin//解决跨域
@RestController
@RequestMapping("/user")
public class UserController {

    public String userImage; //保存用户头像的地址,下一次注册用户的信息进来后添加在数据库中

    @Autowired
    public UserService userService;

    @RequestMapping("/getNumber/{number}")
    public User getName(@PathVariable("number") String number) {  //根据微信号查询
        return userService.getNumber(number);
    }

    @RequestMapping("/getById/{id}")
    public List<User> getById(@PathVariable("id") String id) {  //根据id查询
        return userService.getById(id);
    }

    @RequestMapping("/getNumAndPassword/{number}/{password}")
    public Map<Object, Object> getNumAndPassword(@PathVariable("number") String number, @PathVariable("password") String password, HttpServletRequest request) {  //根据微信号和密码进行查询

        return userService.getNumAndPassword(number, password, request);
    }

    @RequestMapping("/FileImage")
    public String FileImage(@RequestParam("file") MultipartFile file) throws Exception{
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
        String newstr = http+uuid+suffixName;

        System.out.println("最终路径"+newstr);

        // 设置文件存储路径
        String filePath = "F:\\we\\we\\userImage\\";


        //拼装存在磁盘的地址
        String diskPath = filePath+uuid+suffixName;

        System.out.println("磁盘路径"+diskPath);

        this.userImage = newstr; //存在上面成员变量中,用来保存头像地址的

        //磁盘路径放在file文件操作类中
        File dest = new File(diskPath);

        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();// 新建文件夹
        }
        file.transferTo(dest);// 文件写入
        return "上传成功";
    }

    @RequestMapping("/insert/{name}/{number}/{password}")
    public void insert(@PathVariable("name") String name,@PathVariable("number") String number,@PathVariable("password") String password){  //添加用户信息
        System.out.println("头像地址"+this.userImage);
        userService.insert(name,this.userImage,number,password);
    }


    @RequestMapping("/updateImage") //更新url地址
    public void updateImage(@RequestParam("file") MultipartFile file, @PathParam("id") String id) throws IOException {
        userService.updateImage(file,id);
    }

}
