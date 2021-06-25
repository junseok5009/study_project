package com.hjs.study.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hjs.study.activity.sta_tag
import com.hjs.study.model.TodayStock
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

class VmTodayStock: ViewModel() {

    /* note
        MutableLiveData : 값의 get/set 모두를 할 수 있다. : 값의 get/set 모두를 허용하기에, 값의 변경과 읽기 모두 가능하다.
        LiveData : 값의 get()만을 할 수 있다. : LiveData는 읽기 전용의 데이터를 만들기에 값의 변경은 불가능하다.
          - LiveData는 항상 MainThread로 값을 처리한다. 그래서 아래와 같이 사용하는 경우는 좋은 방법이 아니다.
             > ViewModel보다 더 안쪽인 Repository(UseCase)와 같이 내부에서 LiveData로 값을 가져오는 경우
             > View 업데이트가 없는 코드에서 LiveData를 활용하는 경우

       note
        >> 이런 기법은 ViewModel과 View의 역할을 분리하기 위함이라 이렇게 사용한다.
        ViewModel은 언제나 새로운 값의 변경이 일어나고, 다시 읽을 수 있는 형태로 사용하는 것이고, View는 값의 입력이 아닌 읽기만을 허용하는 것이다.
     */

    val _liveData : MutableLiveData<ArrayList<TodayStock>> = MutableLiveData()
    val liveData: LiveData<ArrayList<TodayStock>>
        get() = _liveData
    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable){
        compositeDisposable.add(disposable)
    }

    fun loadData(){
        /*
         sample
            _liveData.value = ~~~
                * set : MainThread(UI)가 보장될 경우에는 set을 활용한다.
            _liveData.postValue( ~~~ )
                * postValue : MainThread가 아닌 IO 스케쥴러를 활용하는 경우 postValue를 활용한다
          note
           > 정리하면 setValue를 하든 postValue를 하든 LiveData는 항상 MainThread에서 사용할 값을 보증한다.
           > 결국 데이터의 처리가 IO에서 발생해야 하는 경우라면 LiveData 활용은 맞지 않는 것이다.
        */

    }

    override fun onCleared() {
        compositeDisposable.dispose()
        Log.d(sta_tag, "VmTodayStock onCleared() / compositeDisposable.dispose()")
        super.onCleared()
    }

}