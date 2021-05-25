package app.igarashi.igaryo.firebase

import android.app.DownloadManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime

const val TAG = "iphone"

class MainActivity : AppCompatActivity() {

    private val db = Firebase.firestore

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getFromFirebase()

        addButton.setOnClickListener {
            val time = LocalDateTime.now().toString()
            val text = editText.text.toString()
            val addPost = Post(text,time)
            db.collection("texts")
                .add(addPost)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this, "投稿しました", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener{ e ->
                    Toast.makeText(this, "投稿に失敗しました", Toast.LENGTH_LONG).show()
                }
            getFromFirebase()
        }
    }

    private fun getFromFirebase(){

        val adapter = RecyclerViewAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        db.collection("texts")
                .orderBy(Post::time.name,Query.Direction.DESCENDING)
                .limit(100)
                .get()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val postList = it.result!!.toObjects(Post::class.java)
                        adapter.addAll(postList)
                        adapter.notifyDataSetChanged()
                    }else{
                        Toast.makeText(this, "投稿の取得に失敗しました", Toast.LENGTH_LONG).show()
                    }
                }
    }

    fun addFavorite(position:Int){
        
    }
}