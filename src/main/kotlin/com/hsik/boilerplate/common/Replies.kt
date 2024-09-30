package com.hsik.boilerplate.common

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections

open class Replies<T>(
    content: Iterable<T>,
) : Iterable<T> {
    @JsonIgnore
    val collection: MutableCollection<T> = content.toMutableList()

    @JsonProperty("content")
    open fun getContent(): Collection<T> = Collections.unmodifiableCollection(collection)

    override fun iterator(): Iterator<T> = collection.iterator()
}
