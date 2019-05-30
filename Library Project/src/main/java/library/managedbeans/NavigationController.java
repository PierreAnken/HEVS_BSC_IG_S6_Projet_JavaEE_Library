package library.managedbeans;

//Source: https://www.tutorialspoint.com/jsf/jsf_page_navigation.htm

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "navigationController", eager = true)
@RequestScoped

public class NavigationController implements Serializable {

   @ManagedProperty(value = "#{param.pageId}")
   private String pageId;
   
   public String showPage() {
      if(pageId == null)
         return "index";
      
      return pageId+"?faces-redirect=true";
   }
   
	public String getPageId() {
		return pageId;
	}
	
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
}