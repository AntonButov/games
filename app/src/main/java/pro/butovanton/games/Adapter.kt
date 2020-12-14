package pro.butovanton.games

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import okhttp3.internal.notifyAll
import pro.butovanton.games.databinding.ItemBinding
import pro.butovanton.games.db.Data


class Adapter(private val inflater: LayoutInflater): RecyclerView.Adapter<Adapter.Holder>() {

    val games = mutableListOf<Data>()

      class Holder(val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
            Holder(ItemBinding.inflate(inflater, parent, false))

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.binding.itemName.text = games[position].name
            holder.binding.itemChanels.text = "Chanels: " + games[position].chanels
            holder.binding.itemViewers.text = "Viewers: " + games[position].viewers
            val url: String = games[position].logo
            Glide
                .with(holder.itemView)
                .load(url)
                .centerCrop()
                .into(holder.binding.itemLogo)
        }

        fun add(gamesMore: List<Data>) {
            games.clear()
            games.addAll(gamesMore)
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = games.size
}

