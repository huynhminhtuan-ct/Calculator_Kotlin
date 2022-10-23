package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity() {
    //Create vali textBox
    var textBox:TextView? = null;

    /**
     * Contracter
     * @param {*} savedInstanceState
     * @return {void}
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //set data of text view
        textBox =findViewById(R.id.textView);
    }

    /**
     * Chang data is calculate
     * @param {View} view
     * @return {void}
     */
    fun changText(view: View) {
        var item: Button = view as Button;
        //set data of texBox
        textBox?.text = textBox?.text.toString() + item.text.toString().trim();
        //Delet index[0] = 0
        if(textBox?.text?.trim()?.indexOf("0")!! >=0)
            textBox?.text = textBox?.text?.toString()?.let { textBox?.text?.trim()?.substring(1, it.length) }
        // Check Key
        when(item.text.toString().trim()){
            //remote all data
            "AC" -> textBox?.text =  "0"
            //remote 1 item
            "C"  ->textBox?.text =   textBox?.text?.substring(1, textBox?.text.toString().indexOf("C"))

        }
    }

    /**
     * result calculate
     * @param {View} view
     * @return {void}
     */
    fun status(view: View) {
        var result = Expression(textBox?.text.toString());
        var data : String ?= null
        if(result.calculate().toString().indexOf(".") >=0){
            data = result.calculate().toString()
                .substring(0,result.calculate().toString().indexOf("."));
        }
        else{
            data = result.toString()
        }
        textBox?.text = data.toString();
    }
}