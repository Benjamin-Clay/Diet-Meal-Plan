package com.mona_daigle.diet_meal_plan.upgrade

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.billingclient.api.SkuDetails
import com.mona_daigle.diet_meal_plan.databinding.ItemDiamondBinding

class UpgradeDiamondAdapter(private val onItemDiamondClickListener: OnItemDiamondClickListener) :
    RecyclerView.Adapter<UpgradeDiamondAdapter.ViewHolder>() {
    var skuDiamonds: List<SkuDetails> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class ViewHolder(val binding: ItemDiamondBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDiamondBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val skuDiamond = skuDiamonds[position]
        with(holder.binding) {
            with(holder.binding) {
                try {
                    tvDiamond.text = skuDiamond.sku.split("_")[1];
                } catch (e: Exception) {
                    tvDiamond.text = skuDiamond.title.split(" ")[0]
                }
                tvPrice.text = "${skuDiamond.price}"
            }
            root.setOnClickListener {
                onItemDiamondClickListener.onItemDiamondClick(skuDiamond)
            }
        }
    }

    override fun getItemCount(): Int {
        return skuDiamonds.size
    }

    interface OnItemDiamondClickListener {
        fun onItemDiamondClick(skuDiamond: SkuDetails)
    }
}