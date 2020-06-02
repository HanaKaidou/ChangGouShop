package com.changgou.file.controller;

import com.changgou.file.fileBean.FastDFSFile;
import com.changgou.file.utils.FastDFSClient;
import com.changgou.framework.entity.Result;
import com.changgou.framework.entity.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author 常恃豪
 * @version 1.0
 * @date 2020/6/2 21:53
 */
@RestController
@RequestMapping("/upload")
@CrossOrigin
public class FileUploadController {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping()
    public Result upload(@RequestParam(value = "file") MultipartFile file) {
        FastDFSFile fastDFSFile = null;
        try {
            fastDFSFile = new FastDFSFile(
                    file.getOriginalFilename(),
                    file.getBytes(),
                    StringUtils.getFilenameExtension(file.getOriginalFilename())
            );
            Boolean flag = FastDFSClient.upload(fastDFSFile);
            if (!flag) {
                return new Result(false, StatusCode.ERROR, "文件上传失败");
            }
        } catch (Exception e) {
            logger.error(e.getCause().getMessage());
            return new Result(false, StatusCode.ERROR, "文件上传出现异常");
        }

        return new Result(true, StatusCode.OK, "文件上传成功");
    }
}
