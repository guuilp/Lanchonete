package com.github.guuilp.lanchonete.promocoes

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.guuilp.lanchonete.R
import com.github.guuilp.lanchonete.data.Promocao
import kotlinx.android.synthetic.main.list_item_promocao.view.*

class PromocoesAdapter(
        private val promocoes: List<Promocao>,
        private val context: Context?,
        private var onItemClickListener: (promocao: Promocao, position: Int) -> Unit) : RecyclerView.Adapter<PromocoesAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val promocao = promocoes[position]
        holder.let {
            it.bindView(promocao)
            it.itemView.setOnClickListener {
                onItemClickListener(promocao, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_promocao, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return promocoes.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(promocao: Promocao) {
            val titulo = itemView.titulo
            val descricao = itemView.descricao

            titulo.text = promocao.name
            descricao.text = promocao.description
        }
    }
}