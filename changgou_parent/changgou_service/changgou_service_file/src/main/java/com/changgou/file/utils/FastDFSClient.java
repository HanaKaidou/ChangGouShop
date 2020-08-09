package com.changgou.file.utils;

import com.changgou.file.fileBean.FastDFSFile;
import org.apache.commons.lang.StringUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 常恃豪
 * @version 1.0
 * @date 2020/5/31 21:40
 */
public class FastDFSClient {

    private static final String DEFAULT_DOWN_PATH = "C:/ChangGouDownFile/" + new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss").format(new Date()) + "/";

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
     * 获取到TrackerServer
     *
     * @return
     * @throws IOException
     */
    public static TrackerServer getTrackerServer() throws Exception {

        TrackerClient trackerClient = new TrackerClient(); //创建Tracker的访问客户端
        TrackerServer trackerServer = trackerClient.getConnection(); //通过访问TrackerClient的连接 获取到TrackerServer
        return trackerServer;
    }

    /**
     * 文件上传
     *
     * @param file 上传的文件信息封装
     * @return
     */
    public static String[] uploadFile(FastDFSFile file) throws Exception {
        NameValuePair[] meta_list = new NameValuePair[4];
        meta_list[0] = new NameValuePair("author", file.getAuthor());
        meta_list[1] = new NameValuePair("fileName", file.getFileName());
        meta_list[2] = new NameValuePair("uploader", file.getUploader());
        meta_list[3] = new NameValuePair("uploadDate", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));

        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);//通过TrackerSerever获取到StorageClient的连接信息
        return storageClient.upload_file(file.getContent(), file.getExt(), meta_list);//1)文件字节数组 2)文件扩展名 3)文件作者、文件名、上传者、上传时间
    }

    /**
     * 获取文件信息
     *
     * @param groupName      组名
     * @param remoteFileName 文件储存名
     * @return
     */
    public static FileInfo getFileInfo(String groupName, String remoteFileName) throws Exception {
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient.get_file_info(groupName, remoteFileName);
    }

    /**
     * 下载文件
     *
     * @param groupName      组名
     * @param remoteFileName 文件储存名
     * @param downPath       自定义下载路径，如果不写默认地址为 DEFAULT_DOWN_PATH
     * @return
     */
    public static InputStream deownFile(String groupName, String remoteFileName, String downPath) throws Exception {
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            TrackerServer trackerServer = getTrackerServer();
            StorageClient storageClient = new StorageClient(trackerServer, null);
            byte[] bytes = storageClient.download_file(groupName, remoteFileName);
            is = new ByteArrayInputStream(bytes);


            if (StringUtils.isBlank(downPath)) {//当没有指定下载路径，则默认存放在c盘
                fos = new FileOutputStream(DEFAULT_DOWN_PATH);
            } else {
                fos = new FileOutputStream(downPath);
            }

            byte[] buffer = new byte[1024];
            while (is.read(buffer) != -1) {
                fos.write(buffer);
            }
            return is;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null && is != null) {
                try {
                    fos.flush();
                    is.close();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * @param groupName      组名
     * @param remoteFileName 文件储存名
     * @return true 删除成功  false 删除失败
     */
    public static Boolean deleteFile(String groupName, String remoteFileName) throws Exception {
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        int i = storageClient.delete_file(groupName, remoteFileName);
        return i >= 0 ? true : false;
    }

    /**
     *获取Storage
     */
    public static void getStoreStorages() throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = getTrackerServer();
        StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer);

    }

}
