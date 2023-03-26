package fr.isen.barbosa.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import fr.isen.barbosa.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_home)


        val entree = findViewById<TextView>(R.id.Entrees)
        entree.setOnClickListener {
            Log.d("HomeActivity", "vous avez cliqué sur entrée")
            Toast.makeText(this,"vous avez cliqué sur entrée", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("category",getString(R.string.home_starters))
            startActivity(intent)
        }


        val plat = findViewById<TextView>(R.id.Plats)
        plat.setOnClickListener {
            Log.d("HomeActivity", "vous avez cliqué sur plats")
            Toast.makeText(this,"vous avez cliqué sur plats", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("category", "Plats")
            startActivity(intent)
        }


        val dessert = findViewById<TextView>(R.id.Dessert)
        dessert.setOnClickListener {
            Log.d("HomeActivity", "vous avez cliqué sur Dessert")
            Toast.makeText(this,"vous avez cliqué sur Dessert", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("category", "Desserts")
            startActivity(intent)
        }


    }

    fun lala(){
        println("clicked")
    }


}



