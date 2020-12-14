package pro.butovanton.games

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pro.butovanton.games.net.ResponseNet
import pro.butovanton.games.utils.TestObserver
import java.util.concurrent.TimeUnit

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ServerTest {

    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    val server = (App).appcomponent.getServer()

    @Test
    fun serverTest() {
    TestObserver.test(server.get(0))
        .awaitValue(10000, TimeUnit.SECONDS)
        .assertValue { responseFromServer ->
            responseFromServer?.response == ResponseNet.Ok }
    }
}