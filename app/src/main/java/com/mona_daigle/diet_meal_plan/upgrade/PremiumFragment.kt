package com.mona_daigle.diet_meal_plan.upgrade

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.android.billingclient.api.*
import com.mona_daigle.diet_meal_plan.R
import com.mona_daigle.diet_meal_plan.databinding.FragmentPremiumBinding
import com.google.android.material.snackbar.Snackbar

class PremiumFragment : Fragment(),
    UpgradePremiumAdapter.OnItemPremiumClickListener, PurchasesUpdatedListener, BillingClientStateListener {
    private lateinit var binding: FragmentPremiumBinding
    private val upgradePremiumAdapter by lazy {
        UpgradePremiumAdapter(this)
    }
    private lateinit var billingClient: BillingClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPremiumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        billingClient = BillingClient.newBuilder(requireActivity())
            .setListener(this)
            .enablePendingPurchases()
            .build()

        billingClient.startConnection(this)

        binding.pbPremium.visibility = View.VISIBLE

        binding.rvPremium.adapter = upgradePremiumAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = PremiumFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }

    override fun onItemPremiumClick(skuPremium: SkuDetails) {
        val flowParams = BillingFlowParams.newBuilder()
            .setSkuDetails(skuPremium)
            .build()
        billingClient.launchBillingFlow(requireActivity(), flowParams)
    }

    override fun onPurchasesUpdated(billingResult: BillingResult, purchases: MutableList<Purchase>?) {
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            for (purchase in purchases) {
                if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
                    handlePurchase(purchase)
                    break
                }
            }
        } else {
            binding.tvPurchaseStatus.text = "Error, please try again!"
            binding.purchaseStatus.visibility = View.VISIBLE
            binding.purchaseStatus.setCardBackgroundColor(Color.RED)
            Handler(Looper.myLooper()!!).postDelayed({
                binding.purchaseStatus.visibility = View.GONE
            }, 1000)
        }

    }

    private fun handlePurchase(purchase: Purchase) {
        if (!purchase.isAcknowledged) {
            val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                .setPurchaseToken(purchase.purchaseToken).build()
            billingClient.acknowledgePurchase(acknowledgePurchaseParams) { }
            UpgradeManager.getInstance()?.isPremium = true
            binding.tvPurchaseStatus.text = "Thanks to buy"
            binding.purchaseStatus.visibility = View.VISIBLE
            binding.purchaseStatus.setCardBackgroundColor(Color.GREEN)
            Handler(Looper.myLooper()!!).postDelayed({
                binding.purchaseStatus.visibility = View.GONE
            }, 1000)
        }
    }


    override fun onBillingServiceDisconnected() {
        binding.pbPremium.visibility = View.INVISIBLE
        binding.rvPremium.visibility = View.INVISIBLE
        binding.tvError.visibility = View.VISIBLE
    }

    override fun onBillingSetupFinished(billingResult: BillingResult) {
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
            val skuPremiumParams = SkuDetailsParams.newBuilder()
                .setSkusList(UpgradeManager.premiumIds)
                .setType(BillingClient.SkuType.SUBS)
                .build()
            billingClient.querySkuDetailsAsync(
                skuPremiumParams
            ) { billingResult, skuPremiums ->
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && skuPremiums != null) {
                    requireActivity().runOnUiThread {
                        upgradePremiumAdapter.skuPremiums = skuPremiums
                        binding.pbPremium.visibility = View.INVISIBLE
                        binding.rvPremium.visibility = View.VISIBLE
                        if (skuPremiums.isEmpty()) {
                            binding.tvError.visibility = View.VISIBLE
                            binding.tvError.text = "No products available";
                        }
                    }
                } else {
                    requireActivity().runOnUiThread {
                        binding.pbPremium.visibility = View.INVISIBLE
                        binding.rvPremium.visibility = View.INVISIBLE
                        binding.tvError.visibility = View.VISIBLE
                    }
                }
            }
        } else {
            binding.pbPremium.visibility = View.INVISIBLE
            binding.rvPremium.visibility = View.INVISIBLE
            binding.tvError.visibility = View.VISIBLE
        }
    }
}