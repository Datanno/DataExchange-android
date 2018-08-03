package com.xiong.common.lib.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


public class GJsonUtil {

	private static Gson gson = new Gson();
	private static Gson expGson = new GsonBuilder()
			.excludeFieldsWithoutExposeAnnotation().create();

	@SuppressWarnings({ "unchecked" })
	public static <T> T toObject(String json, Type type) {
		if (json == null) {
			return null;
		}
		if (type == null) {
			return null;
		}
		Gson gsonMap = new Gson();
		T object;
		try {
			object = (T) gsonMap.fromJson(json, type);
		} catch (RuntimeException e) {
			throw e;
		}
		return object;
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T toObject(String json, Class beanClass) {
		if (json == null) {
			return null;
		}
		if (beanClass == null) {
			return null;
		}

		@SuppressWarnings("unused")
		T object;
		try {
			// json = URLDecoder.decode(json, "UTF-8");
			return (T) gson.fromJson(json, beanClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@SuppressWarnings("rawtypes")
	public static String toJson(Object object, Class beanClass) {
		if (object == null) {
			return null;
		}
		if (beanClass == null) {
			return null;
		}
		return gson.toJson(object, beanClass);
	}

	@SuppressWarnings("rawtypes")
	public static String toJsonExp(Object object, Class beanClass) {
		if (object == null) {
			return null;
		}
		if (beanClass == null) {
			return null;
		}
		return expGson.toJson(object, beanClass);
	}


	public static String mapToJson(Map<String, Object> entity) {
		if (entity == null)
			return null;
		return gson.toJson(entity, HashMap.class);
	}


	public static String objToJson(Object obj) {
		if (obj == null)
			return null;
		return gson.toJson(obj);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(String json) {
		return gson.fromJson(json, Map.class);
	}
}
