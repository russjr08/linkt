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

package com.divbyruss.libraries.linkt.interfaces.http

import com.divbyruss.libraries.linkt.pojos.Link
import com.divbyruss.libraries.linkt.pojos.Links
import com.divbyruss.libraries.linkt.pojos.NewLink
import com.divbyruss.libraries.linkt.pojos.SortField
import retrofit2.Call
import retrofit2.http.*

/**
 * Retrofit uses this interface to generate the implementation of the various API Endpoint calls that
 * Linkt can make, specifically to LinkAce's links endpoints.
 */
interface LinkService {
    /**
     * Initiates a GET request over to `/api/v1/links`, and returns the data
     * returned by the API as a Retrofit [Call], which will contain the wrapped
     * model of this response, a [Links] object.
     */
    @GET("links")
    fun getLinks(): Call<Links>

    /**
     * Similar to [getLinks] but allows for passing in extra parameters to the LinkAce
     * API, customizing its response.
     *
     * @param perPage The number of entries to return per page, -1 returns all items.
     * @param orderBy Sorts the order of elements by a specified field type.
     * @param orderDirection Sets whether the returned elements should be in ascending
     *  or descending order.
     */
    @GET("links")
    fun getLinksWithParams(@Query("per_page") perPage: Int, @Query("order_by") orderBy: SortField,
                           @Query("order_dir") orderDirection: SortField.OrderDirection): Call<Links>

    /**
     * Generally used in tandem with the [getLinks] method. The `/api/v1/links` endpoint
     * is paginated, and this method allows you to specify which page you wish to obtain.
     *
     * WHen looking for all Links, you would generally run [getLinks] initially, then look at the
     * returned [Links.nextPageUrl] and extract the page number, and use it here. Then continue
     * doing so until [Links.nextPageUrl] is null.
     */
    @GET("links")
    fun getPagedLinks(@Query("page") page: Int): Call<Links>

    /**
     * Creates a new Link with the LinkAce API, and returns the resultant created [Link].
     * @param link The new link to create in the connected LinkAce instance.
     * @return The resulting [Link] that was created on the LinkAce instance.
     */
    @POST("links")
    fun createNewLink(@Body link: NewLink): Call<Link>

    /**
     * Gets a specific [Link] from LinkAce, by the specific ID given by LinkAce.
     * If the specified ID does not exist, a 404 response will be returned.
     * @param linkId The ID of the [Link] that you're requesting from LinkAce.
     * @return The Link, if found.
     */
    @GET("links/{link_id}")
    fun getLink(@Path("link_id") linkId: Int): Call<Link>

    /**
     * Updates a specific [Link] with LinkAce.
     * @param linkId The ID of the [Link] to be updated.
     * @param link The representation of what the link will now look like.
     * @return The updated instance of the [Link].
     */
    @PATCH("links/{link_id}")
    fun updateLink(@Path("link_id") linkId: Int, @Body link: NewLink): Call<Link>

    /**
     * Deletes a [Link] from LinkAce.
     * These are generally "soft-deleted" from the database,
     * and can be retrieved from the Trash.
     * @param linkId The ID of the Link to be deleted.
     */
    @DELETE("links/{link_id}")
    fun deleteLink(@Path("link_id") linkId: Int): Call<Void>

    /**
     * TODO: Retrieves the notes from a [Link].
     */
    @GET("links/{link_id}/notes")
    fun getNotesForLink(): Call<Void>

    /**
     * Allows searching the LinkAce database for the specified parameters.
     * @param query The query to search for Links. **Required**.
     * @param perPage The number of entries to return per page. `-1` will return all items.
     * @param searchTitle Enables searching the title of Links. Defaults to `true`.
     * @param searchDescription Enables search the description of Links. Defaults to `true`.
     * @param privateOnly Whether to search for private links only. Defaults to `false`.
     * @param brokenOnly Whether to only search for links that are "broken". Defaults to `false`.
     * @param emptyLists Whether to search for links without any lists attached. Defaults to `false`.
     * @param emptyTags Whether to search for links without any tags attached. Defaults to `false`.
     * @param onlyLists A comma-separated list of List IDs a link must have. Defaults to empty (`""`).
     * @param onlyTags A comma-separated list of Tag IDs a link must have. Defaults to empty (`""`).
     * @param orderBy Allows changing the ordering of the results. Format is `field_to_order:order_direction`.
     * @see [SortField] For the values to use to format the orderBy parameter.
     */
    @GET("search/links")
    fun searchLinks(@Query("query") query: String, @Query("per_page") perPage: Int = 7,
                    @Query("search_title") searchTitle: Boolean = true,
                    @Query("search_description") searchDescription: Boolean = true,
                    @Query("private_only") privateOnly: Boolean = false,
                    @Query("broken_only") brokenOnly: Boolean = false,
                    @Query("empty_lists") emptyLists: Boolean = false,
                    @Query("empty_tags") emptyTags: Boolean = false,
                    @Query("only_lists") onlyLists: String = "",
                    @Query("only_tags") onlyTags: String = "",
                    @Query("order_by") orderBy: String = "title:asc"): Call<Links>
}