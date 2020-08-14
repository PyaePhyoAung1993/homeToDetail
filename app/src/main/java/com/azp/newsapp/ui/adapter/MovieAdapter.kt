package com.azp.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azp.newsapp.R

import com.azp.newsapp.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home.view.*

class MovieAdapter(var resultList: List<Result> = ArrayList<Result>()) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var mClickListener: ClickListener? = null

    fun setOnClickListener(clickListener: ClickListener){
        this.mClickListener = clickListener
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        lateinit var result: Result

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(result: Result) {
            this.result = result
            itemView.txt.text = result.id.toString()
            itemView.title.text = result.title
            itemView.video.text = result.video.toString()
        }

        override fun onClick(view: View?) {
            mClickListener?.onClcik(result)
        }
    }

    fun updateArticle(articleList: List<Result>) {
        this.resultList = articleList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(resultList.get(position))
    }

    interface ClickListener {
        fun onClcik(result: Result)
    }

}