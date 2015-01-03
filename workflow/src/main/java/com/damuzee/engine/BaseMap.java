package com.damuzee.engine;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 针对处理 Json 数据更容易使用的 Map 类型, 仿 JSONObject, 继承自 Map, 主要完成以下功能: 
 * <li>更简单的数据访问, 通过 getInteger, optDouble...等方法获取简单类型</li> 
 * <li>灵活的空指针处理, 使用 optIntger, optDouble 等方法时, 如果集合中并不包含此数值,<br>
 *  那么系统会返还一个默认值</li>
 *  <li>灵活的put 策略, 使用 pustrict 存储 K-V 对时, 如果 V 为 null, 则放弃存储.</li>
 * 
 * <p>
 * NOTE:<br>
 * 1. 由于赶任务, 只实现了一部分 getT/optT 方法, 其它请自补<br>
 * 2. 高级功能 getJsonMap getJsonArray 等待实现
 * </p>
 * @author austen
 * @date 2014年8月20日
 */
public class BaseMap implements Map<String, Object>, Serializable {
	private static final long serialVersionUID = -1114652284164682999L;
	private Map<String, Object> warp;
	
	public BaseMap() {
		warp = new HashMap();
	}
	
	public BaseMap(String key, Object object) {
		this();
		put(key, object);
	}
	
	public BaseMap(Map<String, Object> warp) {
		this.warp = warp;
	}
	
	public String id() {
		return getString("_id");
	}
	
	public Integer getInteger(String key) {
		String v = getString(key);
		return v == null ? null : v.isEmpty() ? new Integer(0) : Integer.valueOf(v);
	}
	public Double getDouble(String key) {
		String v = getString(key);
		return v == null ? null : v.isEmpty() ? new Double(0) : Double.valueOf(v);
	}
	public Boolean getBoolean(String key) {
		String v = getString(key);
		return v == null ? null : v.isEmpty() ? Boolean.FALSE : Boolean.valueOf(v.toString());
	}
	public String getString(String key) {
		Object v = get(key);
		return v == null ? null : v.toString();
	}
	public String optString(String key) {
		Object v = get(key);
		return v == null ? "" : v.toString();
	}
	public Integer optiInteger(String key) {
		Integer v = getInteger(key);
		return v == null ? 0 : v;
	}
	public Double optDouble(String key) {
		Double d = getDouble(key);
		return d == null ? new Double(0) : d;
	}
	public Boolean optBoolean(String key) {
		Boolean v = getBoolean(key);
		return v == null ? Boolean.FALSE : Boolean.valueOf(v.toString());
	}
	
	/**
	 * 当 value 为 null 时, 将不会存储此 K-V.
	 * @see {@link BaseMap#put(String, Object)}
	 */
	public Object pustrict(String key, Object value) {
		if (value == null) {
			return null;
		}
		return put(key, value);
	}
	
	public BaseMap append(String key, Object value) {
		put(key, value);
		return this;
	}
	
	public BaseMap appendStrict(String key, Object value) {
		pustrict(key, value);
		return this;
	}
	
	/**
	 * 放入一个新值,并将其返回.
	 * @param key
	 * @param T
	 * @return
	 * @author austen
	 * @date 2014年9月23日
	 */
	public <T> T putNew(String key, T T) {
		put(key, T);
		return T;
	}
	
	@Override
	public int size() {
		return warp.size();
	}
	@Override
	public boolean isEmpty() {
		return warp.isEmpty();
	}
	@Override
	public boolean containsKey(Object key) {
		return warp.containsKey(key);
	}
	@Override
	public boolean containsValue(Object value) {
		return warp.containsValue(value);
	}
	@Override
	public Object get(Object key) {
		return warp.get(key);
	}
	@Override
	public Object put(String key, Object value) {
		return warp.put(key, value);
	}
	@Override
	public Object remove(Object key) {
		return warp.remove(key);
	}
	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		warp.putAll(m);
	}
	@Override
	public void clear() {
		warp.clear();
	}
	@Override
	public Set<String> keySet() {
		return warp.keySet();
	}
	@Override
	public Collection<Object> values() {
		return warp.values();
	}
	@Override
	public Set<Entry<String, Object>> entrySet() {
		return warp.entrySet();
	}

	@Override
	public int hashCode() {
		return warp.hashCode();
	}
	
	@Override
	public String toString() {
		return warp.toString();
	}
}
