package intercom

import kotlin.reflect.KClass

/**
 * Tiny console harness shared by every `Practice.kt` and `Solution.kt` main.
 *
 * - [scenario] isolates each example so one `TODO()` or crash doesn't hide the rest.
 * - [expect] prints PASS / FAIL with expected vs actual values.
 * - [summary] prints totals (call it at the end of every `main`).
 */
object Harness {
    private var passed = 0
    private var failed = 0
    private var notImplemented = 0

    fun scenario(name: String, block: () -> Unit) {
        println()
        println("=== $name ===")
        try {
            block()
        } catch (e: NotImplementedError) {
            notImplemented++
            println("  TODO  ${e.message}")
        } catch (e: Exception) {
            failed++
            println("  ERROR ${e::class.simpleName}: ${e.message}")
        }
    }

    fun <T> expect(label: String, actual: T, expected: T) {
        if (actual == expected) {
            passed++
            println("  PASS  $label -> $actual")
        } else {
            failed++
            println("  FAIL  $label")
            println("        expected: $expected")
            println("        actual:   $actual")
        }
    }

    fun expectThrows(label: String, expected: KClass<out Throwable>, block: () -> Unit) {
        val thrown = try {
            block()
            null
        } catch (e: Throwable) {
            if (e is NotImplementedError) throw e
            e
        }
        when {
            thrown == null -> {
                failed++
                println("  FAIL  $label -> expected ${expected.simpleName} but nothing was thrown")
            }
            expected.isInstance(thrown) -> {
                passed++
                println("  PASS  $label -> threw ${thrown::class.simpleName}: ${thrown.message}")
            }
            else -> {
                failed++
                println("  FAIL  $label -> expected ${expected.simpleName} but got ${thrown::class.simpleName}: ${thrown.message}")
            }
        }
    }

    fun note(text: String) = println("  ...   $text")

    fun summary() {
        println()
        println("Summary: $passed passed, $failed failed, $notImplemented scenario(s) not implemented yet")
        passed = 0
        failed = 0
        notImplemented = 0
    }
}
