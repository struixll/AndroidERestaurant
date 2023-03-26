package fr.isen.barbosa.androiderestaurant


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.isen.barbosa.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.barbosa.androiderestaurant.model.Items



class DetailActivity : AppCompatActivity() {

    private lateinit var item: Items
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        item = intent.getSerializableExtra("detail") as Items
        val title = item.nameFr
        supportActionBar?.title=title
        //val ingredients = item.ingredients ?:""


        //val viewPager = findViewById<ViewPager>(R.id.imageDetail)

        //val leCarrouselAdapter = CarrouselAdapter(this, this.item.images)
        //viewPager.adapter = leCarrouselAdapter



    }



    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}