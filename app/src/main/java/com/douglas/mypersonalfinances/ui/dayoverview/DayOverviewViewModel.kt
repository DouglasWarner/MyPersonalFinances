package com.douglas.mypersonalfinances.ui.dayoverview

import androidx.lifecycle.*
import com.douglas.mypersonalfinances.data.MpfDatabase
import com.douglas.mypersonalfinances.data.repository.UserAppRepository
import kotlinx.coroutines.launch
import org.joda.time.LocalDateTime

class DayOverviewViewModel(private val repository: UserAppRepository) : ViewModel() {

    private val displayDate = MutableLiveData(LocalDateTime.now())
//    private val listTransactionData = MutableLiveData(
//        DayTransactionResult(
//            listExpense = repository.expenseTransactionList.asLiveData().value,
//            listIncome = repository.incomeTransactionList.asLiveData().value
//        )
//    )

    val displayDateResult: LiveData<LocalDateTime> = displayDate
//    val listDataResult: LiveData<DayTransactionResult> = listTransactionData

    //region PUBLIC


    fun loadExpenses() {
//        viewModelScope.launch {
//            repository.expenseTransactionList.collect { expenses ->
//                listTransactionData.postValue(
//                    DayTransactionResult(
//                        listExpense = expenses
//                    )
//                )
//            }
//        }
    }

    fun loadIncomes() {
//        viewModelScope.launch {
//            repository.incomeTransactionList.collect { incomes ->
//                listTransactionData.postValue(
//                    DayTransactionResult(
//                        listIncome = incomes
//                    )
//                )
//            }
//        }
    }

    fun nextDay() {
        displayDate.value?.let { localDate ->
            localDate.plusDays(1)
            displayDate.postValue(localDate)
        }
    }

    fun lastDay() {
        displayDate.value?.let { localDate ->
            localDate.minusDays(1)
            displayDate.postValue(localDate)
        }
    }

    //endregion
}