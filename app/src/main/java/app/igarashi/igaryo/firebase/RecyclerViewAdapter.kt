package app.igarashi.igaryo.firebase

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val context:Context): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val posts:MutableList<Post> = mutableListOf()
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val postText: TextView = view.findViewById(R.id.postTextView)
        val timeText: TextView = view.findViewById(R.id.timeTextView)
        val favorite: Button = view.findViewById(R.id.favoriteButton)
        init{
            favorite.setOnClickListener {
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.post_data_cell,parent,false)
        return  ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]
        holder.postText.text = post.text
        holder.timeText.text = post.time
        holder.favorite.text = post.favorite.toString() + "いいね"
    }

    fun addAll(items:List<Post>){
        this.posts.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}