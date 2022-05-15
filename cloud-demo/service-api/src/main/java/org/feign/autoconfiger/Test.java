package org.feign.autoconfiger;

import java.util.ArrayList;

/**
 * @description:
 * @date: 2022/4/9 22:38
 * @author: LiHaoHan
 * @program: org.feign.autoconfiger
 */
public class Test {
    static ArrayList<Object> objects = new ArrayList<>();
    public static void main(String[] args) {
        objects.add(1);
        objects.add(1);
        objects.add(1);
        objects.add(1);
        System.out.println("objects = " + objects);


        String rangeString = "bytes=500-999";
        int i = rangeString.indexOf("=");
        int i1 = rangeString.indexOf("-");
        String substring = rangeString.substring(i+1, i1);
        System.out.println("substring = " + substring);
        long range = Long.valueOf(rangeString.substring(rangeString.indexOf("=") + 1, rangeString.indexOf("-")));
        System.out.println("range = " + range);
    }
}
