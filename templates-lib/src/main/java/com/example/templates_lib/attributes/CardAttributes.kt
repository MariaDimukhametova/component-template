package com.example.templates_lib.attributes

import android.content.Context
import android.content.res.TypedArray
import androidx.core.content.ContextCompat
import com.example.templates_lib.R

enum class CardType{
    IMAGE,
    IMAGE_TEXT,
    IMAGE_TEXT_TEXT
}

class CardAttributes (
    context: Context,
    typedArray: TypedArray
)  {
    var cardType: CardType
    var titleText: String
    var subtitleText: String
    var backgroundColor: Int
    var textColor: Int

    // Additional attributes for IMAGE type
    var imageAttr: Int = R.drawable.default_image

    // Additional attributes for IMAGE_TEXT_TEXT type
    var iconAttr: Int = R.drawable.default_icon

    init {
        cardType = when (typedArray.getInt(R.styleable.ToolBarTemplate_type_card, 0)) {
            0 -> CardType.IMAGE
            1 -> CardType.IMAGE_TEXT
            2 -> CardType.IMAGE_TEXT_TEXT
            else -> CardType.IMAGE // Default value
        }

        titleText = typedArray.getString(R.styleable.CardTemplate_title_text) ?: "Default Title"
        subtitleText = typedArray.getString(R.styleable.CardTemplate_subtitle_text) ?: "Default Subtitle"
        backgroundColor = typedArray.getColor(
            R.styleable.CardTemplate_background_color,
            ContextCompat.getColor(context, R.color.default_background_card_color)
        )
        textColor = typedArray.getColor(
            R.styleable.CardTemplate_text_color,
            ContextCompat.getColor(context, R.color.default_card_text_color)
        )

        when (cardType) {
            CardType.IMAGE -> {
                imageAttr = typedArray.getResourceId(
                    R.styleable.CardTemplate_image_attr,
                    R.drawable.default_image
                )
            }
            CardType.IMAGE_TEXT_TEXT -> {
                iconAttr = typedArray.getResourceId(
                    R.styleable.CardTemplate_icon_attr,
                    R.drawable.default_icon
                )
            }
        }
        typedArray.recycle()
    }
}