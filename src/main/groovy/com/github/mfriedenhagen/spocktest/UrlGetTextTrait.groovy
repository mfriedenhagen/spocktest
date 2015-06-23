package com.github.mfriedenhagen.spocktest

import java.util.concurrent.TimeUnit

trait UrlGetTextTrait {
    static final long CONNECT_TIMEOUT = 5
    static final long READ_TIMEOUT = 5

    String getText(URL url) {
        return getText(url, CONNECT_TIMEOUT, READ_TIMEOUT)
    }

    String getText(URL url, long connectTimeout, long readTimeout) {
        return url.getText([
                'connectTimeout': TimeUnit.MILLISECONDS.convert(connectTimeout, TimeUnit.SECONDS),
                'readTimeout'   : TimeUnit.MILLISECONDS.convert(readTimeout, TimeUnit.SECONDS)],
                'UTF-8')
    }
}
