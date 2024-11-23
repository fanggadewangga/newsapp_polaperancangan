package com.faiqaryadewangga.newsapp_coil.data.datasource.remote

import com.faiqaryadewangga.newsapp_coil.data.datasource.BaseDatasource
import com.faiqaryadewangga.newsapp_coil.data.datasource.DatasourceFactory

class RemoteDatasourceFactory : DatasourceFactory() {
    override fun createDatasource(): BaseDatasource {
        return RemoteDatasource()
    }
}