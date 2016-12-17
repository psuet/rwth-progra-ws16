import java.util.*;

/**
 * Functional data structure for a set.
 * @param <E> Element type.
 */
public class FunctionalSet<E> implements Set<E> {

    /**
     * The head of the list of operations representing the set.
     */
    private SimpleFunctionalSet<E> head;

    public String toString() {
        String res = "{";
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            res = res + it.next();
            if (it.hasNext()) {
                res = res + ", ";
            }
        }
        return res + "}";
    }

    /**
     * Creates an empty functional set.
     */
    public FunctionalSet() {
        this.head = new EmptySet<E>();
    }

    @Override
    public boolean add(E e) {
        if (this.contains(e)) {
            return false;
        } else {
            this.head = new AddSet<E>(e, this.head);
            return true;
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean res = false;
        for (E elem : c) {
            res |= this.add(elem);
        }
        return res;
    }

    @Override
    public void clear() {
        this.head = new EmptySet<E>();
    }

    @Override
    public boolean contains(Object o) {
        return this.head.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!this.contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || o.getClass() != this.getClass()) {
            return false;
        } else {
            FunctionalSet<?> set = (FunctionalSet<?>)o;
            return this.containsAll(set) && set.containsAll(this);
        }
    }

    @Override
    public int hashCode() {
        int res = 5;
        final int prime = 7;
        for (E elem : this) {
            res += prime * elem.hashCode();
        }
        return res;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean remove(Object o) {
        if (this.contains(o)) {
            this.head = new RemoveSet<E>(o, this.head);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean res = false;
        for (Object o : c) {
            res |= this.remove(o);
        }
        return res;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        List<E> store = new ArrayList<E>();
        boolean change = false;
        for (E elem : this) {
            if (c.contains(elem)) {
                store.add(elem);
            } else {
                change = true;
            }
        }
        if (change) {
            this.clear();
            for (E elem : store) {
                this.add(elem);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        int res = 0;
        for (Iterator<E> it = this.iterator(); it.hasNext(); it.next()) {
            res++;
        }
        return res;
    }

    @Override
    public Object[] toArray() {
        Object[] res = new Object[this.size()];
        int i = 0;
        for (E elem : this) {
            res[i] = elem;
            i++;
        }
        return res;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T>T[] toArray(T[] a) {
        final int size = this.size();
        final T[] res;
        if (a.length < size) {
            res = Arrays.copyOf(a, size);
        } else {
            res = a;
        }
        int i = 0;
        for (E elem : this) {
            try {
                res[i] = (T)elem;
            } catch (ClassCastException e) {
                throw new ArrayStoreException(
                    "Element " + elem + " cannot be cast to type of specified array!"
                );
            }
            i++;
        }
        return res;
    }

}
