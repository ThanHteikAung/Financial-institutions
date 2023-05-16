package com.tha.testforexam.network.responses

import com.google.gson.annotations.SerializedName
import com.tha.testforexam.data.vos.AccountVO

data class AccountResponse(

    @SerializedName("attributes")
    val attributes: AccountVO


)
