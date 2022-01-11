package com.mona_daigle.diet_meal_plan

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.android.billingclient.api.*
import com.mona_daigle.diet_meal_plan.database.AppDatabase
import com.mona_daigle.diet_meal_plan.models.dataMeals
import com.mona_daigle.diet_meal_plan.upgrade.UpgradeManager

class SplashActivity : AppCompatActivity(), PurchasesUpdatedListener, BillingClientStateListener,
    PurchasesResponseListener {
    private lateinit var billingClient: BillingClient
    private val appDatabase by lazy {
        AppDatabase(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if(appDatabase.getMeals(null, null).isEmpty()){
            appDatabase.insertMeals(dataMeals)
        }


        billingClient = BillingClient.newBuilder(this)
            .setListener(this)
            .enablePendingPurchases()
            .build()

        billingClient.startConnection(this)
    }

    override fun onPurchasesUpdated(
        billingResult: BillingResult,
        purchases: MutableList<Purchase>?
    ) {
    }

    override fun onBillingServiceDisconnected() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onBillingSetupFinished(billingResult: BillingResult) {
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
            billingClient.queryPurchasesAsync(BillingClient.SkuType.SUBS, this)
        } else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onQueryPurchasesResponse(
        billingResult: BillingResult,
        purchases: MutableList<Purchase>
    ) {
        UpgradeManager.getInstance()?.isPremium = purchases.isNotEmpty()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}