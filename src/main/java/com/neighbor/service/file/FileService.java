package com.neighbor.service.file;

import com.neighbor.module.file.FileModel;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传展示逻辑层
 */
public interface FileService {

    public FileModel save( MultipartFile file) throws Exception ;

    public void delFile(String id) throws Exception;
}
