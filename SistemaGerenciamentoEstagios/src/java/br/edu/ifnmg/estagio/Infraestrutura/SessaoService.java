package br.edu.ifnmg.estagio.Infraestrutura;

import java.io.Serializable;
import java.util.HashMap;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SessionScoped
public class SessaoService implements Serializable {

    public void put(String key, String value) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Cookie ck = new Cookie(key, value);
        ck.setMaxAge(-1);
        ((HttpServletResponse) ctx.getExternalContext().getResponse()).addCookie(ck);

    }

    HashMap<String, Cookie> cookies = new HashMap<>();

    private Cookie getCookie(String key) {

        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
        Cookie[] tmp = request.getCookies();

        for (Cookie cookie : tmp) {
            System.err.println("name ck: " + cookie.getName());
            cookies.put(cookie.getName(), cookie);

        }

        if (cookies.containsKey(key)) {
            return cookies.get(key);
        } else {
            return null;
        }
    }

    public String get(String key) {
        Cookie tmp = getCookie(key);

        if (tmp != null) {
            return tmp.getValue();
        } else {
            return null;
        }
    }

    public void delete(String key) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();

        Cookie cookie = getCookie(key);
        cookie.setPath(request.getContextPath());
        cookie.setDomain(request.getServerName());

        if (cookie != null) {

            cookie.setMaxAge(0);
            cookie.setValue(null);
            ((HttpServletResponse) ctx.getExternalContext().getResponse()).addCookie(cookie);

        }

    }

}
