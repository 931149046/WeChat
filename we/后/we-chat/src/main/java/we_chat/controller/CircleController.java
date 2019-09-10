package we_chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import we_chat.pojo.CircleNew;
import we_chat.service.CircleService;

import javax.websocket.server.PathParam;
import java.io.File;
import java.lang.reflect.Array;
import java.util.List;
import java.util.UUID;

//朋友圈的控制器
@CrossOrigin //解决跨域
@RestController
@RequestMapping("/circle")
public class CircleController {
    @Autowired
    public CircleService  circleService;

    @RequestMapping("/insert/{id}/{content}/{address}")
    public void inserts(@PathVariable("id") String id,@PathVariable("content") String content,@PathVariable("address") String address){
        circleService.inserts(id,content,address);
    }

    @RequestMapping("/FileImage")
    public String FileImage(@RequestParam("file") MultipartFile file) throws Exception{
        return circleService.FileImage(file);
    }

    @RequestMapping("/getByUid/{uid}")
    List<CircleNew> getByUid(@PathVariable("uid") String uid){
       return circleService.getByUid(uid);
    }
}
