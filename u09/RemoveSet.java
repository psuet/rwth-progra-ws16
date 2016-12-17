/**
 * @param <E> Element type.
 * Simple set removing an object from another simple set.
 */
public class RemoveSet<E> extends SimpleFunctionalSet<E> {

    /**
     * The object to remove.
     */
    private final Object obj;

    /**
     * @param o The object to remove.
     * @param s The remaining set.
     */
    public RemoveSet(Object o, SimpleFunctionalSet<E> s) {
        super(s);
        this.obj = o;
    }

    @Override
    public boolean contains(Object o) {
        if (this.obj == null) {
            if (o == null) {
                return false;
            } else {
                return this.getRemainingSet().contains(o);
            }
        } else if (this.obj.equals(o)) {
            return false;
        } else {
            return this.getRemainingSet().contains(o);
        }
    }

    /**
     * @return The object to remove.
     */
    public Object getObject() {
        return this.obj;
    }

}
