package fr.isen.barbosa.androiderestaurant


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.barbosa.androiderestaurant.model.Items
import com.squareup.picasso.Picasso


class PlatAdapter(private var platList: ArrayList<Items>, val onClickListener: (Items) -> Unit) :
    RecyclerView.Adapter<PlatAdapter.PlatViewHolder>() {

    private var list = arrayListOf<Items>()

    inner class PlatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dishTitle: TextView = itemView.findViewById(R.id.dishTitle)
        val image: ImageView = itemView.findViewById(R.id.image_meal)
        val dishPrice: TextView = itemView.findViewById(R.id.dishPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_plat_adapter,
            parent, false)
        return PlatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return platList.size
    }

    override fun onBindViewHolder(holder: PlatViewHolder, position: Int) { //PlatViewHolder = CategoryViewHolder
        val item = platList[position]
        holder.dishTitle.text = item.nameFr ?: ""
        holder.dishPrice.text = item.prices[0].price ?:""
        if (item.images[0].isNotEmpty()){
            Picasso.get().load(platList[position].images[0])
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.image)
        }
        holder.itemView.setOnClickListener {
            onClickListener(item)
        }

    }


    fun updateDishes(platListFromApi: ArrayList<Items>) {

        for (dish in platListFromApi){
            Log.d("TAG_Plat_", "updateDishes: " + dish)
        }

        list = platListFromApi
        platList = platListFromApi
        notifyDataSetChanged()
    }


}
