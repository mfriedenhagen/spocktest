package com.github.mfriedenhagen.spocktest

import com.google.common.collect.ImmutableSet
import com.google.common.reflect.ClassPath
import org.junit.experimental.ParallelComputer
import org.junit.internal.TextListener
import org.junit.runner.JUnitCore
import org.junit.runner.Result
import spock.lang.Specification

class App {
    static void main(String[] args) {
        ClassPath classPath = ClassPath.from(App.class.getClassLoader())
        ImmutableSet<ClassPath.ClassInfo> classInfos = classPath.getTopLevelClasses(App.package.name)
        Set<Class> specifications = classInfos
                .collect { it.load() }
                .findAll { it.genericSuperclass.is(Specification) }
        println "All specifications ${specifications.collect { it.simpleName }}"
        if (args) {
            if ('-h' in args) {
                println 'Usage "java -jar SHADED.jar SpecificationRegeex"'
                System.exit(0)
            }
            specifications = specifications.findAll{ it.simpleName ==~ args[0] }
        }
        println "Filtered specifications ${specifications}"
        def core = new JUnitCore()
        core.addListener(new ExtendedTextListener(System.out))
        def result = core.run(new ParallelComputer(true, true), specifications.toArray(new Class[specifications.size()]))
        System.exit(result.failureCount)
    }
}
