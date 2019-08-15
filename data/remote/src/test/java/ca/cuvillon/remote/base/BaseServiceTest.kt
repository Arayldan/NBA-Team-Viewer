package ca.cuvillon.remote.base

import ca.cuvillon.remote.dispatchers.MockServerDispatcher
import ca.cuvillon.remote.di.createRemoteModule
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

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
        mockServer.dispatcher = MockServerDispatcher()
        mockServer.start()
    }

    private fun stopMockServer() {
        mockServer.shutdown()
    }
}
