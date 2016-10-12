package filters;

import com.sun.deploy.net.HttpResponse;
import javaeetutorial.dukesbookstore.web.managedbeans.MemberSessionBean;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;

/**
 * Created by matt on 11/10/2016.
 */
@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {
    @Inject
    private MemberSessionBean sessionBean;

    private String[][] protectedUrls =  {
            {"/admin/(.*)", "admin"},
            {"/rest/(.*)", "admin,customer"},
            {"/checkout\\.xhtml", "customer"}
    };


    private String[][] getProtectedUrls() {
        return protectedUrls;
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        try {
            URL url = new URL(((HttpServletRequest) req).getRequestURL().toString());
            String path = url.getPath().substring(url.getPath().indexOf("/") + 1);
            path = path.substring(path.indexOf("/"));

            for (int i = 0; i < this.getProtectedUrls().length; i++) {
                if (path.matches(this.getProtectedUrls()[i][0])) {
                    System.out.println("User requesting secure resources");
                    if (this.sessionBean.user() == null) {
                        if (resp instanceof HttpServletResponse) {
                            req.getRequestDispatcher("/login.xhtml").forward(req, resp);
                        }
                    } else {
                        boolean permitted = false;
                        String allowedGroups[] = this.getProtectedUrls()[i][1].split(",");
                        for (String allowedGroup : allowedGroups) {
                            System.out.println(allowedGroup);
                            if (this.sessionBean.hasPermissionGroup(allowedGroup)) {
                                System.out.println("User permitted to access resource");
                                permitted = true;
                                break;
                            }
                        }

                        if (permitted){
                            chain.doFilter(req, resp);
                        } else {
                            ((HttpServletResponse) resp).sendError(403);
                        }
                    }
                }
            }
            chain.doFilter(req, resp);
        } catch (Exception e){
            ((HttpServletResponse) resp).sendError(500);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
