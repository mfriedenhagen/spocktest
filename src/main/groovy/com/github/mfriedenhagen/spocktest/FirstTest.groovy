package com.github.mfriedenhagen.spocktest

import groovy.json.JsonSlurper
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.util.concurrent.TimeUnit

class FirstTest extends Specification implements UrlGetTextTrait {


    @Unroll("Test #urlString ends with #needle")
    def 'test url'(String urlString, String needle) {
        given:
        @Subject
        def url = new URL(urlString)
        when:
        def text = getText(url)
        then:
        text.trim().endsWith(needle)
        where:
        urlString               | needle
        'http://www.google.de/' | '</html>'
        'http://web.de/'        | '</html>'
        'http://gmx.net/'       | '</html>'
        'http://repo.jfrog.org/artifactory/api/system/ping' | 'OK'
    }

    def 'test artifactory'() {
        given:
        @Subject
        def url = new URL('http://repo.jfrog.org/artifactory/api/search/gavc?g=junit&a=junit&v=4.1*')
        when:
        def json = new JsonSlurper().parse(url)
        List<Map<String, URI>> results = json['results']
        then:
        results.size() > 0

    }
}
