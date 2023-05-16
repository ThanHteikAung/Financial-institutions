package com.tha.testforexam.network.responses

import com.google.gson.annotations.SerializedName
import com.tha.testforexam.data.vos.UserAccountVO

data class UserAccountResponse(

    @SerializedName("attributes")
    val attributes: UserAccountVO?
)