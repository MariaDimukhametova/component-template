package com.example.component_template

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Xml
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.templates_lib.attributes.ToolBarAttributes
import com.example.templates_lib.attributes.ToolbarType
import com.example.templates_lib.xml.generator.ToolBarXMLGenerator
import org.xmlpull.v1.XmlPullParser
import java.io.StringReader

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val xmlGenerator = ToolBarXMLGenerator(this)

        val resources: Resources = resources
        var toolBarAttributes: ToolBarAttributes
        val attributeSet: AttributeSet? = null
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val containerLayout = findViewById<ConstraintLayout>(com.google.android.material.R.id.container)
        val parser = Xml.newPullParser()


        val typedArray: TypedArray =
            this.obtainStyledAttributes(attributeSet,R.styleable.ToolBarTemplate)

        toolBarAttributes = ToolBarAttributes(this, typedArray)

        toolBarAttributes.typeToolbar = ToolbarType.SINGLE_ICON
        toolBarAttributes.titleText = "Generated Tool Bar"
        toolBarAttributes.icon = com.example.templates_lib.R.drawable.baseline_chevron_left_24
        toolBarAttributes.backgroundColor = ContextCompat.getColor(this, R.color.black)
        toolBarAttributes.textColor = ContextCompat.getColor(this, R.color.white)

        val generatedXml = xmlGenerator.generateXML(toolBarAttributes)

        parser.setInput(StringReader(generatedXml))

        while (parser.eventType != XmlPullParser.END_DOCUMENT) {
            if (parser.eventType == XmlPullParser.START_TAG) {
                val view = inflater.inflate(
                    resources.getLayout(parser.name.toInt()),
                    containerLayout,
                    false
                )
                containerLayout.addView(view)
            }
            parser.next()
        }

    }
}