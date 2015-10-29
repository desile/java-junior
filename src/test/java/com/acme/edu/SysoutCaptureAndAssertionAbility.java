package com.acme.edu;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.fest.assertions.Assertions.*;

public interface SysoutCaptureAndAssertionAbility {
    ByteArrayOutputStream OUT = new ByteArrayOutputStream();

    /**
     * Перенаправление стандартного потока вывода
     */
    public default void captureSysout() {
        System.setOut(new PrintStream(OUT));
    }

    /**
     * Очистить содержимое вывода <br>
     * Может быть удобно для проведения тестов
     */
    public default void flushSysout() {
        OUT.reset();
    }

    default void assertSysoutEquals(String expected) {
        assertThat(OUT.toString()).isEqualTo(expected);
    }

    default void assertSysoutContains(String expected) {
        assertThat(OUT.toString()).contains(expected);
    }
}
