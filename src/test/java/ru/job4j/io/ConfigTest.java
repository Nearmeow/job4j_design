package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutArg() {
        String path = "./data/pair_without_arg.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Filya"));
        assertThat(config.value("type"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithEmptyLines() {
        String path = "./data/pair_with_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Filya"));
        assertThat(config.value("type"), is("cat"));
    }

    @Test
    public void whenPairWithComments() {
        String path = "./data/pair_with_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Filya"));
        assertThat(config.value("type"), is("cat"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWitIllegalArgument() {
        String path = "./data/pair_with_illegal_argument.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoKeyParameter() {
        String path = "./data/pair_with_no_key.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenMultipleEqualsSigns() {
        String path = "./data/when_multiple_equals_signs.properties";
        Config config = new Config(path);
        config.load();
    }



}