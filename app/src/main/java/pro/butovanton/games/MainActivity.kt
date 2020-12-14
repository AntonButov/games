package pro.butovanton.games

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pro.butovanton.games.databinding.ActivityMainBinding
import pro.butovanton.games.db.Data

class MainActivity : AppCompatActivity() {

    private val ENDVALUE = 1
    var isLoading = false

    val model: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val adapterRecycler = Adapter(layoutInflater)
        binding.recycler.apply {
            adapter = adapterRecycler
            layoutManager = LinearLayoutManager(context)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val last = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    if (last == recyclerView.adapter!!.itemCount - 1 && !isLoading && dy != 0 ) {
                        isLoading = true
                        model.getMoreGames()
                    }
                }
            })
        }
        setContentView(binding.root)

        model.getGames().observe(this, object : Observer<List<Data>> {
            override fun onChanged(data: List<Data>) {
                adapterRecycler.add(data)
                isLoading = false
            }
        })

    }
}