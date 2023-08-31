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

import com.divbyruss.libraries.linkt.interfaces.http.LinkService
import com.squareup.moshi.Json

/**
 * Represents a new link that will be used when invoking
 * [LinkService.createNewLink]
 */
data class NewLink(

    /**
     * The URL that this new link will point to.
     */
    val url: String,

    /**
     * Allows setting a title for the link. If not set,
     * LinkAce will generally try to create one for you,
     * based on the title of the page when navigated to.
     */
    val title: String?,

    /**
     * Allows setting a description for the link. If not set,
     * LinkAce will generally try to create one for you,
     * based on the first block found on the page when
     * navigated to.
     */
    val description: String?,

    // TODO Implement List and Tag POJOs, then add these in.
    /** val lists: List<List>, **/
    /** val tags: List<Tag>, **/

    /**
     * Allows setting whether this link should require authentication,
     * making it visible only to the user who created it. If not set,
     * LinkAce will set it to whatever is specified by the user's
     * preferences.
     */
    @Json(name = "is_private") val isPrivate: Boolean,

    /**
     * Allows setting whether LinkAce should try to monitor this link
     * for notifying if a link is no longer available (or has moved).
     *
     * @see Link.isCheckDisabled
     */
    @Json(name = "check_disabled") val isCheckDisabled: Boolean

)