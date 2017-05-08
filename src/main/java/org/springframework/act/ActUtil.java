package org.springframework.act;

import java.lang.reflect.Field;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public abstract class ActUtil {
	
	// 获取对象的hashCode
    public static String hashCode(Object obj) {
    	if(obj == null) {
    		return "NULL";
    	} 
    	else {
    		//String name = obj.getClass().getSimpleName();
    		String name = obj.getClass().getName();
    		Integer hash = obj.hashCode();
    		return name + "@" + Integer.toHexString(hash);
    	}
    }
    
    // AbstractRefreshableApplicationContext DefaultListableBeanFactory beanFactory
    public static String hashCode(Object obj, String attribute) {	
    	if(obj == null) {
    		return "NULL";
    	} 
    	else {
    		Class clz = obj.getClass();
    		Field attr = getField(clz, attribute);
    		if(attr == null) {
    			return "NULL";
    		}
    		attr.setAccessible(true);
    		// String name = attr.getType().getSimpleName();
    		String name = attr.getType().getName();
    		Integer hash = null;
			try {
				hash = attr.get(obj).hashCode();
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
    		return name + "@" + Integer.toHexString(hash);
    	}
    }
    
    public static Field getField(Class clz, String attribute) {
    	if(clz == null) {
    		return null;
    	} else {
    		Field[] fs = clz.getDeclaredFields();
    		if(fs != null && fs.length > 0) {
    			for(Field f : fs) {
    				if(f.getName().equals(attribute)) {
    					return f;
    				}	
    			}
    		}
    		clz = clz.getSuperclass();
    		return getField(clz, attribute);
    	}
    }
}
