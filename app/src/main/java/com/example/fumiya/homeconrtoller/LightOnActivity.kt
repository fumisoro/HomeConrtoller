package com.example.fumiya.homeconrtoller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager

class LightOnActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_light_on)

        FuelManager.instance.baseHeaders = mapOf("Content-Type" to "application/json")
        FuelManager.instance.basePath = "https://hooks.slack.com"
        postToSlack("{\"text\":\"電気つけて\"}")
    }

    fun postToSlack(body : String){
        val path = getString(R.string.slack_api_token);
        Fuel.post(path).body(body).response { request, response, result ->
            println(request.toString())
            println(response.toString())
            println(result.toString())
        }
    }
}
