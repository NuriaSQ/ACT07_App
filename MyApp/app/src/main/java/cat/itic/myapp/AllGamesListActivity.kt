package cat.itic.myapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class AllGamesListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_games_list)

        val games = mutableListOf("NINTENDO", "PLAYSTATION", "STEAM", "GOG", "XBOX", "MOBILE")

        val recyclerView = findViewById<RecyclerView>(R.id.gamesRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        val adapter = GameAdapter(games)
        recyclerView.adapter = adapter

        val search = findViewById<EditText>(R.id.searchExplore)
        search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                adapter.filter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        findViewById<ImageButton>(R.id.menuButton)
            .setOnClickListener { startActivity(Intent(this, MenuActivity::class.java)) }

        findViewById<ImageButton>(R.id.profileButton)
            .setOnClickListener { startActivity(Intent(this, ProfileActivity::class.java)) }
    }

    class GameAdapter(private var items: MutableList<String>) :
        RecyclerView.Adapter<GameAdapter.GameHolder>() {

        private val allItems = items.toList()

        inner class GameHolder(view: View) : RecyclerView.ViewHolder(view) {
            val name: TextView = view.findViewById(R.id.gameName)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_game, parent, false)
            return GameHolder(view)
        }

        override fun onBindViewHolder(holder: GameHolder, position: Int) {
            val game = items[position]
            holder.name.text = game
            holder.itemView.setOnClickListener {
                Toast.makeText(holder.itemView.context, "Clicked: $game", Toast.LENGTH_SHORT).show()
            }
        }

        override fun getItemCount() = items.size

        @SuppressLint("NotifyDataSetChanged")
        fun filter(query: String) {
            items = if (query.isEmpty()) allItems.toMutableList()
            else allItems.filter { it.contains(query, true) }.toMutableList()
            notifyDataSetChanged()
        }
    }
}

