package cat.itic.myapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class CollectionViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection_view)

        val games = mutableListOf("Mass Effect", "Silent Hill 2", "Yakuza")

        val recycler = findViewById<RecyclerView>(R.id.collectionGamesRecycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = CollectionGamesAdapter(games)

        findViewById<ImageButton>(R.id.menuButton)
            .setOnClickListener { startActivity(Intent(this, MenuActivity::class.java)) }

        findViewById<ImageButton>(R.id.profileButton)
            .setOnClickListener { startActivity(Intent(this, ProfileActivity::class.java)) }
    }

    class CollectionGamesAdapter(private val items: MutableList<String>) :
        RecyclerView.Adapter<CollectionGamesAdapter.GameHolder>() {

        inner class GameHolder(view: View) : RecyclerView.ViewHolder(view) {
            val name: TextView = view.findViewById(R.id.gameName)
            val delete: Button = view.findViewById(R.id.deleteButton)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_selected_game, parent, false)
            return GameHolder(view)
        }

        override fun onBindViewHolder(holder: GameHolder, position: Int) {
            val game = items[position]
            holder.name.text = game

            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, GameViewActivity::class.java)
                intent.putExtra("gameName", game)
                holder.itemView.context.startActivity(intent)
            }

            holder.delete.setOnClickListener {
                items.removeAt(position)
                notifyItemRemoved(position)
            }
        }

        override fun getItemCount() = items.size
    }
}
