package library.managedbeans;

//Source: https://www.tutorialspoint.com/jsf/jsf_page_navigation.htm

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name = "navigationController", eager = true)
@Stateless
public class NavigationController implements Serializable {

	
    private static final long serialVersionUID = -652163167414303398L;
    
	@ManagedProperty(value = "#{param.pageId}")
	private String pageId;
	
	@ManagedProperty(value="#{UserSession}")
    private UserSession userSession;

	
	public String showPage() {
		if (pageId == null)
			return "inside";
		else {
			if(pageId.equals("index"))
				userSession.reset();
			return pageId + "?faces-redirect=true";
		}
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

}