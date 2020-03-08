package com.example.newapplication.new_utill;

import java.lang.reflect.Type;

public interface ObjectCallback<T>  {

    T convert(String response, Type type);
}
