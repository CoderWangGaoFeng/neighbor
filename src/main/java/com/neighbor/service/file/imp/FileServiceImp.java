package com.neighbor.service.file.imp;

import com.neighbor.exception.NeiException;
import com.neighbor.model.file.FileModel;
import com.neighbor.repository.file.FileRepository;
import com.neighbor.service.file.FileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 * 文件处理逻辑层实现类
 * @author wgf
 */
@Service
@AllArgsConstructor
public class FileServiceImp implements FileService {

    private final FileRepository fileRepository;

    /**
     * 文件/图片保存
     * @param file
     * @return
     * @throws Exception
     */
    @Override
    public FileModel save(MultipartFile file) throws Exception {
        FileModel returnFile = null;
        try {
            FileModel f = new FileModel();
            f.setContentType(file.getContentType())
                    .setContent(file.getBytes())
                    .setName(file.getOriginalFilename())
                    .setSize(file.getSize());
            returnFile = this.fileRepository.save(f);
        } catch (Exception e){
            throw new NeiException();
        }
        return returnFile;
    }

    /**
     * 删除图片信息
     * @param id
     */
    @Override
    public void delFile(String id) throws Exception{
        this.fileRepository.deleteById(id);
    }
}

