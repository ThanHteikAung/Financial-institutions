package com.tha.testforexam.network.dataagents

import com.tha.testforexam.data.vos.UserAccountVO
import com.tha.testforexam.network.responses.AccountResponse

interface FinancialDataAgent {

    fun getUserAccount(
        userId: String,
        onSuccess: (UserAccountVO) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun getAccountList(
        userId: String,
        onSuccess: (List<AccountResponse>) -> Unit,
        onFailure: (String) -> Unit
    )

}