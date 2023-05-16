package com.tha.testforexam.data.models

import com.tha.testforexam.data.vos.UserAccountVO
import com.tha.testforexam.network.responses.AccountResponse

interface FinancialModel {
    fun getUserAccount(
        userId: String,
        onSuccess: (UserAccountVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getAccountList(
        userId: String,
        onSuccess: (List<AccountResponse>) -> Unit,
        onFailure: (String) -> Unit
    )
}