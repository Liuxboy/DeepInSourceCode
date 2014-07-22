package org.arcie.dong.interview.java;

/**
 * Created with IntelliJ IDEA13.0
 * Author:刘春东
 * E-mail:liuchundong2008@gamil.com
 * Date:14-7-22
 * Time:上午10:30
 * Version:0.1
 */

/**约瑟夫环算法
 * 假设有20个人手拉手围成一圈，顺时针开始报号，报到3的人出圈，然后继续往后报，报到3的人出圈，
 * 依次把所有报到3的人都踢出圈，最后剩下一人也踢出圈，问先后被踢出圈的那些人原来是圈内的几号？
 */
public class JosephCircle {
    /**
     * n个人中，第k个出列
     * @param n
     * @param k
     */
    public void josephCircle(int n,int k){
        int flag=0;
        //设置n个状态的数组，初始化为false
        boolean[] kick = new boolean[n];
        for(int i = 0; i < n-1; i++){
            kick[flag]=false;
        }
        int counter=0;      //计数器
        int accumulate=0;   //累加器
        while(true){
            if(!kick[flag]){
                accumulate++;
                //计算器等于最后一个
                if(counter == n-1){
                    System.out.println("kick last person===="+(flag+1));
                    break;
                }
                if(accumulate == k){
                    kick[flag] = true;
                    System.out.println("kick person===="+(flag+1));
                    accumulate = 0;
                    counter++;
                }
            }
            flag = (flag + 1) % n;  //实际上是在+1递增，递增到n时，下一步又转回到0，核心算法。
        }

    }
    public static void main(String[] args) {
        JosephCircle jCircle = new JosephCircle();
        jCircle.josephCircle(5, 3);
    }
}
