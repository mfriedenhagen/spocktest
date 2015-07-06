package com.github.mfriedenhagen.spocktest;


import org.junit.internal.TextListener;
import org.junit.runner.Description;

import java.io.PrintStream;
import java.util.Locale;

public class ExtendedTextListener extends TextListener {

    private final PrintStream writer;

    public ExtendedTextListener(PrintStream writer) {
        super(writer);
        this.writer = writer;
    }

    @Override
    public void testStarted(Description description) {
        writer.printf(Locale.ENGLISH, "Started %s %s%n", Thread.currentThread(), description);
    }
}
