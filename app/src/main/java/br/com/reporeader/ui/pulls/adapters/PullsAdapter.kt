package br.com.reporeader.ui.pulls.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.reporeader.R
import br.com.reporeader.data.responses.PullsResponse
import com.bumptech.glide.Glide

class PullsAdapter (val context:Context, val pullList:ArrayList<PullsResponse>):RecyclerView.Adapter<PullsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(group: ViewGroup, position: Int): ViewHolder {
        val v = LayoutInflater.from(group?.context).inflate(R.layout.pull_item, group, false)
        return ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return pullList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        loadImage(holder, position)
        setName(holder, position)
        setTitle(holder, position)
        setUrl(holder, position)
        setBody(holder, position)
    }

    private fun setName(holder: ViewHolder, position: Int) {
        holder.name.text = pullList[position].user.login
    }

    private fun setTitle(holder: ViewHolder, position: Int) {
        holder.title.text = pullList[position].title
    }

    private fun setUrl(holder: ViewHolder, position: Int) {
        holder.url.text = pullList[position].htmlUrl
    }

    private fun setBody(holder: ViewHolder, position: Int) {
        holder.body.text = pullList[position].body
    }

    private fun loadImage(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(pullList[position].user.avatar)
            .into(holder.picture)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun addList(items:List<PullsResponse>) {
        pullList.addAll(ArrayList(items))
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val picture = itemView.findViewById<ImageView>(R.id.iv_picture)
        val name = itemView.findViewById<TextView>(R.id.tv_name)
        val url = itemView.findViewById<TextView>(R.id.tv_url)
        val body = itemView.findViewById<TextView>(R.id.tv_body)


    }
}