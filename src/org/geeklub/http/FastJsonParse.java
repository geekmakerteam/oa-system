package org.geeklub.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class FastJsonParse {
	
	public static <T> T getJsonObject(String jsonString,Class<T> cls){
		T t = null;
		try {
			t = JSON.parseObject(jsonString, cls); 

		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}


	public static <T> List<T> getJsonArray(String jsonString,Class<T> cls){
		List<T> list = new ArrayList<T>();
		try {
			list = JSON.parseArray(jsonString, cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Map<String,Object>> listKeyMap(String jsonString){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = JSON.parseObject(jsonString, new TypeReference<List<Map<String,Object>>>(){
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static Map<String,Object> KeyMap(String jsonString){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			map = JSON.parseObject(jsonString, new TypeReference<Map<String,Object>>(){

			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;

	}

}
