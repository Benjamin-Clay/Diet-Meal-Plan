package com.mona_daigle.diet_meal_plan.upgrade

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.billingclient.api.SkuDetails
import com.mona_daigle.diet_meal_plan.databinding.ItemPremiumBinding

class UpgradePremiumAdapter(private val onItemPremiumClickListener: OnItemPremiumClickListener) :
    RecyclerView.Adapter<UpgradePremiumAdapter.ViewHolder>() {
    var skuPremiums: List<SkuDetails> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class ViewHolder(val binding: ItemPremiumBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPremiumBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val skuPremium = skuPremiums[position]
        with(holder.binding) {
            with(holder.binding) {
                tvTitle.text = skuPremium.title
                tvDes.text = skuPremium.description
                tvPrice.text = skuPremium.price
            }
            root.setOnClickListener {
                onItemPremiumClickListener.onItemPremiumClick(skuPremium)
            }
        }
    }

    override fun getItemCount(): Int {
        return skuPremiums.size
    }

    interface OnItemPremiumClickListener {
        fun onItemPremiumClick(skuPremium: SkuDetails)
    }
}