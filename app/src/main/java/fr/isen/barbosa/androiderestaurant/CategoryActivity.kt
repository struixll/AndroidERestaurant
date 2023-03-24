package fr.isen.barbosa.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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


@Suppress("DEPRECATION")
class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.CategoryTitle.text = intent.getStringExtra("category")
            ?: "" //faire des if pour associer les listes aux diff entrées plats dessert
        //this.title = categoryName

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val platList = resources.getStringArray(R.array.liste_plats).toList() as ArrayList
        val platRecyclerView = findViewById<RecyclerView>(R.id.ListePlats)
        platRecyclerView.layoutManager = LinearLayoutManager(this)
        platRecyclerView.adapter = PlatAdapter(platList)  //platList = dishes
        startActivity(Intent(this, DetailActivity::class.java))
        //intent.putExtra(plat : it)
        getDishFromServer()


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
            { response ->
                Log.d("CategoryActivity", "ça marche")
                val data = Gson().fromJson(response.toString(), DataResult::class.java)
                val platList = data.data[0].items.map { it.categNameFr ?: "" }.toList() as ArrayList
            },
            { error ->
                Log.e("CategoryActivity", "ça marche pas")
            }
        )

        queue.add(request)

    }

    private fun handleAPIData(data: String) {
        val dishesResult = Gson().fromJson(data, DataResult::class.java)
        val dishCategory = dishesResult.data.firstOrNull { it.nameFr == category }
        val adapter = binding.categoryList.adapter as DishAdapter
        adapter.refreshList(dishCategory?.items as ArrayList<Items>)
    }

}