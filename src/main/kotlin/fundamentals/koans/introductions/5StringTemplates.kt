package fundamentals.koans.introductions

/**
 * Triple-quoted strings are not only useful for multiline strings but also for creating regex patterns
 * as you don't need to escape a backslash with a backslash.
 * The following pattern matches a date in the format 13.06.1992 (two digits, a dot, two digits, a dot, four digits):
 * """\d{2}\.\d{2}\.\d{4}"""
 * fun getPattern() = """\d{2}\.\d{2}\.\d{4}"""
 * Using the month variable, rewrite this pattern in such a way that it
 * matches the date in the format 13 JUN 1992 (two digits, one whitespace,
 * a month abbreviation, one whitespace, four digits).
 *
 * val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"
 *
 * fun getPattern(): String = TODO
 */


fun main() {

}

val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"

fun getPattern(): String = """\d{2} $month \d{4}"""




