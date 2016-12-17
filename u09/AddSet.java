/**
 * @param <E> Element type.
 * Simple functional set adding an element to another simple functional set.
 */
public class AddSet<E> extends SimpleFunctionalSet<E> {

    /**
     * The element to add.
     */
    private final E element;

    /**
     * @param elem The element to add.
     * @param s The remaining set.
     */
    public AddSet(E elem, SimpleFunctionalSet<E> s) {
        super(s);
        this.element = elem;
    }

    @Override
    public boolean contains(Object o) {
        if (this.element == null) {
            if (o == null) {
                return true;
            } else {
                return this.getRemainingSet().contains(o);
            }
        } else if (this.element.equals(o)) {
            return true;
        } else {
            return this.getRemainingSet().contains(o);
        }
    }

    /**
     * @return The element to add.
     */
    public E getElement() {
        return this.element;
    }

}
