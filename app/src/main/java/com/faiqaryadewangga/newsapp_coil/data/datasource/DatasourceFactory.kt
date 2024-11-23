package com.faiqaryadewangga.newsapp_coil.data.datasource

abstract class DatasourceFactory {
    abstract fun createDatasource(): BaseDatasource
}