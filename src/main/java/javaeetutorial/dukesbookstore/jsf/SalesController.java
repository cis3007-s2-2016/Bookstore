package javaeetutorial.dukesbookstore.jsf;
/**
 *
 * @author amanda hugnkiss
 * 
 * TODO change the bookstore.template javascript for links
 * 
 */
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;
import javaeetutorial.dukesbookstore.entity.Member;
import javaeetutorial.dukesbookstore.entity.SaleUsed;
import javaeetutorial.dukesbookstore.session.SaleFacade;
import javaeetutorial.dukesbookstore.util.JsfUtil;
import javaeetutorial.dukesbookstore.util.PaginationHelper;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

        
@Named//("salesController")
@SessionScoped
public class SalesController implements Serializable {

    private SaleUsed current;
    private DataModel items = null;    
    @EJB
    private SaleFacade ejbFacade;    
    private PaginationHelper pagination;    
    private int selectedItemIndex;    
    private Date date = new Date();    
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("javaeetutorial.dukesbookstore.web.messages.Messages");
    private HttpSession session;
    private EntityManager em;
    
    public SalesController() {
    }
    
    public SaleUsed getSelected() { // retrieves each field from form
        if (current == null) {
            current = new SaleUsed();
            selectedItemIndex = -1;
        }
        return current;
    }

    private SaleFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "salesList";
    }

    public SaleUsed getCurrent() {
        return current;
    }

    public void setCurrent(SaleUsed current) {
        this.current = current;
    }

    public SaleFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(SaleFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public void setResourceBundle(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public String prepareView() {
        current = (SaleUsed) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "salesView";
    }

    public String prepareCreateFixed() { //after written to database before new add sale page displayed
        current = new SaleUsed();
        selectedItemIndex = -1;
        return "salesCreateFixed";
    }

    public String prepareCreateAuction() {
        current = new SaleUsed();
        selectedItemIndex = -1;
        return "salesCreateAuction";
    }

    public String createFixed() {
        
        current.setSaletype("Fixed Sale");
        current.setDatelisted(new Timestamp(date.getTime()));
        String successMessage= (resourceBundle.getString("SalesCreated"));
        successMessage = (current.toString()) + " " + successMessage;
        try {
            getFacade().create(current); //this is where data is written to the table
            JsfUtil.addSuccessMessage(successMessage);
            return prepareCreateFixed(); //current destroyed and re-created
        } catch (Exception e) { 
            JsfUtil.addErrorMessage(e, resourceBundle.getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    public String createAuction() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        this.setSession(request.getSession());
        Member member = new Member();           
        member = (Member) session.getAttribute("user");  
        
        current.setSelleridId(member);        
        current.setSaletype("Auction");
        current.setDatelisted(new Timestamp(date.getTime())); 
        current.setSaleprice(BigDecimal.ZERO);
        current.setCommission(BigDecimal.TEN);
        current.setAmount(BigDecimal.ZERO);
        current.setPaid(false);
        
        String successMessage= (resourceBundle.getString("AuctionCreated"));
        successMessage = (current.toString()) + " " + successMessage;
        try {
//            SaleFacade test = new SaleFacade();
//            test.create(current);
            getFacade().create(current); //this is where data is written to the table
            JsfUtil.addSuccessMessage(successMessage);            
            return prepareCreateAuction(); //current destroyed and re-created
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, resourceBundle.getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(resourceBundle.getString("SalesUpdated"));
            return "salesView";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, resourceBundle.getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public DataModel getItems() { // upon display of list.xhtml, CreateFixed.xhtml, CreateAuction.xhtml
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "salesList";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "salesList";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public SaleUsed getSales(java.lang.Long id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = SaleUsed.class)
    public static class SalesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            
            FacesContext context = FacesContext.getCurrentInstance();
                SalesController controller = (SalesController)context.getApplication()
                        .evaluateExpressionGet(context, "#{salesController}", SalesController.class);                
                
//            SalesController controller = (SalesController) facesContext.getApplication().getELResolver().
//                    getValue(facesContext.getELContext(), null, "salesController");
            return controller.getSales(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof SaleUsed) {
                SaleUsed o = (SaleUsed) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + SaleUsed.class.getName());
            }
        }

    }

    public String prepareEdit() {
        current = (SaleUsed) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "salesEdit";
    }
   
    public String destroy() {
        current = (SaleUsed) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "salesList";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "salesView";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "salesList";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("resources/Bundle").getString("SalesDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("resources/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

}
