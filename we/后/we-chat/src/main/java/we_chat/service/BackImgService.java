package we_chat.service;

import we_chat.pojo.Backimage;

import java.util.List;

public interface BackImgService {
    public List<Backimage> getById(String uid);  //根据用户名的id查询背景图
}
