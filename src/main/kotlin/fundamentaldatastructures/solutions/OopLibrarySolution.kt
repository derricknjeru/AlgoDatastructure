package fundamentaldatastructures.solutions

interface SolutionLibraryItem {
    val id: String
    val title: String

    fun description(): String
}

data class SolutionLibraryBook(
    override val id: String,
    override val title: String,
    val author: String
) : SolutionLibraryItem {
    override fun description(): String = "$title by $author"
}

data class SolutionLibraryAudioBook(
    override val id: String,
    override val title: String,
    val narrator: String
) : SolutionLibraryItem {
    override fun description(): String = "$title, narrated by $narrator"
}

class SolutionLibraryMember(
    val id: String,
    val name: String,
    private val maxLoans: Int
) {
    private val borrowedItemIds = mutableSetOf<String>()

    init {
        require(id.isNotBlank()) { "member id must not be blank" }
        require(name.isNotBlank()) { "member name must not be blank" }
        require(maxLoans > 0) { "maxLoans must be positive" }
    }

    fun borrow(itemId: String): Boolean {
        if (borrowedItemIds.size >= maxLoans) return false
        return borrowedItemIds.add(itemId)
    }

    fun returnItem(itemId: String): Boolean = borrowedItemIds.remove(itemId)

    fun borrowedCount(): Int = borrowedItemIds.size
}

class SolutionLibrary(items: List<SolutionLibraryItem>) {
    private val itemsById: Map<String, SolutionLibraryItem>
    private val borrowerByItemId = mutableMapOf<String, String>()

    init {
        require(items.map { it.id }.toSet().size == items.size) {
            "item ids must be unique"
        }
        itemsById = items.associateBy { it.id }
    }

    fun availableTitle(itemId: String): String? {
        if (itemId in borrowerByItemId) return null
        return itemsById[itemId]?.title
    }

    fun lend(itemId: String, member: SolutionLibraryMember): String {
        val item = itemsById[itemId] ?: return "Item not found"
        if (itemId in borrowerByItemId) return "Item unavailable"
        if (!member.borrow(itemId)) return "Loan limit reached"

        borrowerByItemId[itemId] = member.id
        return "${member.name} borrowed ${item.title}"
    }

    fun returnItem(itemId: String, member: SolutionLibraryMember): String {
        val item = itemsById[itemId] ?: return "Item not found"
        if (borrowerByItemId[itemId] != member.id) return "Item not borrowed by ${member.name}"

        check(member.returnItem(itemId)) { "library and member loan state disagree" }
        borrowerByItemId.remove(itemId)
        return "${member.name} returned ${item.title}"
    }
}

/*
 * Item lookup, lend, and return use expected O(1) map/set operations.
 * Constructing the library is O(n) time and O(n) space.
 *
 * How to test:
 * ./gradlew -q runMain -PmainClass=fundamentaldatastructures.solutions.OopLibrarySolutionKt
 */
fun main() {
    val kotlinBook = SolutionLibraryBook("book-1", "Kotlin in Action", "Dmitry Jemerov")
    val cleanCode = SolutionLibraryBook("book-2", "Clean Code", "Robert C. Martin")
    val audioBook = SolutionLibraryAudioBook("audio-1", "Refactoring", "Martin Fowler")
    val library = SolutionLibrary(listOf(kotlinBook, cleanCode, audioBook))
    val member = SolutionLibraryMember("member-1", "Amina", maxLoans = 2)

    println("Available: ${library.availableTitle("book-1")}")
    println(library.lend("book-1", member))
    println(library.lend("book-2", member))
    println(library.lend("audio-1", member))
    println(library.returnItem("book-1", member))
    println("Available: ${library.availableTitle("book-1")}")
}
