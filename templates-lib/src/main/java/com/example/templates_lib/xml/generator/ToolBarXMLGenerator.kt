package com.example.templates_lib.xml.generator

import android.content.Context
import android.util.Xml
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.templates_lib.BR
import com.example.templates_lib.R
import com.example.templates_lib.attributes.ToolBarAttributes
import com.example.templates_lib.attributes.ToolbarType
import com.example.templates_lib.xml.XMLGenerator
import org.xmlpull.v1.XmlSerializer
import java.io.StringWriter

class ToolBarXMLGenerator(val context: Context) : XMLGenerator<ToolBarAttributes> {
    override fun generateXML(attributes: ToolBarAttributes): String {
        val layoutResId = when (attributes.typeToolbar) {
            ToolbarType.SINGLE_ICON -> R.layout.toolbar_single_icon
            ToolbarType.DOUBLE_ICON -> R.layout.toolbar_double_icon
            ToolbarType.IMAGE_ICON -> R.layout.toolbar_image_icon
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
            is ImageButton -> {
                // Обработка для ImageButton
                xmlSerializer.startTag(null, "ImageButton")
                xmlSerializer.attribute(null, "src", view.contentDescription?.toString() ?: "")
                xmlSerializer.endTag(null, "ImageButton")
            }
            is ImageView -> {
                // Обработка для ImageView
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