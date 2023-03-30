package fr.isen.barbosa.androiderestaurant


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import fr.isen.barbosa.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.barbosa.androiderestaurant.model.Items


@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    private lateinit var item: Items
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        item = intent.getSerializableExtra("detail") as Items

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Récupération du nom du plat
        val title = item.nameFr
        supportActionBar?.title=title
        //val ingredients = item.ingredients ?:""

        //appel du Carrousel
        val viewPager = findViewById<ViewPager>(R.id.imageDetail)
        val leCarrouselAdapter = CarrouselAdapter(this, this.item.images)
        viewPager.adapter = leCarrouselAdapter



    }



    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}