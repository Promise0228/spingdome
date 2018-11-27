package com.util;

public class Adhsuafu {

	int i=0;
	String a="";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Adhsuafu fsd=new Adhsuafu();
		String b=fsd.asddd();
		System.out.println(b);
	}

	public String asddd(){
		i++;
		if(i<10){
		a+="a+"+i;
		asddd();
		}
		
		return a;
	}
}
