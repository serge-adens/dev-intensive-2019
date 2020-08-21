package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firstName to lastName
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val list = listOf(firstName, lastName).filter { !it.isNullOrBlank() }
        return if (list.count() == 0) null
        else list.map { it!!.first().toUpperCase() }
            .joinToString("")
    }

    private val mapTrans = mapOf<String, String>(
        "а" to "a",
        "б" to "b",
        "в" to "v",
        "г" to "g",
        "д" to "d",
        "е" to "e",
        "ё" to "e",
        "ж" to "zh",
        "з" to "z",
        "и" to "i",
        "й" to "i",
        "к" to "k",
        "л" to "l",
        "м" to "m",
        "н" to "n",
        "о" to "o",
        "п" to "p",
        "р" to "r",
        "с" to "s",
        "т" to "t",
        "у" to "u",
        "ф" to "f",
        "х" to "h",
        "ц" to "c",
        "ч" to "ch",
        "ш" to "sh",
        "щ" to "sh'",
        "ъ" to "",
        "ы" to "i",
        "ь" to "",
        "э" to "e",
        "ю" to "yu",
        "я" to "ya"
    )
    private val mapTransUpper =
        mapTrans.map { entry -> entry.key.toUpperCase() to entry.value.capitalize() }.toMap()

    fun transliteration(payload: String, divider: String = " "): String {
        val parts = payload.split(" ")
        return parts
            .map {
                it.map { s -> mapTrans[s.toString()] ?: mapTransUpper[s.toString()] ?: s }
                    .joinToString("")
            }
            .joinToString(divider)
    }

    fun getTensAndOnes(value: Int): Pair<Int, Int> {
        return Pair(value.div(10).rem(10), value.rem(10))
    }

}
