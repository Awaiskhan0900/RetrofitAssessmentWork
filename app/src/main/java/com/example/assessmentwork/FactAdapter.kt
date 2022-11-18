package com.example.assessmentwork
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// on below line we are creating
// a course rv adapter class.
class FactAdapter(
    // on below line we are passing variables
    // as course list and context
    private var factList: ArrayList<Facts>,
) : RecyclerView.Adapter<FactAdapter.CourseViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FactAdapter.CourseViewHolder {
        // this method is use to inflate the layout file which
        // we have created for our recycler view.
        // on below line we are inflating our layout file.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.course_rv_item,
            parent, false
        )
        // at last we are returning our view
        // holder class with our item View File.
        return CourseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FactAdapter.CourseViewHolder, position: Int) {
        // on below line we are setting data to our text view and our image view.
        holder.courseIV.text = "Fact:  "+factList.get(position).languageName
        holder.courseNameTV.text = "Length:  "+factList.get(position).languageImg.toString()
    }

    override fun getItemCount(): Int {
        // on below line we are
        // returning our size of our list
        return factList.size
    }

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line we are initializing our
        // course name text view and our image view.
        val courseNameTV: TextView = itemView.findViewById(R.id.title_tv)
        val courseIV: TextView = itemView.findViewById(R.id.id_tv)
    }
}