package Control;

import Control.util.JsfUtil;
import Control.util.PaginationHelper;

import java.io.Serializable;
import java.util.ArrayList;
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

@Named("ventasController")
@SessionScoped
public class VentasController implements Serializable {

    private Ventas current;
    private DataModel items = null;
    @EJB
    private Control.VentasFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    List<Ventas> ventasuser;

    public VentasController() {
    }

    public List<Ventas> getVentasuser() {
        HttpServletRequest httpservlet = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Usuario usu = (Usuario) httpservlet.getSession().getAttribute("usu1");
        if (ventasuser == null) {
            ventasuser = new ArrayList<Ventas>();
        }
        ventasuser.clear();
        if (usu != null) {
            List<Ventas> list = getFacade().findAll();
            for (Ventas venta : list) {
                if (venta.getIdCliente() == usu.getId()) {
                    ventasuser.add(venta);
                }
            }
        }

        return ventasuser;
    }

    public void eliminar(Ventas venta) {
        List<Ventas> list = getFacade().findAll();
        venta.setStatus(3);
        ejbFacade.edit(venta);
        for (Ventas venta1 : list) {
            if (venta1.getStatus() == 3) {
                getFacade().remove(venta1);
            }
        }
        JsfUtil.addSuccessMessage("Producto Eliminado");
    }

    public void setVentasuser(List<Ventas> ventasuser) {
        this.ventasuser = ventasuser;
    }

    public Ventas getSelected() {
        if (current == null) {
            current = new Ventas();
            selectedItemIndex = -1;
        }
        return current;
    }

    private VentasFacade getFacade() {
        return ejbFacade;
    }

    public void setStatus(Ventas venta, int status) {
        venta.setStatus(status);
        ejbFacade.edit(venta);
        JsfUtil.addSuccessMessage("Compra Realizada Correctamente");
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

    public String prepareView() {
        current = (Ventas) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Ventas();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("VentasCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Ventas) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("VentasUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Ventas) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("VentasDeleted"));
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

    public Ventas getVentas(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Ventas.class)
    public static class VentasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VentasController controller = (VentasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ventasController");
            return controller.getVentas(getKey(value));
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
            if (object instanceof Ventas) {
                Ventas o = (Ventas) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Ventas.class.getName());
            }
        }

    }

}
