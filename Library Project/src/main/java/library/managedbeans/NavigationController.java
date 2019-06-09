package library.managedbeans;

//Source: https://www.tutorialspoint.com/jsf/jsf_page_navigation.htm

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import library.businessobject.Reader;

@ManagedBean(name = "navigationController", eager = true)
public class NavigationController implements Serializable {


    private static final long serialVersionUID = -652163167414303398L;
	@ManagedProperty(value = "#{param.pageId}")
	private String pageId;
   
	@ManagedProperty(value = "#{param.objectId}")
	private String objectId;

	public String showPage() {
		if (pageId == null)
			return "index";
		if (objectId != null) 
			return pageId + "?faces-redirect=true&objectId=" + objectId;
		else
			return pageId + "?faces-redirect=true";
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	
	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
}