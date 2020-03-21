package v4

// create node class
class LinkedList<T> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private var size = 0

    fun isEmpty() = size == 0

    fun push(element: T): LinkedList<T> {
        head = Node(element, next = head)
        if (tail == null)
            tail = head
        size++
        return this
    }

    //TODO(implement append function)

    fun append(element: T): LinkedList<T> {
        if (isEmpty()) {
            return push(element)
        } else {
            tail?.next = Node(value = element)
            tail = tail?.next
        }
        return this
    }

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
    "testing append" example {
        val list = LinkedList<Int>()
        list.append(1).append(2).append(3)

        println(list)
    }
}


private infix fun String.example(function: () -> Unit) {
    function()
}

