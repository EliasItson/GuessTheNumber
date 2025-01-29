package elias.jorge.guessthenumber

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var minValue = 0
    var maxValue = 100
    var num:Int = 0
    var won = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textViewMessage: TextView = findViewById(R.id.textViewMessage)
        val buttonDown: Button = findViewById(R.id.buttonDown)
        val buttonUp: Button = findViewById(R.id.buttonUp)
        val buttonGenerate: Button = findViewById(R.id.buttonGenerate)
        val buttonGuessed: Button = findViewById(R.id.buttonGuessed)

        buttonGenerate.setOnClickListener {
            num = Random.nextInt(minValue, maxValue)
            textViewMessage.setText(num.toString())
            buttonGenerate.visibility = View.INVISIBLE
            buttonGuessed.visibility = View.VISIBLE
            buttonDown.visibility = View.VISIBLE
            buttonUp.visibility = View.VISIBLE
        }

        buttonUp.setOnClickListener {
            minValue = num
            if (checkingLimits()) {
                num = Random.nextInt(minValue, maxValue)
                textViewMessage.setText(num.toString())
            } else{
                textViewMessage.setText("You win >:(")
                buttonGuessed.setText("Play Again")
                won = true
            }

        }

        buttonDown.setOnClickListener {
            maxValue = num
            if (checkingLimits()) {
                num = Random.nextInt(minValue, maxValue)
                textViewMessage.setText(num.toString())
            } else{
                textViewMessage.setText("You win >:(")
                buttonGuessed.setText("Play Again")
                won = true
            }
        }

        buttonGuessed.setOnClickListener {
            if(!won) {
                textViewMessage.setText("I win, your number is " + num + " >:3")
                buttonGuessed.setText("Play Again")
                buttonDown.visibility = View.INVISIBLE
                buttonUp.visibility = View.INVISIBLE
                won = true
            } else {
                buttonGenerate.visibility = View.VISIBLE
                textViewMessage.setText("Tap on generate to start")
                buttonGuessed.setText("Guessed")
                buttonGuessed.visibility = View.GONE
                buttonDown.visibility = View.INVISIBLE
                buttonUp.visibility = View.INVISIBLE
                resetValues()
            }
        }
    }

    fun resetValues(){
        minValue = 0
        maxValue = 100
        num = 0
        won = false

    }

    fun checkingLimits(): Boolean
    {
        return maxValue != minValue
    }
}