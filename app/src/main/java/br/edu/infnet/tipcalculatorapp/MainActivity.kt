package br.edu.infnet.tipcalculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import java.text.NumberFormat

class MainActivity : AppCompatActivity(), View.OnFocusChangeListener, SeekBar.OnSeekBarChangeListener {
    private lateinit var txtBalance : EditText
    private lateinit var txtCustomers : EditText
    private lateinit var skTip : SeekBar
    private val formatter = NumberFormat. getCurrencyInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // render layout

        txtBalance = this.findViewById<EditText>(R.id.txtBalance)
        txtBalance.onFocusChangeListener = this

        txtCustomers = this.findViewById<EditText>(R.id.txtCustomers)
        txtCustomers.onFocusChangeListener = this

        skTip = this.findViewById<SeekBar>(R.id.skTip)
        skTip.setOnSeekBarChangeListener(this)
    }

    override fun onFocusChange(p0: View?, p1: Boolean) {
        this.updateBill()
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        var lblTipPercentage = this.findViewById<TextView>(R.id.lblTipPercentage)
        lblTipPercentage.text = skTip.progress.toString() + "%"
        //-------------------------------------------------
        this.updateBill()
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }

    private fun updateBill() {
        if(txtBalance.text.toString().isNotEmpty() && txtCustomers.text.toString().isNotEmpty()) {
            var billValue = txtBalance.text.toString().toDouble()
            var customers = txtCustomers.text.toString().toInt()
            //-------------------------------------------------
            var lblFinalTipAmount = this.findViewById<TextView>(R.id.textView4)
            var finalTipAmount = billValue * skTip.progress / 100
            lblFinalTipAmount.text = formatter.format(finalTipAmount)
            //-------------------------------------------------
            var lblFinalBillAmount = this.findViewById<TextView>(R.id.textView5)
            lblFinalBillAmount.text = formatter.format(billValue + finalTipAmount)
            var lblFinalAmountPerCustomer = this.findViewById<TextView>(R.id.textView6)
            lblFinalAmountPerCustomer.text = formatter.format((billValue + finalTipAmount) / customers)
        }
    }
}