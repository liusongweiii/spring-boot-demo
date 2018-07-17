package com.common;

/**
 * Created by Administrator on 2018/7/10 0010.
 */
public class ArrayQue<T> {

    int first,last;
    Object[] storage;
    int inicap=4,step=4;//队列初始容量和增加时的步长
    public ArrayQue()
    {
        first=last=-1;
        storage=new Object[inicap];
    }
    public boolean isEmpty() {

        return first==-1;
    }

    public void push(T el)
    {
        if(isFull())
        {
            enLarge();
            System.out.println("队列容量不够，已动态增加数组容量到："+storage.length);
        }

        if(last==storage.length-1||first==-1)
        {
            storage[0]=el;
            last=0;
            if(first==-1) {
                first = 0;
            }
        }
        else {
            storage[++last] = el;
        }
    }

    public boolean isFull() {
        return (first==0&&last==storage.length-1)||(first==last+1);
    }

    public Object  delFirst() {
        // TODO Auto-generated method stub
        Object temp=storage[first];
        if(first==last) {
            first = last = -1;
        }
        else if(first==storage.length-1) {
            first = 0;
        }
        else{ first++;}
        return temp;
    }

    public void printAll() {
        for(Object i:storage) {
            System.out.print(i + "  ");
        }
        System.out.println();
    }

    public void enLarge() {
        Object[] a=new Object[storage.length+step];
        if(first==0&&last==storage.length-1) {
            System.arraycopy(storage, 0, a, 0, storage.length);
        }else
        {
            System.arraycopy(storage, 0, a, 0, last+1);
            System.arraycopy(storage, first, a, a.length-storage.length+first, storage.length-first);

        }
        storage=a;
    }

}
