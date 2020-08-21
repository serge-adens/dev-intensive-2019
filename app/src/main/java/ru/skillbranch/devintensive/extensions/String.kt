package ru.skillbranch.devintensive.extensions

fun String.stripHtml(): String {
    val regex1 = """<.+?>""".toRegex()
    val regex2 = """\s+""".toRegex()
    val regex3 = """&\w+;""".toRegex()
    return regex1.replace(this, "").replace(regex2, " ").replace(regex3, "")

}

fun String.truncate(len: Int = 16): String {
    val trimmed = this.trimEnd()
    return when {
        trimmed.length <= len -> trimmed
        else -> trimmed.take(len).trimEnd() + "..."
    }
}