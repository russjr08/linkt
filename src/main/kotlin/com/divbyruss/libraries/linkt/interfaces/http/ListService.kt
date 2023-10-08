package com.divbyruss.libraries.linkt.interfaces.http

import com.divbyruss.libraries.linkt.pojos.LinktList
import com.divbyruss.libraries.linkt.pojos.NewList
import com.divbyruss.libraries.linkt.pojos.PaginatedResponse
import com.divbyruss.libraries.linkt.pojos.SortField
import retrofit2.Call
import retrofit2.http.*

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

    /**
     * Creates a new List with the target LinkAce instance, and returns the resultant [LinktList].
     * @param list The [NewList] to create against the LinkAce API.
     * @return The new [LinktList] that was created, if successful.
     */
    @POST("lists")
    fun createNewList(@Body list: NewList): Call<LinktList>

    /**
     * Grabs a specific [LinktList] from the LinkAce API.
     * @param listId The ID of the List that you wish to obtain from LinkAce.
     * @return The [LinktList], if found.
     */
    @GET("lists/{list_id}")
    fun getList(@Query("list_id") listId: Int): Call<LinktList>

    /**
     * Updates a specific [LinktList] with LinkAce.
     * @param listId The ID of the List to update.
     * @param list The new representation of the List to update the server-side copy to.
     * @return The resulting copy of the new List from LinkAce.
     */
    @PATCH("lists/{list_id}")
    fun updateList(@Query("list_id") listId: Int, @Body list: NewList): Call<LinktList>

    /**
     * Deletes a [LinktList] from LinkAce.
     * These are generally "soft-deleted" from the database,
     * and can be retrieved from the Trash.
     * @param listId The ID of the List to be deleted.
     */
    @DELETE("lists/{list_id}")
    fun deleteList(@Query("list_id") listId: Int): Call<Void>
}