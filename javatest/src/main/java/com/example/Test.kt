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
        launch {
            if(isActive)
            delay(10000L)
            println("Task from runBlocking")
        }

        coroutineScope { // 创建一个协程作用域
            launch {
                delay(5000L)
                println("Task from nested launch")
            }

            delay(100L)
            println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
        }
        //会等待coroutineScope执行完成，才走下面的语句，coroutineScope会等待子协程都执行完成，才会执行下面的语句
        println("Coroutine scope is no over")
        println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
    }
}