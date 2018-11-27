package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.FileType;
import com.entity.Files;
import com.mapper.RepositoryMapper;
import com.service.impl.IRepositoryService;
@Service
public class RepositoryService implements IRepositoryService {

	@Autowired
	private RepositoryMapper repositoryMapper;
	/**
	 * 查询当前系统中所有文件
	 */
	@Override
	public List<FileType> findFileType() {
		// TODO Auto-generated method stub
		return repositoryMapper.findFileType();
	}
	/**
	 * 向filetype中插入信息
	 * @return
	 */
	public int addFolder(FileType fileType) {
		// TODO Auto-generated method stub
		return repositoryMapper.addFolder(fileType);
	}
	/**
	 * 查询资源文件表信息
	 * @return
	 */
	public List<Files> findFiles() {
		// TODO Auto-generated method stub
		return repositoryMapper.findFiles();
	}
	
	/**
	 * 将文件信息插入数据库
	 */
	public int addFiles(Map map) {
		// TODO Auto-generated method stub
		return repositoryMapper.addFiles(map);
	}
	
	/**
	 * 查询文件是否拥有密码
	 */
	@Override
	public List<Files> getMD5(Files files) {
		// TODO Auto-generated method stub
		return repositoryMapper.getMD5(files);
	}
	
	/**
	 * 查找文件夹下面的文件夹
	 */
	@Override
	public List<FileType> findFileTypeById(int id) {
		// TODO Auto-generated method stub
		return repositoryMapper.findFileTypeById(id);
	}
	
	/**
	 * 查找文件夹下面的文件
	 */
	@Override
	public List<Files> getFilesById(int id) {
		// TODO Auto-generated method stub
		return repositoryMapper.getFilesById(id);
	}
	
	/**
	 * 下载文件密码匹配
	 */
	@Override
	public int getPswd(String filePswd) {
		// TODO Auto-generated method stub
		return repositoryMapper.getPswd(filePswd);
	}
	

}
