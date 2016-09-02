package cn.cinema.manage.tag;

import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.components.Component;

import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.ServiceLocator;

import com.opensymphony.xwork2.util.ValueStack;

public class DictComponent extends Component {
	/**
	 * pojo 名.
	 */
	private String pojoName;
	/**
	 * 显示列名.
	 */
	private String showColName;
	/**
	 * 值列名.
	 */
	private String hiddColName;
	/**
	 * defValue 默认选中值.
	 */
	private String defValue;
	/**
	 * 查询条件.
	 */
	private String where;
	/**
	 * 控件类型.
	 */
	private String type;
	/**
	 * 名称.
	 */
	private String name;
	/**
	 * 是否可用.
	 */
	private String disabled;
	/**
	 * 样式名.
	 */
	private String className;
	/**
	 * JS 调用方法.
	 */
	private String func;
	/**
	 * 是否只为输出.
	 */
	private String outPrint = "";

	/**
	 * 固定选项.
	 */
	private String fixedOption = "";

	public String getOutPrint() {
		return outPrint;
	}

	public void setOutPrint(String outPrint) {
		this.outPrint = outPrint;
	}

	private String[] defValues = new String[] {};

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public String getPojoName() {
		return pojoName;
	}

	public void setPojoName(String pojoName) {
		this.pojoName = pojoName;
	}

	public String getShowColName() {
		return showColName;
	}

	public void setShowColName(String showColName) {
		this.showColName = showColName;
	}

	public String getHiddColName() {
		return hiddColName;
	}

	public void setHiddColName(String hiddColName) {
		this.hiddColName = hiddColName;
	}

	public String getDefValue() {
		return defValue;
	}

	public void setDefValue(String defValue) {
		this.defValue = defValue;
		if (defValue.length() > 0) {
			defValues = defValue.split(",");
		}
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getFixedOption() {
		return fixedOption;
	}

	public void setFixedOption(String fixedOption) {
		this.fixedOption = fixedOption;
	}

	/**
	 * 数据操作类.
	 */
	private static BaseService bs = ServiceLocator.getBaseService();

	public DictComponent(ValueStack stack) {
		super(stack);
		// TODO Auto-generated constructor stub
	}

	// 将自己需要输出的逻辑通过writer输出字符串就可以了。
	public boolean start(Writer writer) {

		StringBuffer sb = new StringBuffer();
		StringBuffer hqlsb = new StringBuffer();
		// 串接查询SQL.
		hqlsb.append("select ").append(hiddColName).append(",").append(
				showColName).append(" from ").append(pojoName);
		if (where.length() > 0) {
			hqlsb.append(" where ").append(where);
		}
		// 查询数据.
		List resultList = new ArrayList();
		if (fixedOption.length() > 1) {
			JSONArray fixedOpt = JSONArray.fromObject(fixedOption);
			for (int i = 0; i < fixedOpt.size(); i++) {
				JSONObject opt = fixedOpt.getJSONObject(i);
				HashMap map = new HashMap();
				map.put("optionValue", opt.getString("optionValue"));
				map.put("optionText", opt.getString("optionText"));
				resultList.add(map);

			}
		}
		String sql =hqlsb.toString();
		resultList.addAll(bs.queryForList("select_sysTemp",sql ));

		boolean result = super.start(writer);
		try {
			// 如果为只输出模式
			if ("true".equalsIgnoreCase(outPrint)) {
				sb = getOutPrintStr(resultList);
			} else {

				// 根据类型,形成对应HTML.
				/**
				 * select类型.
				 */
				if ("select".equalsIgnoreCase(this.type)) {
					sb = getSelectHtml(resultList);
				}
				/**
				 * checkBox类型.
				 */
				else if ("checkbox".equalsIgnoreCase(this.type)) {
					sb = getCheckBoxHtml(resultList);
				}
				/**
				 * radio类型.
				 */
				else if ("radio".equalsIgnoreCase(this.type)) {
					sb = getRadioHtml(resultList);
				}
			}
			writer.write(sb.toString());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * 返回select样式代码.
	 * 
	 * @return html字符串.
	 */
	private StringBuffer getSelectHtml(List list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("<select  name='").append(this.name).append("' class='")
				.append(this.className).append("'");
		// 如果DISABLED 将控件DISABLED掉.
		if (disabled.length() > 0) {
			sb.append("disabled='").append(this.disabled).append("' ");
		}
		sb.append(this.func).append(">");
		// 循环对象.
		for (Object obj : list) {
			HashMap map = (HashMap) obj;
			Object[] resObje = map.values().toArray();
			// 创建OPTION.
			sb.append("<option value='").append(
					resObje[0].toString().replaceAll("<", "&lt;")).append("'");
			// 循环默认选中.
			for (String sel : defValues) {
				// 如果默认选中 ,select.
				if (sel.equalsIgnoreCase((String) resObje[0])) {
					sb.append("selected='true'");
				}
			}

			sb.append(" >").append(
					resObje[1].toString().replaceAll("<", "&lt;")).append(
					"</option>");

		}
		sb.append("</select>");
		return sb;
	}

	/**
	 * 返回checkbox样式代码.
	 * 
	 * @return html字符串.
	 * 
	 * @throws Exception
	 *             异常.
	 */
	private StringBuffer getCheckBoxHtml(List list) throws Exception {

		StringBuffer sb = new StringBuffer();
		for (Object obj : list) {
			Object[] resObje = (Object[]) obj;
			// 创建checkbox
			sb.append("<input type='checkbox'  name='").append(this.name)
					.append("' class='").append(this.className).append("'");
			sb.append("value='").append(resObje[0]).append("' ");
			// 如果DISABLED 将控件DISABLED掉.
			if (disabled.length() > 0) {
				sb.append("disabled='").append(this.disabled).append("' ");
			}
			// 循环默认选中.
			for (String sel : defValues) {
				// 如果默认选中 ,checked.
				if (sel.equalsIgnoreCase((String) resObje[0])) {
					sb.append("checked='true'");
				}
			}

			sb.append(this.func).append(">");

			sb.append(resObje[1].toString().replaceAll("<", "&lt;"));

		}

		return sb;
	}

	/**
	 * 返回radio样式代码.
	 * 
	 * @return html字符串.
	 */
	private StringBuffer getRadioHtml(List list) {

		StringBuffer sb = new StringBuffer();
		for (Object obj : list) {
			Object[] resObje = (Object[]) obj;
			// 创建radio.
			sb.append("<input type='radio'  name='").append(this.name).append(
					"' class='").append(this.className).append("'");
			// 如果DISABLED 将控件DISABLED掉.
			if (disabled.length() > 0) {
				sb.append("disabled='").append(this.disabled).append("' ");
			}

			// 循环默认选中.
			for (String sel : defValues) {
				// 如果默认选中 ,checked.
				if (sel.equalsIgnoreCase((String) resObje[0])) {
					sb.append("checked='true'");
				}
			}

			sb.append(this.func).append(">");

			sb.append(resObje[1].toString().replaceAll("<", "&lt;"));

		}

		return sb;
	}

	/**
	 * 如果只为输出,则直接返回第一个符合条件的值.
	 * 
	 * @return
	 */
	private StringBuffer getOutPrintStr(List list) {
		StringBuffer sb = new StringBuffer();
		for (Object obj : list) {
			HashMap resObjeTemp =  (HashMap) obj;
			Object[] resObje = resObjeTemp.values().toArray();
			if (defValues.length > 0) {
				// 循环判断是否为默认选中.
				for (String sel : defValues) {
					// 如果默认选中 ,将值串接.
					if (sel.equalsIgnoreCase((String) resObje[0])) {
						sb.append(resObje[1].toString().replaceAll("<",
												"&lt;")).append(",");
					}
				}
			} else {

					sb.append(resObje[1].toString().replaceAll("<", "&lt;"))
							.append(",");

			}

		}
		// 将最后一个,去除.
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb;
	}

}
