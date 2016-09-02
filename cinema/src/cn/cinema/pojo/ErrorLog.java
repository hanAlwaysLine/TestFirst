/**
 * 
 */
package cn.cinema.pojo;

/**
 * TODO
 * 
 * @ClassName ErrorLog
 * @Description TODO
 * @author 汤凤欣
 * @date 2012-12-25
 */
public class ErrorLog {
	private String id;
	private String name;
	private String time;
	private String exception;
	private String result;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
}
