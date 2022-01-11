package com.mona_daigle.diet_meal_plan.upgrade

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.billingclient.api.*
import com.mona_daigle.diet_meal_plan.databinding.FragmentDiamondBinding

class DiamondFragment : Fragment(),
    UpgradeDiamondAdapter.OnItemDiamondClickListener, PurchasesUpdatedListener, BillingClientStateListener {
    private lateinit var binding: FragmentDiamondBinding
    private val upgradeDiamondAdapter by lazy {
        UpgradeDiamondAdapter(this)
    }
    private lateinit var billingClient: BillingClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiamondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        billingClient = BillingClient.newBuilder(requireActivity())
            .setListener(this)
            .enablePendingPurchases()
            .build()

        billingClient.startConnection(this)

        binding.pbDiamond.visibility = View.VISIBLE

        binding.rvDiamond.adapter = upgradeDiamondAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = DiamondFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }

    override fun onItemDiamondClick(skuDiamond: SkuDetails) {
        val flowParams = BillingFlowParams.newBuilder()
            .setSkuDetails(skuDiamond)
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
        val consumeParams = ConsumeParams.newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()
        billingClient.consumeAsync(consumeParams) { _, _ ->

        }
        val diamond: Int = purchase.skus[0].split("_")[1].toInt()
        UpgradeManager.getInstance()?.incrementDiamond(requireActivity(), diamond)
        binding.tvPurchaseStatus.text = "Thanks to buy"
        binding.purchaseStatus.visibility = View.VISIBLE
        binding.purchaseStatus.setCardBackgroundColor(Color.GREEN)
        Handler(Looper.myLooper()!!).postDelayed({
            binding.purchaseStatus.visibility = View.GONE
        }, 1000)
    }


    override fun onBillingServiceDisconnected() {
        binding.pbDiamond.visibility = View.INVISIBLE
        binding.rvDiamond.visibility = View.INVISIBLE
        binding.tvError.visibility = View.VISIBLE
    }

    override fun onBillingSetupFinished(billingResult: BillingResult) {
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
            val skuDiamondParams = SkuDetailsParams.newBuilder()
                .setSkusList(UpgradeManager.diamondIds)
                .setType(BillingClient.SkuType.INAPP)
                .build()
            billingClient.querySkuDetailsAsync(
                skuDiamondParams
            ) { billingResult, skuDiamonds ->
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && skuDiamonds != null) {
                    requireActivity().runOnUiThread {
                        upgradeDiamondAdapter.skuDiamonds = skuDiamonds
                        binding.pbDiamond.visibility = View.INVISIBLE
                        binding.rvDiamond.visibility = View.VISIBLE
                        if (skuDiamonds.isEmpty()) {
                            binding.tvError.visibility = View.VISIBLE
                            binding.tvError.text = "No products available";
                        }
                    }
                } else {
                    requireActivity().runOnUiThread {
                        binding.pbDiamond.visibility = View.INVISIBLE
                        binding.rvDiamond.visibility = View.INVISIBLE
                        binding.tvError.visibility = View.VISIBLE
                    }
                }
            }
        } else {
            binding.pbDiamond.visibility = View.INVISIBLE
            binding.rvDiamond.visibility = View.INVISIBLE
            binding.tvError.visibility = View.VISIBLE
        }
    }
}