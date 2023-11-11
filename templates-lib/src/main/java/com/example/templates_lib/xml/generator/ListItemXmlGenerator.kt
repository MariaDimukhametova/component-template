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
import com.example.templates_lib.R
import com.example.templates_lib.attributes.ListItemAttributes
import com.example.templates_lib.attributes.ListItemType
import com.example.templates_lib.xml.XMLGenerator
import org.xmlpull.v1.XmlSerializer
import java.io.StringWriter

class ListItemXmlGenerator (val context: Context) : XMLGenerator<ListItemAttributes> {
    override fun generateXML(attributes: ListItemAttributes): String {
        val layoutResId = when (attributes.typeListItem) {
            ListItemType.TEXT -> R.layout.list_item_text
            ListItemType.ICON_TEXT -> R.layout.list_item_icon_text
            ListItemType.IMAGE_TEXT -> R.layout.list_item_image_text
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