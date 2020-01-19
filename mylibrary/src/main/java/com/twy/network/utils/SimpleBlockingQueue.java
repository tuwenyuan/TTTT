package com.twy.network.utils;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author by twy, Email 499216359@qq.com, Date on ${DATA}.
 * PS: Not easy to write code, please indicate.
 */
public class SimpleBlockingQueue<T> {
    //队列容器
    private LinkedList<T> container = new LinkedList<>();
    //申明锁
    private Lock lock = new ReentrantLock();
    //标识，可以表示具体的线程
    private final Condition conditionNull = lock.newCondition();
    private final Condition conditionFull = lock.newCondition();

    /**
     * 将数据加入到队列中
     * @param item
     * @throws InterruptedException
     */
    public void put(T item) throws InterruptedException {
        if(item == null){
            throw new NullPointerException();
        }
        //申明可中断锁，简单起见也可以直接使用lock.lock(),lock.tryLock()
        lock.lockInterruptibly();
        try{
            //System.out.println("添加元素:"+item);
            container.push(item);
            //System.out.println("唤醒阻塞线程...");
            //添加成功之后，调用signal()发出唤醒通知
            //注意这里的唤醒一定要在unlock()之前
            conditionNull.signal();
            //Thread.sleep(2000);
        }finally {
            //System.out.println("添加方法释放锁...");
            //手动释放锁
            lock.unlock();
        }
    }

    /**
     * 从队列中后去数据
     * @return
     * @throws InterruptedException
     */
    public T take() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            try {
                while (container.size() == 0){
                    //System.out.println("队列元素为空，进入阻塞...");
                    conditionNull.await();
                }
            } catch (InterruptedException ie) {
                //System.out.println("出现异常，唤醒阻塞线程conditionNull");
                conditionNull.signal();
                throw ie;
            }
            T x = container.pollLast();
            //System.out.println("容器大小:"+container.size());
            //System.out.println("取出方法取出元素:"+x);
            conditionFull.signal();
            return x;

        } finally {
            //System.out.println("取出方法释放锁...");
            lock.unlock();
        }
    }

    public boolean contains(T t){
        return container.contains(t);
    }

    public boolean remove(T t){
        return container.remove(t);
    }

    public int size(){
        return container.size();
    }
}
