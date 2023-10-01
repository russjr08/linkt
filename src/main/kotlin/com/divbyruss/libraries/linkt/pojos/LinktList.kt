package com.divbyruss.libraries.linkt.pojos


/**
 * Represents a List within Linkt.
 * The class name is `LinktList` because calling it `List` clashes with
 * quite a few things, as you can imagine.
 */
data class LinktList(
    /**
     * The internal/database ID of this List.
     */
    val id: Int,

    /**
     * The owner of this List.
     */
    val userId: Int,

    /**
     * The name of this List.
     */
    val name: String,

    /**
     * The description of this List, if set.
     */
    val description: String?,

    /**
     * Whether the owner can only access this List.
     */
    val isPrivate: Boolean,

    /**
     * The timestamp of when this List was created.
     */
    val createdAt: String,

    /**
     * The timestamp of when this List was last updated.
     */
    val updatedAt: String,

    /**
     * If this List has been deleted, this will contain the timestamp
     * of when the deletion occurred.
     */
    val deletedAt: String?
)
