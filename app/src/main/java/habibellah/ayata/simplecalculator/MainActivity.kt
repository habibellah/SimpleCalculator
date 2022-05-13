package habibellah.ayata.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import habibellah.ayata.simplecalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var currentOperation:Operation ? = null
   private var result:Double = 0.0
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addCallBack()

    }

    //this method to call the clickListener of views
    private fun addCallBack() {
        binding.clearBtn.setOnClickListener {
            clearText()
        }
        binding.plusBtn.setOnClickListener {
           operationClick(Operation.Sum)
        }
        binding.minsBtn.setOnClickListener {
           operationClick(Operation.Min)
        }
        binding.divBtn.setOnClickListener {
            operationClick(Operation.Div)
        }
        binding.mulBtn.setOnClickListener {
           operationClick(Operation.Mul)
        }
        binding.mulBtn.setOnClickListener {
            operationClick(Operation.Mode)
        }
        binding.equalBtn.setOnClickListener {
            val res = doOperation().toString()
            clearText()
            binding.window.text = res
        }
    }

    //this method help to know the current operation clicked
    private fun operationClick(operation: Operation)
    {
        currentOperation = operation
        result = binding.window.text.toString().toDouble()
        clearText()
    }

    //this method do the operation and return it as a double and that with the help of an enum to know
    //the current operation
    private fun doOperation():Double {
        val currentNumberOnWindow = binding.window.text.toString().toDouble()
      return  when(currentOperation)
        {
            Operation.Sum -> result + currentNumberOnWindow
            Operation.Min -> result - currentNumberOnWindow
            Operation.Div -> result / currentNumberOnWindow
            Operation.Mul -> result * currentNumberOnWindow
            Operation.Mode -> result % currentNumberOnWindow
            null -> 0.0
        }
    }

    //this method clear the text in the windowView
    private fun clearText() {
        binding.window.text = ""
    }

    //i use the onClick method from xml to avoid init all the numbers in a single object
    fun callBacks(v :View)
    {
        //take the current number click and add it with the new click
        val oldNumbers = binding.window.text.toString()
        val newNumbers = (v as Button).text.toString()
        val currentNumbers = oldNumbers + newNumbers
        binding.window.text = currentNumbers
    }
}