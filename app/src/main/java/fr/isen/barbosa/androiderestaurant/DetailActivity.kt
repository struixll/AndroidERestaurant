package fr.isen.barbosa.androiderestaurant


import android.os.Bundle
import android.text.Editable
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
        val ingredients = item.ingredients

        var compteur = 0

        //appel du Carrousel
        val viewPager = findViewById<ViewPager>(R.id.imageDetail)
        val leCarrouselAdapter = CarrouselAdapter(this, this.item.images)
        viewPager.adapter = leCarrouselAdapter

        if (item.ingredients.isNotEmpty()){
            val ingredientsString = StringBuilder()
            ingredients.forEach { ingredients ->
                ingredientsString.append(ingredients.nameFr)
                ingredientsString.append("\n")
            }
            binding.ingredients.text = ingredientsString

            binding.plusButton.setOnClickListener {
                compteur++
                binding.quantity.text = Editable.Factory.getInstance().newEditable(compteur.toString())
                val addition = compteur * item.prices[0].price?.toDouble()!!
                binding.addition.text = addition.toString()
            }

            binding.lessButton.setOnClickListener {
                compteur--
                binding.quantity.text = Editable.Factory.getInstance().newEditable(compteur.toString())
                val addition = compteur * item.prices[0].price?.toDouble()!!
                binding.addition.text = addition.toString()
            }

            binding.quantity.text = compteur.toString()
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}