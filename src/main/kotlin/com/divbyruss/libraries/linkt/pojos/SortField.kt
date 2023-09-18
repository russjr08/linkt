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
 * Allows for specifying one of LinkAce's built-in fields to sort by in some requests.
 */
enum class SortField(val value: String) {

    @SerializedName("id") ID("id"),
    @SerializedName("url") URL("url"),
    @SerializedName("title") TITLE("title"),
    @SerializedName("description") DESCRIPTION("description"),
    @SerializedName("is_private") IS_PRIVATE("is_private"),
    @SerializedName("status") STATUS("status"),
    @SerializedName("check_disabled") CHECK_DISABLED("check_disabled"),
    @SerializedName("created_at") CREATED_AT("created_at"),
    @SerializedName("updated_at") UPDATED_AT("updated_at");

    enum class OrderDirection(val value: String) {
        @SerializedName("asc") ASCENDING("asc"),
        @SerializedName("desc") DESCENDING("desc")
    }

}
