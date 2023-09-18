/*
 * Linkt - A Kotlin/Java wrapper around the LinkAce REST API.
 * Copyright (c) 2023, Russell Richardson.
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License along
 * with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package com.divbyruss.libraries.linkt

import com.google.gson.annotations.SerializedName
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

// https://stackoverflow.com/a/35801262/1391553
class EnumConverterFactory : Converter.Factory() {
    override fun stringConverter(type: Type?, annotations: Array<out Annotation>?,
                                 retrofit: Retrofit?): Converter<*, String>? {
        if (type is Class<*> && type.isEnum) {
            return Converter<Any?, String> { value -> getSerializedNameValue(value as Enum<*>) }
        }
        return null
    }
}

fun <E : Enum<*>> getSerializedNameValue(e: E): String {
    try {
        return e.javaClass.getField(e.name).getAnnotation(SerializedName::class.java).value
    } catch (exception: NoSuchFieldException) {
        exception.printStackTrace()
    }

    return ""
}
