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
        category = intent.getStringExtra("category")?:""
        binding.CategoryTitle.text = category
            ?: "" //faire des if pour associer les listes aux diff entrées plats dessert
        //this.title = categoryName

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val platList = resources.getStringArray(R.array.liste_plats).toList() as ArrayList
        val platRecyclerView = findViewById<RecyclerView>(R.id.ListePlats)
        platRecyclerView.layoutManager = LinearLayoutManager(this)
        platRecyclerView.adapter = PlatAdapter(platList){  //platList = dishes
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("detail", it)
            startActivity(intent)
        }


        //intent.putExtra("plat", it)
        //startActivity(Intent(this, DetailActivity::class.java))

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
                handleAPIData(it.toString())
            },
            {
                Log.e("CategoryActivity", "ça marche pas")
            }
        )

        queue.add(request)

    }

    private fun handleAPIData(data: String) {
        val dishesResult = Gson().fromJson(data, DataResult::class.java) //data
        val dishCategory = dishesResult.data.firstOrNull { it.nameFr == category } //platList
        val adapter = binding.ListePlats.adapter as PlatAdapter //categoryList = ListePlats
        adapter.updateDishes(dishCategory?.items?.map { it.nameFr?:""} as ArrayList<String>)
    }

}