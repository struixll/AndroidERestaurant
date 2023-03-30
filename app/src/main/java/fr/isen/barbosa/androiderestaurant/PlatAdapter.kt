package fr.isen.barbosa.androiderestaurant


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.barbosa.androiderestaurant.model.Items


class PlatAdapter(private var platList: ArrayList<Items>, val onClickListener: (String) -> Unit) :
    RecyclerView.Adapter<PlatAdapter.PlatViewHolder>() {

    private var list = arrayListOf<Items>()

    inner class PlatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dishTitle: TextView = itemView.findViewById(R.id.dishTitle)
        val image: ImageView = itemView.findViewById(R.id.image_meal)
        val dishPrice: TextView = itemView.findViewById(R.id.dishPrice)
        fun bind(plat: String) {
            itemView.setOnClickListener {
                onClickListener(plat)
            }
            //val dishName = view.findViewById<TextView>(R.id.dishName)
            //itemView.findViewById<TextView>(R.id.NomPlat).text = plat
        }
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
        Log.d("TAG_", "onBindViewHolder: " + platList)
        val item = platList[position]
        holder.dishTitle.text = item.nameFr ?: ""
        holder.dishPrice.text = item.prices[0].price ?:""
        Log.d("TAG_", "onBindViewHolder: " + item.images)
        //holder.image.
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
