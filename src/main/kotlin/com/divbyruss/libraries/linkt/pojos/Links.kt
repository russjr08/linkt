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

package com.divbyruss.libraries.linkt.pojos

import com.google.gson.annotations.SerializedName

/**
 * Represents the response from LinkAce's `/api/v1/links` endpoint, which contains
 * a paginated list of [Link] objects known by LinkAce, along with some metadata and data
 * around how to navigate the pages returned from the API.
 */
data class Links(

    /**
     * The total number of links known by LinkAce.
     */
    val total: Int,

    /**
     * The starting index of the returned Links from LinkAce.
     */
    val from: Int,

    /**
     * The ending index of the returned Links from LinkAce.
     */
    val to: Int,

    /**
     * A URL to the previous page of Links through the API.
     */
    @SerializedName("prev_page_url") val prevPageUrl: String?,

    /**
     * The number of Links returned by each page via the API.
     */
    @SerializedName("per_page") val perPage: String,

    /**
     * A URL to the next page of Links through the API.
     */
    @SerializedName("next_page_url") val nextPageUrl: String?,

    /**
     * A URL to the last page of Links through the API.
     */
    @SerializedName("last_page") val lastPage: Int,

    /**
     * A URL to the first page of Links through the API.
     */
    @SerializedName("first_page_url") val firstPageUrl: String?,

    /**
     * The collection of Links returned in this page. Represented
     * by a [List] of [Link] objects.
     */
    @SerializedName("data") val links: List<Link>
)
