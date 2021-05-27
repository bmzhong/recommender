package back.oss.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;



public interface OssService {
    //上传头像到oss
    String uploadFileAvatar(MultipartFile file) throws IOException;
}
