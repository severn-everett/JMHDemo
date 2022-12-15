package com.severett.common.serde.jackson

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.severett.common.model.Foo

class FooSerializer : StdSerializer<Foo>(Foo::class.java) {
    override fun serialize(foo: Foo, jsonGenerator: JsonGenerator, p2: SerializerProvider?) {
        jsonGenerator.writeStartObject()
        jsonGenerator.writeStringField(Foo.FIZZ_FIELD, foo.fizz)
        jsonGenerator.writeArrayFieldStart(Foo.BAZZ_FIELD)
        foo.bazz.forEach(jsonGenerator::writeString)
        jsonGenerator.writeEndArray()
        jsonGenerator.writeNumberField(Foo.COUNT_FIELD, foo.count.toInt())
        jsonGenerator.writeEndObject()
    }
}
