package com.tha.testforexam.data.vos

import com.google.gson.annotations.SerializedName

//Convert Json string to data type
data class AccountVO(
    @SerializedName("id")
    val id: String,

    @SerializedName("user_id")
    val userId: String,

    @SerializedName("name")
    val accountName: String,

    @SerializedName("balance")
    val balance: Double
)