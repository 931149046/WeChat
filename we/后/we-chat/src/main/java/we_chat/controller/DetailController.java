package we_chat.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import we_chat.pojo.Detail;
import we_chat.service.DetailService;

import java.util.List;

//通讯录人详细信息(detail)
@CrossOrigin //解决跨域
@RestController
@RequestMapping("/deta")
public class DetailController {
    @Autowired
    public DetailService detailService;

    @RequestMapping("/getById/{id}")
    public List<Detail> getById(@PathVariable("id") String id) {  //根据t_id查询通讯录详细表对应的数据
        return detailService.getById(id);
    }

    @RequestMapping("/getByDid1/{id}")
    public List<Detail> getByDid1(@PathVariable("id") String id) {  //根据t_id查询通讯录详细表对应的数据
        return detailService.getByDid1(id);
    }

    @RequestMapping("/getByDid/{id}")
    public List<Detail> getByDid(@PathVariable("id") String id) {  //根据t_id查询通讯录详细表对应的数据
        return detailService.getByDid(id);
    }

    @RequestMapping("/findByTId/{tid}")
    public Detail findByTId(@PathVariable("tid") String tid) {  //根据t_id查询通讯录详细表对应的数据
        return detailService.findByTId(tid);
    }

    @RequestMapping("/updateName/{name}/{tid}")
    public void updateName(@PathVariable("name") String name,@PathVariable("tid") String tid) {  //根据t_id查询通讯录详细表对应的数据
       detailService.updateName(name,tid);
    }

    @RequestMapping("/updateSex/{sex}/{tid}")
    public void updateSex(@PathVariable("sex") String sex,@PathVariable("tid") String tid) {  //根据t_id查询通讯录详细表对应的数据
       detailService.updateSex(sex,tid);
    }

    @RequestMapping("/updateAddress/{address}/{tid}")
    public void updateAddress(@PathVariable("address") String address,@PathVariable("tid") String tid) {  //根据t_id查询通讯录详细表对应的数据
       detailService.updateAderess(address,tid);
    }
}
