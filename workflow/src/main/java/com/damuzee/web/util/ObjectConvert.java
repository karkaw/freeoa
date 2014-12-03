package com.damuzee.web.util;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 根据request 封装 Map类型的层次结构数据，请调用
 * 
 * ObjectConvert.convertParamToMap(HttpServletRequest request, Map<String,
 * Object> objectDef)
 * 
 * @author Jack Yuan
 * @date 2014-11-11
 */
@SuppressWarnings("unchecked")
public class ObjectConvert {

	/**
	 * 字符串
	 */
	public static final String OBJ_FIELD_TYPE_STRING = "java.lang.String";
	/**
	 * 整数
	 */
	public static final String OBJ_FIELD_TYPE_INTEGER = "java.lang.Integer";
	/**
	 * 整数
	 */
	public static final String OBJ_FIELD_TYPE_DOUBLE = "java.lang.Double";
	/**
	 * 日期
	 */
	public static final String OBJ_FIELD_TYPE_DATE = "java.lang.Date";
	/**
	 * 对象
	 */
	public static final String OBJ_FIELD_TYPE_MAP = "java.util.Map";
	/**
	 * 表格
	 */
	public static final String OBJ_FIELD_TYPE_LIST = "java.util.List";

	/**
	 * 转换参数为Map的层次结构
	 * 
	 * @param request
	 *            对象定义
	 * @return
	 */
	public static Map<String, Object> convertParamToMap(
			HttpServletRequest request) {
		return convertParamToMap(request.getParameterMap());
	}

	/**
	 * 转换参数为Map的层次结构
	 * 
	 * @param params
	 *            对象定义
	 * @return
	 */
	public static Map<String, Object> convertParamToMap(
			Map<String, Object> params) {
		// 定义返回值
		Map<String, Object> model = new HashMap<String, Object>();
		if (isNotEmptyMap(params)) {
			List<String> keys = new ArrayList<String>();
			Iterator<String> iterator = params.keySet().iterator();
			while (iterator.hasNext()) {
				keys.add(iterator.next());
			}
			Collections.sort(keys);
			for (String key : keys) {
                if(!key.contains("####")){
                    String[] value = (String[]) params.get(key);
                    if (value.length > 1) {
                        buildModelField(model, key, value);
                    } else {
                        buildModelField(model, key, value[0]);
                    }
                }

			}
		}
		return model;
	}

	/**
	 * 构建对象模型的字段
	 * 
	 * @param model
	 *            对象
	 *            对象定义
	 * @param key
	 *            字段名称
	 * @param value
	 *            值
	 */
	private static void buildModelField(Map<String, Object> model,
			 String key, Object value) {
		List<String> fieldLevels = renderFieldLevels(key);
		if (isNotEmptyList(fieldLevels)) {
			Object parentField = model;// 父对象， 比如 同上 name 的父对象 为department
			String parentFieldType = OBJ_FIELD_TYPE_MAP;// 父对象的字段类型
			for (String fieldName : fieldLevels) {
				String fieldType = getObjectDefType( key, fieldName);

				if (OBJ_FIELD_TYPE_LIST.equals(fieldType)) {
					// 表格
					List<Map<String, Object>> thisFields = null;
					if (OBJ_FIELD_TYPE_MAP.equals(parentFieldType)) {
						Map<String, Object> po = (Map<String, Object>) parentField;
						// 父对象是对象
						thisFields = (List<Map<String, Object>>) po
								.get(fieldName);
						if (isEmptyList(thisFields)) {
							thisFields = new ArrayList<Map<String, Object>>();
							po.put(fieldName, thisFields);
						}
						parentField = thisFields;
						parentFieldType = fieldType;
					} else if (OBJ_FIELD_TYPE_LIST.equals(parentFieldType)) {
						throw new RuntimeException( "转换List时出错");
					}
				} else if (OBJ_FIELD_TYPE_MAP.equals(fieldType)) {
					// 对象
					Map<String, Object> thisFields = null;
					if (OBJ_FIELD_TYPE_MAP.equals(parentFieldType)) {
						Map<String, Object> po = (Map<String, Object>) parentField;
						// 父对象是对象
						thisFields = (Map<String, Object>) po.get(fieldName);
						if (isEmptyMap(thisFields)) {
							thisFields = new HashMap<String, Object>();
							po.put(fieldName, thisFields);
						}
						parentField = thisFields;
						parentFieldType = fieldType;
					} else if (OBJ_FIELD_TYPE_LIST.equals(parentFieldType)) {
						List<Map<String, Object>> po = (List<Map<String, Object>>) parentField;
						if (isEmptyList(po) || po.size() <= getArrayIndex(key)) {
							// 表格为空 或者下标大于表格大小
							// 表格为空时，新增一个对象
							po.add(new HashMap<String, Object>());
						}
					}
				} else {
					// 简单类型
					if (OBJ_FIELD_TYPE_MAP.equals(parentFieldType)) {
						Map<String, Object> po = (Map<String, Object>) parentField;
						po.put(fieldName, convertValueByType(value, fieldType));
					} else {
						List<Map<String, Object>> po = (List<Map<String, Object>>) parentField;
						int index = getArrayIndex(key);
						if (isEmptyList(po) || po.size() <= getArrayIndex(key)) {
							// 表格为空 或者下标大于表格大小
							// 表格为空时，新增一个对象
							po.add(new HashMap<String, Object>());
						}
						po.get(index).put(fieldName,
								convertValueByType(value, fieldType));
					}
					break;
				}
			}
		}
	}

	/**
	 * 根据类型转至
	 * 
	 * @param value
	 * @param fieldType
	 * @return
	 */
	private static Object convertValueByType(Object value, String fieldType) {
		if (value == null) {
			return null;
		}
		if (OBJ_FIELD_TYPE_STRING.equals(fieldType) || isEmpty(fieldType)) {
			return value.toString();
		} else if (OBJ_FIELD_TYPE_INTEGER.equals(fieldType)) {
			return Integer.valueOf(value.toString());
		} else if (OBJ_FIELD_TYPE_DOUBLE.equals(fieldType)) {
			return Double.valueOf(value.toString());
		} else if (OBJ_FIELD_TYPE_DATE.equals(fieldType)) {
			// TODO 需要等待日期的格式化决定好了再说
			return value;
		} else {
			return value;
		}
	}

	/**
	 * 获取数组的下标 ，比如 users[3].name 返回3
	 * 
	 * 如果出现users[3].roles[2]这种情况是 不支持的
	 * 
	 * @param key
	 * @return
	 */
	private static int getArrayIndex(String key) {
		int arrayIndex = -1;
		int beginIndex = key.indexOf("[");
		int endIndex = key.indexOf("]");
		if (isNotEmpty(key) && beginIndex > 0 && endIndex > beginIndex) {
			String str = key.substring(beginIndex + 1, endIndex);
			arrayIndex = Integer.valueOf(str);
		}
		return arrayIndex;
	}

	private static String getObjectDefType(  String key, String fieldName) {
		String fieldType = null;
        if (key.startsWith(fieldName + "[") || key.contains("." + fieldName + "[")) {
            fieldType = OBJ_FIELD_TYPE_LIST;
        } else if (key.startsWith(fieldName + ".")
                || key.contains("." + fieldName + ".")) {
            fieldType = OBJ_FIELD_TYPE_MAP;
        } else {
            fieldType = OBJ_FIELD_TYPE_STRING;
        }
		return fieldType;
	}

	/**
	 * 把 key值转换为层级类型的数组
	 * 
	 * @param key
	 * @return
	 */
	private static List<String> renderFieldLevels(String key) {
		List<String> levels = new ArrayList<String>();
		if (isNotEmpty(key)) {
			String[] arrays = key.split("\\.");
			for (String item : arrays) {
				if (isEmpty(item)) {
					continue;
				}
				// 处理数组的下标
				int index = item.indexOf("[");
				if (index > 0 && item.length() > 3) {
					levels.add(item.substring(0, index));
				} else {
					levels.add(item);
				}
			}
		}
		return levels;
	}

	/**
	 * 检查List是否为非空
	 * 
	 * @param list
	 * @return
	 */
	public static <E> boolean isNotEmptyList(List<E> list) {
		return !isEmptyList(list);
	}

	/**
	 * 检查List是否为空
	 * 
	 * @param list
	 * @return
	 */
	public static <E> boolean isEmptyList(List<E> list) {
		return list == null || list.isEmpty();
	}

	/**
	 * 检查Map是否为非空
	 * 
	 * @param map
	 * @return
	 */
	public static <E> boolean isNotEmptyMap(Map<String, E> map) {
		return !isEmptyMap(map);
	}

	/**
	 * 检查Map是否为空
	 * 
	 * @param map
	 * @return
	 */
	public static <E> boolean isEmptyMap(Map<String, E> map) {
		return map == null || map.isEmpty();
	}

	/**
	 * 检查String是否为非空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(CharSequence str) {
		return !isEmpty(str);
	}

	/**
	 * 检查String是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(CharSequence str) {
		return str == null || str.toString().trim().isEmpty();
	}

	public static void main(String[] args) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();

		paramsMap.put("loginName", "12");
		paramsMap.put("password", "12");
		paramsMap.put("personInfo.userName", "12");
		paramsMap.put("personInfo.age", "12");
		paramsMap.put("gender", "2");
		paramsMap.put("personInfo.province", "12");
		paramsMap.put("personInfo.city", "12");
		paramsMap.put("personInfo.contant[0].name", "12");
		paramsMap.put("personInfo.contant[0].relation", "12");
		paramsMap.put("personInfo.contant[0].mobile", "12");
		paramsMap.put("personInfo.contant[0].note", "12");
		paramsMap.put("personInfo.contant[1].name", "23");
		paramsMap.put("personInfo.contant[1].relation", "23");
		paramsMap.put("personInfo.contant[1].mobile", "23");
		paramsMap.put("personInfo.contant[1].note", "23");
		paramsMap.put("personInfo.contant[2].name", "");
		paramsMap.put("personInfo.contant[2].relation", "");
		paramsMap.put("personInfo.contant[2].mobile", "");
		paramsMap.put("personInfo.contant[2].note", "");
		paramsMap.put("addressInfo[0].name", "33");
		paramsMap.put("addressInfo[0].province", "33");
		paramsMap.put("addressInfo[0].city", "33");
		paramsMap.put("addressInfo[0].street", "33");
		paramsMap.put("addressInfo[0].zipCode", "33");
		paramsMap.put("addressInfo[1].name", "44");
		paramsMap.put("addressInfo[1].province", "44");
		paramsMap.put("addressInfo[1].city", "44");
		paramsMap.put("addressInfo[1].street", "44");
		paramsMap.put("addressInfo[1].zipCode", "44");
		paramsMap.put("addressInfo[2].name", "");
		paramsMap.put("addressInfo[2].province", "");
		paramsMap.put("addressInfo[2].city", "");
		paramsMap.put("addressInfo[2].street", "");
		paramsMap.put("addressInfo[2].zipCode", "");
		paramsMap.put("listField", "addressInfo");
		paramsMap.put("otherInfo.info1", "2014-11-22");
		paramsMap.put("otherInfo.info2", "");
		paramsMap.put("otherInfo.info3", "7");
		paramsMap.put("otherInfo.info4.info41", "41");
		paramsMap.put("otherInfo.info4.info42", "42");
		Map map = convertParamToMap(paramsMap);
		System.err.println(map);
	}
}
