package com.github.liuxboy.interview.code;

/**
 * Created with IntelliJ IDEA13.0
 * Author:刘春东
 * E-mail:liuchundong2008@gamil.com
 * Date:puzzle14-7-23
 * Time:上午10:32
 * Version:0.1
 */

public class MissedNumber {
    /**
     * 查找缺失的数字
     * 原题：
     * 说有一数组如 int[]{0,1,2} 这样的一个数组，这个数组的第一个必须从0开始，以次+1列出，该数组内最后一个数是这个数组的长度，因此：
     * int[]{1,2}, missed number为0
     * int[]{0,1,2}, missed number为3
     * int[]{0,2}, missed number为1
     * 思想：等差数列和所有数字和之差为所缺的数字
     */
    public int findMissedOne(int[] numArray) {
        int sum = 0;
        int idx = -1;
        for (int i = 0; i < numArray.length; i++) {
            if (numArray[i] == 0) {
                idx = i;
            } else {
                sum += numArray[i];
            }
        }

        // the total sum of numbers between 1 and arr.length.
        int total = (numArray.length + 1) * numArray.length / 2;
        int missedNumber = total - sum;
        return missedNumber;
    }

    /**
     * 运用同样的思想：既然要知道缺失了哪一个数，那肯定知道原来有哪些数，要不然，没有判断数字是否有缺失
     * @param initArray
     * @param missedArray
     * @return
     */
    public int findMissedNumber(int initArray[], int missedArray[]){
        int sum = 0;
        for(int i : initArray){
            sum += i;
        }
        for(int j : missedArray){
            sum -= j;
        }
        return sum;
    }

    public void printArray(int[] a){
        for(int i = 0; i < a.length; i++){
            System.out.println("a[" + i + "]:" + a[i]);
        }
    }
    public static void main(String[] args){
        MissedNumber missedNumber = new MissedNumber();
        //测试findMissedOne
        int[] testArray = new int[] {0,1,2,4,5};
        missedNumber.printArray(testArray);
        System.out.println("findMissOne找出的缺失的数字为：" + missedNumber.findMissedOne(testArray));

        //生成随机数目,(数据类型)(最小值+Math.random()*(最大值-最小值+1)) 
        int[] a = new int[10],b = new int[10];
        for(int i = 0; i < 10; i++){
            a[i] = (int)(1 + Math.random()*10);
            b[i] = a[i];
        }
        System.out.println("初始时数组：");
        missedNumber.printArray(a);
        a[5] = 0;   //相当于a[5]缺失了
        System.out.println("缺失后数组：");
        missedNumber.printArray(a);
        System.out.println("findMissNumber找出的缺失的数字为：" + missedNumber.findMissedNumber(b,a));
    }
}
