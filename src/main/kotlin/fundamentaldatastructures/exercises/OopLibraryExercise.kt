package fundamentaldatastructures.exercises

/*
 * OOP Practice: Library Lending
 *
 * Build a small lending system that practices:
 * - an interface for polymorphic library items;
 * - data classes for value-like item records;
 * - encapsulated member state;
 * - validation and methods that preserve invariants;
 * - composition: a library coordinates items and members.
 *
 * Requirements:
 * 1. LibraryItem exposes id, title, and description().
 * 2. LibraryBook implements LibraryItem and includes an author.
 * 3. LibraryAudioBook implements LibraryItem and includes a narrator.
 * 4. LibraryMember keeps borrowed item ids private and enforces maxLoans.
 * 5. PracticeLibrary lends only available items and returns only borrowed items.
 * 6. Duplicate item ids and non-positive loan limits must be rejected.
 *
 * Acceptance flow:
 * Available: Kotlin in Action
 * Amina borrowed Kotlin in Action
 * Amina borrowed Clean Code
 * Loan limit reached
 * Amina returned Kotlin in Action
 * Available: Kotlin in Action
 *
 * Complexity target:
 * - Find, lend, and return should use expected O(1) map/set operations.
 * - Listing all available items may take O(n).
 *
 * How to test after implementing the TODOs:
 * ./gradlew -q compileKotlin
 * ./gradlew -q runMain -PmainClass=fundamentaldatastructures.exercises.OopLibraryExerciseKt
 */

interface PracticeLibraryItem {
    val id: String
    val title: String

    fun description(): String
}

data class PracticeLibraryBook(
    override val id: String,
    override val title: String,
    val author: String
) : PracticeLibraryItem {
    override fun description(): String = TODO("Describe the book")
}

data class PracticeLibraryAudioBook(
    override val id: String,
    override val title: String,
    val narrator: String
) : PracticeLibraryItem {
    override fun description(): String = TODO("Describe the audiobook")
}

class PracticeLibraryMember(
    val id: String,
    val name: String,
    private val maxLoans: Int
) {
    private val borrowedItemIds = mutableSetOf<String>()

    init {
        TODO("Validate member data and maxLoans")
    }

    fun borrow(itemId: String): Boolean =
        TODO("Enforce the loan limit and record the item")

    fun returnItem(itemId: String): Boolean =
        TODO("Remove and report whether the item was borrowed")

    fun borrowedCount(): Int =
        TODO("Return the number of borrowed items")
}

class PracticeLibrary(items: List<PracticeLibraryItem>) {
    private val itemsById: Map<String, PracticeLibraryItem> =
        TODO("Validate unique ids and index items")
    private val borrowerByItemId = mutableMapOf<String, String>()

    fun availableTitle(itemId: String): String? =
        TODO("Return the title only when the item exists and is available")

    fun lend(itemId: String, member: PracticeLibraryMember): String =
        TODO("Lend an available item when the member can borrow")

    fun returnItem(itemId: String, member: PracticeLibraryMember): String =
        TODO("Return an item only when this member borrowed it")
}

fun main() {
    val kotlinBook = PracticeLibraryBook("book-1", "Kotlin in Action", "Dmitry Jemerov")
    val cleanCode = PracticeLibraryBook("book-2", "Clean Code", "Robert C. Martin")
    val audioBook = PracticeLibraryAudioBook("audio-1", "Refactoring", "Martin Fowler")
    val library = PracticeLibrary(listOf(kotlinBook, cleanCode, audioBook))
    val member = PracticeLibraryMember("member-1", "Amina", maxLoans = 2)

    println("Available: ${library.availableTitle("book-1")}")
    println(library.lend("book-1", member))
    println(library.lend("book-2", member))
    println(library.lend("audio-1", member))
    println(library.returnItem("book-1", member))
    println("Available: ${library.availableTitle("book-1")}")
}

