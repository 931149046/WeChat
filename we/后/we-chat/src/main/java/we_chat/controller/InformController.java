package we_chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import we_chat.pojo.Inform;
import we_chat.pojo.NewInform;
import we_chat.service.InformService;

import java.util.List;

@RestController
@RequestMapping("/inform")
public class InformController {
    @Autowired
    public InformService informService;

    @RequestMapping("/findByFIdAndFindstate/{fid}")
    public List<Inform> findByFIdAndFindstate(@PathVariable("fid") String fid) {  //根据fid和findstate查询
        return informService.findByFIdAndFindstate(fid);
    }

    @RequestMapping("/updateJidState/{fid}")
    public void updateJidState(@PathVariable("fid") String fid) {  //根据fid进行更新查看状态
        informService.updateJidState(fid);
    }

    @RequestMapping("/findByJId/{jid}")
    public List<NewInform> findByJId(@PathVariable("jid") String jid) {
        return informService.findByJId(jid);
    }

    @RequestMapping("/deleteRelationTable/{preid}/{byid}")
    void deleteRelationTable(@PathVariable("preid") String pid,@PathVariable("byid") String bid){  //删除根据这两个id有关系的表数据整个数据库,pid是当前用户id。bid是被删除用户id
        informService.deleteRelationTable(pid,bid);
    }
}
