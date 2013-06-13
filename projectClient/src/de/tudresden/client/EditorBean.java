package de.tudresden.client;
import javax.faces.bean.ManagedBean;
 
@ManagedBean(name = "editor")
public class EditorBean {
 
	private String value = "This editor is provided by PrimeFaces";
 
	public String getValue() {
		return value;
	}
 
	public void setValue(String value) {
		System.out.println("Editor Bean");
		this.value = value;
	}
}