package com.example.fumiya.homeconrtoller

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager

import kotlinx.android.synthetic.main.activity_tv.*

class TVActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv)
        setSupportActionBar(toolbar)

        FuelManager.instance.baseHeaders = mapOf("Content-Type" to "application/json")
        FuelManager.instance.basePath = "https://hooks.slack.com"
        findViewById(R.id.tvPowerButton).setOnClickListener {v -> postToSlack("{\"text\":\"テレビ電源\"}")}
        findViewById(R.id.tvChannelNextButton).setOnClickListener {v -> postToSlack("{\"text\":\"チャンネル次\"}")}
        findViewById(R.id.tvChannelPrevButton).setOnClickListener {v -> postToSlack("{\"text\":\"チャンネル前\"}")}
        findViewById(R.id.tvVolumeDownButton).setOnClickListener {v -> postToSlack("{\"text\":\"音量下げ\"}")}
        findViewById(R.id.tvVolumenUpButton).setOnClickListener {v -> postToSlack("{\"text\":\"音量上げ\"}")}
        findViewById(R.id.chromeCastButton).setOnClickListener {v -> postToSlack("{\"text\":\"キャスト\"}")}
        findViewById(R.id.nSwitchButton).setOnClickListener {v -> postToSlack("{\"text\":\"switch\"}")}
        findViewById(R.id.ps4PcButton).setOnClickListener {v -> postToSlack("{\"text\":\"ps4\"}")}
        findViewById(R.id.NHKButton).setOnClickListener {v -> postToSlack("{\"text\":\"NHK\"}")}
        findViewById(R.id.ETVButton).setOnClickListener {v -> postToSlack("{\"text\":\"Eテレ\"}")}
        findViewById(R.id.TVKButton).setOnClickListener {v -> postToSlack("{\"text\":\"TVK\"}")}
        findViewById(R.id.NTVButton).setOnClickListener {v -> postToSlack("{\"text\":\"日テレ\"}")}
        findViewById(R.id.TVAsahiButton).setOnClickListener {v -> postToSlack("{\"text\":\"テレ朝\"}")}
        findViewById(R.id.TBSButton).setOnClickListener {v -> postToSlack("{\"text\":\"TBS\"}")}
        findViewById(R.id.TVTokyoButton).setOnClickListener {v -> postToSlack("{\"text\":\"テレ東\"}")}
        findViewById(R.id.TokyoMXButton).setOnClickListener {v -> postToSlack("{\"text\":\"TokyoMX\"}")}

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
