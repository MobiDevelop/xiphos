package xiphos

interface Net {

    suspend fun get(url: String): String

}