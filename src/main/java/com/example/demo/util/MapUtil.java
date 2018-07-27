package com.example.demo.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class MapUtil {

	public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass) {
		if (map == null)
			return null;

		T obj = null;
		try {
			obj = beanClass.newInstance();

			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				int mod = field.getModifiers();
				if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
					continue;
				}

				field.setAccessible(true);
				field.set(obj, map.get(field.getName()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return obj;
	}

	public static Map<String, Object> objectToMap(Object obj) {
		if (obj == null) {
			return null;
		}

		Map<String, Object> map = new HashMap<String, Object>();

		Field[] declaredFields = obj.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			if("serialVersionUID".equalsIgnoreCase(field.getName())){
				continue;
			}
			try {
				PropertyDescriptor pd = new PropertyDescriptor(field.getName(),obj.getClass());  
				Method getMethod = pd.getReadMethod();//获得get方法  
				Object value = getMethod.invoke(obj);//执行get方法返回一个Object  
				map.put(field.getName(), value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return map;
	}
}
