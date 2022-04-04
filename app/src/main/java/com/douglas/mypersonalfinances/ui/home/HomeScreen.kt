package com.douglas.mypersonalfinances.ui.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.douglas.mypersonalfinances.data.model.transaction.MyTransaction

@ExperimentalAnimationApi
@Composable
fun HomeScreen(
    navController: NavHostController?,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "My transactions",
            fontSize = MaterialTheme.typography.h4.fontSize
        )
    }
}

@Composable
fun ListTransaction(data: MonthDataResult) {
    data.monthData?.let { monthData ->
        Column(Modifier.fillMaxSize()) {
            Text(
                text = monthData.month,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Divider(thickness = 5.dp)

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(monthData.expensesList) { expense ->
                    TransactionItem(item = expense)
                }
            }
        }
    }
}

@Composable
fun TransactionItem(item: MyTransaction) {
    Column {
        Text(text = item.dateTime.toString())
        Text(text = item.category)
        Text(text = item.amount.toString())
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = null)
}