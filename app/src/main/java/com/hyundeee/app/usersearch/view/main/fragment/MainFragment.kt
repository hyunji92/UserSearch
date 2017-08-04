package com.hyundeee.app.usersearch.view.main.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.hyundeee.app.usersearch.dto.SearchResponse
import com.hyundeee.app.usersearch.dto.User
import com.hyundeee.app.usersearch.view.main.presenter.MainPresenter
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.hyundeee.app.usersearch.R
import com.hyundeee.app.usersearch.Yame
import com.hyundeee.app.usersearch.view.main.di.DaggerMainUserListComponent
import com.hyundeee.app.usersearch.view.main.di.MainUserListModule
import com.hyundeee.app.usersearch.view.main.fragment.adpter.UserListAdapter
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Created by jeonghyeonji on 2017. 8. 3..
 */
class MainFragment : Fragment(), MainPresenter.View {

    @Inject
    lateinit var presenter: MainPresenter

    private var index: Int = 0

    val subject: PublishSubject<String> = PublishSubject.create()
    val items by lazy { ArrayList<User>() }

    val userAdapter by lazy { UserListAdapter(context, items) }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val rv = inflater?.inflate(R.layout.fragment_list, container, false) as RecyclerView
        rv.apply {
            val linearLayout = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            layoutManager = linearLayout
            adapter = userAdapter
        }

        DaggerMainUserListComponent.builder()
                .mainUserListModule(MainUserListModule(this))
                .build()
                .inject(this)


        if (arguments.get("position") == 1) {
            Log.d("WTF", "POSITION : 1 ")
            Yame.subject.subscribe({
                userAdapter.items.add(it)
                userAdapter.notifyDataSetChanged()
            })
        }

        return rv
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt("Index", index)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onRefreshListView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDataLoaded(storeResponse: SearchResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDataFailed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDataComplete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun searchUser(searchWord: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}