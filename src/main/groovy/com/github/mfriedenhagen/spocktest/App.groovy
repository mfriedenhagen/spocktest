package com.github.mfriedenhagen.spocktest

import com.google.common.collect.ImmutableSet
import com.google.common.reflect.ClassPath
import org.junit.internal.TextListener
import org.junit.runner.JUnitCore
import org.junit.runner.Result
import spock.lang.Specification

class App {
    static void main(String[] args) {
        ClassPath classPath = ClassPath.from(App.class.getClassLoader())
        ImmutableSet<ClassPath.ClassInfo> classInfos = classPath.getTopLevelClasses(App.package.name)
        def specifications = classInfos.find { it.load().genericSuperclass.is(Specification) }
        println specifications
        println args
        def core = new JUnitCore()
        core.addListener(new ExtendedTextListener(System.out))
        def result = core.run(FirstTest)
        System.exit(result.failureCount)
    }
}
