package com.alextroy.habrss.dto

import org.simpleframework.xml.*


@Root(name = "rss", strict = false)
data class Rss(
    @field:Element(name = "channel")
    @param:Element(name = "channel")
    val channel: Channel? = null
)

@Root(name = "channel", strict = false)
data class Channel(
    @field:ElementList(entry = "item", inline = true)
    @param:ElementList(entry = "item", inline = true)
    val entries: List<Entry>? = null
)

@Root(name = "item", strict = false)
data class Entry(
    @field:Path("description")
    @field:Text(required = false)
    @param:Path("description")
    @param:Text(required = false)
    val description: String? = null,

    @field:Path("category")
    @field:Text(required = false)
    @param:Path("category")
    @param:Text(required = false)
    val category: String? = null,

    @field:Element(name = "guid")
    @param:Element(name = "guid")
    val guid: String? = null,

    @field:Element(name = "title")
    @param:Element(name = "title")
    val title: String? = null

)