package com.company;

import java.util.List;

public interface IRepository<T> {
    void delete(int id);
    List<T> get();

}
