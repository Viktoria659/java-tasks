package ru.mail.polis.homework.objects;

public class StringTasks {

    /**
     * Убрать все лишние символы из строки и вернуть получившееся число.
     * Разрешенные символы: цифры, '-', '.', 'e'
     * Если '.' и 'e' больше чем 1, возвращаем null
     * Правила на '-' является валидность числа. --3 не валидно. -3e-1 валидино
     * Любой класс-обертка StringTasksTest над примитивами наследуется от Number
     * Можно использовать функции типа Double.valueOf()
     * <p>
     * Работайте со строкой, НЕ надо ее переводить в массив байт (это можно использовать только для цикла)
     * У класса Character есть полезные методы, например Character.isDigit()
     * БЕЗ РЕГУЛЯРОК!
     * 6 тугриков
     */
    public static Number valueOf(String str) {
        int minus1 = 0;
        int minus2 = 0;
        int countMinus = 0;
        int posE = -1;
        int posPoint = -1;
        StringBuilder sb = new StringBuilder();

        if ((str == null) || (str.isEmpty())) {
            return null;
        }

        //удаление лишних символов
        for (Character c : str.toCharArray()) {
            if (!Character.isLetter(c) || c == 'e') {
                sb.append(c);
            }
        }

        String result = sb.toString();
        char[] ch = result.toCharArray();

        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == 'e') {
                posE = i;
            } else if (ch[i] == '.') {
                posPoint = i;
            } else if (ch[i] == '-') {
                countMinus++;
                if (minus1 == 0 && countMinus == 1) {
                    minus1 = i;
                } else if (minus2 == 0) {
                    minus2 = i;
                }
            }
        }

        if (!correctMinus(countMinus, minus2, minus1, ch)) return null;

        if (!correctPointAndE(posE, posPoint, result.length())) return null;

        if (result.contains(".") || result.contains("e")) {
            return Double.valueOf(result);
        } else if (Long.parseLong(result) > Integer.MAX_VALUE ||
                Long.parseLong(result) < Integer.MIN_VALUE) {
            return Long.valueOf(result);
        } else {
            return Integer.valueOf(result);
        }
    }

    private static boolean correctPointAndE(int posE, int posPoint, int end) {
        if ((posE >= 0 && posPoint > posE) ||
                (posE == end - 1) ||
                (posE == 0)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean correctMinus(int countMinus, int minus2, int minus1, char[] ch) {
        if (countMinus > 2 || (minus2 - minus1 == 1) ||
                countMinus != 0 &&
                        !((countMinus == 1) && ((minus1 == 0) || (ch[minus1 - 1] == 'e')) ||
                                (countMinus == 2) && (minus1 == 0) && (ch[minus2 - 1] == 'e'))) {
            return false;
        } else {
            return true;
        }
    }
}
