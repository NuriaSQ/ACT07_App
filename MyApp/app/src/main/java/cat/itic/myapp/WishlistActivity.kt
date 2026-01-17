package cat.itic.myapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class WishlistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wishlist)

        val wishlist = mutableListOf("Cyberpunk 2077", "Hollow Knight", "Elden Ring", "Super Mario Bros")

        val recyclerView = findViewById<RecyclerView>(R.id.wishlistRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = WishlistAdapter(wishlist)
        recyclerView.adapter = adapter

        val search = findViewById<EditText>(R.id.searchWishlist)
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

        findViewById<Button>(R.id.addButton)
            .setOnClickListener {
                Toast.makeText(this, "ADD clicked", Toast.LENGTH_SHORT).show()
            }

        findViewById<Button>(R.id.filterButton)
            .setOnClickListener {
                Toast.makeText(this, "FILTER clicked", Toast.LENGTH_SHORT).show()
            }
    }

    class WishlistAdapter(private var items: MutableList<String>) :
        RecyclerView.Adapter<WishlistAdapter.WishlistHolder>() {

        private val allItems = items.toList()

        inner class WishlistHolder(view: View) : RecyclerView.ViewHolder(view) {
            val title: TextView = view.findViewById(R.id.wishlistTitle)
            val delete: Button = view.findViewById(R.id.deleteButton)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_wishlist, parent, false)
            return WishlistHolder(view)
        }

        override fun onBindViewHolder(holder: WishlistHolder, position: Int) {
            val game = items[position]
            holder.title.text = game

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

        @SuppressLint("NotifyDataSetChanged")
        fun filter(query: String) {
            items = if (query.isEmpty()) allItems.toMutableList()
            else allItems.filter { it.contains(query, true) }.toMutableList()
            notifyDataSetChanged()
        }
    }
}
