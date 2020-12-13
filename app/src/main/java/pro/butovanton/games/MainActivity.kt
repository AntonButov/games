package pro.butovanton.games

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import pro.butovanton.games.databinding.ActivityMainBinding
import pro.butovanton.games.db.Data

class MainActivity : AppCompatActivity() {

    val model: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val adapterRecycler = Adapter(layoutInflater)
        binding.recycler.apply {
            adapter = adapterRecycler
            layoutManager = LinearLayoutManager(context)
        }
        setContentView(binding.root)

        model.getGames().observe(this, object : Observer<List<Data>> {
            override fun onChanged(data: List<Data>) {
                adapterRecycler.add(data)
            }
        })

        adapterRecycler.onMoreData = object : Adapter.OnMoreData {
            override fun giveMore() {
            model.getMoreGames()
            }
        }
    }
}