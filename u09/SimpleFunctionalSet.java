/**
 * @param <E> Element type.
 * Abstract class for simple functional sets just offering a characteristic function realized by the
 * contains method.
 */
public abstract class SimpleFunctionalSet<E> {

    /**
     * The remaining set.
     */
    private final SimpleFunctionalSet<E> set;

    /**
     * @param s The remaining set.
     */
    public SimpleFunctionalSet(SimpleFunctionalSet<E> s) {
        this.set = s;
    }

    /**
     * Returns <tt>true</tt> if this set contains the specified element.
     * More formally, contains(o) returns <tt>true</tt> if and only if this set
     * contains an element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;e.equals(o))</tt>.
     * @param o Element whose presence in this set is to be tested.
     * @return <tt>true</tt> if this set contains the specified element.
     */
    public abstract boolean contains(Object o);

    /**
     * @return The remaining set.
     */
    public SimpleFunctionalSet<E> getRemainingSet() {
        return this.set;
    }

}
