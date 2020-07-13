package com.example

import com.example.common.Convert
import jdk.nashorn.internal.objects.Global
import kotlinx.coroutines.*
import javax.xml.bind.JAXBElement

/**
 * Created by {wq} on 2018/6/28.
 */
object Test {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking<Unit> {
        CoroutineScope(Dispatchers.IO).launch {
            System.out.println("Hello world")
            var msg = ""
            showGetWorld(getWorld())
            System.out.println("Hello bey")
        }
        delay(3000)
    }

    suspend fun getWorld(): String {
        var msg = "hehe"
        val job = GlobalScope.launch {
            delay(1000)
            msg = "getWorld"
        }
       job.join()
        return msg
    }

    suspend fun showGetWorld(msg: String) {
        System.out.println("showGetWorld")
        System.out.println(msg)
    }
}