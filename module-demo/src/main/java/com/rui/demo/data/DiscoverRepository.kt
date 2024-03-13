package com.rui.demo.data

import androidx.lifecycle.LiveData
import com.rui.base.entity.ApiResponse
import com.rui.base.entity.ApiResponseTest
import com.rui.base.network.RetrofitClient
import com.rui.demo.data.bean.JokeInfo
import com.rui.demo.data.source.HttpDataSource
import com.rui.demo.data.source.LocalDataSource
import com.rui.demo.data.source.http.HttpDataSourceImpl
import com.rui.demo.data.source.http.service.HomeApiService
import com.rui.demo.data.source.local.LocalDataSourceImpl
import com.rui.demo.data.source.local.db.Person
import com.rui.mvvmlazy.base.BaseModel
import com.rui.mvvmlazy.http.PagingData

/**
 * ******************************
 * *@Author
 * *date ：
 * *description:MVVM的Model层，统一模块的数据仓库，包含网络数据和本地数据（一个应用可以有多个Repositor）
 * *******************************
 */
val repository: DiscoverRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    DiscoverRepository()
}

class DiscoverRepository : BaseModel(), HttpDataSource, LocalDataSource {
    private val mHttpDataSource: HttpDataSource by lazy {
        HttpDataSourceImpl(
            RetrofitClient.instance.create(
                HomeApiService::class.java
            )
        )
    }
    private val mLocalDataSource: LocalDataSource by lazy {
        LocalDataSourceImpl()
    }

    override fun insertWords(vararg words: Person) {
        mLocalDataSource.insertWords(*words)
    }

    override fun updateWords(vararg words: Person) {
        mLocalDataSource.updateWords(*words)
    }

    override fun deleteWords(vararg words: Person) {
        mLocalDataSource.deleteWords(*words)
    }

    override fun deleteAllWords() {
        mLocalDataSource.deleteAllWords()
    }

    override fun getAllWordsLive(): LiveData<List<Person>> {
        return mLocalDataSource.getAllWordsLive()
    }

    override suspend fun getJoke(
        page: Int,
        size: Int
    ): ApiResponse<PagingData<JokeInfo>> {
        return mHttpDataSource.getJoke(page, size)
    }

    override suspend fun testApi(page: String): ApiResponseTest<Map<String, String>> {
        return mHttpDataSource.testApi(page)
    }
}