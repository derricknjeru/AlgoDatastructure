package fundamentaldatastructures.exercises

data class Shop(val name: String, val customers: List<Customer>)

data class Customer(val name: String, val city: City, val orders: List<Order>) {
    override fun toString() = "$name from ${city.name}"
}

data class Order(val products: List<Product>, val isDelivered: Boolean)

data class Product(val name: String, val price: Double) {
    override fun toString() = "$name for $price"
}

data class City(val name: String, val country: String) {
    override fun toString() = name
}

// get set of customers
fun Shop.getSetOfCustomers(): Set<Customer> = customers.toSet()

/** Sort */

/**
 * val strings = listOf("bbb", "a", "cc")
 * strings.sorted() ==
 *         listOf("a", "bbb", "cc")
 *
 * strings.sortedBy { it.length } ==
 *         listOf("a", "cc", "bbb")
 *
 * strings.sortedDescending() ==
 *         listOf("cc", "bbb", "a")
 *
 * strings.sortedByDescending { it.length } ==
 *         listOf("bbb", "cc", "a")
 */
// return a set of customers, sorted in the descending by the number of orders they have made
fun Shop.getCustomersSortedByOrders(): List<Customer> =
    customers.sortedByDescending { it.orders.size }// customers.sortedWith(compareByDescending{it.orders.size})


/** Filter; map **/
/**
 * val numbers = listOf(1, -1, 2)
 * numbers.filter { it > 0 } == listOf(1, 2)
 * numbers.map { it * it } == listOf(1, 1, 4)
 */
// find all the different cities the customers are from
fun Shop.getCustomerCities(): Set<City> = customers.map { it.city }.toSet()

// Find customers living in a given city
fun Shop.getCustomersFrom(city: City): List<Customer> = customers.filter { it.city == city }

/** All, Any, and other predicates **/
/**
 * val numbers = listOf(-1, 0, 2)
 * val isZero: (Int) -> Boolean = { it == 0 }
 * numbers.any(isZero) == true
 * numbers.all(isZero) == false
 * numbers.count(isZero) == 1
 * numbers.find { it > 0 } == 2
 */

// Return true if all customers are from a given city
fun Shop.checkAllCustomersAreFrom(city: City): Boolean =
    customers.all { it.city == city }

// Return true if there is at least one customer from a given city
fun Shop.hasCustomerFrom(city: City): Boolean =
    customers.any { it.city == city }

// Return the number of customers from a given city
fun Shop.countCustomersFrom(city: City): Int =
    customers.count { it.city == city }

// / Return a customer who lives in a given city, or null if there is none
fun Shop.findCustomerFrom(city: City): Customer? =
    customers.find { it.city == city }

/** Associate **/
/**
 * val list = listOf("abc", "cdef")
 *
 * list.associateBy { it.first() } ==
 *         mapOf('a' to "abc", 'c' to "cdef")
 *
 * list.associateWith { it.length } ==
 *         mapOf("abc" to 3, "cdef" to 4)
 *
 * list.associate { it.first() to it.length } ==
 *         mapOf('a' to 3, 'c' to 4)
 */
// Build a map from the customer name to the customer
fun Shop.nameToCustomerMap(): Map<String, Customer> =
    customers.associateBy { it.name }

// Build a map from the customer to their city
fun Shop.customerToCityMap(): Map<Customer, City> =
    customers.associateWith { it.city }

// Build a map from the customer name to their city
fun Shop.customerNameToCityMap(): Map<String, City> =
    customers.associate { it.name to it.city }

/** groupBy**/
/**
val numbers = listOf("one", "two", "three", "four", "five")
// Groups the strings by their first letter using groupingBy() and counts the elements in each group
val grouped = numbers.groupingBy { it.first() }.eachCount()

// Iterates through each group and prints the key and its associated values
// Key: o, Count: 1
// Key: t, Count: 2
// Key: f, Count: 2
val result =
listOf("a", "b", "ba", "ccc", "ad")
.groupBy { it.length }

result == mapOf(
1 to listOf("a", "b"),
2 to listOf("ba", "ad"),
3 to listOf("ccc"))
 **/

// Build a map that stores the customers living in a given city
fun Shop.groupCustomersByCity(): Map<City, List<Customer>> =
    customers.groupBy { it.city }

// Build a map from each city to the number of customers living there
fun Shop.countCustomersByCity(): Map<City, Int> =
    customers.groupingBy { it.city }.eachCount()

/**
 * Partition
 */
/**
 * val numbers = listOf(1, 3, -4, 2, -11)
 * val (positive, negative) =
 *     numbers.partition { it > 0 }
 *
 * positive == listOf(1, 3, 2)
 * negative == listOf(-4, -11)
 */

// Return customers who have more undelivered orders than delivered orders
fun Shop.getCustomersWithMoreUndeliveredOrders(): Set<Customer> =
    customers.filter {
        val (delivered, undelivered) =
            it.orders.partition { order -> order.isDelivered }

        undelivered.size > delivered.size
    }.toSet()

// FlatMap
/**
 * val result = listOf("abc", "12")
 *     .flatMap { it.toList() }
 * ​
 * result == listOf('a', 'b', 'c', '1', '2')
 */

// Return all products the given customer has ordered
fun Customer.getOrderedProducts(): List<Product> =
    orders.flatMap { it.products }

// Return all products that were ordered by at least one customer
fun Shop.getOrderedProducts(): Set<Product> =
    customers.flatMap {it.getOrderedProducts()}.toSet()