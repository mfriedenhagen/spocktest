package com.github.mfriedenhagen.spocktest

import org.junit.internal.TextListener
import org.junit.runner.JUnitCore
import org.junit.runner.Result

class App {
    static void main(String[] args) {
        println(args)
        def core = new JUnitCore()
        core.addListener(new ExtendedTextListener(System.out))
        def result = core.run(FirstTest)
        System.exit(result.failureCount)
    }
}
