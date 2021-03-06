package com.ufo.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * 一些有用的正则表达式
 * @author: UFO
 * @date: 2021/7/4
 */
public class UsefulPattens {

    public static Pattern WEB_PROTOCOL_IN_PATH = Pattern.compile(".+(?=://)");//向前匹配,捕捉网址开头的协议
    public static Pattern DIR_NAME_IN_PATH = Pattern.compile(".+(?:\\\\|/)");//向前匹配,捕捉文件夹的路径,不包含文件名
    public static Pattern FILE_NAME_IN_PATH = Pattern.compile("[^\\\\/]+[.]?\\w+$");//捕捉文件路径结尾的文件名

    public static Pattern FILE_NAME_IN_PATH_NO_TYPE = Pattern.compile("[^\\\\/]+(?=\\.\\w+$)");//捕捉文件路径结尾的文件名,没有扩展名

    public static Pattern SAFE_MASK_IDCARD=Pattern.compile("(\\d{6})\\d*(\\w{4})");
    public static Pattern SAFE_MASK_TELPHONE=Pattern.compile("(\\d{3})\\d*(\\d{4})");

    public static void main(String[] args){
        Matcher m = SAFE_MASK_IDCARD.matcher("32010220010101123X");
//        m = SAFE_MASK_IDCARD.matcher("320102700101123");
        String s = m.replaceAll("$1********$2");
        System.out.printf("SAFE_MASK_IDCARD:%s",s);
        System.out.print("\n");
        Matcher m2 = SAFE_MASK_TELPHONE.matcher("11045641345");
        String s2 = m2.replaceAll("$1****$2");
        System.out.printf("SAFE_MASK_TELPHONE:%s",s2);
    }

    public static void testFileMasks(){
        Matcher matcher = FILE_NAME_IN_PATH.matcher("/doc/profile.d/file.s.x");
        System.out.println(matcher.find()+":"+matcher.group());
        Matcher matcher2 = FILE_NAME_IN_PATH_NO_TYPE.matcher("/doc/profile.d/file.s.jpg");
        System.out.println(matcher2.find()+":"+matcher2.group());

        //一个正则分组替换的例子
        Pattern r = Pattern.compile("\\b(\\w[-.\\w]*@[-\\w]+(\\.[\\w]+)*\\.(com|edu|info))\\b",
                Pattern.CASE_INSENSITIVE|Pattern.COMMENTS);
        Matcher m=r.matcher("mailhost@amail.com");
        System.out.println(m.find());
        String text=m.replaceAll("<a href=\"mailto:$1\">$1</a>");
        System.out.println(text);
        //简写形式也支持分组
        System.out.println("mailhost@amail.com".replaceAll("\\b(\\w[-.\\w]*@[-\\w]+(\\.[\\w]+)*\\.(com|edu|info))\\b","<a href=\"mailto:$1\">$1</a>"));
    }
}
