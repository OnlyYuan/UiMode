package com.example.uimode.activity

import java.util.*

/**
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 */

class CQueue {

    var firstStack:Stack<Int> = Stack()
    var secondStack:Stack<Int> = Stack()

    fun appendTail(value: Int) {
        firstStack.push(value)
    }


    fun deleteHead(): Int {

        if (firstStack.empty()){
            return -1
        }

        while (!firstStack.empty()){
            secondStack.push(firstStack.pop())
        }
        var deletNum =  secondStack.pop()

        while(!secondStack.empty()){
            firstStack.push(secondStack.pop())
        }

        return deletNum
    }


}