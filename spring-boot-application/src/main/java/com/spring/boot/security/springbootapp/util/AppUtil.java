package com.spring.boot.security.springbootapp.util;

import java.util.LinkedList;
import java.util.List;

public class AppUtil {

	public static <T> List<T> convertIterableToList(Iterable<T> iterable){
		List<T> list = new LinkedList<>();
		for(T t : iterable){
			list.add(t);
		}
		return list;
	}
}
