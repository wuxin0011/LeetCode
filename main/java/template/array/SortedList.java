package template.array;

/**
 * @author: wuxin0011
 * @Description:
 */

import java.util.*;

public class SortedList<E extends Comparable<? super E>> implements Iterable<E> {
    private int _len;
    private final int _load;
    private final List<List<E>> _lists;
    private final List<Integer> _listLens;
    private final List<E> _minS;
    private final List<Integer> _fenTree;
    private boolean _rebuild;


    public SortedList() {
        this(null, 200);
    }

    public SortedList(Collection<? extends E> iterable, int _load) {
        if (iterable == null) {
            iterable = new ArrayList<>();
        }
        List<E> values = new ArrayList<>(iterable);
        values.sort(Comparator.naturalOrder());
        _len = values.size();
        this._load = _load;
        _lists = new ArrayList<>();
        for (int i = 0; i < _len; i += _load) {
            _lists.add(new ArrayList<>(values.subList(i, Math.min(i + _load, _len))));
        }
        _listLens = new ArrayList<>();
        for (List<E> list : _lists) {
            _listLens.add(list.size());
        }
        _minS = new ArrayList<>();
        for (List<E> list : _lists) {
            _minS.add(list.get(0));
        }
        _fenTree = new ArrayList<>();
        _rebuild = true;
    }

    private void _fenBuild() {
        _fenTree.clear();
        _fenTree.addAll(_listLens);
        for (int i = 0; i < _fenTree.size(); i++) {
            if ((i | i + 1) < _fenTree.size()) {
                _fenTree.set(i | i + 1, _fenTree.get(i | i + 1) + _fenTree.get(i));
            }
        }
        _rebuild = false;
    }

    private void _fenUpdate(int index, int value) {
        if (!_rebuild) {
            while (index < _fenTree.size()) {
                _fenTree.set(index, _fenTree.get(index) + value);
                index |= index + 1;
            }
        }
    }

    private int _fenQuery(int end) {
        if (_rebuild) {
            _fenBuild();
        }
        int x = 0;
        while (end > 0) {
            x += _fenTree.get(end - 1);
            end &= end - 1;
        }
        return x;
    }

    private Pair<Integer, Integer> _fenFindKth(int k) {
        if (k < _listLens.get(0)) {
            return new Pair<>(0, k);
        }
        if (k >= _len - _listLens.get(_listLens.size() - 1)) {
            return new Pair<>(_listLens.size() - 1, k + _listLens.get(_listLens.size() - 1) - _len);
        }
        if (_rebuild) {
            _fenBuild();
        }
        int idx = -1;
        for (int d = Integer.toBinaryString(_fenTree.size()).length() - 1; d >= 0; d--) {
            int rightIdx = idx + (1 << d);
            if (rightIdx < _fenTree.size() && k >= _fenTree.get(rightIdx)) {
                idx = rightIdx;
                k -= _fenTree.get(idx);
            }
        }
        return new Pair<>(idx + 1, k);
    }

    private void _delete(int pos, int idx) {
        _len--;
        _fenUpdate(pos, -1);
        _lists.get(pos).remove(idx);
        _listLens.set(pos, _listLens.get(pos) - 1);
        if (_listLens.get(pos) > 0) {
            _minS.set(pos, _lists.get(pos).get(0));
        } else {
            _lists.remove(pos);
            _listLens.remove(pos);
            _minS.remove(pos);
            _rebuild = true;
        }
    }

    private Pair<Integer, Integer> _locLeft(E value) {
        if (_len == 0) {
            return new Pair<>(0, 0);
        }
        int lo = -1, pos = _lists.size() - 1;
        while (lo + 1 < pos) {
            int mi = (lo + pos) >>> 1;
            if (value.compareTo(_minS.get(mi)) <= 0) {
                pos = mi;
            } else {
                lo = mi;
            }
        }
        if (pos > 0 && value.compareTo(_lists.get(pos - 1).get(_lists.get(pos - 1).size() - 1)) <= 0) {
            pos--;
        }
        List<E> list = _lists.get(pos);
        lo = -1;
        int idx = list.size();
        while (lo + 1 < idx) {
            int mi = (lo + idx) >>> 1;
            if (value.compareTo(list.get(mi)) <= 0) {
                idx = mi;
            } else {
                lo = mi;
            }
        }
        return new Pair<>(pos, idx);
    }

    private Pair<Integer, Integer> _locRight(E value) {
        if (_len == 0) {
            return new Pair<>(0, 0);
        }
        int pos = 0, hi = _lists.size();
        while (pos + 1 < hi) {
            int mi = (pos + hi) >>> 1;
            if (value.compareTo(_minS.get(mi)) < 0) {
                hi = mi;
            } else {
                pos = mi;
            }
        }
        List<E> list = _lists.get(pos);
        int lo = -1;
        int idx = list.size();
        while (lo + 1 < idx) {
            int mi = (lo + idx) >>> 1;
            if (value.compareTo(list.get(mi)) < 0) {
                idx = mi;
            } else {
                lo = mi;
            }
        }
        return new Pair<>(pos, idx);
    }

    public void add(E value) {
        _len++;
        if (!_lists.isEmpty()) {
            Pair<Integer, Integer> pair = _locRight(value);
            _fenUpdate(pair.getKey(), 1);
            List<E> list = _lists.get(pair.getKey());
            list.add(pair.getValue(), value);
            _listLens.set(pair.getKey(), _listLens.get(pair.getKey()) + 1);
            _minS.set(pair.getKey(), list.get(0));
            if (_load + _load < list.size()) {
                _lists.add(pair.getKey() + 1, new ArrayList<>(list.subList(_load, list.size())));
                _listLens.add(pair.getKey() + 1, list.size() - _load);
                _minS.add(pair.getKey() + 1, list.get(_load));
                _listLens.set(pair.getKey(), _load);
                list.subList(_load, list.size()).clear();
                _rebuild = true;
            }
        } else {
            List<E> newList = new ArrayList<>();
            newList.add(value);
            _lists.add(newList);
            _minS.add(value);
            _listLens.add(1);
            _rebuild = true;
        }
    }

    public void discard(E value) {
        Pair<Integer, Integer> pair = _locRight(value);
        if (pair.getValue() > 0 && _lists.get(pair.getKey()).get(pair.getValue() - 1).equals(value)) {
            _delete(pair.getKey(), pair.getValue() - 1);
        }
    }

    public void remove(E value) {
        int len = _len;
        discard(value);
        if (len == _len) {
            throw new NoSuchElementException(value + " not in list");
        }
    }

    public E pop(int index) {
        Pair<Integer, Integer> pair = _fenFindKth(_len + index < 0 ? _len + index : index);
        E value = _lists.get(pair.getKey()).get(pair.getValue());
        _delete(pair.getKey(), pair.getValue());
        return value;
    }

    public int bisectLeft(E value) {
        Pair<Integer, Integer> pair = _locLeft(value);
        return _fenQuery(pair.getKey()) + pair.getValue();
    }

    public int bisectRight(E value) {
        Pair<Integer, Integer> pair = _locRight(value);
        return _fenQuery(pair.getKey()) + pair.getValue();
    }

    public int count(E value) {
        return bisectRight(value) - bisectLeft(value);
    }

    public int size() {
        return _len;
    }

    public E get(int index) {
        Pair<Integer, Integer> pair = _fenFindKth(_len + index < 0 ? _len + index : index);
        return _lists.get(pair.getKey()).get(pair.getValue());
    }

    public void removeAt(int index) {
        Pair<Integer, Integer> pair = _fenFindKth(_len + index < 0 ? _len + index : index);
        _delete(pair.getKey(), pair.getValue());
    }

    public boolean contains(E value) {
        Pair<Integer, Integer> pair = _locLeft(value);
        return pair.getValue() < _lists.get(pair.getKey()).size() && _lists.get(pair.getKey()).get(pair.getValue()).equals(value);
    }

    @Override
    public Iterator<E> iterator() {
        List<E> allValues = new ArrayList<>();
        for (List<E> list : _lists) {
            allValues.addAll(list);
        }
        return allValues.iterator();
    }


    public List<E> reverse() {
        List<E> rev = new ArrayList<>(_listLens.size());

        return rev;
    }

    @Override
    public String toString() {

        return String.format("SortedList: %s", _lists);
    }

    public static class Pair<K, V> {
        K key;
        V value;

        public Pair(K k, V v) {
            this.key = k;
            this.value = v;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

}