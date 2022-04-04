package com.douglas.mypersonalfinances.ui.home

import androidx.lifecycle.*
import com.douglas.mypersonalfinances.data.repository.MyPlannedRepository
import com.douglas.mypersonalfinances.data.repository.MyTransactionRepository
import com.douglas.mypersonalfinances.utils.toMonthAdapterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.joda.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val myTransactionRepository: MyTransactionRepository,
    private val myPlannedRepository: MyPlannedRepository
) : ViewModel() {

    private val monthDataOverview = MutableLiveData<MonthDataResult>()
    private val displayDate = MutableLiveData(LocalDateTime.now())

    //region OBSERVABLES

    val monthOverviewResult: LiveData<MonthDataResult> = monthDataOverview
    val displayDateResult: LiveData<LocalDateTime> = displayDate
    val allPlannedMonthData = myPlannedRepository.getAllPlanned().asLiveData()

    //endregion

    init {
        loadData()
    }

    //region PUBLIC FUNCTION

    fun loadData() {
        viewModelScope.launch {
            //TODO here need to load by year
            myTransactionRepository.getAllTransactionFromDatabase().collect { allTransaction ->
                monthDataOverview.postValue(
                    MonthDataResult(
                        monthData = ArrayList(allTransaction).toMonthAdapterItem(
                            localDate = displayDate.value ?: LocalDateTime.now()
                        )
                    )
                )
            }
        }
    }

    fun nextYear() {
        displayDate.value?.let { localDate ->
            localDate.plusDays(1)
            displayDate.postValue(localDate)
        }
    }

    fun lastYear() {
        displayDate.value?.let { localDate ->
            localDate.minusDays(1)
            displayDate.postValue(localDate)
        }
    }

    //endregion
}