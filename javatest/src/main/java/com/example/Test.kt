package com.example

import com.example.common.Convert
import jdk.nashorn.internal.objects.Global
import javax.xml.bind.JAXBElement

/**
 * Created by {wq} on 2018/6/28.
 */
object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        var a=-85;
        var b=0x00ff;
        var c=a and b;
        System.out.println(c)
        System.out.println(Integer.toHexString(c))
    }
}