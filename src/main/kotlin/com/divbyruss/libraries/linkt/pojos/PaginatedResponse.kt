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
 * When querying most collection of objects from LinkAce, it is returned wrapped in pagination.
 * Since the format of this response is standardized across the API, this class represents that format,
 * and takes a generic type, [T] which is the type of object that will be in the `data` field of the API response.
 */
data class PaginatedResponse<T>(
    /**
     * The total number of objects known by LinkAce, representing this type.
     */
    val total: Int,

    /**
     * The starting index of the returned objects from LinkAce.
     */
    val from: Int,

    /**
     * The ending index of the returned objects from LinkAce.
     */
    val to: Int,

    /**
     * A URL to the previous page of objects through the API.
     */
    val prevPageUrl: String?,

    /**
     * The number of objects returned by each page via the API.
     */
    val perPage: String,

    /**
     * A URL to the next page of objects through the API.
     */
    val nextPageUrl: String?,

    /**
     * A URL to the last page of objects through the API.
     */
    val lastPage: Int,

    /**
     * A URL to the first page of objects through the API.
     */
    val firstPageUrl: String?,

    /**
     * The collection of objects returned in this page. Represented
     * by a [List] of the generic type passed in, [T], objects.
     */
    @SerializedName("data") val objects: List<T>
)
