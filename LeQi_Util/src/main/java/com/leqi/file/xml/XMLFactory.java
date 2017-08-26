/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqi.file.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author zhuqing
 */
public class XMLFactory {

    private Document rootDocument;

    public XMLFactory(String xml) {
        this(new ByteArrayInputStream(xml.getBytes()));
    }

    public XMLFactory(InputStream inputStream) {
        SAXReader reader = new SAXReader();
        try {
            rootDocument = reader.read(inputStream);
        } catch (DocumentException ex) {
            Logger.getLogger(XMLFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Element> getElements(String... tagNames) {
        if (tagNames == null || tagNames.length == 0) {
            return Collections.EMPTY_LIST;
        }
        if (!this.rootDocument.getRootElement().getName().equals(tagNames[0])) {
            return Collections.EMPTY_LIST;
        }

        List<Element> elements = Arrays.asList(this.rootDocument.getRootElement());
        for (int i = 1; i < tagNames.length; i++) {
            elements = XMLUtil.getElements(tagNames[i], elements);
        }

        return elements;
    }

}
