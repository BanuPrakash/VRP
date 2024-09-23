package com.visa.prj.util;

@FunctionalInterface
public interface Computation <T> {
    T compute(T arg1, T arg2);
}
