package Control;

import Control.util.JsfUtil;
import Control.util.PaginationHelper;
import java.io.IOException;
import java.io.InputStream;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.primefaces.model.UploadedFile;

@Named("imagenesController")
@SessionScoped
public class ImagenesController implements Serializable {

    private Imagenes current;
    private DataModel items = null;
    @EJB
    private Control.ImagenesFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private UploadedFile file;
    Calendar calendario = new GregorianCalendar();
    int hora, minutos, segundos;

    public ImagenesController() {
    }

    /**
     * @return the file
     */
    public UploadedFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Imagenes getSelected() {
        if (current == null) {
            current = new Imagenes();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ImagenesFacade getFacade() {
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

    public String prepareView() {
        current = (Imagenes) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Imagenes();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() throws IOException {
        //try {
        String ruta = "C:/Users/VSN_b/OneDrive/Documentos/SneakersShop/web/img";
        /* hora = calendario.get(Calendar.HOUR_OF_DAY);
            minutos = calendario.get(Calendar.MINUTE);
            segundos = calendario.get(Calendar.SECOND);*/
        current.setRuta("/img/" + file.getFileName());
       // System.out.println(current.getRuta());
        InputStream input = file.getInputstream();
        Path folder = Paths.get(ruta);
        Path fileToCreatePath = folder.resolve(file.getFileName());
        Path newFilePath = Files.createFile(fileToCreatePath);
        System.out.println("ruta:*****************\n"+current.getRuta());
        Files.copy(input, newFilePath, StandardCopyOption.REPLACE_EXISTING);

        /*  FacesMessage mensaje = new FacesMessage("El archivo " + file.getFileName() + " se guardo correctamente.");
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
         */
        getFacade().create(current);
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ImagenesCreated"));
        return prepareCreate();
        // } catch (Exception e) {
        /* FacesMessage mensaje = new FacesMessage("ERROR AL SUBIR El archivo " + file.getFileName());
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
         *//*
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }*/
    }

    public String prepareEdit() {
        current = (Imagenes) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            if (file != null) {
                String ruta = "C:/Users/VSN_b/OneDrive/Documentos/SneakersShop/web/img";
                /*  hora = calendario.get(Calendar.HOUR_OF_DAY);
                minutos = calendario.get(Calendar.MINUTE);
                segundos = calendario.get(Calendar.SECOND);*/
                current.setRuta("/img/" + file.getFileName());

                InputStream input = file.getInputstream();
                Path folder = Paths.get(ruta);
                Path fileToCreatePath = folder.resolve(file.getFileName());
                Path newFilePath = Files.createFile(fileToCreatePath);

                Files.copy(input, newFilePath, StandardCopyOption.REPLACE_EXISTING);

            }

            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ImagenesUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Imagenes) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ImagenesDeleted"));
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

    public Imagenes getImagenes(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Imagenes.class)
    public static class ImagenesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ImagenesController controller = (ImagenesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "imagenesController");
            return controller.getImagenes(getKey(value));
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
            if (object instanceof Imagenes) {
                Imagenes o = (Imagenes) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Imagenes.class.getName());
            }
        }

    }

}
