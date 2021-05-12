package chapter24;

public abstract class MyAbstractList<E> implements MyList<E>{

    protected int size = 0;

    protected MyAbstractList(){

    }

    protected MyAbstractList(E[] objects){
        for (int i=0; i<objects.length; i++){
            add(objects[i]);
        }
    }

    @Override
    public void add(E e) {
        add(size, e);
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean remove(E e) {
        if (indexOf(e) >= 0){
            remove(indexOf(e));
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(MyList<E> otherList) {
        if (otherList == null || otherList.size() == 0){
            return false;
        }
        for (E e: otherList){
            add(e);
        }
        return true;
    }

    @Override
    public boolean removeAll(MyList<E> otherList) {
        if (otherList == null || otherList.size() == 0){
            return false;
        }
        boolean changed = false;
        for (E e: otherList){
            while (contains(e)){
                remove(e);
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean retainAll(MyList<E> otherList) {
        if (otherList == null || otherList.size() == 0){
            return false;
        }
        boolean changed = false;
        for (int i=0; i<size(); i++){
            if (otherList.indexOf(get(i)) < 0){
                remove(i);
                i--;
                changed = true;
            }
        }
        return changed;
    }
}
