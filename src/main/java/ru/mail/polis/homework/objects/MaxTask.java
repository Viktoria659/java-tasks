package ru.mail.polis.homework.objects;

public class MaxTask {

    /**
     * Вам дан массив и количество элементов в возвращаемом массиве
     * Вернуть нужно массив из count максимальных элементов array, упорядоченный по убыванию.
     * Если массив null или его длина меньше count, то вернуть null
     * Например ({1, 3, 10, 11, 22, 0}, 2) -> {22, 11}
     * ({1, 3, 22, 11, 22, 0}, 3) -> {22, 22, 11}
     * НЕЛЬЗЯ СОРТИРОВАТЬ массив array и его копии
     * 4 тугрика
     */
    public static int[] getMaxArray(int[] array, int count) {
        if (array == null || array.length == 0) {
            return array;
        } else if (count == 0) {
            return new int[]{};
        } else if (array.length < count) {
            return null;
        } else {
            int[] newArray = new int[count];
            array = quickSort(array, array.length);
            for (int i = 0; i < count; i++) {
                newArray[i] = array[array.length - 1 - i];
            }
            return newArray;
        }
    }

    private static int[] quickSort(int[] array, int count) {
        if (count <= 1) {
            return array;
        }
        int pivot = array[0];
        int[] less = new int[array.length - 1];
        int[] greater = new int[array.length - 1];
        int c = 0;
        int k = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] <= pivot) {
                less[c] = array[i];
                c++;
            } else {
                greater[k] = array[i];
                k++;
            }
        }
        less = deleteEmptyElements(less, c);
        greater = deleteEmptyElements(greater, k);

        int[] both = new int[less.length + greater.length + 1];
        System.arraycopy(quickSort(less, c), 0, both, 0, less.length);
        both[c] = pivot;
        System.arraycopy(quickSort(greater, k), 0, both, less.length + 1, greater.length);
        return both;
    }

    private static int[] deleteEmptyElements(int[] array, int count) {
        int[] newArray = new int[count];
        System.arraycopy(array, 0, newArray, 0, count);
        return newArray;
    }

}
