package com.example.calculatorp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.calculatorp.databinding.ActivityMainBinding

private const val TAG = "signs"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var operation= ""
    private var oldNumber= ""
    private var newNumber= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btn1.setOnClickListener { clear() }
        binding.btn2.setOnClickListener { percentage() }
        binding.btn3.setOnClickListener { delete() }
        binding.btn4.setOnClickListener {
            oldNumber = binding.txtCalc.text.toString();
            appendNumber("/")
        }
        binding.btn5.setOnClickListener { appendNumber("7") }
        binding.btn6.setOnClickListener { appendNumber("8") }
        binding.btn7.setOnClickListener { appendNumber("9") }
        binding.btn8.setOnClickListener {
            oldNumber = binding.txtCalc.text.toString();
            appendNumber("x")
        }
        binding.btn9.setOnClickListener { appendNumber("4") }
        binding.btn10.setOnClickListener { appendNumber("5") }
        binding.btn11.setOnClickListener { appendNumber("6") }
        binding.btn12.setOnClickListener {
            oldNumber = binding.txtCalc.text.toString();
            appendNumber("-")
        }
        binding.btn13.setOnClickListener { appendNumber("1") }
        binding.btn14.setOnClickListener { appendNumber("2") }
        binding.btn15.setOnClickListener { appendNumber("3") }
        binding.btn16.setOnClickListener {
            oldNumber = binding.txtCalc.text.toString();
            appendNumber("+")
        }
        binding.btn17.setOnClickListener { appendNumber("0") }
        binding.btn18.setOnClickListener { appendNumber(".") }
        binding.btn19.setOnClickListener { sqrt() }
        binding.btn20.setOnClickListener { calculate() }
    }
    private fun clear() {
        binding.txtCalc.setText("0")
        oldNumber = ""
        newNumber = ""
        operation = ""
    }
    private fun  percentage() {
        val num = binding.txtCalc.text.toString().toDouble() / 100
        binding.txtCalc.setText(num.toString())
    }
    private fun delete(){
        val text = binding.txtCalc.text.toString()
        if (text.isNotEmpty()) {
            binding.txtCalc.setText(text.substring(0, text.length -1))
        }
    }
    private  fun appendNumber(number: String){
        if(binding.txtCalc.text.toString() == "0") {
            binding.txtCalc.setText(number)
        } else {
            binding.txtCalc.append(number)
        }
    }
    private fun operation(op: String) {
        if (oldNumber.isNotEmpty()) {
            calculate()
        }
        oldNumber = binding.txtCalc.text.toString()
        newNumber = ""
        binding.txtCalc.setText("")
        operation = op
    }
    private fun calculate() {
        var operate:Button? = null
        val newNum = binding.txtCalc.text.toString()
        val signs = ArrayList<Char>()

        if(newNum.isNotEmpty()){
            for(i in newNum.indices){
                if(!newNum[i].isDigit()){
                    signs.add(newNum[i])
                }
            }
        }
        var result = 0.0
        if(signs.size == 1){
            when (signs[signs.size - 1]) {
                '+' ->{
                    val split = newNum.split("+")

                    val result1 = split[0].toInt()  + split[1].toInt()
                    result = result1.toDouble()
                }
                '-' ->{
                    val split = newNum.split("-")
                    val result1 = split[0].toInt()  - split[1].toInt()
                    result = result1.toDouble()
                    Log.d(TAG, "number yetu -> $newNum")
                    Log.d(TAG, "jibu letu -> $result")
                    Log.d(TAG, "sign zetu -> ${signs[signs.size - 1]}")
                }
                'x' -> {
                    val split = newNum.split("x")
                    val result1 = split[0].toInt()  * split[1].toInt()
                    result = result1.toDouble()
                    Log.d(TAG, "jibu letu -> $result1")
                    Log.d(TAG, "jibu letu -> $result1")
                    Log.d(TAG, "jibu letu -> $split")
                }
                '/' -> {
                    val split = newNum.split("/")
                    val result1 = split[0].toInt()  / split[1].toInt()
                    result = result1.toDouble()

                    Log.d(TAG, "number yetu -> $newNum")
                    Log.d(TAG, "jibu letu -> $result")
                }
            }

        }else{
            Toast.makeText(this@MainActivity, "sorry, its a simple a calc. clear first", Toast.LENGTH_LONG).show()
        }
        binding.txtCalc.setText(result.toString())
        oldNumber = ""
        newNumber = ""
        operation = ""
    }
    private fun sqrt() {
        val num = binding.txtCalc.text.toString().toDouble()
        val sqrt = Math.sqrt(num)
        binding.txtCalc.setText(sqrt.toString())
    }
}
