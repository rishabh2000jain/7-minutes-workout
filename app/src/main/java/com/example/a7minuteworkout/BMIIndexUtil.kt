package com.example.a7minuteworkout

object BMIIndexUtil {
    private val map: Map<String, String> =
        mapOf(

            Pair("Underweight", "You are underweight buddy you need to eat something.😅🍔"),
            Pair("Normal", "You are fit you need to make some mess.😅🍔"),
            Pair("Overweight", "You are Overweight bro you eat too much,eat less and get your ass in gym.😅🏋️💪")

        )

    fun getBMICategory(index: Double): String {
        return when (index) {
            in 0.0..18.4 -> "Underweight"
            in 18.5..24.9 -> "Normal"
            else -> "Overweight"
        }
    }

    fun getBMIDescFromCategory(category:String): String {
        return map[category].orEmpty()
    }

}