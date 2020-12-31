package com.github.dynosaur;

import java.util.ArrayList;

public class Util {
    public static <T> ArrayList<T> toArrayList(T[] array) {
        ArrayList<T> arrayList = new ArrayList<T>();
        for (T element : array) arrayList.add(element);
        return arrayList;
    }
}
