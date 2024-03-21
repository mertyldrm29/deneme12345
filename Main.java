import java.util.EmptyStackException;

// StackNode class representing a node in the stack
class StackNode<T> {
    T data;
    StackNode<T> next;

    public StackNode(T data) {
        this.data = data;
        this.next = null;
    }
}

// SortableStack class implementing stack with sorting capabilities
class SortableStack<T extends Comparable<T>> {
    private StackNode<T> top;

    // Push operation
    public void push(T data) {
        StackNode<T> newNode = new StackNode<>(data);
        if (isEmpty()) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
    }

    // Pop operation
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T data = top.data;
        top = top.next;
        return data;
    }

    // Peek operation
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.data;
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return top == null;
    }

    // Sort operation
    public void sort() {
        if (!isEmpty()) {
            SortableStack<T> tempStack = new SortableStack<>();
            while (!isEmpty()) {
                T temp = pop();
                while (!tempStack.isEmpty() && temp.compareTo(tempStack.peek()) < 0) {
                    push(tempStack.pop());
                }
                tempStack.push(temp);
            }
            while (!tempStack.isEmpty()) {
                push(tempStack.pop());
            }
        }
    }

    // Override toString() method to display stack
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        StackNode<T> current = top;
        while (current != null) {
            sb.append(current.data).append(" ");
            current = current.next;
        }
        return sb.toString();
    }
}

// Process class representing a process running on the computer
class Process implements Comparable<Process> {
    private int id;
    private String name;
    private int priority;

    public Process(int id, String name, int priority) {
        this.id = id;
        this.name = name;
        this.priority = priority;
    }

    // Getter and setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    // Override toString() method to display Process instance
    @Override
    public String toString() {
        return "Process{id=" + id + ", name='" + name + "', priority=" + priority + "}";
    }

    // Implement compareTo method to compare processes based on priority
    @Override
    public int compareTo(Process other) {
        return Integer.compare(this.priority, other.priority);
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            SortableStack<Integer> stack = new SortableStack<>();
            stack.push(5);
            stack.push(3);
            stack.push(8);
            stack.push(1);
            stack.push(2);
            System.out.println("Before sorting:");
            System.out.println(stack.toString());
            stack.sort();
            System.out.println("After sorting:");
            System.out.println(stack.toString());

            SortableStack<Process> stack1 = new SortableStack<>();
            stack1.push(new Process(123214, "GoogleChrome", 3));
            stack1.push(new Process(12324, "VSCode", 3));
            stack1.push(new Process(982, "Edge", 2));
            stack1.push(new Process(321, "Java", 1));
            stack1.push(new Process(23, "MsOffice", 4));
            System.out.println("Before sorting:");
            System.out.println(stack1.toString());
            stack1.sort();
            System.out.println("After sorting:");
            System.out.println(stack1.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
