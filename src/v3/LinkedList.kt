package v3


// create node class
class LinkedList<T> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private var size = 0

    fun isEmpty() = size == 0

    //TODO(implement Push , with fluent interface pattern)

    override fun toString(): String {
        return if (isEmpty())
            "Empty List"
        else
            head.toString()
    }
}

data class Node<T>(var value: T, var next: Node<T>? = null) {
    override fun toString(): String {
        return if (next != null) {
            "$value -> ${next.toString()}"
        } else {
            "$value"
        }
    }
}

fun main() {
    "creating and linking nodes" example {

        val node1 = Node(value = 1)
        val node2 = Node(value = 2)
        val node3 = Node(value = 3)
        node1.next = node2
        node2.next = node3
        println(node1)
    }
}


private infix fun String.example(function: () -> Unit) {
    function()
}

