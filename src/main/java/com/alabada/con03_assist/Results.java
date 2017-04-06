package com.alabada.con03_assist;

/**
 * @Author 温枝达
 * @Email alabadazi@gmail.com
 * @Date 2017/4/6 21:49
 * @Description
 */
public class Results {

    private int data[];

    public Results(int size){
        data=new int[size];
    }

    public void  setData(int position, int value){
        data[position]=value;
    }

    public int[] getData(){
        return data;
    }
}
