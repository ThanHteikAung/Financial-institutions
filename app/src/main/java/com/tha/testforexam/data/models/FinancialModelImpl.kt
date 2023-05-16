package com.tha.testforexam.data.models

import com.tha.testforexam.data.vos.UserAccountVO
import com.tha.testforexam.network.dataagents.FinancialDataAgent
import com.tha.testforexam.network.dataagents.FinancialDataAgentImpl
import com.tha.testforexam.network.responses.AccountResponse

object FinancialModelImpl : FinancialModel {

    private val mFinancialDataAgent: FinancialDataAgent = FinancialDataAgentImpl

    override fun getUserAccount(
        userId: String,
        onSuccess: (UserAccountVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //call and pass userId to DataAgent
        mFinancialDataAgent.getUserAccount(
            userId = userId,
            onSuccess,
            onFailure
        )
    }

    override fun getAccountList(
        userId: String,
        onSuccess: (List<AccountResponse>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //call and pass userId to DataAgent
        mFinancialDataAgent.getAccountList(
            userId = userId,
            onSuccess,
            onFailure
        )
    }
}