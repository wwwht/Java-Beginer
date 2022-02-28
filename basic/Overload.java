/*
 * @Descripttion: 
 * @version: 
 * @Author: wht
 * @Date: 2022-02-23 09:49:58
 * @LastEditors: wht
 * @LastEditTime: 2022-02-23 11:43:54
 */
class Animal {
    public void move(){
        System.out.println("Animal move");
    }
}

class Dog extends Animal{
    public void move(){
        System.out.println("Dog move");
    }
}

public class Overload{
    public static void main(String[] args) {
        Animal a = new Animal();
        Animal b = new Dog();
        a.move();
        b.move();
    }
}