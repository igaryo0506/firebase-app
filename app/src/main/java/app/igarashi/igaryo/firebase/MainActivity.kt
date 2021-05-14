package app.igarashi.igaryo.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

const val TAG = "iphone"

class MainActivity : AppCompatActivity() {

    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getFromFirebase()

        addButton.setOnClickListener {
            var addText = hashMapOf("content" to editText.text.toString())
            db.collection("texts")
                .add(addText)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG,"DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener{ e ->
                    Log.w(TAG,"Error adding document",e)
                }
            getFromFirebase()
        }
    }

    private fun getFromFirebase(){
        val postData : MutableList<Post> = mutableListOf()
        db.collection("texts")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val post = Post(document.data.toString())
                    postData += post
                    Log.d(TAG, "${document.id} => ${document.data}")
                }

                val adapter = RecyclerViewAdapter(this)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = adapter
                adapter.addAll(postData)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

    }
}