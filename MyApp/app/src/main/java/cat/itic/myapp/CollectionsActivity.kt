package cat.itic.myapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CollectionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collections)

        val collections =
            mutableListOf("Collection 1", "Collection 2", "Collection 3", "New Collection")

        val recycler = findViewById<RecyclerView>(R.id.collectionsRecycler)
        recycler.layoutManager = GridLayoutManager(this, 2)
        recycler.adapter = CollectionsAdapter(collections)

        findViewById<ImageButton>(R.id.menuButton)
            .setOnClickListener { startActivity(Intent(this, MenuActivity::class.java)) }

        findViewById<ImageButton>(R.id.profileButton)
            .setOnClickListener { startActivity(Intent(this, ProfileActivity::class.java)) }

        findViewById<Button>(R.id.createButton)
            .setOnClickListener {
                startActivity(Intent(this, CollectionCreateActivity::class.java))
            }
    }

    class CollectionsAdapter(private val items: MutableList<String>) :
        RecyclerView.Adapter<CollectionsAdapter.CollectionHolder>() {

        inner class CollectionHolder(view: View) : RecyclerView.ViewHolder(view) {
            val name: TextView = view.findViewById(R.id.collectionName)
            val modify: Button = view.findViewById(R.id.modifyButton)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_collection, parent, false)
            return CollectionHolder(view)
        }

        override fun onBindViewHolder(holder: CollectionHolder, position: Int) {
            val collection = items[position]
            holder.name.text = collection

            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, CollectionViewActivity::class.java)
                intent.putExtra("collectionName", collection)
                holder.itemView.context.startActivity(intent)
            }

            holder.modify.setOnClickListener {
                val intent = Intent(holder.itemView.context, CollectionsModifyActivity::class.java)
                intent.putExtra("collectionName", collection)
                holder.itemView.context.startActivity(intent)
            }
        }

        override fun getItemCount() = items.size
    }
}
