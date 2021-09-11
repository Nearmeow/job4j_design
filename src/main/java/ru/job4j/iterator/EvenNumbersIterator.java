package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (index == data.length) {
            return false;
        }
        return getNextEvenNumber(0) != null;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return getNextEvenNumber(1);
    }

    private Integer getNextEvenNumber(Integer plus) {
        if (data[index] % 2 == 0) {
            index += plus;
            return data[index - plus];
        }
        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                index++;
                return data[i];
            }
        }
        return null;
    }

}
