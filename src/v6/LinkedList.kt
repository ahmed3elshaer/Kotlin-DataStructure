package v6

import java.lang.IndexOutOfBoundsException


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
    "testing insert" example {
        val list = LinkedList<Int>()
        list.append(1).append(2).append(3)
        var middleNode = list.nodeAt(1)!!
        for (i in 1..3) {
            middleNode = list.insert(i * -1, middleNode)
        }
        println(list)
    }
}


private infix fun String.example(function: () -> Unit) {
    function()
}

