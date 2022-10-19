package com.codebox.speedrun.domain.networking.core.adapters

import com.codebox.speedrun.data.common.enums.RunTimeEnum
import com.squareup.moshi.*
import javax.inject.Inject

class RunTimeEnumAdapter @Inject constructor() : JsonAdapter<RunTimeEnum?>() {
    @FromJson
    override fun fromJson(reader: JsonReader): RunTimeEnum {
        val str = reader.readJsonValue()?.toString()
        return RunTimeEnum.fromJsonValue(str)
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: RunTimeEnum?) {
        if (value != null) writer.value(value.jsonValue)
        else writer.nullValue()
    }
}

