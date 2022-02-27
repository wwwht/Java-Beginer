/*
 * @Descripttion: 
 * @version: 
 * @Author: wht
 * @Date: 2021-12-08 09:52:23
 * @LastEditors: wht
 * @LastEditTime: 2022-02-21 14:25:49
 */
// Hello.java
public class Hello {
    public static void main(String[] args) {
        String s1="a"+"b"+"c";
        String s2="abc";
        System.out.println(s1==s2);
        System.out.println(s1.equals(s2));
    }
}

class E {
    public static void main(String[] args) {
        System.out.print("I am E Class, which is a main class");
    }
}