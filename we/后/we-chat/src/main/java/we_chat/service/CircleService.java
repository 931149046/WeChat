package we_chat.service;

import org.springframework.web.multipart.MultipartFile;
import we_chat.pojo.CircleNew;

import java.io.IOException;
import java.util.List;

public interface CircleService {
    void inserts(String id, String content, String address);

    String FileImage(MultipartFile file) throws IOException;

    List<CircleNew> getByUid(String uid);
}
