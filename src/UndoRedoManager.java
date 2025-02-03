/**
 * Implement an application that supports undo/redo functionality. Use a linked list to maintain a sequence of states.
 * Each state change is stored as a node in the list, allowing for easy navigation.
 * 1<>2<>3<>4<>5
 */

public class UndoRedoManager<T> {
    private class Node {
        private final T state;
        private Node prev;
        private Node next;

        private Node(T state) {
            this.state = state;
        }
    }

    private Node currentState;

    // Undo operation
    public T undo() {
        if (currentState != null && currentState.prev != null) {
            currentState = currentState.prev;
            return currentState.state;
        }

        return null;
    }

    // Perform an operation
    public void addState(T newState) {
        if (currentState == null) {
            currentState = new Node(newState);
        } else {
            currentState.next = null;

            Node newNode = new Node(newState);
            newNode.prev = currentState;
            currentState.next = newNode;

            currentState = newNode;
        }
    }

    // Redo Operation
    public T redo() {
        if (currentState != null && currentState.next != null) {
            currentState = currentState.next;
            return currentState.state;
        }

        return null;
    }

    // Test bench
    public static void main(String[] args) {

        UndoRedoManager<String> manager = new UndoRedoManager<>();

        manager.addState("A");
        manager.addState("B");
        manager.addState("C");

        System.out.println("Undo: " + manager.undo());
        System.out.println("Undo: " + manager.undo());

        System.out.println("Redo: " + manager.redo());

        manager.addState("D");

        System.out.println("Redo: " + manager.redo());
    }
}
