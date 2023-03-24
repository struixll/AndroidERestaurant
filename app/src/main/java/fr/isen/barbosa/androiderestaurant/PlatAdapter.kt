package fr.isen.barbosa.androiderestaurant

import android.content.DialogInterface.OnClickListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.barbosa.androiderestaurant.model.Items



class PlatAdapter(var platList: List<String>, var onClickListener: (String) -> Unit) :
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
            itemView.setOnClickListener {
                onClickListener(plat)
            }
            //val dishName = view.findViewById<TextView>(R.id.dishName)
            itemView.findViewById<TextView>(R.id.NomPlat).text = plat
        }
    }
}
