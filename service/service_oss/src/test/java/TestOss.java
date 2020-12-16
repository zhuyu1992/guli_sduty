import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;

import java.io.*;

/**
 * @Author:zzzgyu
 */
//https://allweing-guli.oss-cn-hangzhou.aliyuncs.com/avatar/f1.jpg
public class TestOss {
    public static void main(String[] args) throws IOException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4FcF3uc7AUadbPuPCf6z";
        String accessKeySecret = "7gmU5BMHIAqXjrLedS16c2fPWICkF7";
        String bucketName = "allweing-guli";
// <yourObjectName>从OSS下载文件时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String objectName = "avatar/f1.jpg";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
        OSSObject ossObject = ossClient.getObject(bucketName, objectName);
// 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
        InputStream content = ossObject.getObjectContent();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(content);
        File file = new File("F:\\4.jpg");
        OutputStream outputStream = new FileOutputStream(file);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = bufferedInputStream.read(buffer))!=-1) {
            bufferedOutputStream.write(buffer,0,len);
            // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
        }
        bufferedInputStream.close();
        bufferedOutputStream.close();

// 关闭OSSClient。
        ossClient.shutdown();
    }
}
