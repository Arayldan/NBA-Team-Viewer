package ca.cuvillon.remote.base

import ca.cuvillon.remote.di.createRemoteModule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import java.io.File
import java.net.HttpURLConnection

abstract class BaseServiceTest : KoinTest {

    private lateinit var mockServer: MockWebServer

    @Before
    open fun setUp() {
        configureMockServer()
        configureDi()
    }

    @After
    open fun tearDown() {
        stopMockServer()
        stopKoin()
    }

    private fun configureDi() {
        startKoin {
            modules(createRemoteModule(mockServer.url("/").toString()))
        }
    }

    private fun configureMockServer() {
        mockServer = MockWebServer()
        mockServer.start()
    }

    private fun stopMockServer() {
        mockServer.shutdown()
    }

    fun mockHttpResponse(fileName: String, responseCode: Int = HttpURLConnection.HTTP_OK) {
        mockServer.enqueue(
            MockResponse().setResponseCode(responseCode).setBody(getJson(fileName))
        )
    }

    private fun getJson(path: String): String {
        val uri = javaClass.classLoader.getResource(path)!!
        val file = File(uri.path)
        return String(file.readBytes())
    }
}
