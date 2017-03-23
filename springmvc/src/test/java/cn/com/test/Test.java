package cn.com.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

/**
* Created by IntelliJ IDEA.
* File: TestRef.java
* User: leizhimin
* Date: 2008-1-28 14:48:44
*/
public class Test {
	
	/** 
	 * 根据属性名获取属性值 
	 * */  
   private static Object getFieldValueByName1(String fieldName, Object o) {  
       try {    
           String firstLetter = fieldName.substring(0, 1).toUpperCase();    
           String getter = "get" + firstLetter + fieldName.substring(1);    
           Method method = o.getClass().getMethod(getter, new Class[] {});    
           Object value = method.invoke(o, new Object[] {});    
           return value;    
       } catch (Exception e) {    
           return null;    
       }    
   } 
   
   	public static Object getFieldValueByName(String fieldName, Object o){
   		Object val = null;
   		try {
	   		Field[] fs = o.getClass().getDeclaredFields();  
	        for(int i = 0 ; i < fs.length; i++){  
	            Field f = fs[i];  
	            if (f.getName().equals(fieldName)) {
	            	f.setAccessible(true);
	            	val = f.get(o);
	            	System.out.println(f.isAnnotationPresent(Mymete.class));
				}
	            continue;
	        }  
        } catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return val;
   	}

    public static void main(String args[]) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Foo foo = new Foo("这个一个Foo对象！");
        Class clazz = foo.getClass();
//        Method m1 = clazz.getDeclaredMethod("outInfo");
//        Method m2 = clazz.getDeclaredMethod("setMsg", String.class);
//        Method m3 = clazz.getDeclaredMethod("getMsg");
        System.out.println(getFieldValueByName("msg",foo));
//        m1.invoke(foo);
//        m2.invoke(foo, "重新设置msg信息！");
//        String msg = (String) m3.invoke(foo);
//        System.out.println(msg);
    }
}

class Foo {
	
	@Mymete("消息")
    private String msg;

    public Foo(String msg) {
        this.msg = msg;
    }

//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public String getMsg() {
//        return msg;
//    }

    public void outInfo() {
        System.out.println("这是测试Java反射的测试类");
    }
}