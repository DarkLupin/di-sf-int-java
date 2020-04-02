package superiterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<T> implements Iterable<T> {
    private Iterable<T> self;

    public SuperIterable(Iterable<T> self) {
        this.self = self;
    }


    public SuperIterable<T> filter(Predicate<T> crit) {
        List<T> out = new ArrayList<>();
        for (T s : self) {
            if (crit.test(s)) {
                out.add(s);
            }
        }
        return new SuperIterable(out);
    }

    public <U> SuperIterable<U> map(Function<T, U> op) {
        List<U> out = new ArrayList<>();
        for (T s : self) {
            System.out.println("SuperIterable applying tranformation to " + s);
            U u = op.apply(s);
            out.add(u);
        }
        return new SuperIterable(out);
    }

    // NOt quite right for laziness
//    public <U> SuperIterable<U> map(Function<T, U> op) {
//        final Iterator<T> iterator = self.iterator();
//        return new SuperIterable(self) {
//            @Override
//            public Iterator<U> iterator() {
//                return new Iterator<U>(){
//                    @Override
//                    public boolean hasNext() {
//                        return iterator.hasNext();
//                    }
//                    @Override
//                    public U next() {
//                        T t = iterator.next();
//                        return op.apply(t);
//                    }
//                };
//            }
//        };
//    }

    public <U> SuperIterable<U> flatMap(Function<T, SuperIterable<U>> op) {
        List<U> out = new ArrayList<>();
        for (T s : self) {
            SuperIterable<U> siu = op.apply(s);
            for (U u : siu) {
                out.add(u);
            }
        }
        return new SuperIterable(out);
    }


// This is effectively "forEach", so we don't need it
//    public void showAll(Consumer<T> op) {
//        for (T s : self) {
//            op.accept(s);
//        }
//    }

    public Iterator iterator() {
        return self.iterator();
    }
}
