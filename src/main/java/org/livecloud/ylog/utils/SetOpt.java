package org.livecloud.ylog.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetOpt {
	
	public static <T> Set<T> diff(List<T> list, String[] tValues, Class<T> klass) {
		Set<T> newValues = new HashSet<T>();
		for (int i = 0; i < tValues.length; i++){ 
			try {
				T newT = klass.getDeclaredConstructor(String.class).newInstance(tValues[i]);
				newValues.add(newT);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		newValues.removeAll(list);
		return newValues;
	}
	
	public static <T> boolean exist(List<T> list, String tValues, Class<T> klass) {
		try {
			T newT = klass.getDeclaredConstructor(String.class).newInstance(tValues);
			return list.contains(newT);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return false;
	}
}
