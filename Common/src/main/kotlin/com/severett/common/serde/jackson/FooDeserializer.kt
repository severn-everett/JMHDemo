package com.severett.common.serde.jackson

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.node.ArrayNode
import com.severett.common.model.Foo

class FooDeserializer : StdDeserializer<Foo>(Foo::class.java) {
    override fun deserialize(jsonParser: JsonParser, p1: DeserializationContext?): Foo {
        val node = jsonParser.codec.readTree<JsonNode>(jsonParser)
        return Foo(
            fizz = node[Foo.FIZZ_FIELD].textValue(),
            bazz = (node[Foo.BAZZ_FIELD] as ArrayNode).map { it.textValue() },
            count = node[Foo.COUNT_FIELD].numberValue().toInt().toUInt()
        )
    }
}
