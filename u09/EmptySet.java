/**
 * @param <E> Element type.
 * Simple functional set containing no element.
 */
public class EmptySet<E> extends SimpleFunctionalSet<E> {

    /**
     * Creates an empty simple functional set.
     */
    public EmptySet() {
        super(null);
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

}
