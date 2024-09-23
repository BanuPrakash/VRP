package com.visa.prj.client;

import com.visa.prj.util.Computation;

public class LambdaExample {
    public static void main(String[] args) {
        Computation<Integer> c1 = new Computation<Integer>() {
            @Override
            public Integer compute(Integer arg1, Integer arg2) {
                return arg1 + arg2;
            }
        };

        System.out.println(c1.compute(3,4));

        // Type Infer
        Computation<Double> c2 = (arg1, arg2) ->  arg1 * arg2;

        System.out.println(c2.compute(4.11, 5.11));
    }
}
