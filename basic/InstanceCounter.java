import javax.print.event.PrintEvent;

/*
 * @Descripttion: 
 * @version: 
 * @Author: wht
 * @Date: 2022-02-18 10:38:56
 * @LastEditors: wht
 * @LastEditTime: 2022-02-18 11:17:54
 */

// static
public class InstanceCounter{
    private static int numInstance = 0;
    protected static int getCount(){
        return numInstance;
    }

    private static void addInstance(){
        numInstance ++;
    }
    // 
    InstanceCounter(){
        InstanceCounter.addInstance();
    }

    public static void main(String[] args) {
        System.out.println("Starting with" + InstanceCounter.getCount()
        + " Instance");

        for(int i=0;i<500;i++){
            new InstanceCounter();
        }
        System.out.println("Created " + InstanceCounter.getCount() + 
        " instances");
    }
}