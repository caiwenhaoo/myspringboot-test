package com.cwh.myspringbootutils.help;

@FunctionalInterface
public interface CheckedFunction<T, R> {

    R apply(T t) throws Exception;

}
