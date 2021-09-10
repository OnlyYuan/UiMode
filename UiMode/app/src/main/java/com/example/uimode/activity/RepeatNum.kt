package com.example.uimode.activity

/**
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 */
class RepeatNum {

    fun findRepeatNumber(nums: IntArray): Int {

        var mHash = HashSet<Int>();
        for ( element in nums){
            if (!(mHash.add(element))){
                return element
            }

        }
        return 0;
    }

}