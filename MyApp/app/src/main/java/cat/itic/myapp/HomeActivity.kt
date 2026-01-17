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
import android.widget.ImageView
import android.widget.TextView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val newGames = mutableListOf(
            HomeNewGame("God of War Ragnarok"),
            HomeNewGame("Cyberpunk 2077")
        )

        val activityList = mutableListOf(
            HomeActivityItem("Updated notes for Cyberpunk", android.R.drawable.ic_menu_edit),
            HomeActivityItem("New message in your forum thread", android.R.drawable.ic_dialog_email)
        )

        val newGamesRecycler = findViewById<RecyclerView>(R.id.homeNewGamesRecycler)
        newGamesRecycler.layoutManager = LinearLayoutManager(this)
        newGamesRecycler.adapter = NewGamesAdapter(newGames)

        val activityRecycler = findViewById<RecyclerView>(R.id.homeActivityRecycler)
        activityRecycler.layoutManager = LinearLayoutManager(this)
        activityRecycler.adapter = ActivityAdapter(activityList)

        findViewById<ImageButton>(R.id.menuButton)
            .setOnClickListener { startActivity(Intent(this, MenuActivity::class.java)) }

        findViewById<ImageButton>(R.id.profileButton)
            .setOnClickListener { startActivity(Intent(this, ProfileActivity::class.java)) }
    }

    data class HomeNewGame(val title: String)
    data class HomeActivityItem(val text: String, val iconRes: Int)

    class NewGamesAdapter(private val items: MutableList<HomeNewGame>) :
        RecyclerView.Adapter<NewGamesAdapter.NewGameHolder>() {

        inner class NewGameHolder(view: View) : RecyclerView.ViewHolder(view) {
            val title: TextView = view.findViewById(R.id.newGameTitle)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewGameHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home_new_game, parent, false)
            return NewGameHolder(view)
        }

        override fun onBindViewHolder(holder: NewGameHolder, position: Int) {
            val game = items[position]
            holder.title.text = game.title

            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, GameViewActivity::class.java)
                intent.putExtra("gameName", game.title)
                holder.itemView.context.startActivity(intent)
            }
        }

        override fun getItemCount() = items.size
    }

    class ActivityAdapter(private val items: MutableList<HomeActivityItem>) :
        RecyclerView.Adapter<ActivityAdapter.ActivityHolder>() {

        inner class ActivityHolder(view: View) : RecyclerView.ViewHolder(view) {
            val icon: ImageView = view.findViewById(R.id.activityIcon)
            val text: TextView = view.findViewById(R.id.activityText)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home_activity, parent, false)
            return ActivityHolder(view)
        }

        override fun onBindViewHolder(holder: ActivityHolder, position: Int) {
            val item = items[position]
            holder.text.text = item.text
            holder.icon.setImageResource(item.iconRes)
        }

        override fun getItemCount() = items.size
    }
}
