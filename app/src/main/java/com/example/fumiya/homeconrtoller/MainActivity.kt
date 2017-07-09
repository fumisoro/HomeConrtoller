package com.example.fumiya.homeconrtoller

import android.app.NotificationManager
import android.app.Service
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.NotificationCompat
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.view.*
import android.widget.Button
import android.widget.TextView
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import android.content.Intent
import android.app.PendingIntent
import android.content.res.Resources
import android.view.View


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvIntent = Intent(applicationContext, TVActivity::class.java)
        val tvPendingIntent = PendingIntent.getActivity(
                this, 0, tvIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val lightOnIntent = Intent(applicationContext, LightOnActivity::class.java)
        val lightOnPendingIntent = PendingIntent.getActivity(
                this, 0, lightOnIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val lightOffIntent = Intent(applicationContext, LightOffActivity::class.java)
        val lightOffPendingIntent = PendingIntent.getActivity(
                this, 0, lightOffIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val tvPowerIntent = Intent(applicationContext, TVPowerActivity::class.java)
        val tvPowerPendingIntent = PendingIntent.getActivity(
                this, 0, tvPowerIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val notification = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.abc_btn_switch_to_on_mtrl_00001)
                .setContentText("出先で間違って押さないように！！")
                .setContentTitle("おうち操作")
                .setContentInfo("(^o^)")
                .setTicker("通知概要")
                .addAction(R.drawable.abc_spinner_mtrl_am_alpha, "tv", tvPowerPendingIntent)
                .addAction(R.drawable.abc_ic_star_black_16dp, "電on", lightOnPendingIntent)
                .addAction(R.drawable.abc_ic_star_black_16dp, "電off", lightOffPendingIntent)


        val manager = getSystemService(Service.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(0, notification.build())


        FuelManager.instance.baseHeaders = mapOf("Content-Type" to "application/json")
        FuelManager.instance.basePath = "https://hooks.slack.com"
        findViewById(R.id.tvButton).setOnClickListener {v -> this.changeActivity()}
        findViewById(R.id.tvPowerButton).setOnClickListener {v -> postToSlack("{\"text\":\"テレビ電源\"}")}
        findViewById(R.id.channelNextButton).setOnClickListener {v -> postToSlack("{\"text\":\"チャンネル次\"}")}
        findViewById(R.id.channelPrevButton).setOnClickListener {v -> postToSlack("{\"text\":\"チャンネル前\"}")}
        findViewById(R.id.rightOffButton).setOnClickListener {v -> postToSlack("{\"text\":\"電気消して\"}")}
        findViewById(R.id.rightOnButton).setOnClickListener {v -> postToSlack("{\"text\":\"電気つけて\"}")}
        findViewById(R.id.airconCoolButton).setOnClickListener {v -> postToSlack("{\"text\":\"エアコン冷房\"}")}
        findViewById(R.id.airconStopButton).setOnClickListener {v -> postToSlack("{\"text\":\"エアコン消して\"}")}
        findViewById(R.id.volumeDownButton).setOnClickListener {v -> postToSlack("{\"text\":\"音量下\"}")}
        findViewById(R.id.volumeUpButton).setOnClickListener {v -> postToSlack("{\"text\":\"音量上\"}")}
    }

    fun postToSlack(body : String){
        val path = getString(R.string.slack_api_token);
        Fuel.post(path).body(body).response { request, response, result ->
            println(request.toString())
            println(response.toString())
            println(result.toString())
        }
    }

    fun changeActivity(){
        val intent = Intent(this, TVActivity::class.java)
        startActivity(intent)
    }
}
