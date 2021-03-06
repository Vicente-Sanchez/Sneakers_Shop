/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;


import Control.Usuario;
import Control.UsuarioFacade;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author mteluis
 */
@Named(value = "login")
@SessionScoped
public class Login implements Serializable {
    
    private String usu;
    private String pasword;
    
    private HttpServletRequest httpservlet;
    
    @EJB
    private UsuarioFacade usufacade;
    
    private Usuario usuautenticado;

    public Usuario getUsuautenticado() {
        return usuautenticado;
    }

    public void setUsuautenticado(Usuario usuautenticado) {
        this.usuautenticado = usuautenticado;
    }
    
   
    /**
     * Creates a new instance of Login
     */
    public Login() {
        httpservlet = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    
    
    public void Acceso() throws IOException{
         
        httpservlet = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        usuautenticado = usufacade.Buscar(getUsu(), getPasword());
        if(usuautenticado!=null){
            httpservlet.getSession().setAttribute("usu", usuautenticado.getUsu());
            httpservlet.getSession().setAttribute("nombre", usuautenticado.getNombre());
            httpservlet.getSession().setAttribute("nivel_usu", usuautenticado.getNivelUsuario());
            httpservlet.getSession().setAttribute("usuario", usuautenticado);
            switch(usuautenticado.getNivelUsuario()){
                case 1:
                    FacesContext.getCurrentInstance().getExternalContext().redirect("plantilla_admin.xhtml");
                    break;
                case 2:
                    FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                    break;
                default:
                    FacesContext.getCurrentInstance().getExternalContext().redirect("plantilla_cliente.xhtml");
                    break;
            }
            
            //return "Acceder";
        }else{
            System.out.println("no se encuentra de la base");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Usuario o password incorrecto",null));
        }
        
        //return null;
        
    }
    
    public void cerrarSesion(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("./templates/login.xhtml");
            
        } catch (Exception e) {
        }
    }
    
    public void verificaSesionynivel(int nivel) throws IOException{
        
        httpservlet = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Usuario usu = (Usuario) httpservlet.getSession().getAttribute("usuario");
        if(usu != null){
            if(usu.getNivelUsuario()== nivel){
            }else{
                FacesContext.getCurrentInstance().getExternalContext().redirect("plantilla_cliente.xhtml");
            }
            
        }else{
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("./templates/login.xhtml.xhtml");
        }
    }

    /**
     * @return the usu
     */
    public String getUsu() {
        return usu;
    }

    /**
     * @param usu the usu to set
     */
    public void setUsu(String usu) {
        this.usu = usu;
    }

    /**
     * @return the pasword
     */
    public String getPasword() {
        return pasword;
    }

    /**
     * @param pasword the pasword to set
     */
    public void setPasword(String pasword) {
        this.pasword = pasword;
    }
    
}
