package com.tha.testforexam.activites

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.tha.testforexam.data.models.FinancialModel
import com.tha.testforexam.data.models.FinancialModelImpl
import com.tha.testforexam.data.vos.UserAccountVO
import com.tha.testforexam.databinding.ActivityMainBinding
import com.tha.testforexam.mvp.presenters.MainPresenter
import com.tha.testforexam.mvp.presenters.MainPresenterImpl
import com.tha.testforexam.mvp.views.MainView
import com.tha.testforexam.network.responses.AccountResponse

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var binding: ActivityMainBinding
    private val mFinancialModel: FinancialModel = FinancialModelImpl
    private lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpPresenter()
        setOnClickListener()
    }

    //setUp Presenter
    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[MainPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    //request data to WebApi
    private fun requestData() {
        //get User Account info(id,name,account_ids)
        //go to onSuccess when found userID
        //show error message when not found userId
        mFinancialModel.getUserAccount(
            userId = binding.txtInputUserId.text.toString(),
            onSuccess = {
                bindUserAccountData(it)
            },
            onFailure = {
                showError(it)
            }
        )

        //get accountList info(id,user_id,name,balance)
        //go to onSuccess when found userID
        //show error message when not found userId
        mFinancialModel.getAccountList(
            userId = binding.txtInputUserId.text.toString(),
            onSuccess = { accountList ->
                bindAccountsData(accountList)
            },
            onFailure = {
                showError(it)
            }
        )

    }

    //Do action when click button(Search,clear)
    private fun setOnClickListener() {
        //Go to onTapSearchBtn() method when click Search button
        binding.btnSearch.setOnClickListener {
            mPresenter.onTapSearchBtn()
        }

        //Go to onTapClearBtn() method when click clear button
        binding.btnClear.setOnClickListener {
            mPresenter.onTapClearBtn()
            binding.txtInputUserId.text?.clear()
        }
    }

    //bind user name when get userData from Web Api
    private fun bindUserAccountData(userData: UserAccountVO) {
        binding.txtName.text = userData.name
    }

    //bind user's account list(all financial and total balance)
    private fun bindAccountsData(accountsData: List<AccountResponse>) {
        var strAllFinancial = ""
        var dblTotalBalance = 0.0
        //loop condition for when user have more than one account
        for (account in accountsData) {
            account.attributes?.let { accountVO ->
                strAllFinancial =
                    "$strAllFinancial Account ID = ${accountVO.id}\n User ID = ${accountVO.userId}" +
                            "\n AccountName = ${accountVO.accountName}" +
                            "\n Balance = ${accountVO.balance}"

                dblTotalBalance += accountVO.balance
            }
            strAllFinancial = "$strAllFinancial\n==============\n"
        }
        //bind all financial instruction data
        binding.txtAllFinancial.text = strAllFinancial

        //bind total balance
        binding.txtTotalBalance.text = dblTotalBalance.toString()
    }

    //for show userAccount
    override fun showUserAccountData() {
        requestData()
    }

    //clear username,all financial data and total balance when click clear button
    override fun clearText() {
        binding.txtName.text = ""
        binding.txtAllFinancial.text = ""
        binding.txtTotalBalance.text = ""
    }

    //show Error message when not found UserId
    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        clearText()
    }
}