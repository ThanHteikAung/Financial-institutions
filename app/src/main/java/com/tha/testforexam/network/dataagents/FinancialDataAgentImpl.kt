package com.tha.testforexam.network.dataagents

import com.tha.testforexam.data.vos.UserAccountVO
import com.tha.testforexam.network.FinancialApi
import com.tha.testforexam.network.responses.AccountResponse
import com.tha.testforexam.network.responses.UserAccountResponse
import com.tha.testforexam.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object FinancialDataAgentImpl : FinancialDataAgent {

    private var mFinancialApi: FinancialApi? = null

    //network call Api state
    //connect web Api using retrofit
    init {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mFinancialApi = retrofit.create(FinancialApi::class.java)
    }

    override fun getUserAccount(
        userId: String,
        onSuccess: (UserAccountVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //getUserBanks Info
        //connect to web api(https://sample-accounts-api.herokuapp.com/users/userId)
        mFinancialApi?.getUserBanks(userId = userId)?.enqueue(
            object : Callback<UserAccountResponse> {
                //retrofit return two method(onResponse() and onFailure()) when call from network to web api
                //go to onResponse when success to connect with web api
                //go to onFailure when network fail or not connect with web api or etc..
                override fun onResponse(
                    call: Call<UserAccountResponse>,
                    response: Response<UserAccountResponse>
                ) {
                    //check again actually success or not
                    //go to if condition when success
                    //go to else condition when not success
                    if (response.isSuccessful) {
                        response.body()?.attributes?.let {
                            //return data to UI
                            onSuccess(it)
                        }
                    } else {
                        //return error message to UI
                        onFailure(response.message() ?: "")
                    }
                }

                override fun onFailure(call: Call<UserAccountResponse>, t: Throwable) {
                    //return error message to UI
                    onFailure(t.message ?: "")
                }

            }
        )
    }

    override fun getAccountList(
        userId: String,
        onSuccess: (List<AccountResponse>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //get user's account info
        //connect to web api(https://sample-accounts-api.herokuapp.com/users/userId/accounts)
        mFinancialApi?.getUserAccounts(userId = userId)?.enqueue(
            object : Callback<List<AccountResponse>> {
                //retrofit return two method(onResponse() and onFailure()) when call from network to web api
                //go to onResponse when success to connect with web api
                //go to onFailure when network fail or not connect with web api or etc..
                override fun onResponse(
                    call: Call<List<AccountResponse>>,
                    response: Response<List<AccountResponse>>
                ) {
                    //check again actually success or not
                    //go to if condition when success
                    //go to else condition when not success
                    if (response.isSuccessful) {
                        response.body()?.let { accountList ->
                            //return data to UI
                            onSuccess(accountList)
                        }
                    } else {
                        //return error message to UI
                        onFailure(response.message() ?: "")
                    }
                }

                override fun onFailure(call: Call<List<AccountResponse>>, t: Throwable) {
                    //return error message to UI
                    onFailure(t.message ?: "")
                }


            }
        )
    }

}