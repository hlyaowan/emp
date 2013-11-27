package com.quartz.monitor.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;


public class QuanWeight {
    // public static boolean getWeight(int value){
    // boolean flag = false;
    // //配置文件获取切面值
    // int aspectValue =value;
    // //获取随机值 0-9
    // int randNum= new Random().nextInt(10);
    //
    // if(aspectValue >= randNum){
    // //调用原有接口
    // flag = true;
    // }
    // return flag;
    // }

    /***
     * appinfo[a1,a1,a1,a1,a2,a2,a2,a3,a3,a4] a1:40%,a2:30%,a3:20%,a4:10%
     * 
     * @return
     */
    public static int getWeight() {
        int arr[] = { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3 };
        Random random = new Random();
        int ranvalue = random.nextInt(20);
        int value = arr[ranvalue];
        return value;
    }


    public static void main(String[] args) {
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        for (int i = 0; i < 100000; i++) {
            int tmp = getWeight();
            if (count.containsKey(tmp)) {
                count.put(tmp, count.get(tmp) + 1);
            }
            else {
                count.put(tmp, 1);
            }
        }

        for (Entry<Integer, Integer> entry : count.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
