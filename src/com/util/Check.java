package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.entity.UserInfo;
import com.service.impl.ILogInfoService;

@Aspect
public class Check {
	@Autowired
	HttpServletRequest request;
	@Autowired
	ILogInfoService logInfoService;

	/**
	 * @comment: 获取IP地址
	 * @param request
	 * @return String
	 * @author changjiaqi
	 * @date 2018年7月21日
	 */
	public String getRemoteHost() {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}

	/**
	 * 回环通知需要携带ProceedingJoinPoint类型参数
	 * 回环通知类似于动态代理的全过程：ProceedingJoinPoint类型的参数可以决定是否执行目标方法
	 * 且回环通知必须有返回值，返回值即为目标方法的返回值
	 * 
	 * @param joinPoint
	 * @return
	 */
	// 登录
	@Around("execution(* com.service.*.login(..))")
	public Object aroundLogin(ProceedingJoinPoint joinPoint) {
		Object rs = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 时间
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String string = dateFormat.format(date);
			// 可以获取目标方法的形参
			Object os[] = joinPoint.getArgs();
			UserInfo userInfo = (UserInfo) os[0];
			if (logInfoService.getUserIdByName(userInfo) != 0) {
				map.put("userId", logInfoService.getUserIdByName(userInfo));
			}
			if (logInfoService.getProjIdByUserCode(userInfo) != null) {
				map.put("projId", Integer.parseInt(logInfoService
						.getProjIdByUserCode(userInfo)));
			}
			map.put("url", request.getServletPath());
			// 获取Ip
			map.put("IP", getRemoteHost());
			map.put("logInfo", userInfo.getUserName() + "在" + string + "执行了"
					+ joinPoint.getSignature().getName() + "方法" + "进行登录");
			logInfoService.insertLogInfo(map);
			rs = joinPoint.proceed();// 以插入点形式执行,执行后用rs来保留目标方法的返回值
			System.out.println(userInfo.getUserName() + "在" + string + "执行了"
					+ joinPoint.getSignature().getName() + "方法" + "进行登录");
		} catch (Throwable throwable) {
			throwable.printStackTrace();

		} finally {

		}
		return rs;
	}

	// 退出登录
	@Around("execution(* com.service.*.logout(..))")
	public Object aroundLogout(ProceedingJoinPoint joinPoint) {
		Object rs = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 时间
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String string = dateFormat.format(date);
			// 可以获取目标方法的形参
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
					"UserInfo");
			if(userInfo!=null){
				map.put("userId", userInfo.getUserId());
			
			map.put("url", request.getServletPath());
			// 获取Ip
			map.put("IP", getRemoteHost());
			map.put("logInfo", userInfo.getUserName() + "在" + string + "执行了"
					+ joinPoint.getSignature().getName() + "方法" + "退出登录");
			
				logInfoService.insertLogInfo(map);
			
			rs = joinPoint.proceed();// 以插入点形式执行,执行后用rs来保留目标方法的返回值
			System.out.println(userInfo.getUserName() + "在" + string + "执行了"
					+ joinPoint.getSignature().getName() + "方法" + "退出登录");
			}
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
			
			
		
		
		return rs;
	}

	// 删除
	@Around("execution(* com.service.*.del*(..))")
	public Object aroundDelete(ProceedingJoinPoint joinPoint) {
		Object rs = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 时间
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String string = dateFormat.format(date);
			// 可以获取目标方法的形参
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
					"UserInfo");
			
				map.put("userId", userInfo.getUserId());
			
			map.put("url", request.getServletPath());
			// 获取Ip
			map.put("IP", getRemoteHost());
			map.put("logInfo", userInfo.getUserName() + "在" + string + "执行了"
					+ joinPoint.getSignature().getName() + "方法进行数据删除");

			logInfoService.insertLogInfo(map);
			rs = joinPoint.proceed();// 以插入点形式执行,执行后用rs来保留目标方法的返回值
			System.out.println(userInfo.getUserName() + "在" + string + "执行了"
					+ joinPoint.getSignature().getName() + "方法进行删除数据");
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return rs;
	}

	// 修改
	@Around("execution(* com.service.*.upd*(..))")
	public Object aroundUpdate(ProceedingJoinPoint joinPoint) {
		Object rs = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 时间
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String string = dateFormat.format(date);
			// 可以获取目标方法的形参
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
					"UserInfo");
			
				map.put("userId", userInfo.getUserId());
			
			map.put("url", request.getServletPath());
			// 获取Ip
			map.put("IP", getRemoteHost());
			map.put("logInfo", userInfo.getUserName() + "在" + string + "执行了"
					+ joinPoint.getSignature().getName() + "方法进行数据修改");

			logInfoService.insertLogInfo(map);
			rs = joinPoint.proceed();// 以插入点形式执行,执行后用rs来保留目标方法的返回值
			System.out.println(userInfo.getUserName() + "在" + string + "执行了"
					+ joinPoint.getSignature().getName() + "方法进行数据修改");
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return rs;
	}

	// 添加
	@Around("execution(* com.service.*.add*(..))")
	public Object aroundAdd(ProceedingJoinPoint joinPoint) {
		Object rs = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 时间
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String string = dateFormat.format(date);
			// 可以获取目标方法的形参
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
					"UserInfo");
			
				map.put("userId",userInfo.getUserId());
			
			map.put("url", request.getServletPath());
			// 获取Ip
			map.put("IP", getRemoteHost());
			map.put("logInfo", userInfo.getUserName() + "在" + string + "执行了"
					+ joinPoint.getSignature().getName() + "方法进行数据添加");

			logInfoService.insertLogInfo(map);
			rs = joinPoint.proceed();// 以插入点形式执行,执行后用rs来保留目标方法的返回值
			System.out.println(userInfo.getUserName() + "在" + string + "执行了"
					+ joinPoint.getSignature().getName() + "方法进行数据添加");
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return rs;
	}
}
