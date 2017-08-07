package com.github.liuxboy.interview.code;

/**
 * Package: com.github.liuxboy.interview.code <br>
 * Author: liuchundong <br>
 * Date: 2017/6/27 <br>
 * Time: 11:50 <br>
 * Desc: 以DB实现分布式锁
 * 其实是利用DB的事务以及锁机制
 * 方式一：数据表中插入一条记录，根据索引或者主键执行for update加record lock，释放锁就是commit
 * 方式二：数据表设置一条记录，根据主键索引冲突，删除记录表示release lock
 *
 * 问题：
 * 1、性能不高
 * 2、SQL超时或者异常,JDBC超时具体有3种超时,具体见深入理解JDBC的超时设置<a href="http://www.importnew.com/2466.html"></a>
 *  2.1 框架层的事务超时
 *  2.2 JDBC的查询超时
 *  2.3 Socket的读超时
 */
public class DistributeLockByDatabase {
    public void lock() {
        //connect.setAutoCommit(false);
        int count = 0;
        //重试4次
        while (count < 4) {
            try {
                String sql = "select * from lock where lock_name = xxx for update";
                //执行sql后结果不为空
                /*
                if (execute(sql) != null) {
                    //代表获取到锁
                    return;
                }
                */
            } catch (Exception e) {

            }
            //为空或者抛异常的话都表示没有获取到锁
            count++;
        }
    }

    public void release() {
        //提交事务
        //connection.commit();
    }
}
