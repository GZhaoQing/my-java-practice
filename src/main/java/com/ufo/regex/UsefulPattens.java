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


    public static void main(String[] args){
        Matcher matcher = FILE_NAME_IN_PATH.matcher("/doc/profile.d/file");
        System.out.println(matcher.find()+":"+matcher.group());
    }
}