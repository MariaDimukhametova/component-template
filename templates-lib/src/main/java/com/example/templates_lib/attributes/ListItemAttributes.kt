package com.example.templates_lib.attributes

import android.content.Context
import android.content.res.TypedArray
import androidx.core.content.ContextCompat
import com.example.templates_lib.R

enum class ListItemType{
    TEXT,
    IMAGE_TEXT,
    ICON_TEXT
}

class ListItemAttributes(
    context: Context,
    typedArray: TypedArray
)  {
    var typeListItem: ListItemType
    var titleText: String
    var backgroundColor: Int
    var textColor: Int


    // Additional attributes for double_icon
    var iconAttr: Int = R.drawable.default_icon

    // Additional attributes for image_icon
    var imageAttr: Int = R.drawable.default_image

    init {


        typeListItem = when (typedArray.getInt(R.styleable.ToolBarTemplate_type_list_item, 0)) {
            0 -> ListItemType.TEXT
            1 -> ListItemType.ICON_TEXT
            2 -> ListItemType.IMAGE_TEXT
            else -> ListItemType.TEXT // Default value
        }


        titleText = typedArray.getString(R.styleable.ToolBarTemplate_title_text) ?: "222"
        backgroundColor = typedArray.getColor(
            R.styleable.ToolBarTemplate_background_color,
            ContextCompat.getColor(context, R.color.default_background_toolbar_color)
        )
        textColor = typedArray.getColor(
            R.styleable.ToolBarTemplate_text_color,
            ContextCompat.getColor(context, R.color.default_toolbar_text_color)
        )

        when (typeListItem) {

            ListItemType.ICON_TEXT -> {
                iconAttr = typedArray.getResourceId(
                    R.styleable.ListItemTemplate_text_icon_icon_attr,
                    R.drawable.default_icon
                )

            }
            ListItemType.IMAGE_TEXT -> {
                imageAttr = typedArray.getResourceId(
                    R.styleable.ListItemTemplate_text_icon_icon_attr,
                    R.drawable.default_icon
                )
            }
        }
        typedArray.recycle()
    }
}