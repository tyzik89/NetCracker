package ru.sukmanov.lab3.sorting;

import java.util.Comparator;

public interface ObjectComparator<T> extends Comparator<T> {
    public int compare(T a, T b);
}
