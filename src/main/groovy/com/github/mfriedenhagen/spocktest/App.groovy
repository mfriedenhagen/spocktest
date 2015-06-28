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
        Set<Class> specifications = classInfos
                .findAll { it.load().genericSuperclass.is(Specification) }
                .collect { it.load() }
        println specifications
        println args
        def core = new JUnitCore()
        core.addListener(new ExtendedTextListener(System.out))
        def result = core.run(specifications.toArray(new Class[specifications.size()]))
        System.exit(result.failureCount)
    }
}
