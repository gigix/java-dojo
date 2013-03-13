package org.thoughtworkers;

import org.hamcrest.Matcher;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StringGameTest {
    @Test
    public void shouldAddBorderToSingleChar() throws Exception {
        StringGame game = new StringGame("b");
        String expectedOutput = "" +
                " - \n" +
                "|b|\n" +
                " - \n";
        assertThat(game.output(), is(expectedOutput));
    }

    @Test
    public void shouldAddBorderToMultipleChars() throws Exception {
        StringGame game = new StringGame("abc");
        String expectedOutput = "" +
                " --- \n" +
                "|abc|\n" +
                " --- \n";
        assertThat(game.output(), is(expectedOutput));
    }

    @Test
    public void shouldAddBorderToMultipleLines() throws Exception {
        StringGame game = new StringGame("a", "ab");
        String expectedOutput = "" +
                " -- \n" +
                "|a |\n" +
                "|ab|\n" +
                " -- \n";
        assertThat(game.output(), is(expectedOutput));

    }
}
