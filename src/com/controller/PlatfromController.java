/**  
 * @Title: PlatfromController.java
 * @Package com.item.controller
 * @Description: TODO
 * @author 赵帅帅
 * @date 2018年7月16日
 */
package com.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.entity.Message;
import com.entity.MyMessage;
import com.entity.UserInfo;
import com.service.impl.IPlatfromService;

/**
 * ClassName: PlatfromController
 * 
 * @Description: TODO items
 * @author 赵帅帅
 * @date 2018年7月16日
 */
@Controller
@RequestMapping("platfrom")
public class PlatfromController {
	@Autowired
	IPlatfromService platfromService;
    /**
     * 
     * @Description: 跳转站内信列表 分页展示数据
     * @param @param request
     * @param @param message
     * @param @return   
     * @return ModelAndView  
     * @throws
     * @author 赵帅帅
     * @date 2018年7月29日
     */
	@RequestMapping("platfromlist")
	public ModelAndView toPlatfromList(HttpServletRequest request,
			Message message) {
		ModelAndView mav = new ModelAndView("platfrom-list");
		mav.addObject("page",
				platfromService.getPlatfromPageBean(request, message));
		mav.addObject("message", platfromService.findMessage(message));
		return mav;
	}
    /**
     * 
     * @Description: 跳转我的站内信列表 分页展示数据
     * @param @param request
     * @param @param message
     * @param @return   
     * @return ModelAndView  
     * @throws
     * @author 赵帅帅
     * @date 2018年7月29日
     */
	@RequestMapping("myplatfromlist")
	public ModelAndView toMyPlatfromList(HttpServletRequest request,
			Message message) {
		ModelAndView mav = new ModelAndView("myplatfrom-list");
		mav.addObject("page",platfromService.getMyPlatfromPageBean(request, message));
		mav.addObject("message", platfromService.findMessage(message));	
		mav.addObject("count", platfromService.getCount(request));
		int count = platfromService.getCount(request);
		request.getSession().setAttribute("nearOutMessage", count);
		return mav;
	}
    /**
     * 
     * @Description: 跳转发送站内信展示数据
     * @param @param request
     * @param @param myMessage
     * @param @return   
     * @return ModelAndView  
     * @throws
     * @author 赵帅帅
     * @date 2018年7月29日
     */
	@RequestMapping("massagetolist")
	public ModelAndView messageTo(HttpServletRequest request,
			MyMessage myMessage) {
		ModelAndView mav = new ModelAndView("messageto");
		request.getSession().setAttribute("team",platfromService.findAllTeams());
		return mav;
	}
    /**
     * 
     * @Description: 查询所以用户信息
     * @param @param request
     * @param @return   
     * @return JSONObject  
     * @throws
     * @author 赵帅帅
     * @date 2018年7月29日
     */
	@RequestMapping("onesend")
	@ResponseBody
	public JSONObject oneSend(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		json.put("users", platfromService.findAllUsers());
		return json;
	}
    /**
     * 
     * @Description: 根据用户code模糊查询信息
     * @param @param request
     * @param @param userInfo
     * @param @return   
     * @return JSONObject  
     * @throws
     * @author 赵帅帅
     * @date 2018年7月29日
     */
	@RequestMapping("findusers")
	@ResponseBody
	public JSONObject findUsers(HttpServletRequest request, UserInfo userInfo) {
		JSONObject json = new JSONObject();
		json.put("users", platfromService.findUserByCode(userInfo));
		return json;
	}
    /**
     * 
     * @Description: 发送站内信  添加站内信信息  添加站内信关系表信息  文件上传
     * @param @param file
     * @param @param request
     * @param @param message
     * @param @return   
     * @return String  
     * @throws
     * @author 赵帅帅
     * @date 2018年7月29日
     */
	@RequestMapping("sendmessage")
	public String  addMessage(@RequestParam("file") MultipartFile file,HttpServletRequest request, Message message) {
		int addMessage = platfromService.addMessage(file,request, message);
		String back = "";
		if (addMessage > 0) {
			back = "redirect:platfromlist.action";
		} else {
			back = "redirect:massagetolist.action";
		}
		return back;
	}
    /**
     * 
     * @Description: 根据messageid查询touser用户code
     * @param @param request
     * @param @param message
     * @param @return   
     * @return JSONObject  
     * @throws
     * @author 赵帅帅
     * @date 2018年7月29日
     */
	@RequestMapping("getusercode")
	@ResponseBody
	public JSONObject getUserCode(HttpServletRequest request, Message message) {
		JSONObject json = new JSONObject();
		json.put("usercode", platfromService.findUserCodeByMsgid(message));
		json.put("msgid", request.getParameter("msgid"));
		return json;
	}
    /**
     * 
     * @Description: 单挑回复页面跳转
     * @param @param request
     * @param @param message
     * @param @return   
     * @return ModelAndView  
     * @throws
     * @author 赵帅帅
     * @date 2018年7月29日
     */
	@RequestMapping("onereply")
	public ModelAndView oneReply(HttpServletRequest request, Message message) {
		ModelAndView mav = new ModelAndView("replymessage");
		mav.addObject("usercode", request.getParameter("usercode"));
		mav.addObject("touser", platfromService.findUserCodeByMsgid(message));
		mav.addObject("msgid", request.getParameter("msgid"));
		mav.addObject("message", platfromService.findMessage(message));
		return mav;
	}
    /**
     * 
     * @Description: 全部回复页面跳转
     * @param @param request
     * @param @param message
     * @param @return   
     * @return ModelAndView  
     * @throws
     * @author 赵帅帅
     * @date 2018年7月29日
     */
	@RequestMapping("allreply")
	public ModelAndView allreply(HttpServletRequest request, Message message) {
		ModelAndView mav = new ModelAndView("replymessage");
		//获取session中uesr对象
		UserInfo user = (UserInfo) request.getSession().getAttribute("UserInfo");
		//根据msgid查询所有touser的code集合
		String str = platfromService.findUserCodesByMsgid(message)+",";
		//获取当前用户的code
		String usercode = user.getUserCode()+",";
		//截去当前用户的code
		String reusercode = str.replace(usercode, "");
		//带回页面
		mav.addObject("usercode", user.getUserCode());
		mav.addObject("touser", reusercode);
		mav.addObject("mark", "allreply");
		mav.addObject("message", platfromService.findMessage(message));
		return mav;
	}
    /**
     * 
     * @Description: 回复站内信提交信息
     * @param @param file
     * @param @param request
     * @param @param message
     * @param @return   
     * @return String  
     * @throws
     * @author 赵帅帅
     * @date 2018年7月29日
     */
	@RequestMapping("replymessage")
	public String replyMessage(@RequestParam("file")MultipartFile file,HttpServletRequest request, Message message) {
		platfromService.addMessage(file,request, message);
		return "redirect:myplatfromlist.action";
	}
	/**
	 * 
	 * @Description: 全部标记已读
	 * @param @param request
	 * @param @param message
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月29日
	 */
	@RequestMapping("allmarkread")
	public String allMarkRead(HttpServletRequest request, Message message) {
		platfromService.allMarkRead();
		request.getSession().setAttribute("nearOutMessage", 0);
		return "redirect:myplatfromlist.action";
	}
	/**
	 * 
	 * @Description: 全部发送回写
	 * @param @param request
	 * @param @param message
	 * @param @return   
	 * @return JSONObject  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月29日
	 */
	@RequestMapping("allsend")
	@ResponseBody
	public JSONObject allSend(HttpServletRequest request, Message message) {
		JSONObject json = new JSONObject();
		//获取session中user对象
		UserInfo user = (UserInfo) request.getSession().getAttribute("UserInfo");
		//获取所有用户code的集合
		String str = platfromService.findAllUserCode()+",";
		//获取当前用户的code
		String usercode = user.getUserCode()+",";
		//截去当前用户的code
		String reusercode = str.replace(usercode, "");
		//带回页面
		json.put("usercode", reusercode);		
		return json ;
	}
	/**
	 * 
	 * @Description: 文件下载
	 * @param @param request
	 * @param @param response
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年7月29日
	 */
	@RequestMapping("/download")  
    public void down(HttpServletRequest request,HttpServletResponse response) throws Exception{  
   
    	//模拟文件，myfile.txt为需要下载的文件  
        String fileName = request.getSession().getServletContext().getRealPath("upload")+"/"+request.getParameter("fileurl");  
        //获取输入流  
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));  
        //假如以中文名下载的话  
        String filename = request.getParameter("fileurl");  
        //转码，免得文件名中文乱码  
        filename = URLEncoder.encode(filename,"UTF-8");  
        //设置文件下载头  
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
        response.setContentType("multipart/form-data"); 
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }  
        out.close();  
        bis.close();
    }
	

}
