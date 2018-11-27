package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.entity.FileType;
import com.entity.Files;
import com.entity.Recycle;
import com.entity.UserInfo;
import com.service.impl.IRecycleService;
import com.service.impl.IRepositoryService;
import com.util.CompressUtil;
/**
 * 资源库管理系统
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/repository")
public class RepositoryController {

	@Autowired
	private IRepositoryService repositoryService;
	@Autowired
	IRecycleService iRecycleService;
	
	private int a=0;
	private int b=0;
	/**
	 * 进入文件夹管理页面
	 * @return
	 */
	@RequestMapping("/list")
	public String jumpRepository(HttpServletRequest request){
		String s="s";
		//1.定义JSON数组对象
		JSONArray jsonArr=new JSONArray();
		//2.查询当前系统中所有文件夹
		List<FileType> list=repositoryService.findFileType();
		//2.1查询当前系统中所有文件夹
		List<Files> list1=repositoryService.findFiles();
		//3.循环遍历数组(文件夹)
		for (FileType fileType : list) {
					JSONObject json=new JSONObject();
					  //id 标识 ，pId 父id，name 名称，open 是否展开节点， checked  是否选中      
					json.put("id", fileType.getFileTypeId());
					json.put("pId", fileType.getParentId());
					json.put("name", fileType.getTypeName());
					json.put("open", false);
					json.put("typeCode", fileType.getTypeCode());
					json.put("typeGrade", fileType.getGrade());
					json.put("isParent", true);
					//int a=repositoryService.getFileTypeById(fileType.getFileTypeId());
					/*if(listtype.size()>0){
						json.put("font", "{'color':'red'}");
					}*/
					jsonArr.add(json);		
				}
		//3.1循环遍历数组(文件)
				for (Files files : list1) {
					JSONObject json=new JSONObject();
					//id 标识 ，pId 父id，name 名称，open 是否展开节点， checked  是否选中      
					json.put("id", files.getFileId()+s);
					json.put("pId", files.getFileTypeId());
					json.put("name", files.getFileName());
					json.put("open", false);
					//json1.put("isParent", false);
					json.put("fileId", files.getFileId());
					//json.put("typeCode", fileType.getTypeCode());
					//json.put("typeGrade", fileType.getGrade());
							//int a=repositoryService.getFileTypeById(fileType.getFileTypeId());
							/*if(listtype.size()>0){
								json.put("font", "{'color':'red'}");
							}*/
					jsonArr.add(json);		
					}
				request.setAttribute("fileTypeArrays", jsonArr);
				return "repository";
	}
	
	/**
	 * 新建文件夹
	 * @param request
	 * @param fileType
	 * @return
	 */
	@RequestMapping("/addFolder")
	@ResponseBody
	public JSONObject addFolder(HttpServletRequest request,FileType fileType){
		JSONObject json = new JSONObject();
		String directory=request.getSession().getServletContext().getRealPath("/")+"Folder_management";
		//判断父id是否为0
		if (fileType.getParentId()==0){
			//如果为0，则表示在根目录下创建文件夹C:\Program Files\Apache Software Foundation\Tomcat 7.0\Folder_management
			File file = new File(directory+"/"+fileType.getTypeCode());
			//查看文件夹是否存在
			if(file.exists()){
				//文件夹已存在
				json.put("res", 3);
			}else{
				//创建文件夹
				file.mkdirs();
				//查看文件夹是否已经生成
				if(file.exists()){
					//文件夹已生成，将文件夹数据写入数据库
					int a=repositoryService.addFolder(fileType);
					if(a>0){
						//插入数据库成功
						json.put("res", 1);
					}else{
						//插入数据失败
						json.put("res", 0);
					}
				}else{
					//文件夹生成失败
					json.put("res", 2);
				}
			}
		}else{
			String url=request.getParameter("url");
			//在其他文件夹下创建文件夹
			File file = new File(directory+url+"/"+fileType.getTypeCode());
			//查看文件夹是否存在
			if(file.exists()){
				//文件夹已存在
				json.put("res", 3);
			}else{
				//创建文件夹
				file.mkdirs();
				//查看文件夹是否已经生成
				if(file.exists()){
					//文件夹已生成，将文件夹数据写入数据库
					int a=repositoryService.addFolder(fileType);
					if(a>0){
						//插入数据库成功
						json.put("res", 1);
					}else{
						//插入数据失败
						json.put("res", 0);
					}
				}else{
					//文件夹生成失败
					json.put("res", 2);
				}
			}
		}
		return json;
	}

	/**
	 * 上传文件
	 * @throws IOException 
	 * @throws IllegalStateException 
	 * @throws FileUploadException 
	 */
	@RequestMapping("/addFiles")
	public String addFiles(MultipartFile file,HttpServletRequest request,Files files) throws IllegalStateException, IOException {
		//判断文件是否为空
		if(!file.isEmpty()){
			UserInfo userInfo=(UserInfo) request.getSession().getAttribute("UserInfo");
			int fileTypeId=Integer.parseInt(request.getParameter("file_type_id"));
			//文件保存路径
			String directory=request.getSession().getServletContext().getRealPath("/")+"Folder_management"+files.getFileUrl()+"/"+file.getOriginalFilename();
			//转存文件
			file.transferTo(new File(directory));
			//将url保存
			files.setFileUrl(directory);
			files.setFileName(file.getOriginalFilename());
			Map map = new HashMap();
			//将文件信息插入数据库
			map.put("userInfo", userInfo);
			map.put("files", files);
			map.put("fileTypeId", fileTypeId);
			int a= repositoryService.addFiles(map);
			if(a>0){
				System.out.println("文件上传成功");
			}else{
				System.out.println("文件上传失败");
			}
		}
		return "redirect:list.action";
	}
	/**
	 * 判断文件是否有密码
	 * @param request
	 * @param files
	 * @return
	 */
	@RequestMapping("getMD5")
	@ResponseBody
	public JSONObject getMD5(HttpServletRequest request,Files files){
		JSONObject json =new JSONObject();
		//查询是否拥有密码
		List<Files> list = repositoryService.getMD5(files);
		for (Files files2 : list) {
			if(files2.getFilePswd()!=null){
				//拥有密码
				json.put("res", "1");
			}else{
				//没有密码
				json.put("res", "0");
			}
		}
		return json;
		
	}
	/**
	 * 判断文件夹下面存在密码的文件节点数量
	 * @param request
	 * @param files
	 * @return
	 */
	@RequestMapping("getMD5s")
	@ResponseBody
	public JSONObject getMD5s(HttpServletRequest request,Files files){
		a=0;
		Map map =new HashMap();
		JSONObject json =new JSONObject();
		int id=Integer.parseInt(request.getParameter("id"));
		int l=findFile(map,id);
		if(l>1){
			//拥有多个密码文件
			json.put("res", "2");
		}else if(l==1){
			//拥有1个密码文件,得到该文件id
			json.put("res", "1");
			json.put("fileId", map.get("fileId"));
		}else{
			//没有密码或没有文件
			//判断有没有文件
			if(map.get("b")!=null){
				int s=(Integer) map.get("b");
				if(s>0){
					//有文件，没密码
					json.put("res", "3");
				}else{
					//没文件
					json.put("res", "0");
					}
			}else{
				//没文件
			json.put("res", "0");
			}
		}
		return json;
	}
	/**
	 * 查找含有密码的文件
	 * @param map 
	 * @return
	 */
	public int findFile(Map map, int id){
		//查找文件夹下的文件夹及文件
		//文件
		//select file_id from files where file_type_id=#{fileTypeId}
		List<Files> list1 = repositoryService.getFilesById(id);
		//遍历文件
		if(list1.size()>0){
			for (Files files : list1) {
				if(files.getFilePswd()!=null&&files.getFilePswd()!=""){
					a++;
				map.put("fileId", files.getFileId());
				}
				b++;
				map.put("b", b);
			}
			
		}
		//文件夹
		//select file_type_id from file_type where parent_id=#{fileTypeId}
		List<FileType> list = repositoryService.findFileTypeById(id);
		if(list.size()>0){
			//存在文件夹，递归
			for (FileType fileType : list) {
				int s=fileType.getFileTypeId();
				findFile(map,s);
			}
		}
		return a;
	}
	
	/**
	 * 下载文件密码匹配
	 * @param request
	 * @param files
	 * @return
	 */
	@RequestMapping("getPswd")
	@ResponseBody
	public JSONObject getPswd(HttpServletRequest request,Files files){
		JSONObject json =new JSONObject();
		//接收查询返回值
		int l=repositoryService.getPswd(files.getFilePswd());
		if(l>0){
			//密码正确
			json.put("res", "1");
		}else{
			//密码错误
			json.put("res", "0");
		}
		return json;
	}
	
	/**
	 * 压缩文件、并返回压缩包路径
	 * @param request
	 * @param fileType
	 * @return
	 */
	@RequestMapping("downloadAll")
	@ResponseBody
	public JSONObject downloadAll(HttpServletRequest request,FileType fileType){
		JSONObject json =new JSONObject();
		String url=request.getSession().getServletContext().getRealPath("/")+"Folder_management"+fileType.getTypeName();
		String toUrl=fileType.getTypeName()+".zip";
		File file = new File(url+".zip");
		if(file.exists()){
			//压缩包已存在,删除
			file.delete();
		}
		//引入压缩工具类
		String zipUrl=CompressUtil.zip(url);
		//判断压缩包是否存在，
		if(zipUrl!=null){
			//存在，返回压缩包路径，返回1
			json.put("zip", toUrl);
			json.put("res", "1");
		}else{
			//不存在，压缩失败，返回0
			json.put("res", "0");
		}
		
		return json;
	}
	
	/**
	 * 删除文件
	 * @param request
	 * @return
	 */
	@RequestMapping("deleteFileType")
	@ResponseBody
	public JSONObject deleteFileType(HttpServletRequest request,Recycle recycle){
		JSONObject json =new JSONObject();
		int a=iRecycleService.delToRecycle(recycle);
		System.out.println(a+"a");
		json.put("res", a);
		return json;
	}
}

