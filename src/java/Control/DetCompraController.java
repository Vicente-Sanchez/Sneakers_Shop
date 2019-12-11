package Control;

import Control.util.JsfUtil;
import Control.util.PaginationHelper;
import java.io.IOException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

@Named("detCompraController")
@SessionScoped
public class DetCompraController implements Serializable {

    private HttpServletRequest httpservlet;
    @EJB
    private Control.VentasFacade ejbventafacade;
    @EJB
    private Control.DetVentaFacade ejbdetventafacade;
    private DetCompra current;
    private DataModel items = null;
    @EJB
    private Control.DetCompraFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public DetCompraController() {
    }

    public DetCompra getSelected() {
        if (current == null) {
            current = new DetCompra();
            selectedItemIndex = -1;
        }
        return current;
    }

    public DetVentaFacade getEjbdetventafacade() {
        if (ejbdetventafacade == null) {
            ejbdetventafacade = new DetVentaFacade();
        }
        return ejbdetventafacade;
    }

    public void setEjbdetventafacade(DetVentaFacade ejbdetventafacade) {
        this.ejbdetventafacade = ejbdetventafacade;
    }

    public VentasFacade getEjbventafacade() {
        if (ejbventafacade == null) {
            ejbventafacade = new VentasFacade();
        }
        return ejbventafacade;
    }

    public void setEjbventafacade(VentasFacade ejbventafacade) {
        this.ejbventafacade = ejbventafacade;
    }

    public DetCompraFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(DetCompraFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    private DetCompraFacade getFacade() {
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
        return "List";
    }

    public void addCar(Producto prod) throws IOException {
        String message = "Inicia Sesión";
        Ventas ventasuser = null;
        if (verificaSesionynivel()) {
            httpservlet = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            Usuario usu = (Usuario) httpservlet.getSession().getAttribute("usu1");
            ventasuser = getVentaAct(usu);

            if (ventasuser == null) {
                ventasuser = new Ventas();
                ventasuser.setFecha(new Date());
                ventasuser.setIdCliente(usu.getId());
                ventasuser.setIdTipodepago(null);
                ventasuser.setIva(0);
                ventasuser.setStatus(0);
                ventasuser.setSubtotal(0);
                ventasuser.setTotal(0);
                getEjbventafacade().create(ventasuser);
                ventasuser = getVentaAct(usu);
            }

            DetVenta detventa = new DetVenta();
            detventa.setIdProd(prod);
            detventa.setIdVenta(ventasuser);
            detventa.setPrecioCompra((float) (prod.getPrecioProd()*.70));
            detventa.setPrecioVenta(prod.getPrecioProd());
            detventa.setStatus(1);
            detventa.setCantidad(1);
            
            getEjbdetventafacade().create(detventa);
            ventasuser.setIva((float) (prod.getPrecioProd()*.16));
            ventasuser.setSubtotal((float)(prod.getPrecioProd()*.84));
            ventasuser.setTotal(prod.getPrecioProd());
            ejbventafacade.edit(ventasuser);
            message="Producto Agregado al Carrito";
            JsfUtil.addSuccessMessage(message);
            
        } else {
            JsfUtil.addSuccessMessage(message);
        }
    }

    public Ventas getVentaAct(Usuario usu) {
        List<Ventas> list = getEjbventafacade().findAll();
        Ventas ventasuser = null;
        for (Ventas venta : list) {
            if (venta.getStatus() == 0 && venta.getIdCliente() == usu.getId()) {
                ventasuser = venta;
                break;
            }
        }
        return ventasuser;
    }

    public boolean verificaSesionynivel() throws IOException {

        httpservlet = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Usuario usu = (Usuario) httpservlet.getSession().getAttribute("usu1");
        if (usu != null) {

            return true;
        } else {
            return false;
        }
    }

    public String prepareView() {
        current = (DetCompra) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new DetCompra();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetCompraCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (DetCompra) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetCompraUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (DetCompra) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetCompraDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
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

    public DataModel getItems() {
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
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public DetCompra getDetCompra(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = DetCompra.class)
    public static class DetCompraControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetCompraController controller = (DetCompraController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detCompraController");
            return controller.getDetCompra(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof DetCompra) {
                DetCompra o = (DetCompra) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + DetCompra.class.getName());
            }
        }

    }

}
