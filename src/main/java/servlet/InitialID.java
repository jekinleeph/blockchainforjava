package servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.UUID;

/**
 * @Author: cfx
 * @Description: 初始化时，使用UUID来作为节点ID
 * @Date: Created in 2018/5/9 17:17
 */
@WebListener
public class InitialID implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        servletContext.setAttribute("uuid", uuid);
        System.out.println("uuid is : "+servletContext.getAttribute("uuid"));
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}

