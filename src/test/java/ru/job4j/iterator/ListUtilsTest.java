package ru.job4j.iterator;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);

        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);

        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenAddAfterInTheMiddle() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 5));
        ListUtils.addAfter(input, 1, 8);

        assertThat(input, is(Arrays.asList(0, 1, 8, 2, 5)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 5));
        ListUtils.addAfter(input, 4, 8);
    }

    @Test
    public void whenRemoveIfOdd() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 5, 8));
        Predicate<Integer> predicate = i -> i % 2 != 0;
        ListUtils.removeIf(input, predicate);

        assertThat(input, is(Arrays.asList(0, 2, 8)));
    }

    @Test
    public void whenReplaceIfEven() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 5, 8));
        Predicate<Integer> predicate = i -> i % 2 == 0;
        ListUtils.replaceIf(input, predicate, 77);

        assertThat(input, is(Arrays.asList(77, 1, 77, 5, 77)));
    }

    @Test
    public void whenRemoveAllIfInTheElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 5, 8));
        List<Integer> elements = new ArrayList<>(Arrays.asList(0, 1, 5));
        ListUtils.removeAll(input, elements);

        assertThat(input, is(Arrays.asList(2, 8)));
    }

}