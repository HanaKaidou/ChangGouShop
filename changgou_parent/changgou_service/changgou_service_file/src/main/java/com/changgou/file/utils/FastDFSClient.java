package com.changgou.file.utils;

import com.changgou.file.fileBean.FastDFSFile;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 常恃豪
 * @version 1.0
 * @date 2020/5/31 21:40
 */
public class FastDFSClient {

    private static final Logger logger = LoggerFactory.getLogger(FastDFSFile.class);

    /**
     * 初始化加载FastDFS的TrackerServer配置
     */
    static {
        try {
            //查找classpath下的文件
            String fileName = new ClassPathResource("fdfs_client.conf").getFilename();
            //加载tracker连接信息
            ClientGlobal.init(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传
     *
     * @param file 上传的文件信息封装
     * @return
     */
    public static Boolean upload(FastDFSFile file) {
        NameValuePair[] meta_list = new NameValuePair[4];
        meta_list[0] = new NameValuePair("author", file.getAuthor());
        meta_list[1] = new NameValuePair("fileName", file.getFileName());
        meta_list[2] = new NameValuePair("uploader", file.getUploader());
        meta_list[3] = new NameValuePair("uploadDate", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));

        try {
            //创建Tracker的访问客户端
            TrackerClient trackerClient = new TrackerClient();

            //通过访问TrackerClient的连接 获取到TrackerServer
            TrackerServer trackerServer = trackerClient.getConnection();

            //通过TrackerSerever获取到StorageClient的连接信息
            StorageClient storageClient = new StorageClient(trackerServer, null);

            //1)文件字节数组 2)文件扩展名 3)文件作者、文件名、上传者、上传时间
            storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
        } catch (Exception e) {
            logger.error("Exception when uploading the file:" + file.getFileName(), e);
            return false;
        }
        return true;
    }

}
