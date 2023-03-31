package fr.isen.barbosa.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.barbosa.androiderestaurant.databinding.ActivityCategoryBinding
import org.json.JSONObject
import fr.isen.barbosa.androiderestaurant.model.DataResult
import fr.isen.barbosa.androiderestaurant.model.Items


class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var category : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        category = intent.getStringExtra("category")?:""
        binding.CategoryTitle.text = category

        val platList = arrayListOf<Items>()
        val platRecyclerView = findViewById<RecyclerView>(R.id.ListePlats)

        platRecyclerView.layoutManager = LinearLayoutManager(this)
        platRecyclerView.adapter = PlatAdapter(platList){  //platList = dishes
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("detail", it)
            startActivity(intent)
        }
        getDishFromServer() //appelle la fonction pour faire le lien avec le serveur

    }



    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }


    private fun getDishFromServer() {
        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val body = JSONObject().apply { put("id_shop", "1") }
        val request = JsonObjectRequest(Request.Method.POST, url, body,
            {
                Log.d("CategoryActivity", "ça marche : $it")
                serverAPI(it.toString())
            },
            {
                Log.e("CategoryActivity", "ça marche pas")
            }
        )

        queue.add(request)

    }

    private fun serverAPI(data: String) {
        // Mise en forme des data
        val dishesResult = Gson().fromJson(data, DataResult::class.java) //data
        val dishCategory = dishesResult.data.firstOrNull { it.nameFr == category }

        // Create adapter to populate recyclerview
        val adapter = binding.ListePlats.adapter as PlatAdapter
        for (dishCat in dishesResult.data){
            for (dish in dishCat.items){
            }
        }

        adapter.updateDishes(dishCategory?.items as ArrayList<Items>) }

}