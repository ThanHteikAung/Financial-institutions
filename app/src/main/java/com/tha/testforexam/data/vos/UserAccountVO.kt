package com.tha.testforexam.data.vos

import com.google.gson.annotations.SerializedName

//Convert Json String to data type
data class UserAccountVO(

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String?,

    @SerializedName("account_ids")
    val accountIds: List<Int>
)
