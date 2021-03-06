package back.oss.service.impl;

import back.oss.service.OssService;
import back.oss.utils.ConstantPropertiesUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    //上传头像到oss
    public String uploadFileAvatar(MultipartFile file){
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName=ConstantPropertiesUtils.BUCKET_NAME;

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 获取上传文件输入流
            InputStream inputStream = file.getInputStream();

            //获取文件名称
            String fileName=file.getOriginalFilename();

            //1、在文件名称里面添加随机唯一的值
            String uuid= UUID.randomUUID().toString().replaceAll("-","");
            fileName=uuid+fileName;

            //2 把文件按照日期进行分类
            //获取当前日期

            String datepath=new DateTime().toString("yyyy/MM/dd");

            //拼接
            // yyyy/mm/dd/sdsd22323.jpg
            fileName=datepath+"/"+fileName;

            //调用oss方法上传
            //第一个参数 Bucket名称
            //第二个参数 上传oss文件路径和文件名称
            //第三个参数 上传文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            String url="https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}

