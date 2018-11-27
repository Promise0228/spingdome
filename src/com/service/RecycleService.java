package com.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.FileType;
import com.entity.Recycle;
import com.mapper.RecycleMapper;
import com.service.impl.IRecycleService;
import com.util.PageBean;

import javassist.compiler.ast.NewExpr;

@Service
public class RecycleService implements IRecycleService {
	@Autowired
	RecycleMapper recycleMapper;

	/**
	 * 分页查询方法
	 * 
	 * @param request
	 * @param userInfo
	 * @return
	 * @version 1.0
	 */
	@Override
	public PageBean getPageBean(HttpServletRequest request, Recycle recycle) {
		// 前台传入的当前页码
		String currpage = request.getParameter("currpage");
		// 非空.默认值,设置,以及获取前台当前页码并转码int类型
		int currpages = (currpage != null) ? Integer.parseInt(currpage) : 1;
		// 前台获取每页条数
		String pagelimit = request.getParameter("pagelimit");
		// 非空.默认值,设置,以及获取前台获取每页条数并转码int类型
		int page = (pagelimit != null) ? Integer.parseInt(pagelimit) : 5;
		// 通过构造方法传入当前页码,每页显示条数,用于计算下标
		PageBean pages = new PageBean(page,currpages);
		// 获取前台查询开始时间
		String beginTime = request.getParameter("beginTime");
		// 获取前台查询结束时间
		String endTime = request.getParameter("endTime");
		// 创建HashMap对象,封装userInfo,page,用于储存分页查询的条件的属性.
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("recycle", recycle);
		hashMap.put("page", pages);
		hashMap.put("beginTime", beginTime);
		hashMap.put("endTime", endTime);
		// 获取总条数
		int totalcount = getCount(hashMap);
		// 分页查询的方法,返回List
		List<Recycle> recycleList = recycleMapper.getRecycles(hashMap);
		// 创建请求地址url,并赋值
		String url = "recycle/recyclelist.action";
		// 创建请求参数params,通过StringBuffer对象进行拼接,用append方法拼接,
		StringBuffer params = new StringBuffer();
			String param=params.toString();
		// 通过构造方法给pageBean对象赋值(每页显示条数,总条数,当前页码,分页查询list结果,请求地址,请求参数).
		PageBean pageBean = new PageBean(page,totalcount, currpages,  recycleList, url, param);

		// 返回pageBean对象;
		return pageBean;
	}

	private int getCount(HashMap<String, Object> hashMap) {
		return recycleMapper.getCount(hashMap);
	}

	/**
	 * 公用删除接口（描述、模块id、表名称、主键key、主键value）
	 * 
	 * @return back=1,回收站添加成功,back=2,删除状态成功,back=-1;执行错误;
	 * @version 1.0
	 */
	@Override
	public int delToRecycle(Recycle recycle) {
		int back = 0;
		int addRecycle = recycleMapper.addRecycle(recycle);
		int delToRecycle = recycleMapper.delToRecycle(recycle);
		if (addRecycle > 0) {
			back = 1;
		} else if (delToRecycle > 0) {
			back = 2;
		} else {
			back = -1;
		}
		return back;
	}

	/**
	 * 回收站还原,同时删除回收站中数据
	 * 
	 * @param recycle
	 * @return
	 * @version 1.0
	 */
	@Override
	public int rollbackRecycle(Recycle recycle) {
		System.out.println(
				recycle.getKeyName() + recycle.getKeyValue() + recycle.getTableName() + "---------------------");
		int rollbackRecycle = 0;
		int rollbackDelete = recycleMapper.rollbackDelete(recycle);
		if (rollbackDelete > 0) {
			rollbackRecycle = recycleMapper.rollbackRecycle(recycle);
		}

		return rollbackRecycle;
	}

	/**
	 * 数据的删除(物理删除)（表名称、主键key、主键value），同时删除回收站中数据
	 * 
	 * @param recycle
	 * @return
	 * @version 1.0
	 */
	public int delRecycleById(HttpServletRequest request,Recycle recycle) {
		int res=0;
		
		String tableName=recycle.getTableName();
		//删除其它表数据
		if("files".equals(tableName)){
			//判断为资源文件表
			//先删除磁盘文件、再删除表数据
			//获取磁盘文件地址
			String url=recycleMapper.getFileAdress(recycle);
			System.out.println("url="+url);
			File file=new File(url);
			//查看文件是否存在
			if(file.exists()){
				//存在、删除
				file.delete();
				//判断是否已删除
				if(!file.exists()){
					//已删除、删除表数据
					int delOhterTable = recycleMapper.delOhterTable(recycle);
					//删除回收站表数据
					int delRecycleById = recycleMapper.delRecycleById(recycle);
					if(delOhterTable>0){
						//数据表删除成功
						res=1;//文件删除成功
						return res;
					}else{
						//数据表删除失败
						res=2;
						return res;
					}
				}else{
					//文件删除失败
					res=3;
					return res;
				}
			}else{
				//文件不存在
				res=4;
				return res;
			}
		}else if("file_type".equals(tableName)){
			//资源文件类型表
			//获取文件夹地址
			String url=request.getSession().getServletContext().getRealPath("/")+"Folder_management/";
			int fileTypeId= recycle.getKeyValue();
			url+=getFileTypeUrl(fileTypeId);
			System.out.println("url="+url);
			System.out.println(url);
			File file =new File(url);
			if(file.exists()){
				//文件夹存在、删除
				file.delete();
				//判断是否已删除
				if(!file.exists()){
					//已删除、删除表数据
					int delOhterTable = recycleMapper.delOhterTable(recycle);
					//删除回收站表数据
					int delRecycleById = recycleMapper.delRecycleById(recycle);
					if(delOhterTable>0){
						//数据表删除成功
						res=1;
						return res;
					}else{
						//数据表删除失败
						res=2;
						return res;
					}
				}else{
					//文件删除失败
					res=3;
					return res;
				}
			}else{
				//文件不存在
				res=4;
				return res;
			}
		}else{
			int delOhterTable = recycleMapper.delOhterTable(recycle);
			if(delOhterTable>0){
				//数据表删除成功
				res=1;
				return res;
			}else{
				//数据表删除失败
				res=2;
				return res;
			}
		}		
		
	}
	
	
	public String getFileTypeUrl(int fileTypeId){
		String url="";
		//查询文件夹名称
		List<FileType> types=recycleMapper.getFileType(fileTypeId);
		for (FileType fileType : types) {
			url=fileType.getTypeName()+"/"+url;
			//判断父id为不为0
			if(fileType.getParentId()>0){
				//将父id赋值给id
				fileType.setFileTypeId(fileType.getParentId());
				url=getFileTypeUrl(fileType.getFileTypeId());
			}
		}
		return url;
	}

	
}
