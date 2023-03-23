package com.example.xml


import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.xmlpull.v1.XmlPullParser.END_DOCUMENT
import org.xmlpull.v1.XmlPullParser.START_TAG



class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val txt: TextView = findViewById(R.id.textView)

        val fruits = parseFruitsXml()
        for (fruit in fruits) {


             txt.append("Fruit: ${fruit.name}, ${fruit.color}   ")

        }
    }

    private fun parseFruitsXml(): List<Fruit> {
        val fruits = mutableListOf<Fruit>()
        val parser = resources.getXml(R.xml.fruit)

        var eventType = parser.eventType
        var name: String? = null
        var color: String? = null

        while (eventType != END_DOCUMENT) {
            when (eventType) {
                START_TAG -> {
                    when (parser.name) {
                        "name" -> name = parser.nextText()
                        "color" -> color = parser.nextText()
                    }
                }
            }

            if (name != null && color != null) {
                fruits.add(Fruit(name, color))
                name = null
                color = null
            }

            eventType = parser.next()
        }

        return fruits
    }
}