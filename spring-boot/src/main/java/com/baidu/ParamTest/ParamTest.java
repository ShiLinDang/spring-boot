package com.baidu.ParamTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dangsl
 * @description
 * @date ${date} ${time}
 * ${tags}
 */
public class ParamTest {

    public static void main(String[] args) {
        ParamTest paramTest = new ParamTest();
        paramTest.print("abc");
        paramTest.print("not hello","hello", "李白");
        System.out.println("==========================");
        // 一个方法只能有一个可变长参数，并且这个可变长参数必须是该方法的最后一个参数(调换name与args的顺序,编译不通过)
        paramTest.print2("李白","a","b","c");
        System.out.println("==========================");
        ArrayList list = new ArrayList();
        list.add(0);
        list.add(1);
        list.add(2);
        paramTest.print3(list,"hello","李白");
        System.out.println("==========================");
        paramTest.print4(list,list,list);
    }

    public void print(String... args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
    }

    public void print2(String name,String ... args){
        System.out.println(name);
        for (String arg : args) {
            System.out.println(arg);
        }
    }

    public void print3(List<Integer> list,String ... args){
        System.out.println(list.size());
        System.out.println(args[0]);
    }

    public void print4(ArrayList...list){
        for (ArrayList arrayList : list) {
            System.out.println(arrayList.get(0));
        }
    }
}
