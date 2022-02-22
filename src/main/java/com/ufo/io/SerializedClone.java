package com.ufo.io;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializedClone {
    //用二进制流深拷贝
    public static <T extends Serializable> T clone(T obj) {
        T cloneObj = null;
        try {
            //写入字节流
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream obs = new ObjectOutputStream(out);
            obs.writeObject(obj);
            obs.close();

            //分配内存，写入原始对象，生成新对象
            ByteArrayInputStream ios = new ByteArrayInputStream(out.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(ios);
            //返回生成的新对象
            cloneObj = (T) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }

    public static void main(String[] args){
        A a = new A();
        a.intA=1;
        a.integerA=2;
        a.strA="321";
        a.array=new Object[]{"123"};
        a.list=new ArrayList();
        a.list.add("456");
        System.out.println(JSON.toJSONString(a));
        A clone = clone(a);
        clone.array=new Object[]{"1234"};
        clone.list=new ArrayList();
        clone.list.add("43213");
        System.out.println(JSON.toJSONString(clone));
        System.out.println(JSON.toJSONString(a));

    }
    static class A implements Serializable,Cloneable{
        Integer integerA;
        int intA;
        String strA;
        Object[] array;
        List list;

        public Integer getIntegerA() {
            return integerA;
        }

        public void setIntegerA(Integer integerA) {
            this.integerA = integerA;
        }

        public int getIntA() {
            return intA;
        }

        public void setIntA(int intA) {
            this.intA = intA;
        }

        public String getStrA() {
            return strA;
        }

        public void setStrA(String strA) {
            this.strA = strA;
        }

        public Object[] getArray() {
            return array;
        }

        public void setArray(Object[] array) {
            this.array = array;
        }

        public List getList() {
            return list;
        }

        public void setList(List list) {
            this.list = list;
        }
    }

}
