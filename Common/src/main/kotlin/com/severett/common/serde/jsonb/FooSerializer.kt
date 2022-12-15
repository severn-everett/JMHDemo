package com.severett.common.serde.jsonb

import com.severett.common.model.Foo
import jakarta.json.bind.serializer.JsonbSerializer
import jakarta.json.bind.serializer.SerializationContext
import jakarta.json.stream.JsonGenerator

class FooSerializer : JsonbSerializer<Foo> {
    override fun serialize(foo: Foo, jsonGenerator: JsonGenerator, p2: SerializationContext?) {
        jsonGenerator.writeStartObject()
        jsonGenerator.write(Foo.FIZZ_FIELD, foo.fizz)
        jsonGenerator.writeStartArray(Foo.BAZZ_FIELD)
        foo.bazz.forEach(jsonGenerator::write)
        jsonGenerator.writeEnd()
        jsonGenerator.write(Foo.COUNT_FIELD, foo.count.toInt())
        jsonGenerator.writeEnd()
    }
}
