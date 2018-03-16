package com.neighbor.repository.file;

import com.neighbor.model.file.FileModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.transaction.Transactional;

/**
 * 文件服务
 */
@Transactional
public interface FileRepository extends MongoRepository<FileModel ,String > {
}
