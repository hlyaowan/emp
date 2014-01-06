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
     * 目前16个CP
     * 
     * @return
     */
    public static int getWeight() {
        // int arr[] = { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 3, 3, 4, 4, 5,
        // 6, 7 };
        int arr[] =
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,7,7,7,7,7,8,8,8,8,8,9,9,9,9,9,10,10,10,10,10,11,11,11,11,11,12,12,12,12,12,13,13,13,13,13,14,14,14,14,14,15,15,15,15,15,16,16,16,16,16,17,17,17,18,18,18,19,19,19,20,20,20,21,21,21,22,22,22,23,23,23,23,24,24,24,25,25,25,26,26,26,27,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54};
        Random random = new Random();
//        System.out.println("arr:"+arr.length);
        int ranvalue = random.nextInt(260);
        int value = arr[ranvalue];
        return value;
    }


    public static void main(String[] args) {
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        for (int i = 0; i < 2000000; i++) {
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
