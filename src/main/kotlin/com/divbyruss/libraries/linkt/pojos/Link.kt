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
 * Represents a link returned by the LinkAce API.
 */
data class Link(

    /**
     * The internal ID of the link.
     */
    val id: Int,

    /**
     * The title of the link, which can be user submitted or
     * automatically generated by LinkAce.
     */
    val title: String,

    /**
     * The description of the link, which can be user submitted or
     * automatically generated by LinkAce.
     */
    val description: String?,

    /**
     * The URL that this link goes to.
     */
    val url: String,

    /**
     * The ID of the user that this link belongs to.
     */
    @SerializedName("user_id") val userId: Int,

    /**
     * The FontAwesome tag that is associated with this link. This is not the website
     * preview that is displayed on the Web UI - but generally a paperclip icon.
     */
    val icon: String,

    /**
     * Whether this link requires authentication from the original user's account in order to view.
     */
    @SerializedName("is_private") val isPrivate: Boolean = false,

    /**
     * LinkAce automatically monitors links to see if they've changed URLs, or are no longer available.
     * This field indicates whether the user has opted to disable these checks.
     */
    @SerializedName("check_disabled") val isCheckDisabled: Boolean = false,

    /**
     * Creation time of this link.
     */
    @SerializedName("created_at") val createdAt: String,

    /**
     * The last update time of this link, by the user.
     * NOTE: This is not the last time the link was updated at its source.
     */
    @SerializedName("updated_at") val updatedAt: String,

    /**
     * The time this link was deleted, used mainly when using the Trash endpoint.
     */
    @SerializedName("deleted_at") val deletedAt: String?
)
