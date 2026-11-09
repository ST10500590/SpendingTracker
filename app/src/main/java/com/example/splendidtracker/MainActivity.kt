package com.example.splendidtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.splendidtracker.ui.theme.SplendidTrackerTheme


import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import com.example.splendidtracker.ui.theme.SplendidTrackerTheme
import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.content.res.ColorStateList
import android.text.InputType
import android.widget.ScrollView
import java.util.Locale

class MainActivity : Activity() {


    // APPLICATION COLOUR SCHEME


    private val navy = Color.parseColor("#102A43")
    private val blue = Color.parseColor("#1D4ED8")
    private val gold = Color.parseColor("#F4B942")
    private val lightBackground = Color.parseColor("#F4F7FB")
    private val white = Color.WHITE
    private val darkText = Color.parseColor("#243B53")
    private val greyText = Color.parseColor("#627D98")


    //  FOUR PARALLEL ARRAYS
    // Each array contains seven elements


    private val dates = arrayOf(
        "2026-10-07",
        "2026-10-08",
        "2026-10-09",
        "2026-10-10",
        "2026-10-11",
        "2026-10-12",
        "2026-10-13"
    )

    private val dailyBudget = doubleArrayOf(
        500.0, 250.0, 250.0, 300.0,
        200.0, 350.0, 400.0
    )

    private val dailySpent = doubleArrayOf(
        340.0, 250.0, 300.0, 150.0,
        100.0, 200.0, 350.0
    )

    private val comments = arrayOf(
        "Grocery shopping",
        "Takeaways",
        "Petrol",
        "Transport",
        "Lunch",
        "Shopping",
        "Household items"
    )


    // INITIALISE APPLICATION


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showSplashScreen()
    }


    // REUSABLE SCREEN LAYOUT


    private fun createLayout(): LinearLayout {

        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.TOP
            setPadding(24, 30, 24, 30)
            setBackgroundColor(lightBackground)
        }
    }

    // Makes screens scrollable
    private fun displayScreen(layout: LinearLayout) {

        val scrollView = ScrollView(this).apply {
            isFillViewport = true
            setBackgroundColor(lightBackground)
            addView(layout)
        }

        setContentView(scrollView)
    }


    // REUSABLE HEADING


    private fun addHeading(
        layout: LinearLayout,
        message: String
    ) {

        val heading = TextView(this).apply {
            text = message
            textSize = 26f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(navy)
            gravity = Gravity.CENTER
            setPadding(10, 20, 10, 12)
        }

        layout.addView(heading)
    }


    // REUSABLE BUTTON


    private fun addButton(
        layout: LinearLayout,
        buttonText: String,
        action: () -> Unit
    ) {

        val button = Button(this).apply {
            text = buttonText
            textSize = 15f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(navy)

            backgroundTintList =
                ColorStateList.valueOf(gold)

            isAllCaps = false

            setPadding(15, 12, 15, 12)

            setOnClickListener {
                action()
            }
        }

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        params.setMargins(0, 10, 0, 10)

        layout.addView(button, params)
    }


    //INFORMATION CARD


    private fun addInfoCard(
        layout: LinearLayout,
        message: String
    ): TextView {

        val card = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(18, 16, 18, 16)

            background = GradientDrawable().apply {
                setColor(white)
                cornerRadius = 20f
                setStroke(
                    2,
                    Color.parseColor("#E2E8F0")
                )
            }
        }

        val content = TextView(this).apply {
            text = message
            textSize = 15f
            setTextColor(darkText)
            setLineSpacing(5f, 1f)
        }

        card.addView(content)

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        params.setMargins(0, 8, 0, 8)

        layout.addView(card, params)

        return content
    }

    // Formats monetary values to two decimal places
    private fun money(amount: Double): String {
        return String.format(Locale.getDefault(), "%.2f", amount)
    }


    // SPLASH SCREEN


    private fun showSplashScreen() {

        val layout = createLayout()

        layout.gravity = Gravity.CENTER
        layout.setBackgroundColor(navy)

        val title = TextView(this).apply {
            text = "SPENDING TRACKER"
            textSize = 80f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(gold)
            gravity = Gravity.CENTER
        }

        val description = TextView(this).apply {
            text = "Manage your money.\nTrack your progress."
            textSize = 17f
            setTextColor(white)
            gravity = Gravity.CENTER
            setPadding(10, 20, 10, 10)
        }

        layout.addView(title)
        layout.addView(description)

        setContentView(layout)

        // Navigate to main screen after 3 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            showMainScreen()
        }, 3000)
    }


    // MAIN SCREEN

    private fun showMainScreen() {

        val layout = createLayout()

        addHeading(layout, "Spending Tracker")

        val subtitle = TextView(this).apply {
            text = "Manage your money. Track your progress."
            textSize = 15f
            setTextColor(greyText)
            gravity = Gravity.CENTER
            setPadding(5, 5, 5, 25)
        }

        layout.addView(subtitle)

        // Navigation buttons
        addButton(layout, "View All Spending") {
            showViewScreen()
        }

        addButton(layout, "Calculate Remaining Budget") {
            showCalculateScreen()
        }

        addButton(layout, "EXIT") {
            finish()
        }

        displayScreen(layout)
    }


    // VIEW SPENDING SCREEN


    private fun showViewScreen() {

        val layout = createLayout()

        addHeading(layout, "Weekly Spending Details")

        // Loop through the seven parallel array elements
        for (i in dates.indices) {

            val details = """
                DATE: ${dates[i]}

                Daily Budget: R${money(dailyBudget[i])}
                Daily Spent: R${money(dailySpent[i])}

                Comment: ${comments[i]}
            """.trimIndent()

            // Display each day's information in a card
            addInfoCard(layout, details)
        }

        addButton(layout, "Back to Main Screen") {
            showMainScreen()
        }

        displayScreen(layout)
    }


    // CALCULATE REMAINING BUDGET SCREEN


    private fun showCalculateScreen() {

        val layout = createLayout()

        addHeading(layout, "Remaining Budget")

        val results = addInfoCard(
            layout,
            "Click Calculate Budget to view your results."
        )

        addButton(layout, "Calculate Budget") {

            var totalBudget = 0.0
            var totalSpent = 0.0

            var output = ""

            // Calculate the remaining amount for each day
            for (i in dates.indices) {

                val remaining =
                    dailyBudget[i] - dailySpent[i]

                output += """
                    DATE: ${dates[i]}

                    Budget: R${money(dailyBudget[i])}
                    Spent: R${money(dailySpent[i])}
                    Remaining: R${money(remaining)}

                    -------------------------
                    
                """.trimIndent() + "\n"

                // Add values to weekly totals
                totalBudget += dailyBudget[i]
                totalSpent += dailySpent[i]
            }

            val weeklyRemaining = totalBudget - totalSpent

            // Display the weekly financial summary
            output += """

                WEEKLY SUMMARY

                Total Budget: R${money(totalBudget)}

                Total Spent: R${money(totalSpent)}

                Total Remaining: R${money(weeklyRemaining)}

            """.trimIndent()

            results.text = output
        }

        addButton(layout, "Back to Main Screen") {
            showMainScreen()
        }

        displayScreen(layout)
    }

}