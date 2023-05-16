package com.tha.testforexam.network

import com.tha.testforexam.network.responses.AccountResponse
import com.tha.testforexam.network.responses.UserAccountResponse
import com.tha.testforexam.utils.API_ACCOUNTS
import com.tha.testforexam.utils.API_USER
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FinancialApi {
    //Network call Api setUp
    @GET("$API_USER/{user_id}")
    fun getUserBanks(
        @Path("user_id") userId: String,
    ): Call<UserAccountResponse>

    @GET("$API_USER/{user_id}$API_ACCOUNTS")
    fun getUserAccounts(
        @Path("user_id") userId: String,
    ): Call<List<AccountResponse>>


}