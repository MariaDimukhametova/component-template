package com.example.templates_lib.xml

import android.view.View
import org.xmlpull.v1.XmlSerializer

interface XMLGenerator<T> {
    fun generateXML(attributes: T): String
    fun  viewToXml(xmlSerializer: XmlSerializer, view: View)
}