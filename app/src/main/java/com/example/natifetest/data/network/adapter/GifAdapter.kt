package com.example.natifetest.data.network.adapter

import com.example.natifetest.data.model.Gif
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class GifAdapter : JsonDeserializer<Gif> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Gif {
        val obj = json?.asJsonObject
            ?.takeIf { it.isJsonNull.not() }

        val id = obj?.get("id")
            ?.takeIf { it.isJsonNull.not() }
            ?.asString ?: ""

        val url = obj?.get("images")
            ?.takeIf { it.isJsonNull.not() }
            ?.asJsonObject
            ?.get("fixed_height")
            ?.takeIf { it.isJsonNull.not() }
            ?.asJsonObject
            ?.get("url")
            ?.takeIf { it.isJsonNull.not() }
            ?.asString
            ?: ""

        return Gif(id, url)
    }
}