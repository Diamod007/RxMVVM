package my.itgungnir.rxmvvm.common.redux

import android.app.Application
import com.google.gson.Gson
import my.itgungnir.rxmvvm.common.redux.middleware.MultipleMiddleware
import my.itgungnir.rxmvvm.common.redux.middleware.PlusMiddleware
import my.itgungnir.rxmvvm.core.redux.BaseRedux

class MyRedux(context: Application) : BaseRedux<AppState>(
    context = context,
    initialState = AppState(),
    reducer = MyReducer(),
    middlewareList = listOf(PlusMiddleware(), MultipleMiddleware())
) {

    companion object {

        lateinit var instance: MyRedux

        fun init(context: Application) {
            instance = MyRedux(context)
        }
    }

    override fun deserializeToCurrState(json: String): AppState? =
        Gson().fromJson(json, AppState::class.java)
}