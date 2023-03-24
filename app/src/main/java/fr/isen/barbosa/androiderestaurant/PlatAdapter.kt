package fr.isen.barbosa.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlatAdapter(var platList: List<String>) :
    RecyclerView.Adapter<PlatAdapter.PlatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_plat_adapter,
            parent,
            false
        )
        return PlatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return platList.size
    }

    override fun onBindViewHolder(holder: PlatViewHolder, position: Int) { //PlatViewHolder = CategoryViewHolder
        holder.bind(platList[position])
    }


    fun updateDishes(platListFromApi: ArrayList<String>) {
        platList = platListFromApi
        notifyDataSetChanged()
    }

    inner class PlatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(plat: String) {
            //val dishName = view.findViewById<TextView>(R.id.dishName)
            itemView.findViewById<TextView>(R.id.NomPlat).text = plat
        }
    }
}
