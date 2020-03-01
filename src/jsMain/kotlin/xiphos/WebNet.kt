package xiphos

import kotlinx.coroutines.await
import org.w3c.xhr.XMLHttpRequest
import kotlin.js.Promise

class WebNet : Net {

    override suspend fun get(url: String): String {
        return requestAsync( "GET", url).await()
            .responseText
    }

    private fun requestAsync(method: String, url: String): Promise<XMLHttpRequest> {
        val promise = Promise<XMLHttpRequest> { resolve, reject ->
                val request = XMLHttpRequest()

                request.open(method, url)
                request.onload = { _ -> resolve(request) }
                request.send()
            }
        return promise
    }

}