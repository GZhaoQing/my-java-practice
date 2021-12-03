package com.ufo.number;

import java.util.Arrays;

public class NumberConversion {

    public static void main(String[] args){
//        String format = String.format("%02x", 10);
//        System.out.println(format);
        System.out.println(bit2Hex("10011010"));
        System.out.println(Arrays.toString(hex2boolean("2FAC")));
        System.out.println(hex2bit("2FAC"));
    }

    private static String[] HEX_NUMBERS =  {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
    private static String HEX_NUMBERS_STR =  "0123456789ABCDEF";

    private static String[] BIT4_BINARY =
            {"0000","0001","0010","0011",
                    "0100","0101","0110","0111",
                    "1000","1001","1010","1011",
                    "1100","1101","1110","1111"};

    /**
     * 将二进制数值字符串转化为十六进制数值字符串
     * 如：“10011010” --> “9A”
     * @param bitStr
     * @return
     */

    public static String bit2Hex(String bitStr){
        StringBuffer buf = new StringBuffer();
        int len = bitStr.length();
        String c1,c2,c3,c4;
        for(int i=0;i<len;i=i+4){
            c1=bitStr.substring(i,i+1);
            c2=len>i+1?bitStr.substring(i+1,i+2):"0";
            c3=len>i+2?bitStr.substring(i+2,i+3):"0";
            c4=len>i+3?bitStr.substring(i+3,i+4):"0";

            assert c1.equals("0")||c1.equals("1");
            assert c2.equals("0")||c2.equals("1");
            assert c3.equals("0")||c3.equals("1");
            assert c4.equals("0")||c4.equals("1");

            int decVal = (Integer.parseInt(c1) <<3) + (Integer.parseInt(c2) <<2) + (Integer.parseInt(c3) <<1) + Integer.parseInt(c4);
            buf.append(HEX_NUMBERS[decVal]);
        }

        return buf.toString();
    }

    public static String hex2bit(String hexStr){
        StringBuffer buf = new StringBuffer();
        String[] splitHex = hexStr.split("");
        for(int i=0;i<splitHex.length;i++){
            int pos = HEX_NUMBERS_STR.indexOf(splitHex[i]);
            buf.append(BIT4_BINARY[pos]);
        }
        return buf.toString();
    }

    public static boolean[] hex2boolean(String hexStr){
        int maxLen = hexStr.length()*4;
        boolean[] booleanArray=new boolean[maxLen];
        String[] splitHex = hexStr.split("");
        for(int i=0;i<splitHex.length;i++){
            int pos = HEX_NUMBERS_STR.indexOf(splitHex[i]);
            String bit4 = BIT4_BINARY[pos];
            for(int j=0;j<4 && j+i*4<maxLen; j++){
                booleanArray[i*4+j]=charVal2Boolean(bit4.charAt(j));
            }
        }
        return booleanArray;
    }

    private static boolean charVal2Boolean(char c){
        if(c=='0')return false;
        return true;
    }
}
