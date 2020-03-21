package v8

import java.lang.IndexOutOfBoundsException


// create node class
class LinkedList<T> : Iterable<T> {
    override fun iterator(): Iterator<T> {
        return LinkedListIterator(this)
    }

    class LinkedListIterator<T>(private val list: LinkedList<T>) : Iterator<T> {
        private var index = 0
        private var lastNode: Node<T>? = null
        override fun next(): T {
            if (index >= list.size) throw IndexOutOfBoundsException()
            lastNode = if (index == 0) {
                list.nodeAt(0) } else {
                lastNode?.next }

            index++
            return lastNode!!.value
        }

        override fun hasNext(): Boolean {
            return index < list.size
        }
    }

    //TODO(make it Collection)

    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    var size = 0
        private set

    fun isEmpty() = size == 0

    fun push(element: T): LinkedList<T> {
        head = Node(element, next = head)
        if (tail == null)
            tail = head
        size++
        return this
    }


    fun append(element: T): LinkedList<T> {
        if (isEmpty()) {
            return push(element)
        } else {
            // 1 -> 2_>null
            // we go to 2 node make it's next node is 3_>null
            // we make 3_null is the tail
            tail?.next = Node(value = element)
            tail = tail?.next
            size++
        }
        return this
    }

    fun nodeAt(index: Int): Node<T>? {
        if (isEmpty())
            throw IndexOutOfBoundsException()
        var currentNode = head
        var currentIndex = 0
        while (currentNode != null && currentIndex < index) {
            currentNode = currentNode.next
            currentIndex++
        }
        return currentNode

    }

    fun insert(element: T, afterNode: Node<T>): Node<T> {
        if (afterNode == tail) {
            append(element)
            return tail!!
        }
        val newNode = Node(element, afterNode.next)
        afterNode.next = newNode
        size++
        return newNode
    }

    //TODO(Pop Operation , RemoveLast , RemoveAfter)

    fun pop(): T? {
        if (!isEmpty()) size--
        val result = head?.value
        head = head?.next
        if (isEmpty())
            tail = null
        return result
    }

    fun removeLast(): T? {
        if (!isEmpty()) {
            if (head?.next == null) return pop()
            val previousNode = nodeAt(size - 2)
            val result = previousNode?.next?.value
            previousNode?.next = null
            tail = previousNode
            size--
            return result
        } else
            return null

    }

    fun removeAfter(node: Node<T>): T? {
        val result = node.next?.value
        if (node.next == tail) {
            tail = node
        }
        if (node.next != null) {
            size--
        }
        node.next = node.next?.next
        return result
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
    "testing pop,removeLast" example {
        val list = LinkedList<Int>()
        list.push(4)
        list.push(3)
        list.push(2)
        list.push(1)
        println(list)
        for (item in list) {
            println("Double: ${item * 2}")
        }

    }
}


private infix fun String.example(function: () -> Unit) {
    function()
}

