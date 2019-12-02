package com.cwh.myspringbootutils.help.test;

import com.cwh.myspringbootutils.help.Either;

import java.util.ArrayList;
import java.util.List;

public class test1 {

    public static void main(String[] args) {

        List<String> arr = new ArrayList<>();
        arr.add("aaa");
        arr.add(null);
        arr.add("ccc");

        arr.stream().map(Either.lift(item -> doSomething(item))).forEach(System.out::println);

    }

    public static int doSomething(String par){
        System.out.println("--->" + par.length());
        return par.length();
    }

}
