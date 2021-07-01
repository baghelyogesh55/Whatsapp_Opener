package com.example.whatsappopener

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var number = ""
        if(intent.action == Intent.ACTION_PROCESS_TEXT){
            number = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString().trim();
        }

        if(number==""){}
        else if(number.isDigitsOnly()){
            openWhatsApp(number)
        }
        else{
            Toast.makeText(this,"Please enter a valid phone number",Toast.LENGTH_SHORT).show()
        }
    }

    private fun openWhatsApp(number: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setPackage("com.whatsapp")

        val data = if(number[0]=='+'){
            number.substring(1)
        }
        else if(number.length==10){
            "91"+number
        }
        else{
            number
        }

        intent.data = Uri.parse("https://wa.me/$data")

        if(packageManager.resolveActivity(intent,0)!=null){
            startActivity(intent)
        }
        else{
            Toast.makeText(this,"Please install Whatsapp",Toast.LENGTH_SHORT).show()
        }
        finish()

    }


}