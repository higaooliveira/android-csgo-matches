package com.higor.csgomatches.ui.tournaments

import android.content.Context
import androidx.databinding.*
import com.higor.csgomatches.R
import com.higor.csgomatches.data.tournament.entity.Tournament
import com.higor.csgomatches.data.tournament.repository.TournamentsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException

open class TournamentsViewModel(
    private val repository: TournamentsRepository,
    context: Context
): BaseObservable() {

    private val context: Context = context.applicationContext

    val tournaments: ObservableList<Tournament> = ObservableArrayList()
    val tournamentsLoading = ObservableBoolean(false)
    val emptyViewShowing = ObservableBoolean(false)
    val errorViewShowing = ObservableBoolean(false)
    val errorString = ObservableField<String>()

    fun start() {
        discoverTournaments(tournaments.isEmpty())
    }

    private fun discoverTournaments(showLoading: Boolean) {
        tournamentsLoading.set(showLoading)
        errorViewShowing.set(false)
        emptyViewShowing.set(false)

        repository.getRunningTournaments()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableObserver<List<Tournament>>() {
                override fun onNext(value: List<Tournament>?) {
                    val isEmpty = value == null || value.isEmpty()

                    if(!isEmpty){
                        tournaments.clear()
                        tournaments.addAll(value!!)
                    }

                    emptyViewShowing.set(isEmpty)
                }

                override fun onError(exception: Throwable) {
                    errorViewShowing.set(true);
                    tournamentsLoading.set(false);
                    emptyViewShowing.set(false);

                    errorString.set(getErrorMessage(exception));
                }

                override fun onComplete() {
                    tournamentsLoading.set(false);
                    errorViewShowing.set(false);
                }

            })
    }

    private fun getErrorMessage(exception: Throwable): String {
        if (exception is HttpException) {
            // We had non-2XX http error
            return context.getString(R.string.error_msg_server);
        }
        return if (exception is IOException) {
            // A network or conversion error happened
            context.getString(R.string.error_msg_network);
        } else {
            // Generic error handling
            context.getString(R.string.error_msg_network_generic);
        }
    }

}
