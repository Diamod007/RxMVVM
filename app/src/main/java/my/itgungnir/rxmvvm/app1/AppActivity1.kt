package my.itgungnir.rxmvvm.app1

import android.arch.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_app1.*
import my.itgungnir.rxmvvm.R
import my.itgungnir.rxmvvm.core.mvvm.BaseActivity
import my.itgungnir.rxmvvm.core.mvvm.buildActivityViewModel
import org.jetbrains.anko.toast

/**
 * MVVM Activity
 */
class AppActivity1 : BaseActivity() {

    private val viewModel by lazy {
        buildActivityViewModel(
            activity = this,
            viewModelClass = AppViewModel1::class.java
        )
    }

    override fun layoutId(): Int = R.layout.activity_app1

    override fun initComponent() {
        button.setOnClickListener {
            viewModel.generateRandomNumber()
        }
    }

    override fun observeVM() {

        viewModel.pick(AppState1::randomNum)
            .observe(this, Observer { randomNum ->
                randomNum?.a?.let {
                    number.text = it.toString()
                }
            })

        viewModel.pick(AppState1::error)
            .observe(this, Observer { error ->
                error?.a?.message?.let {
                    toast(it)
                }
            })
    }
}