/**  
 * @Title: Message.java
 * @Package com.item.pojo
 * @Description: TODO
 * @author 赵帅帅
 * @date 2018年7月16日
 */
package com.entity;


/**
 * ClassName: Message
 * 
 * @Description: TODO items
 * @author 赵帅帅
 * @date 2018年7月16日
 */
public class Message {
	// 站内信id
	private Integer msgid;
	// 站内信标题
	private String msgtitle;
	// 站内信内容
	private String msgcontext;
	// 站内信附件
	private String fileurl;
	// 发送类型
	private String sendtype;
	// 站内信创建时间
	private String createtime;
	// 站内信创建人
	private Integer createby;
	// 信息状态
	private String readstate;
	// 用户id
	private Integer userid;
	// 接收人
	private Integer fromUser;
	// 发送人
	private Integer toUser;
	

	public Integer getFromUser() {
		return fromUser;
	}

	public void setFromUser(Integer fromUser) {
		this.fromUser = fromUser;
	}

	public Integer getToUser() {
		return toUser;
	}

	public void setToUser(Integer toUser) {
		this.toUser = toUser;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	

	public String getReadstate() {
		return readstate;
	}

	public void setReadstate(String readstate) {
		this.readstate = readstate;
	}

	public Integer getMsgid() {
		return msgid;
	}

	public void setMsgid(Integer msgid) {
		this.msgid = msgid;
	}

	public String getMsgtitle() {
		return msgtitle;
	}

	public void setMsgtitle(String msgtitle) {
		this.msgtitle = msgtitle;
	}

	public String getMsgcontext() {
		return msgcontext;
	}

	public void setMsgcontext(String msgcontext) {
		this.msgcontext = msgcontext;
	}

	public String getFileurl() {
		return fileurl;
	}

	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}

	public String getSendtype() {
		return sendtype;
	}

	public void setSendtype(String sendtype) {
		this.sendtype = sendtype;
	}

	

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public int getCreateby() {
		return createby;
	}

	public void setCreateby(Integer createby) {
		this.createby = createby;
	}

	@Override
	public String toString() {
		return "Message [msgid=" + msgid + ", msgtitle=" + msgtitle
				+ ", msgcontext=" + msgcontext + ", fileurl=" + fileurl
				+ ", sendtype=" + sendtype + ", createtime=" + createtime
				+ ", createby=" + createby + ", readstate=" + readstate
				+ ", userid=" + userid + "]";
	}

}
