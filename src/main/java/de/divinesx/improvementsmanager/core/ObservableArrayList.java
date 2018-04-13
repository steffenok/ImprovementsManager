package de.divinesx.improvementsmanager.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.collections.ModifiableObservableListBase;
import lombok.experimental.Delegate;

public class ObservableArrayList<E> extends ModifiableObservableListBase<E> {
	
	@Delegate
    private final List<E> delegate = new ArrayList<>();

    public ObservableArrayList() {}
    
    public ObservableArrayList(Collection<? extends E> collection) { this.addAll(collection); }

    @Override
    protected void doAdd(final int index, final E element) {
        delegate.add(index, element);
    }

    @Override
    protected E doSet(final int index, final E element) {
        return delegate.set(index, element);
    }

    @Override
    protected E doRemove(final int index) {
        return delegate.remove(index);
    }
    
}
