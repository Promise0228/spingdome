package com.mapper;

import java.util.HashMap;
import java.util.List;

import com.entity.FileType;
import com.entity.ProjectInfo;
import com.entity.Recycle;
import com.entity.SysClass;

/**
 * 回收站dao层
 * 
 * @comment
 * @author GaoErJi
 * @date 2018年7月17日 上午9:22:15
 * @modifyUser GaoErJi
 * @modifyDate 2018年7月17日 上午9:22:15
 * @modifyDesc TODO
 * @version TODO
 */
public interface RecycleMapper {

	/**
	 * 查询回收站列表
	 * 
	 * @return
	 * @version 1.0
	 */
	public List<Recycle> getRecycles(HashMap<String, Object> hashMap);

	/**
	 * 查询回收站总条数
	 * 
	 * @return
	 * @version 1.0
	 */
	public int getCount(HashMap<String, Object> hashMap);

	/**
	 * 公用删除接口（描述、模块id、表名称、主键key、主键value）
	 * 
	 * @return
	 * @version 1.0
	 */
	public int delToRecycle(Recycle recycle);

	/**
	 * 添加至回收站
	 * 
	 * @param hashMap
	 * @return
	 * @version 1.0
	 */
	public int addRecycle(Recycle recycle);

	/**
	 * 回收站还原,同时删除回收站中数据
	 * 
	 * @param recycle
	 * @return
	 * @version 1.0
	 */
	public int rollbackRecycle(Recycle recycle);

	/**
	 * 回收站还原,改变删除状态
	 * 
	 * @param recycle
	 * @return
	 * @version 1.0
	 */
	public int rollbackDelete(Recycle recycle);
	/**
	 * 数据的删除(物理删除)删除回收站中数据
	 * 
	 * @param recycle
	 * @return
	 * @version 1.0
	 */
	public int delRecycleById(Recycle recycle);
	/**
	 * 数据的删除(物理删除)（表名称、主键key、主键value），删除其它表数据
	 * @param recycle
	 * @return
	 * @version 1.0
	 */
	public int delOhterTable(Recycle recycle);

	/**
	 * 获取文件磁盘地址
	 * @param recycle
	 * @return
	 */
	public String getFileAdress(Recycle recycle);

	/**
	 * 查询文件夹信息
	 * @param recycle
	 * @return
	 */
	public List<FileType> getFileType(int fileTypeId);
}
