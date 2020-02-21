package br.com.reporeader.ui.main.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import br.com.reporeader.R
import br.com.reporeader.data.Repository
import br.com.reporeader.ui.main.MainViewModel
import br.com.reporeader.ui.main.adapters.RepoAdapter
import br.com.reporeader.util.PaginationScrollListener
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mViewModel: MainViewModel by viewModel()
    private lateinit var scrollListener: OnScrollListener
    private var disposable:Disposable? = null
    private var lastVisibleItemPosition: Int? = null
    private var page = 1
    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false

    private val mAdapter: RepoAdapter by lazy {
        RepoAdapter(
            this,
            arrayListOf()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()
        getRepositories(page)
        page++
    }

    private fun initRecycler() {

        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler.layoutManager = manager
        lastVisibleItemPosition = manager.findLastVisibleItemPosition()
        recycler.adapter = mAdapter
        recycler.addOnScrollListener(object:PaginationScrollListener(manager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                getMoreItems()
            }
        })
    }

    fun getMoreItems() {
        isLoading = false
        addRepositories(page)
        page++
    }

    private fun getRepositories(page:Int) {
        showLoading()
        disposable = mViewModel.getRepositories(page).subscribeBy(
            onNext = {
                setAdapterItems(it.items)
            },
            onError = {
                hideLoading()
                Log.e("MainActivity", it.message, it)
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            },
            onComplete = {
                hideLoading()
                Toast.makeText(this, "Repositories successfull loaded.", Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun addRepositories(page:Int) {
        showLoading()
        disposable = mViewModel.getRepositories(page).subscribeBy(
            onNext = {
                addItems(it.items)
            },
            onError = {
                hideLoading()
                Log.e("MainActivity", it.message, it)
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            },
            onComplete = {
                hideLoading()
                Toast.makeText(this, "Repositories successfull loaded.", Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun hideLoading() {
        loading.visibility = View.INVISIBLE
    }

    private fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    private fun setAdapterItems(items:List<Repository>) {
        mAdapter.setList(items)
    }

    private fun addItems(items:List<Repository>) {
        mAdapter.addList(items)
    }

    override fun onStop() {
        super.onStop()
        disposable?.dispose()
    }
}
