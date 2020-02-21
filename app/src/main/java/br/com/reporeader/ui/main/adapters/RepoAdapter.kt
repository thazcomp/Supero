package br.com.reporeader.ui.main.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.reporeader.R
import br.com.reporeader.data.Repository
import br.com.reporeader.ui.pulls.activities.PullsActivity
import com.bumptech.glide.Glide

class RepoAdapter (val context:Context, val repoList:ArrayList<Repository>):RecyclerView.Adapter<RepoAdapter.ViewHolder>(){

    override fun onCreateViewHolder(group: ViewGroup, position: Int): ViewHolder {
        val v = LayoutInflater.from(group?.context).inflate(R.layout.main_item, group, false)
        return ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return repoList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        loadImage(holder, position)
        setName(holder, position)
        setFullName(holder, position)
        setPrivacy(holder, position)
        setUrl(holder, position)
        setScore(holder, position)
        setWatchers(holder, position)
        setForks(holder, position)
        setIssues(holder, position)
        setDesc(holder, position)

        setClick(holder, position)
    }

    private fun setClick(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val intent = Intent(context, PullsActivity::class.java)
            intent.putExtra("owner", repoList[position].owner!!.login)
            intent.putExtra("repo", repoList[position].name)
            context.startActivity(intent)
        }
    }

    private fun setDesc(holder: ViewHolder, position: Int) {
        holder.desc.text = repoList[position].desc
    }

    private fun setIssues(holder: ViewHolder, position: Int) {
        holder.issues.text = repoList[position].issues.toString()
    }

    private fun setForks(holder: ViewHolder, position: Int) {
        holder.forks.text = repoList[position].forks.toString()
    }

    private fun setWatchers(holder: ViewHolder, position: Int) {
        holder.watchers.text = repoList[position].watchers.toString()
    }

    private fun setScore(holder: ViewHolder, position: Int) {
        holder.score.text = repoList[position].score.toString()
    }

    private fun setUrl(holder: ViewHolder, position: Int) {
        holder.url.text = repoList[position].url
    }

    private fun setFullName(holder: ViewHolder, position: Int) {
        holder.fullname.text = repoList[position].fullname
    }

    private fun setName(holder: ViewHolder, position: Int) {
        holder.name.text = repoList[position].name
    }

    private fun setPrivacy(holder: ViewHolder, position: Int) {
        if(repoList[position].isPrivate!!){
            holder.privacy.text = context.getString(R.string.privat)
        }else{
            holder.privacy.text = context.getString(R.string.publi)
        }
    }

    private fun loadImage(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(repoList[position].owner?.avatar)
            .into(holder.picture)
    }

    override fun getItemId(position: Int): Long {
        return repoList[position].id!!.toLong()
    }

    fun setList(items:List<Repository>) {
        repoList.clear()
        repoList.addAll(ArrayList(items))
        notifyDataSetChanged()
    }

    fun addList(items:List<Repository>) {
        repoList.addAll(ArrayList(items))
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val picture = itemView.findViewById<ImageView>(R.id.iv_picture)
        val name = itemView.findViewById<TextView>(R.id.tv_name)
        val fullname = itemView.findViewById<TextView>(R.id.tv_full_name)
        val privacy = itemView.findViewById<TextView>(R.id.tv_privacy)
        val url = itemView.findViewById<TextView>(R.id.tv_url)
        val score = itemView.findViewById<TextView>(R.id.tv_score2)
        val watchers = itemView.findViewById<TextView>(R.id.tv_watchers2)
        val forks = itemView.findViewById<TextView>(R.id.tv_forks2)
        val issues = itemView.findViewById<TextView>(R.id.tv_issues2)
        val desc = itemView.findViewById<TextView>(R.id.tv_description)

    }
}