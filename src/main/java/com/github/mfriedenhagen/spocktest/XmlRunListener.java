package com.github.mfriedenhagen.spocktest;

import groovy.xml.StaxBuilder;
import groovy.xml.StreamingDOMBuilder;
import org.junit.runner.notification.RunListener;

import java.io.File;

public class XmlRunListener extends RunListener {

    private final File targetDir;

    public XmlRunListener(File targetDir) {
        this.targetDir = targetDir;
        targetDir.mkdirs();
        //new StaxBuilder()
    }

}
