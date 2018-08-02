package com.dm.sharedelementtransitions.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

class ItemListAdapter(
    private val dataList: List<Place>,
    private val placeClickListener: PlaceItem.PlaceClickListener,
    private val imageLoadingListener: PlaceItem.ImageLoadingListener? = null
) :
    RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(
            PlaceItem(parent.context), placeClickListener, imageLoadingListener
        )

    override fun getItemCount() = dataList.size

    override fun getItemId(position: Int) =
        dataList.getOrNull(position)?.id ?: super.getItemId(position)

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        dataList.getOrNull(position)?.let { (holder.itemView as? PlaceItem)?.bind(it) }
    }

    class ItemViewHolder(
        itemView: PlaceItem,
        placeClickListener: PlaceItem.PlaceClickListener,
        imageLoadingListener: PlaceItem.ImageLoadingListener?
    ) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.placeClickListener = placeClickListener
            itemView.imageLoadingListener = imageLoadingListener
        }
    }
}