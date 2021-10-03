package com.ufo.io;

import com.ufo.regex.UsefulPattens;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 *
 * @author: UFO
 * @date: 2021/7/3
 */
public class ReadStreamIntoString {
    public static void main(String[] args) throws FileNotFoundException {
        ReadStreamIntoString readStreamIntoString = new ReadStreamIntoString();

        readStreamIntoString.readFile(new Scanner(System.in).next());
    }

    public String readFile(String filePath) throws FileNotFoundException {
        String bytesInStr = new String();
        try(BufferedInputStream b_in = new BufferedInputStream(new FileInputStream(filePath))){//默认8k缓冲
            bytesInStr = readInByteStr(b_in);
            System.out.println(bytesInStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytesInStr;
    }

    public byte[] readInByteArr(InputStream in) throws IOException{
        int bytesRead = 0;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        while ((bytesRead = in.read(buffer)) != -1) {
            byteStream.write(buffer,0,bytesRead);
        }
        return byteStream.toByteArray();
    }

    public byte[] readInByteArrNoBuffer(InputStream in) throws IOException{
        int bytesRead = 0;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        while ((bytesRead = in.read()) != -1) {
            byteStream.write(bytesRead);
        }
        return byteStream.toByteArray();
    }

    public String readInByteStr(InputStream in) throws IOException{
        int bytesRead = 0;
        StringBuffer buf = new StringBuffer();
        while ((bytesRead = in.read()) != -1) {
            buf.append(Integer.toBinaryString(bytesRead));
        }
        return buf.toString();
    }

    public String readByBase64Decoder(){
        return new String();
    }

    public String fileNameOut(String filePath){
        String pathWhole=regexpFind(UsefulPattens.DIR_NAME_IN_PATH,filePath);
        System.out.println(pathWhole);
        String fileNameWhole=regexpFind(UsefulPattens.FILE_NAME_IN_PATH,filePath);
        System.out.println(fileNameWhole);

        int idx = fileNameWhole.lastIndexOf(".");
        String fileName = fileNameWhole.substring(0,fileNameWhole.length()-idx);
        String type = fileNameWhole.substring(idx);

        return fileName+"(1)"+type;
    }

    public String regexpFind(Pattern pattern, String str){
        Matcher matcher = pattern.matcher(str);
        String matchStr=null;
        if(matcher.find()){
            matchStr=matcher.group();
        }
        return matchStr;
    }
}
