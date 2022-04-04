package com.douglas.mypersonalfinances.appwidgets

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.glance.*
import androidx.glance.action.ActionParameters
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.layout.*
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.glance.text.Text
import com.douglas.mypersonalfinances.R
import com.douglas.mypersonalfinances.appwidgets.MyAppWidget.Companion.ACTION_RECORD
import com.douglas.mypersonalfinances.appwidgets.MyAppWidget.Companion.TAG

class MyAppWidget : GlanceAppWidget() {

    companion object {
        const val TAG = "MyAppWidget"
        const val ACTION_RECORD = "ACTION_RECORD"
    }

    @Composable
    override fun Content() {
        WidgetContent()
    }
}

data class TimeItem(val text: String)

@Preview(showBackground = true)
@Composable
fun WidgetContent() {
    val context = LocalContext.current

    // TODO viewmodel
    val times = remember { mutableStateListOf<TimeItem>() }

    val prefs = currentState<Preferences>()
    val timeStatus = prefs[booleanPreferencesKey(ACTION_RECORD)] ?: false
    Column(modifier = GlanceModifier.fillMaxSize().background(R.color.white)) {
        HeaderWidget(modifier = GlanceModifier)
        BodyWidget(modifier = GlanceModifier, timeStatus)
        FooterWidget(modifier = GlanceModifier, items = times)
    }
}

@Composable
fun HeaderWidget(modifier: GlanceModifier) {
    Row(modifier = GlanceModifier.fillMaxWidth()) {
        Image(provider = ImageProvider(R.drawable.ic_balance_wallet), contentDescription = "Logo")
        Spacer(modifier = GlanceModifier.width(50.dp))
        Image(
            provider = ImageProvider(R.drawable.ic_refresh), contentDescription = "Refresh",
            GlanceModifier.clickable(onClick = actionRunCallback<RefreshAction>())
        )
    }
}

@Composable
fun BodyWidget(modifier: GlanceModifier, timeStatus: Boolean) {
    Column(modifier = GlanceModifier.fillMaxWidth()) {
        Text(text = "Lease company")
        Image(
            provider = ImageProvider(R.drawable.ic_start_record),
            contentDescription = "Record button",
            modifier = GlanceModifier.clickable(onClick = actionRunCallback<StartTimeAction>())
        )
    }
}

@Composable
fun FooterWidget(modifier: GlanceModifier, items: List<TimeItem>) {
    LazyColumn {
        items(items) { item ->
            ItemTimes(item)
        }
    }
}

@Composable
fun ItemTimes(times: TimeItem) {
    Text(
        text = times.text,
        modifier = GlanceModifier.fillMaxWidth()
    )
}

class MyAppWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = MyAppWidget()
}

class RefreshAction : ActionCallback {
    override suspend fun onRun(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        Log.e(TAG, "Click refresh")
    }
}

class StartTimeAction : ActionCallback {
    override suspend fun onRun(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        Log.e(TAG, "Start time")
        updateAppWidgetState(context, PreferencesGlanceStateDefinition, glanceId) {
            it.toMutablePreferences()
                .apply {
                    this[booleanPreferencesKey(ACTION_RECORD)] = true
                }
        }
        MyAppWidget().update(context = context, glanceId = glanceId)
    }
}

class StopTimeAction : ActionCallback {
    override suspend fun onRun(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        Log.e(TAG, "Stop time")
        updateAppWidgetState(context, PreferencesGlanceStateDefinition, glanceId) {
            it.toMutablePreferences()
                .apply {
                    this[booleanPreferencesKey(ACTION_RECORD)] = false
                }
        }
        MyAppWidget().update(context = context, glanceId = glanceId)
    }
}