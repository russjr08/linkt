package com.divbyruss.libraries.linkt.interfaces.http

import com.divbyruss.libraries.linkt.pojos.LinktList
import com.divbyruss.libraries.linkt.pojos.PaginatedResponse
import com.divbyruss.libraries.linkt.pojos.SortField
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ListService {

    /**
     * Requests a paginated list of all Lists that the current user has access to.
     * @param perPage The number of Lists to return within each page (-1 will return all). Default is 7.
     * @param orderBy The field to sort Lists by. Default is [SortField.ID].
     * @param orderDirection Whether the sorting should be done ascending, or descending. Default is descending.
     */
    @GET("lists")
    fun getLists(@Query("per_page") perPage: Int = 7,
                 @Query("order_by") orderBy: SortField = SortField.ID,
                 @Query("order_dir") orderDirection: SortField.OrderDirection =
                     SortField.OrderDirection.DESCENDING): Call<PaginatedResponse<LinktList>>

    /**
     * Generally used in tandem with the [getLists] method.
     * The `/api/v1/lists` endpoint is paginated, and this method allows you to specify which page you wish to obtain.
     *
     * When looking for all Lists, you would generally run [getLists] initially, then look at the
     * returned [PaginatedResponse.nextPageUrl] and extract the page number, and use it here.
     * Then continue
     * doing so until [PaginatedResponse.nextPageUrl] is null.
     */
    @GET("lists")
    fun getPagedLists(@Query("page") page: Int): Call<PaginatedResponse<LinktList>>
}