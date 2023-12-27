package com.riofuad.suitmediakmtest.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.riofuad.suitmediakmtest.databinding.ActivityFirstScreenBinding

class FirstScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.apply {
            btnCheck.setOnClickListener {
                val dialog = AlertDialog.Builder(this@FirstScreenActivity)
                if (isPalindrome(inputPalindrome.text.toString())) {
                    dialog.setMessage("isPalindrome")
                } else {
                    dialog.setMessage("not palindrome")
                }
                dialog.show()
            }

            btnNext.setOnClickListener {
                val intent = Intent(this@FirstScreenActivity, SecondScreenActivity::class.java)
                intent.putExtra(SecondScreenActivity.USER_NAME, binding.inputName.text.toString())
                startActivity(intent)
            }
        }
    }

    private fun isPalindrome(text: String): Boolean {
        val formattedText = text.replace("\\s".toRegex(), "")
        val textReversed = formattedText.reversed()
        return formattedText.equals(textReversed, ignoreCase = true)
    }
}