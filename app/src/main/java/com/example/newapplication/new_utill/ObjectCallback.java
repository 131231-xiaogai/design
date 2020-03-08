package com.example.psychologicalcounseling.okhttp;

import java.lang.reflect.Type;

public interface ObjectCallback<T>  {

    T convert(String response, Type type);
}
