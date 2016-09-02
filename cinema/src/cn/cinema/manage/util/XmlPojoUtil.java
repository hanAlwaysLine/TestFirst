package cn.cinema.manage.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 对xml或pojo的相互转换的工具类.
 * 
 * @author ZOUDW.
 * @modify by jiyk
 * @since 2011-12-05.
 */
public class XmlPojoUtil {
	public static String pojoClassPath = "cn.cinema.manage.entity";
	private static Logger log4j = Logger.getLogger(XmlPojoUtil.class);

	public static String getPojoClassPath() {
		return pojoClassPath;
	}

	public static void setPojoClassPath(String pojoClassPath) {
		XmlPojoUtil.pojoClassPath = pojoClassPath;
	}

	/**
	 * 根据所传object建立XML.
	 * 
	 * @param obj
	 *            传入操作对象.
	 * @param isRoot
	 *            是否为根节点.
	 * @param ele
	 *            不是根节点时的操作节点.
	 * @return Element.
	 * @throws SecurityException.
	 * @throws NoSuchMethodException.
	 * @throws IllegalArgumentException.
	 * @throws IllegalAccessException.
	 * @throws InvocationTargetException.
	 */
	@SuppressWarnings("unchecked")
	public static Element createXml(Object obj, boolean isRoot, Element ele) throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Document document = DocumentHelper.createDocument();
		// 创建根节点
		String rootName = obj.getClass().getName().substring(obj.getClass().getName().lastIndexOf(".") + 1);
		Element root = document.addElement(rootName);
		if (isRoot) {
			// 设置根节点属性
//			root.addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
//			root.addAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
		} else {
			// 递归时，继续操作ele 区分命名空间时改进
			// if ((obj.getClass().getName().indexOf(".")) != -1) {
			// root = ele.addElement(obj.getClass().getName().substring((obj.getClass().getName().lastIndexOf(".") + 1)));
			// } else {
			// root = ele.addElement(obj.getClass().getName());
			// }
		}
		// 获取对象所有属性
		Field[] field = obj.getClass().getDeclaredFields();
		for (Field f : field) {
			// 属性名
			String name = toUpStr(f.getName());
			// 子节点
			Element child = root.addElement(name);
			// 属性类型
			String type = f.getGenericType().toString();
			// 根据属性类型，直接添加节点或递归添加节点
			if (type.equals("class java.lang.String")) {
				Method method = obj.getClass().getMethod("get" + name);
				String value = (String) method.invoke(obj);
				if (value == null) {
					value = "";
				}
				child.addText(value);
			}
			if (type.contains("java.util.List")) {
				Method method = obj.getClass().getMethod("get" + name);
				// 获取对象中要递归属性集合，并且循环递归获取节点
				List<Object> valueList = (List<Object>) method.invoke(obj);
				if (valueList != null) {
					for (Object object : valueList) {
						child.add(createXml(object, false, child));
					}
				}
			}
		}
		return root;
	}

	/**
	 * 将字符串首字母大写.
	 * 
	 * @param String
	 *            str 参数字符串.
	 * @return String 处理后字符串.
	 */
	public static String toUpStr(String str) {
		str = str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
		return str;
	}

	/**
	 * 将字符串首字母小写.
	 * 
	 * @param String
	 *            str 参数字符串.
	 * @return String 处理后字符串.
	 */
	public static String toLowStr(String str) {
		str = str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toLowerCase());
		return str;
	}

	/**
	 * 获取对象所有属性.
	 * 
	 * @param obj
	 *            操作对象.
	 * @return Field[] 对象属性组.
	 */
	public static Field[] getPro(Object obj) {
		return obj.getClass().getDeclaredFields();
	}

	/**
	 * 根据xml字符串生成pojo对象
	 * 
	 * @param documentStr
	 *            xml字符串
	 * @param className
	 *            pojo类名称
	 * @return String 处理后的XML字符串.
	 * @throws ClassNotFoundException
	 * @throws DocumentException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	@SuppressWarnings("unchecked")
	public static Object createPojo(String documentStr, String className) throws ClassNotFoundException, DocumentException,
			SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, InstantiationException {
		// 获取对象类型
		Class myClass = Class.forName(XmlPojoUtil.pojoClassPath + "." + className);
		// 实例化对象
		Object object = myClass.newInstance();
		// 获取根节点
		Document document = DocumentHelper.parseText(documentStr);
		Element rootElt = document.getRootElement();
		Iterator iter = rootElt.elementIterator();
		// 遍历根节点下的子节点
		while (iter.hasNext()) {
			Element element = (Element) iter.next();
			// 其下纯在子节点，证明其类型中存在pojo集合属性，遍历实例化
			if (element.elementIterator().hasNext()) {
				ArrayList<Object> list = new ArrayList<Object>();
				Iterator iterChild = element.elementIterator();
				while (iterChild.hasNext()) {
					// 递归获取子对象，add进父对象的pojo集合属性中
					Element child = (Element) iterChild.next();
					list.add(createPojo(child.asXML(), child.getName()));
				}
				// set父对象中pojo集合属性
				Method method = object.getClass().getMethod("set" + element.getName(), java.util.List.class);
				method.invoke(object, list);
			} else {
				// 无子节点，set属性值
				Method method = object.getClass().getMethod("set" + element.getName(), java.lang.String.class);
				method.invoke(object, element.getText());
			}
		}
		return object;
	}

	/**
	 * catch异常.
	 * 
	 * @param obj
	 *            .
	 * @return createXml(obj, true, null).asXML()处理后的字符串.
	 */
	public static String getRetureStr(Object obj) {
		String str = "";
		try {
			str = createXml(obj, true, null).asXML();
		} catch (SecurityException e) {
			log4j.error("创建XML字符串异常："+e.getMessage());
		} catch (IllegalArgumentException e) {
			log4j.error("创建XML字符串异常："+e.getMessage());
		} catch (NoSuchMethodException e) {
			log4j.error("创建XML字符串异常："+e.getMessage());
		} catch (IllegalAccessException e) {
			log4j.error("创建XML字符串异常："+e.getMessage());
		} catch (InvocationTargetException e) {
			log4j.error("创建XML字符串异常："+e.getMessage());
		}
		return str;
	}

	public static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex); // 将给定的正则表达式编译到具有给定标志的模式中。
		Matcher matcher = pattern.matcher(str); // 创建匹配给定输入与此模式的匹配器。
		return matcher.matches(); // 尝试将整个区域与模式匹配。
	}
	
}
