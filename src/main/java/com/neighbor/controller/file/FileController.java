package com.neighbor.controller.file;

import com.neighbor.model.file.FileModel;
import com.neighbor.service.file.FileService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片控制器
 * @author wgf
 */
@RestController
@RequestMapping("/upload")
@AllArgsConstructor
@Api(value = "图片服务")
public class FileController {

    private final FileService fileService;

    /**
     * 上传图片/文件
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping
    public FileModel upload(@RequestParam("file")MultipartFile file) throws Exception{
        return this.fileService.save(file);
    }
}
