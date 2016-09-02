package cn.cinema.manage.action.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.cinema.manage.entity.sys.T0003_Ad_Competence;
import cn.cinema.manage.util.SessionUtil;


/**
 * 权限过滤.
 * 
 * @author chenbl.
 * 
 */
public class AuthFilter implements Filter
{
    /**
     * filter 配置.
     */
    private FilterConfig filterConfig;
    /**
     * 不验证登录
     */
    private String[] noFilteList;
    /**
     * 不验证权限.
     */
    private String[] noAuthList;
    /**
     * 日志类.
     */
    private Logger logger = Logger.getLogger(AuthFilter.class);


    public void destroy()
    {

    }


    /**
     * 1,doFilter方法的第一个参数为ServletRequest对象。此对象给过滤器提供了对进入的信息（包括
     * 表单数据、cookie和HTTP请求头）的完全访问。第二个参数为ServletResponse，通常在简单的过
     * 滤器中忽略此参数。最后一个参数为FilterChain，此参数用来调用servlet或JSP页。
     */
    public void doFilter(ServletRequest servletRequest,
        ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException
    {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        /**
         * 如果处理HTTP请求，并且需要访问诸如getHeader或getCookies等在ServletRequest中
         * 无法得到的方法，就要把此request对象构造成HttpServletRequest
         */
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String currentURL = request.getRequestURI(); // 取得根目录所对应的绝对路径:

        String targetURL = currentURL.substring(currentURL.lastIndexOf("/")+1); // 截取到当前文件名用于比较

        HttpSession session = request.getSession(false);

        if (equalsUrl(targetURL,noFilteList))
        {
            // 判断当前页是否是重定向以后的登录页面，如果是就不做session的判断，防止出现死循环
            if (session == null || session.getAttribute("users") == null)
            {
                // *用户登录以后需手动添加session
                logger.info("被隔离1:" + targetURL);
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                // 如果session为空表示用户没有登录就重定向到login.jsp页面
                return;
            }
            // 权限验证.
            if(equalsUrl(targetURL,noAuthList)){
                if (valAuth(targetURL, SessionUtil.getMenu(session)))
                {
                    // *用户登录以后需手动添加session
                    logger.error("非法访问:" + targetURL);
                    SessionUtil.setError(session, "请不要非法访问" + targetURL);
                    response.sendRedirect(request.getContextPath() + "/error.jsp");
                    // 如果session为空表示用户没有登录就重定向到login.jsp页面
                    return;
                }
            }

        }
        // 加入filter链继续向下执行
        /**
         * 调用FilterChain对象的doFilter方法。Filter接口的doFilter方法取一个FilterChain对象作 为它
         * 的一个参数。在调用此对象的doFilter方法时，激活下一个相关的过滤器。如果没有另
         * 一个过滤器与servlet或JSP页面关联，则servlet或JSP页面被激活。
         */
        filterChain.doFilter(request, response);

    }


    /**
     * filter初始化.
     */
    public void init(FilterConfig filterConfig) throws ServletException
    {
        this.filterConfig = filterConfig;
        // 以下从配置文件获取配置信
        String nofilterPages = filterConfig.getInitParameter("noFilteList");
        String noAuthPages =filterConfig.getInitParameter("noAuthList");
        logger.info("不需要过滤登录页面为:" + nofilterPages);
        logger.info("不需要过滤权限为:"+noAuthPages);
        noFilteList = nofilterPages.split(";");
        noAuthList = noAuthPages.split(";");
     

    }


    /**
     * 比较URL 是否在需要验证的范围内.
     * 
     * @param url
     *            URL;
     * @param nofilterList 不过滤菜单.
     * @return 是否需要验证 需要验证 true 不需要验证 false;
     */
    private Boolean equalsUrl(String url ,String[] nofilterList)
    {
        if(url.length() == 0){
            return false;
        }
        String suffName=url.substring(url.lastIndexOf(".")+1);
       //不过滤png.jpg
        if(suffName.equalsIgnoreCase("png")||suffName.equalsIgnoreCase("jpg")||suffName.equalsIgnoreCase("gif")||suffName.equalsIgnoreCase("flv")||suffName.equalsIgnoreCase("mp4")){
           return false;
       }
        if(suffName.equalsIgnoreCase("js"))
        {
            return false;
        }
        
      // logger.info("过滤页面:" + url);
        // 判断是否在
        for (String nofilterPage : nofilterList)
        {
            if (nofilterPage.equals(url))
            {
                return false;
            }
        }
        return true;
    }


    /**
     * 验证是否是非法访问
     * 
     * @param pageName
     * @param moduList
     * @return 是否非法访问, true 是, false 不是;
     */
    private Boolean valAuth(String pageName, List<T0003_Ad_Competence> moduList)
    {
        return false;
//        /**
//         *  循环判断是否有权限.
//         */
//        for (T0101Module module : moduList)
//        {
//            if (pageName.indexOf(module.getUrl()) != -1)
//            {
//                return false;
//            }
//
//        }
//
//        return true;
    }
}
