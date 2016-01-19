package com.wenbo.util;
/**
 * 从Android源码拿过来的
 * @Description 
 * @author <a href="http://my.oschina.net/chenbo">chenbo</a>
 * @date 2016年1月19日 下午5:22:24
 * @version V1.0
 */
public class TextUtils {
	/**
	 * 判断一个字符串是否为空或者长度为0
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }

}
