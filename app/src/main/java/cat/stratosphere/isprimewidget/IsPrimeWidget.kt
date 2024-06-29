package cat.stratosphere.isprimewidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.math.sqrt

/**
 * Implementation of App Widget functionality.
 */
class IsPrimeWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val widgetText = context.getString(if (isPrime(getToday())) R.string.appwidget_positive_text else R.string.appwidget_negative_text)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.is_prime_widget)
    views.setTextViewText(R.id.appwidget_text, widgetText)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}

fun isPrime(num: Int): Boolean {
    if (num < 2) return false
    val sqrt = sqrt(num.toDouble()).toInt()
    for (i in 2..sqrt) {
        if (num % i == 0) {
            return false
        }
    }
    return true
}

internal fun getToday(): Int {
    return LocalDate.now().year * 10000 + LocalDate.now().monthValue * 100 +LocalDate.now().dayOfMonth
}