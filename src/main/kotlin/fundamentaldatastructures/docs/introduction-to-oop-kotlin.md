# Practical Introduction to OOP in Kotlin

Object-oriented programming groups **state** and the **operations that protect or change that state**. In Kotlin, start with small classes that model real responsibilities rather than building deep class hierarchies.

## 1. Classes and Objects

A class is a blueprint; an object is one instance of that class.

```kotlin
class Lamp {
    var isOn: Boolean = false

    fun switchOn() {
        isOn = true
    }
}

val deskLamp = Lamp()
deskLamp.switchOn()
```

`deskLamp` owns its own `isOn` state. Another `Lamp()` starts with separate state.

## 2. Constructor Properties

Put required data in the primary constructor. Prefix a parameter with `val` or `var` to make it an object property.

```kotlin
class Member(
    val id: String,
    val name: String,
    var email: String
)
```

Prefer `val` unless the property must be reassigned. A `val` collection can still contain mutable data, so choose mutability deliberately.

## 3. Properties, Getters, and Setters

Kotlin generates accessors automatically:

```kotlin
class Profile(
    val id: String,       // public getter, no setter
    var displayName: String // public getter and setter
)
```

Use property syntax (`profile.displayName`) rather than Java-style `getDisplayName()` and `setDisplayName(...)` methods. Kotlin keeps the call site concise while still allowing custom behavior behind the property.

### Custom Accessors and `field`

Use `get()` to control reads and `set(value)` to control assignments:

```kotlin
class UserProfile(initialName: String) {
    var displayName: String = initialName
        get() = field
        set(value) {
            require(value.isNotBlank()) { "display name must not be blank" }
            field = value.trim()
        }

    val label: String
        get() = "User: $displayName"

    init {
        require(initialName.isNotBlank()) { "initial name must not be blank" }
    }
}
```

`field` means the property's backing storage. Writing `displayName = value` inside the `displayName` setter would call the same setter again and recurse until the program fails; assign to `field` instead.

`label` is a computed read-only property. Its getter calculates a value from other state, so it has no backing field and needs no setter.

### Construction Validation vs Later Assignments

A property initializer sets its initial backing value without calling the custom setter. Validate constructor input in `init`, then use the setter to validate every later assignment:

```kotlin
val profile = UserProfile("Amina") // checked by init
profile.displayName = "Mina"       // checked by set(value)
```

If callers should read a property but only the class should change it, use `private set`:

```kotlin
class Counter {
    var value: Int = 0
        private set

    fun increment() {
        value++
    }
}
```

Do not expose a setter when changing state requires a business rule. A bank account should offer `deposit(amount)` and `withdraw(amount)` rather than allowing `account.balance = -500`; behavior methods can validate input and preserve invariants.

**Common accessor mistakes:**

- Using the property name instead of `field` inside its setter, causing recursion.
- Validating later assignments in a setter but forgetting to validate the initial value in `init`.
- Adding custom getters and setters that do nothing beyond Kotlin's defaults.
- Exposing a public setter for state that should change only through a behavior method.

## 4. Encapsulation and Validation

Encapsulation prevents callers from putting an object into an invalid state. Keep mutable state private and expose meaningful operations.

```kotlin
class BankAccount(
    val owner: String,
    openingBalance: Int
) {
    var balance: Int = openingBalance
        private set

    init {
        require(owner.isNotBlank()) { "owner must not be blank" }
        require(openingBalance >= 0) { "opening balance must not be negative" }
    }

    fun deposit(amount: Int) {
        require(amount > 0) { "amount must be positive" }
        balance += amount
    }
}
```

`private set` lets everyone read `balance`, but only the class can change it. Use `require` for invalid caller input and `check` for invalid object state.

## 5. Methods and State

A method should preserve the class invariants before and after it runs.

```kotlin
fun withdraw(amount: Int): Boolean {
    require(amount > 0) { "amount must be positive" }
    if (amount > balance) return false
    balance -= amount
    return true
}
```

Ask: "What must always be true?" For this account, the balance must never become negative.

## 6. Interfaces and Polymorphism

An interface defines behavior without choosing one implementation.

```kotlin
interface LibraryItem {
    val id: String
    val title: String
    fun description(): String
}

data class Book(
    override val id: String,
    override val title: String,
    val author: String
) : LibraryItem {
    override fun description(): String = "$title by $author"
}
```

A `List<LibraryItem>` can contain books, audiobooks, or future item types. Calling `description()` uses the implementation belonging to the actual object.

## 7. Inheritance vs Composition

Inheritance models an **is-a** relationship:

```kotlin
open class Employee(val name: String)
class Manager(name: String) : Employee(name)
```

Composition models a **has-a** relationship:

```kotlin
class Library(private val items: List<LibraryItem>)
```

Prefer composition for most application behavior because parts can be replaced independently. Use inheritance only when every subtype truly satisfies the parent contract; avoid inheritance merely to reuse a few lines.

## 8. Data Classes

Use a `data class` for value-like records.

```kotlin
data class Loan(
    val itemId: String,
    val memberId: String
)
```

Kotlin generates useful `equals`, `hashCode`, `toString`, `copy`, and component functions. Do not use a data class for an object whose identity and changing lifecycle matter more than its property values.

## 9. Visibility

- `public`: available everywhere; this is Kotlin's default.
- `private`: available only inside the declaration or file.
- `protected`: available in a class and its subclasses.
- `internal`: available inside the same module.

Expose the smallest useful API. Private implementation details are easier to change safely.

## 10. Small End-to-End Example

```kotlin
interface Notifier {
    fun send(message: String)
}

class ConsoleNotifier : Notifier {
    override fun send(message: String) {
        println(message)
    }
}

data class Task(val id: Int, val title: String)

class TaskList(private val notifier: Notifier) {
    private val tasks = mutableListOf<Task>()

    fun add(task: Task) {
        require(task.title.isNotBlank()) { "title must not be blank" }
        tasks += task
        notifier.send("Added: ${task.title}")
    }

    fun all(): List<Task> = tasks.toList()
}
```

This example combines an interface, composition, a data class, validation, private mutable state, and a read-only snapshot.

## Complexity Reasoning

OOP does not change algorithmic costs. The chosen collection does:

- Reading or updating a `HashMap` entry is expected O(1).
- Searching a `List` is O(n).
- Returning `items.toList()` is O(n) because it creates a copy.

Mention both the object design and the collection cost when explaining a solution.

## Common Mistakes

- Making every property a public `var`.
- Using inheritance where composition is clearer.
- Returning a private mutable collection directly.
- Putting unrelated responsibilities into one large class.
- Using a data class for a service with complex mutable state.
- Skipping validation and relying on callers to preserve invariants.

## Practice

Implement the [library lending exercise](../exercises/OopLibraryExercise.kt), then compare it with the [reference solution](../solutions/OopLibrarySolution.kt).
