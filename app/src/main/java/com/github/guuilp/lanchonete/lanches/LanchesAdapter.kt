package com.github.guuilp.lanchonete.lanches

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.github.guuilp.lanchonete.R
import com.github.guuilp.lanchonete.data.Ingrediente
import com.github.guuilp.lanchonete.data.Lanche
import kotlinx.android.synthetic.main.list_item_lanche.view.*

class LanchesAdapter(
        private val lanches: List<Lanche>,
        private val context: Context?,
        private var onItemClickListener: (lanche: Lanche, position: Int) -> Unit) : RecyclerView.Adapter<LanchesAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lanche = lanches[position]
        holder.let {
            it.bindView(lanche, context)
            it.itemView.setOnClickListener {
                onItemClickListener(lanche, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_lanche, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lanches.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(lanche: Lanche, context: Context?) {
            val imagem = itemView.imagem
            val titulo = itemView.titulo
            val ingredientes = itemView.ingredientes
            val preco = itemView.preco

            context.let {
                Glide.with(it!!)
                    .load(lanche.image)
                    .into(imagem)
            }

            titulo.text = lanche.name
            ingredientes.text = lanche.ingredientsString
            preco.text = lanche.priceFormated
        }
    }
}