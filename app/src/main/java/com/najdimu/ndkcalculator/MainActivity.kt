package com.najdimu.ndkcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.najdimu.ndkcalculator.databinding.ActivityMainBinding
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.ScriptException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var check = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.darkMode.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
            }
        }

        binding.button1.setOnClickListener{
            button("1")
        }

        binding.button2.setOnClickListener{
            button("2")
        }

        binding.button3.setOnClickListener{
           button("3")
        }

        binding.button4.setOnClickListener{
            button("4")
        }

        binding.button5.setOnClickListener{
            button("5")
        }

        binding.button6.setOnClickListener{
            button("6")
        }

        binding.button7.setOnClickListener{
            button("7")
        }

        binding.button8.setOnClickListener{
            button("8")
        }

        binding.button9.setOnClickListener{
            button("9")
        }

        binding.button0.setOnClickListener{
            button("0")
        }

        binding.button00.setOnClickListener{
            button("00")
        }

        binding.buttonDot.setOnClickListener{
            button(".")
        }

        binding.buttonAdd.setOnClickListener{
            operator("+")
        }

        binding.buttonSub.setOnClickListener{
            operator("-")
        }

        binding.buttonMul.setOnClickListener{
            operator("*")
        }

        binding.buttonDiv.setOnClickListener{
            operator("/")
        }

        binding.buttonPercent.setOnClickListener{
            operator("%")
        }

        binding.buttonEqual.setOnClickListener{
            binding.formula.text = null
        }

        binding.buttonClear.setOnClickListener{
            binding.formula.text = null
            binding.result.text = null
        }

        binding.buttonBackspace.setOnClickListener{
            val BackSpace: String?
            if(binding.formula.text.length > 0) {
                val stringBuilder: StringBuilder = StringBuilder(binding.formula.text)
                val find = binding.formula.text.toString()
                val find2 = find.last()

                if(find2 == '+' || find2 == '-' || find2 == '*' || find2 == '/' || find2 == '%')
                {
                    check -= 1
                }

                stringBuilder.deleteCharAt(binding.formula.text.length-1)
                BackSpace = stringBuilder.toString()
                binding.formula.text = BackSpace
                result(BackSpace)
            }
        }
    }

    private fun result(text: String) {
        val engine: ScriptEngine = ScriptEngineManager().getEngineByName("rhino")
        try {
            if (text.isNotEmpty()) {
                val result: Any = engine.eval(text)
                val mainr = result.toString()

                if(check == 0) {
                    binding.result.text = null
                }
                else {
                    binding.result.text = mainr
                }
            }else{
                binding.result.text = "0"
            }
        }
        catch (e: ScriptException) {
            Log.d("TAG", stringFromJNI())
        }
    }

    fun button(button: String) {
       val text = binding.formula.text.toString()+ button
        binding.formula.text = text
        result(text)
    }

    fun operator(operator: String) {
        val text = binding.formula.text.toString()+ operator
        binding.formula.text = text
        check += 1
    }

    external fun stringFromJNI(): String

    companion object {
        init {
            System.loadLibrary("ndkcalculator")
        }
    }
}