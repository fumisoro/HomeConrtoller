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


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
                this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val notification = NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("内容")
                .setContentTitle("タイトル")
                .setContentInfo("通知欄")
                .setTicker("通知概要")
                .setContentIntent(pendingIntent)

        val manager = getSystemService(Service.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(0, notification.build())


        FuelManager.instance.baseHeaders = mapOf("Content-Type" to "application/json")
        FuelManager.instance.basePath = "https://hooks.slack.com"
        findViewById(R.id.tvButton).setOnClickListener {v -> postToSlack("{\"text\":\"テレビ電源\"}")}
        findViewById(R.id.channelNextButton).setOnClickListener {v -> postToSlack("{\"text\":\"チャンネル次\"}")}
        findViewById(R.id.channelPrevButton).setOnClickListener {v -> postToSlack("{\"text\":\"チャンネル前\"}")}
        findViewById(R.id.rightOffButton).setOnClickListener {v -> postToSlack("{\"text\":\"電気消して\"}")}
        findViewById(R.id.rightOnButton).setOnClickListener {v -> postToSlack("{\"text\":\"電気つけて\"}")}
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
