package app.igarashi.igaryo.firebase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val context:Context): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    val posts:MutableList<Post> = mutableListOf()
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val postText: TextView = view.findViewById(R.id.postTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.post_data_cell,parent,false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]
        holder.postText.text = post.text
    }

    fun addAll(items:List<Post>){
        this.posts.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}