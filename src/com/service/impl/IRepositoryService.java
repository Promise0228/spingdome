package com.service.impl;

import java.util.List;
import java.util.Map;

import com.entity.FileType;
import com.entity.Files;

public interface IRepositoryService {

	/**
	 * 查询系统中所有文件
	 * @return
	 */
	List<FileType> findFileType();

	/**
	 * 查询资源文件表信息
	 * @return
	 */
	List<Files> findFiles();

	/**
	 * 向filetype中插入信息
	 * @param fileType
	 * @return
	 */
	int addFolder(FileType fileType);

	/**
	 * 将文件信息插入数据库
	 * @param map
	 * @return
	 */
	int addFiles(Map map);

	/**
	 * 查询文件是否拥有密码
	 * @param files
	 * @return
	 */
	List<Files> getMD5(Files files);

	/**
	 * 查找文件夹下面的文件夹
	 * @param id
	 * @return
	 */
	List<FileType> findFileTypeById(int id);

	/**
	 * 查找文件夹下面的文件
	 * @param id
	 * @return
	 */
	List<Files> getFilesById(int id);

	/**
	 * 下载文件密码匹配
	 * @param filePswd
	 * @return
	 */
	int getPswd(String filePswd);

}



