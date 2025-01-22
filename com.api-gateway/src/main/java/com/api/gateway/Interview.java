package com.api.gateway;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Interview {

    public static void main(String arg[]){
        int[] input = {10,8,5,4,6,2,6,10,8};
        Arrays.sort(input);
        Set<Integer> set = new HashSet<>();
        for(Integer i : input){
            set.add(i);
        }
        System.out.println(set.toString());
    }
}
