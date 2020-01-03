/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.tipcalculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main Tip Calculator Screen
 */
class MainActivity : AppCompatActivity() {


  override fun onCreate(savedInstanceState: Bundle?) {
    // Switch to AppTheme for displaying the activity
    setTheme(R.style.AppTheme)

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Text changed listener for tip percentage
    tipPercentage.addTextChangedListener(object : TextWatcher {
      override fun afterTextChanged(s: Editable?) {
        if (s.toString().isNotEmpty() && billAmount.text.isNotEmpty()) {

          // Max tip % cannot be greater than 100
          if (s.toString().toInt() > 100) {
            tipPercentage.setText(getString(R.string.default_percent_exceeded))
          }
          calculateTip()
        }
      }

      override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

      override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    })

    // Text changed listener for bill amount
    billAmount.addTextChangedListener(object : TextWatcher {
      override fun afterTextChanged(s: Editable?) {
        if (s.toString().isNotEmpty() && tipPercentage.text.isNotEmpty()) {
          calculateTip()
        }
      }

      override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

      override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    })
  }

  private fun calculateTip() {
    val tipPercentValue = tipPercentage.text.toString().toDouble()
    val billAmountValue = billAmount.text.toString().toDouble()
    val tipAmountValue = (tipPercentValue / 100) * billAmountValue
    val finalAmount = billAmountValue + tipAmountValue
    tipAmount.text = String.format("%.2f", tipAmountValue)
    totalAmount.text = String.format("%.2f", finalAmount)
  }
}
