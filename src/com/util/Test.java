package com.util;

import java.io.File;

public class Test {

	public static void main(String[] args) {
		File file = new File("H:/【推荐先学-第1套】压缩文件，需要下载到电脑学习/JavaEE 学习路线图.png");
		
		//System.out.println(CompressUtil.zip("H:/【推荐先学-第1套】压缩文件，需要下载到电脑学习/就业指导资料"));
		/*String src="H:/【推荐先学-第1套】压缩文件，需要下载到电脑学习";
		String dest="H:/";
		String passwd="123456";*/
		System.out.println(CompressUtil.zip("H:/【推荐先学-第1套】压缩文件，需要下载到电脑学习/就业指导资料"));
	}

}
