package cn.cinema.manage.util;
/**
 *  会话工具类
 * @author chenbl
 *
 */

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.cinema.manage.entity.sys.T0001_Ad_User;
import cn.cinema.manage.entity.sys.T0003_Ad_Competence;
import cn.cinema.manage.entity.sys.T1001_Menu;



/**
 * Session 存取数据公共类.
 * 
 * @author jiyk.
 * 
 */
public class SessionUtil
{
    /**
     * 雇员用户键.
     */
    private final static String employer = "users";
    private final static String menu = "menu";
    /**
     * 用户拥有的一级菜单权限.
     */
    private final static String firstMenu = "firstMenu";
    /**
     * 用户拥有的二级菜单权限.
     */
    private final static String secMenu = "secMenu";
    /**
     * 功能按钮列表.
     */
    private final static String buttonList = "buttonList";
    /**
     * 错误信息.
     */
    private final static String errorInfo="errorInfo";
    /**
     * 分页对象.
     */
    private final static String pageListFlag="pageList";
    /**
     * 从seession中获得用户信息对象.
     * 
     * @param session
     *            httpSession.
     * @return 用户对象.
     */
    public static T0001_Ad_User getEmp(HttpSession session)
    {
        return (T0001_Ad_User) session.getAttribute(employer);
    }


    /**
     * 向session中放入用户对象.
     * 
     * @param session
     *            httpSession.
     * @param emp
     *            用户对象.
     */
    public static void setEmp(HttpSession session, T0001_Ad_User users)
    {
        session.setAttribute(employer, users);
    }


    /**
     * 将一级菜单放入session 中.
     * 
     * @param session
     *            httpsession.
     * @param menulist
     *            一级级菜单list.
     */
    public static void setFirstMenu(HttpSession session,
        List<T1001_Menu> menulist)
    {

        session.setAttribute(firstMenu, menulist);
    }


    /**
     * 从session中获得一级菜单.
     * 
     * @param session
     *            httpSession.
     * @return 一级菜单list.
     */
    public static List<T1001_Menu> getFirstMenu(HttpSession session)
    {

        return (List<T1001_Menu>) session.getAttribute(firstMenu);
    }


    /**
     * 将二级菜单放入session 中.
     * 
     * @param session
     *            httpsession.
     * @param menulist
     *            二级菜单list.
     */
    public static void setSecMenu(HttpSession session,
        List<T1001_Menu> menulist)
    {
        List<T1001_Menu> secMenuList=null;
        //如果Session中没有数据.
        if(session.getAttribute(secMenu)== null){
            secMenuList=new ArrayList<T1001_Menu>();
        }else{
            //如果有,直接获取数据.
            secMenuList=(List<T1001_Menu>)session.getAttribute(secMenu);
        }
        secMenuList.addAll(menulist);
        session.setAttribute(secMenu, secMenuList);
    }


    /**
     * 从session中获得二级菜单.
     * 
     * @param session
     *            httpSession.
     * @return 二级菜单list.
     */
    public static List<T1001_Menu> getSecMenu(HttpSession session)
    {

        return (List<T1001_Menu>) session.getAttribute(secMenu);
    }


    /**
     * 将错误信息放入session 中.
     * 
     * @param session
     *            httpsession.
     * @param errorinfo
     *            错误信息.
     */
    public static void setError(HttpSession session,
        String errorinfo)
    {

        session.setAttribute(errorInfo, errorinfo);
    }


    /**
     * 从session中获得将功能按钮.
     * 
     * @param session
     *            httpSession.
     * @return 将功能按钮list.
     */
    public static String getError(HttpSession session)
    {

        return (String) session.getAttribute(errorInfo);
    }
   
    /**
     * 将所有目录放入session.
     * 
     * @param session
     *            httpSession.
     * @return 所有目录list.
     */
    public static void setMenu(HttpSession session,
            List<T0003_Ad_Competence> menulist)
        {

            session.setAttribute(menu, menulist);
        }


        /**
         * 从session中获得所有目录.
         * 
         * @param session
         *            httpSession.
         * @return 所有目录list.
         */
        public static List<T0003_Ad_Competence> getMenu(HttpSession session)
        {

            return (List<T0003_Ad_Competence>) session.getAttribute(menu);
        }
        /**
         * 将分页放入request中.
         * @param req HttpServletRequest.
         * @param pageList 分页对象.
         */
        public static void setPageList(HttpServletRequest req,PageList pageList){
            
            req.setAttribute(pageListFlag, pageList);
        }
        /**
         * 从request中获取分页对象.
         * @param req HttpServletRequest.
         * @return PageList  分页对象.
         */
        public static PageList getPageList(HttpServletRequest req){
            
            return (PageList)req.getAttribute(pageListFlag);
        }

}

