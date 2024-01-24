package com.app.splitwise.mapper;

public interface Mapper<S, T> {

    T map(S source);
}
