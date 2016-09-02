package cn.cinema.manage.util;

/**
 * 其他的配置文件
**/
public class OtherConfig
{
    /** */
    public OtherConfig (){}

    /** */
    public static String  getkeyvalue(String key)
    {
      return ConfigFile.getInstance().getkeyvalue(key);
    }

}
