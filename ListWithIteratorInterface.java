import LinkedListWithIterator.ListInterface;

import java.util.Iterator;
// An interface that extends ListInterface and Iterable, providing the ability to obtain an iterator

public interface ListWithIteratorInterface<E> extends ListInterface<E>, Iterable<E>
{
    // gets an iterator for the list.

    public Iterator<E> getIterator();
}
