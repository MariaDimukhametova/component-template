package com.example.templates_lib.xml.generator

import android.content.Context
import android.util.Xml
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.templates_lib.attributes.CardAttributes
import com.example.templates_lib.xml.XMLGenerator
import org.xmlpull.v1.XmlSerializer
import java.io.StringWriter

class CardXmlGenerator(val context: Context) : XMLGenerator<CardAttributes> {

    override fun generateXML(attributes: CardAttributes): String {
        val layoutResId = when (attributes.cardType) {
            CardType.IMAGE -> R.layout.card_image
            CardType.IMAGE_TEXT -> R.layout.card_image_text
            CardType.IMAGE_TEXT_TEXT -> R.layout.card_image_text_text
        }

        val binding: ViewDataBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), layoutResId, null, false)

        binding.setVariable(BR.attributes, attributes)
        binding.executePendingBindings()

        val rootView = binding.root

        val stringWriter = StringWriter()
        val xmlSerializer = Xml.newSerializer()
        xmlSerializer.setOutput(stringWriter)

        viewToXml(xmlSerializer, rootView)
        xmlSerializer.endDocument()

        return stringWriter.toString()
    }
    override fun viewToXml(xmlSerializer: XmlSerializer, view: View) {
        when (view) {
            is TextView -> {
                xmlSerializer.startTag(null, "TextView")
                xmlSerializer.text(view.text.toString())
                xmlSerializer.endTag(null, "TextView")
            }
            is ImageView -> {
                xmlSerializer.startTag(null, "ImageView")
                xmlSerializer.attribute(null, "src", view.contentDescription?.toString() ?: "")
                xmlSerializer.endTag(null, "ImageView")
            }
        }

        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                viewToXml(xmlSerializer, view.getChildAt(i))
            }
        }
    }
}