package com.example

import com.example.common.Convert
import com.example.jvm.Student
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
            var msg = ""
            System.out.println("Hello bey")
        }
        makeTest {
            System.out.println("makeTest")
            return@makeTest
        }
        delay(5000)
        System.out.println("end")
    }

    inline fun makeTest(success: () -> Unit) {
        success()
        System.out.println("success")
    }
}