package cn.com.bo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test implements Runnable {
	
	public int j = 20;
	
	public String name;
	
	public void run(){
		for (int i = 0; i < 100; i++) {
			if (j > 0) {
				System.out.println(getName());
				j--;
			}
			
		}
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}




	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}




	public static void main(String[] args) {
		String a = "640*100,640*200";
		System.out.println(a.contains("640*100"));
         
	}
}
