package com.douglas.mypersonalfinances.ui.manageMyTransaction

import androidx.lifecycle.*
import com.douglas.mypersonalfinances.R
import com.douglas.mypersonalfinances.data.MpfDatabase
import com.douglas.mypersonalfinances.data.model.transaction.MyTransaction
import com.douglas.mypersonalfinances.data.repository.UserAppRepository
import kotlinx.coroutines.launch

class ManageMyTransactionViewModel(private val repository: UserAppRepository) : ViewModel() {

    private val myTransactionFormState = MutableLiveData<ManageMyTransactionFormState>()
    private val myTransactionFormResult = MutableLiveData<ManageMyTransactionResult>()

    //region OBSERVABLES

    val myTransactionForm: LiveData<ManageMyTransactionFormState> = myTransactionFormState
    val myTransactionResult: LiveData<ManageMyTransactionResult> = myTransactionFormResult

    //endregion

    //region PUBLIC FUNCTION

    fun myTransactionDataChanged(category: String) {
        if (category.isEmpty()) {
            myTransactionFormState.postValue(ManageMyTransactionFormState(categoryError = R.string.errorDefault))
        } else {
            myTransactionFormState.postValue(ManageMyTransactionFormState(isDataValid = true))
        }
    }

    fun insertTransaction(myTransaction: MyTransaction) = viewModelScope.launch {
//        if (repository.insertTransaction(myTransaction).compareTo(-1) != 0) {
//            myTransactionFormResult.postValue(ManageMyTransactionResult(success = R.string.success))
//        } else {
//            myTransactionFormResult.postValue(ManageMyTransactionResult(error = R.string.errorDefault))
//        }
    }

    //endregion
}