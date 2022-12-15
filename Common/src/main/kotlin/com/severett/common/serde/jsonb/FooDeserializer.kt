package com.severett.common.serde.jsonb

import com.severett.common.model.Foo
import jakarta.json.JsonString
import jakarta.json.bind.serializer.DeserializationContext
import jakarta.json.bind.serializer.JsonbDeserializer
import jakarta.json.stream.JsonParser
import java.lang.reflect.Type

class FooDeserializer : JsonbDeserializer<Foo> {
    override fun deserialize(jsonParser: JsonParser, p1: DeserializationContext?, p2: Type?): Foo {
        var fizzVal: String? = null
        var bazzVal: List<String>? = null
        var countVal: UInt? = null
        while (jsonParser.hasNext()) {
            val event = jsonParser.next()
            if (event != JsonParser.Event.KEY_NAME) continue
            when (jsonParser.string) {
                Foo.FIZZ_FIELD -> {
                    jsonParser.next()
                    fizzVal = jsonParser.string
                }
                Foo.BAZZ_FIELD -> {
                    jsonParser.next()
                    bazzVal = jsonParser.array.getValuesAs(JsonString::class.java).map { it.string }
                }
                Foo.COUNT_FIELD -> {
                    jsonParser.next()
                    countVal = jsonParser.int.toUInt()
                }
            }
        }
        if (fizzVal != null && bazzVal != null && countVal != null) {
            return Foo(fizz = fizzVal, bazz = bazzVal, count = countVal)
        } else {
            throw IllegalStateException("'fizz', 'bazz', and 'count' must be not null")
        }
    }
}
