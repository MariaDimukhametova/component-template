package com.example.templates_lib.attributes

import android.content.Context
import android.content.res.TypedArray
import androidx.core.content.ContextCompat
import com.example.templates_lib.R

enum class ToolbarType {
    SINGLE_ICON,
    DOUBLE_ICON,
    IMAGE_ICON
}

class ToolBarAttributes(
    context: Context,
    typedArray: TypedArray
) {

    var typeToolbar: ToolbarType
    var icon: Int
    var titleText: String
    var backgroundColor: Int
    var textColor: Int


    // Additional attributes for double_icon
    var doubleIcon1Attr: Int = R.drawable.default_icon
    var doubleIcon2Attr: Int = R.drawable.default_icon

    // Additional attributes for image_icon
    var imageIconAttr: Int = R.drawable.default_image

    init {


        typeToolbar = when (typedArray.getInt(R.styleable.ToolBarTemplate_type_toolbar, 0)) {
            0 -> ToolbarType.SINGLE_ICON
            1 -> ToolbarType.DOUBLE_ICON
            2 -> ToolbarType.IMAGE_ICON
            else -> ToolbarType.SINGLE_ICON  // Default value
        }

        icon = typedArray.getResourceId(R.styleable.ToolBarTemplate_icon, R.drawable.default_icon)
        titleText = typedArray.getString(R.styleable.ToolBarTemplate_title_text) ?: "222"
        backgroundColor = typedArray.getColor(
            R.styleable.ToolBarTemplate_background_color,
            ContextCompat.getColor(context, R.color.default_background_toolbar_color)
        )
        textColor = typedArray.getColor(
            R.styleable.ToolBarTemplate_text_color,
            ContextCompat.getColor(context, R.color.default_toolbar_text_color)
        )

        when (typeToolbar) {
            ToolbarType.SINGLE_ICON -> {

            }
            ToolbarType.DOUBLE_ICON -> {
                doubleIcon1Attr = typedArray.getResourceId(
                    R.styleable.ToolBarTemplate_double_icon_double_icon1,
                    R.drawable.default_icon
                )
                doubleIcon2Attr = typedArray.getResourceId(
                    R.styleable.ToolBarTemplate_double_icon_double_icon2,
                    R.drawable.default_icon
                )
            }
            ToolbarType.IMAGE_ICON -> {
                imageIconAttr = typedArray.getResourceId(
                    R.styleable.ToolBarTemplate_image_icon_image,
                    R.drawable.default_image
                )
            }
        }
        typedArray.recycle()
    }
}
