package we_chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import we_chat.pojo.Backimage;
import we_chat.service.BackImgService;

import java.util.List;

//当前用户的背景图控制器
@CrossOrigin //解决跨域
@RestController
@RequestMapping("/back")
public class BackImgController {
    @Autowired
    public BackImgService backImgService;

    @RequestMapping("/getById/{uid}")
    public List<Backimage> getById(@PathVariable("uid") String uid){ //根据用户名的id查询背景图
       return backImgService.getById(uid);
    }
}
