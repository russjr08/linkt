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

package com.divbyruss.libraries.linkt

import com.divbyruss.libraries.linkt.interfaces.http.LinkService
import com.divbyruss.libraries.linkt.pojos.Link
import com.divbyruss.libraries.linkt.pojos.Links
import com.divbyruss.libraries.linkt.pojos.SortField
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.system.exitProcess

/**
 * Entry point into the Linkt library, used to obtain an instance of Retrofit
 * in order to call the LinkAce library.
 *
 * @param baseUrl The "base" of the URL pointing to the LinkAce server you wish
 * to connect to. This should **not** include the `/api/v1/` suffix, as Linkt will
 * add it for you.
 *
 * @param apiKey The API Key that will be used to authorize requests to the specified
 * LinkAce server.
 */
class LinktClient(private val baseUrl: String, apiKey: String) {

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(LinktRequestInterceptor(apiKey))
        .build()

    private val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    /**
     * Retrieves an instance of the Retrofit client, which will be used to make
     * requests to the LinkAce API.
     *
     * @return An instance of [Retrofit], configured to communicate with specified
     * LinkAce API.
     */
    fun getClient(): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("$baseUrl/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(EnumConverterFactory())
            .build()

}

/**
 * The Request [Interceptor] that Linkt uses to make sure that all requests to LinkAce
 * have the correct authorization header attached. Also logs requests to stdout.
 */
class LinktRequestInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header(
                "Authorization", "Bearer $apiKey"
            )
            .build()
        println("[Linkt] Making ${request.method()} request over to: ${request.url().url().toString()}")
        println("Query parameters: ${request}")

        return chain.proceed(request)
    }

}

fun main() {
    // Grab the LinkAce host and API Key from the program's environmental variables
    val baseUrl = System.getenv("LA_HOST") ?: error("You must provide the LA_HOST env variable!")
    val apiKey = System.getenv("LA_API_KEY") ?: error("You must provide the LA_API_KEY env variable!")

    // Initialize the Linkt library, and create a LinkService to work with
    val testClient = LinktClient(baseUrl, apiKey)
    val linkService = testClient.getClient().create(LinkService::class.java)

    // Send off a request to start grabbing all links from LinkAce
    val resp = linkService.getLinks().execute()

    val body: Links? = resp.body()

    var nextPageUrl: String? // LinkAce's API uses pagination, so expect to possibly iterate over the pages
    val links: ArrayList<Link> = ArrayList()

    nextPageUrl = body?.nextPageUrl

    body?.links?.let { links.addAll(it) } // Add all links from Page #1 to our collection of links

    // Iterate through all the pages returned by the API until we no longer have any more pages
    while(nextPageUrl != null) {
        val nextPageIndex = HttpUrl.parse(nextPageUrl)?.queryParameter("page")?.toInt() ?: break

        val pageRequest = linkService.getPagedLinks(nextPageIndex).execute()
        val pageBody = pageRequest.body()

        nextPageUrl = pageBody?.nextPageUrl
        pageBody?.links?.let { links.addAll(it) }
    }

    println(links) // Print all the found links

    // Test the LinkService.getLinksWithParams method
    var linksWithParamsResp = linkService.getLinksWithParams(7, SortField.TITLE, SortField.OrderDirection.ASCENDING)
        .execute()
    if(!linksWithParamsResp.isSuccessful) {
        error(linksWithParamsResp.errorBody()!!.string())
    }
    val linksWithParamsBody: Links? = resp.body()

    println(linksWithParamsBody.toString())

    var specificLinkReq = linkService.getLink(5).execute()

    if(!specificLinkReq.isSuccessful) {
        error(specificLinkReq.errorBody()!!.string())
    }

    val specificLinkBody: Link? = specificLinkReq.body()

    println(specificLinkBody.toString())

    exitProcess(0) // And that's a wrap!
}
