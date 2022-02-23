package com.ufo.io;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class InterruptibleFileIO {

    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        String longText="A powerful programming language is more than just a means for instructing a computer to perform tasks. The language also serves as a framework within which we organize our ideas about processes. Thus, when we describe a language, we should pay particular attention to the means that the language provides for combining simple ideas to form more complex ideas.";
        String path = Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath();
        System.out.println(path);
        File file = new File(path+"text.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int wrote=writeApart(longText,file);
        continueWrite(longText, file, wrote);
    }
    //写入一部分，中途中断
    public static int writeApart(String longText, File file){
        byte[] b=new byte[64];
        int d,wrote=0;
        int start=0,end=0;
        InputStream stream = null;
        RandomAccessFile rf = null;
        try {
            stream = new ByteArrayInputStream(longText.getBytes(StandardCharsets.UTF_8));
            end = stream.available();//不同的stream方法会不一样
            System.out.println("available:"+end);
            double random = end*Math.random();//中断
            rf= new RandomAccessFile(file, "rw");
            while((d = stream.read(b))>0){
                if(wrote>random){
                    break;
                }
                rf.seek(wrote);
                rf.write(b,0,d);
                wrote+=d;
            }
            System.out.println("rf1:"+rf.length());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(stream!=null) stream.close();
                rf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("wrote1:"+wrote);
        return wrote;
    }
    //继续写入
    public static void continueWrite(String longText, File file, int wrote){
        byte[] b=new byte[64];
        int d;
//        long wrote=0;
        int start=0,end=0;
        InputStream stream = null;
        RandomAccessFile rf = null;
        try {
            rf = new RandomAccessFile(file, "rw");
//            long wrote2=rf.length();
//            System.out.println("wrote2:"+wrote2);
            stream = new ByteArrayInputStream(longText.getBytes(StandardCharsets.UTF_8));
            stream.skip(wrote);
            while((d = stream.read(b))>0){
                rf.seek(wrote);
                rf.write(b,0,d);
                wrote+=d;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(stream!=null) stream.close();
                rf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(wrote);
    }
}
