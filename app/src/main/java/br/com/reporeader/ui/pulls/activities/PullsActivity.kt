package br.com.reporeader.ui.pulls.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.reporeader.R
import br.com.reporeader.data.responses.PullsResponse
import br.com.reporeader.ui.pulls.PullsViewModel
import br.com.reporeader.ui.pulls.adapters.PullsAdapter
import br.com.reporeader.util.PaginationScrollListener
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_pulls.*
import org.koin.android.viewmodel.ext.android.viewModel


class PullsActivity : AppCompatActivity() {

    private val mViewModel: PullsViewModel by viewModel()
    private var disposable: Disposable? = null
    private var lastVisibleItemPosition: Int? = null
    private var owner:String? = null
    private var repo:String? = null

    private val mAdapter: PullsAdapter by lazy {
        PullsAdapter(
            this,
            arrayListOf()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pulls)

        owner = intent.getStringExtra("owner")
        repo = intent.getStringExtra("repo")
        initRecycler()
        getPulls()
    }

    private fun initRecycler() {

        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler.layoutManager = manager
        lastVisibleItemPosition = manager.findLastVisibleItemPosition()
        recycler.adapter = mAdapter
    }


    private fun getPulls() {
        showLoading()
        disposable = mViewModel.getPulls(owner!!, repo!!).subscribeBy(
            onNext = {
                addItems(it)
            },
            onError = {
                hideLoading()
                Log.e("PullsActivity", it.message, it)
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            },
            onComplete = {
                hideLoading()
                Toast.makeText(this, "Pulls successfull loaded.", Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun hideLoading() {
        loading.visibility = View.INVISIBLE
    }

    private fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    private fun addItems(items:ArrayList<PullsResponse>) {
        mAdapter.addList(items)
    }

    override fun onStop() {
        super.onStop()
        disposable?.dispose()
    }
}
