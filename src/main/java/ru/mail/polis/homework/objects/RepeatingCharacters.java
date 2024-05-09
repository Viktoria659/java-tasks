package ru.mail.polis.homework.objects;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Нужно найти символ, который встречается подряд в строке чаще всего, и указать количество повторений.
 * Если более одного символа с максимальным значением, то нужно вернуть тот символ,
 * который первый встречается в строчке
 * Если строка пустая или null, то вернуть null
 * Пример abbasbdlbdbfklsssbb -> (s, 3)
 * 4 тугрика
 */
public class RepeatingCharacters {

    public static Pair<Character, Integer> getMaxRepeatingCharacters(String str) {
        if ((str == null) || str.isEmpty()) return null;

        List<Pair<Character, Integer>> pairs = new ArrayList<>();
        pairs.add(new Pair<>(str.charAt(0), 1));
        int c = 1;
        Pair<Character, Integer> maxPair;

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i - 1) == str.charAt(i)) {
                c++;
                pairs.add(new Pair<>(str.charAt(i), c));
            } else {
                c = 1;
            }
        }

        maxPair = pairs.get(0);
        for (int i = 1; i < pairs.size(); i++) {
            if (pairs.get(i).getSecond() > maxPair.getSecond()) {
                maxPair = pairs.get(i);
            }
        }

        return maxPair;
    }

    public static class Pair<T, V> {
        private final T first;
        private final V second;

        public Pair(T first, V second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public V getSecond() {
            return second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

    }
}
