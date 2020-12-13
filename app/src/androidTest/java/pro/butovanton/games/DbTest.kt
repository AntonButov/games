package pro.butovanton.games

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pro.butovanton.games.db.Data
import pro.butovanton.games.net.ResponseNet
import pro.butovanton.games.utils.TestObserver
import java.util.concurrent.TimeUnit

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DbTest {

    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @After
    fun delete() {
    GlobalScope.launch {
        dao.delete()
    }
    }

    val dao = (App).appcomponent.getDao()

    val testData1 = Data("testName1", "http...",999, 777)
    val testData2 = Data("testName2", "http...",999, 777)

    @Test
    fun dbTest() {
        Assert.assertNotNull(dao)
        GlobalScope.launch {  dao.insert(testData1) }
        dao.getAll().observeForever { value ->
           Assert.assertNotNull(value)
        }
        GlobalScope.launch { dao.insert(testData2) }
    }
}