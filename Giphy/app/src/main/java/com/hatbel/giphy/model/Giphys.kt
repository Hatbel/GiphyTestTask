package com.hatbel.giphy.model

import com.google.gson.annotations.Expose

data class Giphys(
    @Expose(serialize = false, deserialize = true)
    var data : List<GiphyImage>,
    var pagination: Pagination,
    var meta: Meta
)

data class GiphyImage(
    @Expose(serialize = false, deserialize = true)
    var url: String,
    var images: Images
)
data class Meta(
    @Expose(serialize = false, deserialize = true)
    var status: Int,
    var msg: String,
    var response_id: String
)

data class Pagination(
    @Expose(serialize = false, deserialize = true)
    var total_count: Int,
    var count: Int,
    var offset: Int
)
data class Images(
    @Expose(serialize = false, deserialize = true)
    val fixed_height: FixedHeight
)
data class FixedHeight(
    @Expose(serialize = false, deserialize = true)
    val url: String
)
