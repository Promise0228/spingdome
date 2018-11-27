/**  
 * @Title: MyMessage.java
 * @Package com.item.pojo
 * @Description: TODO
 * @author 赵帅帅
 * @date 2018年7月17日
 */
package com.entity;

import java.util.Date;

/**
 * ClassName: MyMessage 
 * @Description: TODO items
 * @author 赵帅帅
 * @date 2018年7月17日
 */
public class MyMessage {
	private Integer usermsgid;
	private Integer msgid;
	private String readstate;
	private Integer fromuser;
	private Integer touser;
	private Integer parentid;
	private Date createtime;
	
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Integer getUsermsgid() {
		return usermsgid;
	}
	public void setUsermsgid(Integer usermsgid) {
		this.usermsgid = usermsgid;
	}
	public Integer getMsgid() {
		return msgid;
	}
	public void setMsgid(Integer msgid) {
		this.msgid = msgid;
	}
	public String getReadstate() {
		return readstate;
	}
	public void setReadstate(String readstate) {
		this.readstate = readstate;
	}
	public Integer getFromuser() {
		return fromuser;
	}
	public void setFromuser(Integer fromuser) {
		this.fromuser = fromuser;
	}
	public Integer getTouser() {
		return touser;
	}
	public void setTouser(Integer touser) {
		this.touser = touser;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
    
}
