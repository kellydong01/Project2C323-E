package com.example.project2c323_e

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    // Used for displaying in TextView at the top
    private var currentNumber : String = "0"

    // Used to store results after calculating
    var results : Double = 0.0

    // Used for dealing with first number in a operation
    var initNum : Double = 0.0

    // Used for dealing with second number after an operation is clicked
    var afterNum : Double = 0.0

    // Used for storing the operation that is going to be performed
    var operation : String = ""

    // TextView on the top of the calculator
    lateinit var displayTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayTextView = findViewById<TextView>(R.id.tvDisplay)
        displayTextView.text = currentNumber

        // Storing Buttons

        // Digit Buttons
        val b0 = findViewById<Button>(R.id.b0)
        val b1 = findViewById<Button>(R.id.b1)
        val b2 = findViewById<Button>(R.id.b2)
        val b3 = findViewById<Button>(R.id.b3)
        val b4 = findViewById<Button>(R.id.b4)
        val b5 = findViewById<Button>(R.id.b5)
        val b6 = findViewById<Button>(R.id.b6)
        val b7 = findViewById<Button>(R.id.b7)
        val b8 = findViewById<Button>(R.id.b8)
        val b9 = findViewById<Button>(R.id.b9)

        // Operation Buttons
        val bClear = findViewById<Button>(R.id.bClear)
        val bEquals = findViewById<Button>(R.id.bEquals)
        val bAdd = findViewById<Button>(R.id.bAddition)
        val bSub = findViewById<Button>(R.id.bSubtract)
        val bMul = findViewById<Button>(R.id.bMultiply)
        val bDiv = findViewById<Button>(R.id.bDivision)
        val bSign = findViewById<Button>(R.id.bSign)
        val bPercent = findViewById<Button>(R.id.bPercent)
        val bDec = findViewById<Button>(R.id.bDecimal)

        // OnClick Listeners for all buttons

        bClear.setOnClickListener {
            clear()
        }

        // OnClick Listener for Digits Button
        b0.setOnClickListener { digitButtonClick("0") }
        b1.setOnClickListener { digitButtonClick("1") }
        b2.setOnClickListener { digitButtonClick("2") }
        b3.setOnClickListener { digitButtonClick("3") }
        b4.setOnClickListener { digitButtonClick("4") }
        b5.setOnClickListener { digitButtonClick("5") }
        b6.setOnClickListener { digitButtonClick("6") }
        b7.setOnClickListener { digitButtonClick("7") }
        b8.setOnClickListener { digitButtonClick("8") }
        b9.setOnClickListener { digitButtonClick("9") }

        // OnClick listeners for operations button
        bAdd.setOnClickListener { operationsClick("+") }
        bSub.setOnClickListener { operationsClick("-") }
        bMul.setOnClickListener { operationsClick("x") }
        bDiv.setOnClickListener { operationsClick("/") }

        bPercent.setOnClickListener {
            operationsClick("%")
            equalsOperations(operation)
            initNum = results
            currentNumber = results.toString()
            displayTextView.text = results.toString()
        }

        bSign.setOnClickListener {
            currentNumber = (currentNumber.toDouble() * -1).toString()
            displayTextView.text = currentNumber
        }

        bEquals.setOnClickListener {
            afterNum = currentNumber.toDouble()
            equalsOperations(operation)
            currentNumber = results.toString()
            initNum = results
            displayTextView.text = results.toString()
        }

        bDec.setOnClickListener {
            if (!currentNumber.contains(".")){
                currentNumber += "."
                displayTextView.text = currentNumber
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        clear()
    }

    /**
     * Method used for the C Button, resets and clears display
     */
    fun clear(){
        currentNumber = "0"
        results = 0.0
        initNum = 0.0
        afterNum = 0.0
        operation = ""
        displayTextView.text = currentNumber
    }

    /**
     * Method for getting results when clicking equals
     * @param String:op - takes a string for a specific operation
     */
    fun equalsOperations (op : String) {
        when (operation){
            "+" -> results = initNum + afterNum
            "-" -> results = initNum - afterNum
            "x" -> results = initNum * afterNum
            "/" -> results = initNum / afterNum
            "%" -> results = initNum / 100.0
        }
    }

    /**
     * Normalized all click listeners onto this method action
     * @param digit - the number button that is being pressed
     */
    fun digitButtonClick (digit : String) {
        if (currentNumber == "0") currentNumber = digit
        else currentNumber += digit
        displayTextView.text = currentNumber
    }

    fun operationsClick (op : String) {
        if (currentNumber != "0") {
            initNum = currentNumber.toDouble()
            currentNumber = "0"
        }
        operation = op
    }
}