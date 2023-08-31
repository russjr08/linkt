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

import com.divbyruss.libraries.linkt.pojos.Links
import com.divbyruss.libraries.linkt.pojos.NewLink
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

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
     * Generally used in tandem with the [getLinks] method. The `/api/v1/links` endpoint
     * is paginated, and this method allows you to specify which page you wish to obtain.
     *
     * WHen looking for all Links, you would generally run [getLinks] initially, then look at the
     * returned [Links.nextPageUrl] and extract the page number, and use it here. Then continue
     * doing so until [Links.nextPageUrl] is null.
     */
    @GET("links")
    fun getPagedLinks(@Query("page") page: Int): Call<Links>

    fun createNewLink(@Body link: NewLink)
}