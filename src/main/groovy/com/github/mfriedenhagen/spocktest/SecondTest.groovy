package com.github.mfriedenhagen.spocktest

import spock.lang.Specification

class SecondTest extends Specification{
    void 'my test'() {
        expect:
        1 == 1
    }
}
