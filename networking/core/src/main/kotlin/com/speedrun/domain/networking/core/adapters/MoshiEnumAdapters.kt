package com.speedrun.domain.networking.core.adapters

import com.speedrun.data.common.enums.RunTimeEnum
import com.speedrun.data.common.enums.RunTypeEnum
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

class RunTypeEnumAdapter @Inject constructor() : JsonAdapter<RunTypeEnum?>() {
    @FromJson
    override fun fromJson(reader: JsonReader): RunTypeEnum {
        val str = reader.readJsonValue()?.toString()
        return RunTypeEnum.fromJsonValue(str)
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: RunTypeEnum?) {
        if (value != null) writer.value(value.jsonValue)
        else writer.nullValue()
    }
}
