package am.example.retrofit_post_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class RecyclerAdapter(val list : List<UserModel.UserData>?) :
    RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (list?.size == null)  return 0 else list.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

          holder.id.setText(list?.get(position)?.id.toString())
          holder.title.setText(list?.get(position)?.title.toString())

          Glide.with(holder.image)
            .load(list?.get(position)?.url)
            .placeholder(R.drawable.ic_baseline_emoji_emotions_24)
            .error(R.drawable.ic_baseline_error_outline_24)
            .into(holder.image)

    }

    class CustomViewHolder(listItemView: View): RecyclerView.ViewHolder(listItemView){

        val image = listItemView.findViewById<ImageView>(R.id.image)
        val title = listItemView.findViewById<TextView>(R.id.title)
        var id = listItemView.findViewById<TextView>(R.id.id)
    }
}
