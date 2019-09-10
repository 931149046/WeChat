package we_chat.service;

import org.springframework.web.multipart.MultipartFile;
import we_chat.pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserService {
    public User getNumber(String number); //根据微信号查询

    public List<User> getById(String id);  //根据id进行查询用户

    public Map<Object, Object> getNumAndPassword(String number, String password, HttpServletRequest request);  //根据number和password查询

    public void insert(String name, String image, String number, String password);

    void updateImage(MultipartFile file, String id) throws IOException;
}
