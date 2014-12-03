package com.damuzee.web.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertMap {

	Map<String, Object> result = new HashMap<String, Object>();

	public static void main(String[] args) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> ff = new HashMap<String, Object>();
		String key1 = "a.b.c";
		build(new KeyReader(key1), ff, "123", ret);
		build(new KeyReader("b[0].h"), ff, "b0", ret);
		build(new KeyReader("b[0].f"), ff, "b0", ret);
		build(new KeyReader("b[1]"), ff, "b1", ret);
		build(new KeyReader("b[2]"), ff, "b2", ret);
		build(new KeyReader("b[3][0]"), ff, "b30", ret);
		build(new KeyReader("b[3][1]"), ff, "b31", ret);
		build(new KeyReader("b[4][0].m"), ff, "b40m", ret);
		build(new KeyReader("b[4][0].h"), ff, "b40h", ret);
		build(new KeyReader("b[4][1]"), ff, "b41", ret);
		build(new KeyReader("c.e"), ff, "dd", ret);
		build(new KeyReader("c.F.G.G.G.k.k.k"), ff, "dd", ret);
		build(new KeyReader("c.F.G.G.G.k.k.t"), ff, "dd", ret);
		System.out.println(ret);
	}

	@SuppressWarnings("unchecked")
	public static void build(KeyReader kr, Map<String, Object> ff, Object value, Map<String, Object> ret) {
		String key = kr.next();
		String parentKey = kr.parentKey();
		if (!kr.hasNext()) {
			ret.put(key, value);
			return;
		}
		if (kr.isArrayElement()) {
			// 如果 key 是数组元素, 获取我的数组
			List<Object> me = (List<Object>) ff.get(parentKey);
			if (me == null) {
				me = new ArrayList<Object>(10);
				ff.put(parentKey, me);
				me.add(kr.index(), value);
				build(kr, ff, me, ret);
			} else {
				me.add(kr.index(), value);
			}
		} else {
			// 如果 key 是非数组元素　
			Map<String, Object> me = (Map<String, Object>) ff.get(parentKey);
			if (me == null) {
				me = new HashMap<String, Object>();
				ff.put(parentKey, me);
				me.put(key, value);
				build(kr, ff, me, ret);
			} else {
				me.put(key, value);
			}
		}
	}
	
	public static void ensureCap(int index, List<Object> list) {
		if (index < list.size()) {
			return;
		}
		int ca = index - list.size();
		Object[] object = new Object[ca];
		list.addAll(list.size() -1, Arrays.asList(object));
	}
}