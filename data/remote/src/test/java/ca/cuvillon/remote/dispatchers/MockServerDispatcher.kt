package ca.cuvillon.remote.dispatchers

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.io.File
import java.net.HttpURLConnection

class MockServerDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/input.json" -> MockResponse().setBody(getJson("get_teams.json"))
            else -> MockResponse().setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
        }
    }

    private fun getJson(path: String): String {
        val uri = javaClass.classLoader.getResource(path)!!
        val file = File(uri.path)
        return String(file.readBytes())
    }
}
