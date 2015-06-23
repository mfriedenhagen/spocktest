package com.github.mfriedenhagen.spocktest

import spock.lang.Specification
import spock.lang.Unroll

class FirstTest extends Specification {
    @Unroll("Test #urlString")
    def 'test url'(def urlString) {
        given:
        def url = new URL(urlString)
        when:
        def text = url.getText('UTF-8')
        then:
        text != ''
        where:
        urlString << [
                'http://www.google.de/',
                'http://web.de/',
                'http://gmx.net/'
        ]
    }
}
