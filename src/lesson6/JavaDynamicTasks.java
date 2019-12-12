package lesson6;

import kotlin.NotImplementedError;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     *
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Время: O(N*M), N = длина первой сторки; M - длина второй строки
     * Память: O(N*M)  N = длина первой сторки; M - длина второй строки */
    public static String longestCommonSubSequence(String first, String second)throws IOException {
        int firstLength = first.length();
        int secondLength = second.length();
        char character;
        int left;
        String result = "";
        int length = secondLength;
        int height = firstLength;
        int [][] massive = new int[secondLength + 1][firstLength + 1];
        for (int i = 1; i <= secondLength; i++){
            character = second.charAt(i - 1);
            for (int j = 1; j <= firstLength; j++) {
                char firstWord = first.charAt(j - 1);
                if (character == first.charAt(j - 1)) massive[i][j] = massive[i - 1] [j - 1] + 1;
                else {
                    massive[i][j] = Math.max(massive[i - 1][j], massive[i][j - 1]);
                }
            }
        }
        while (massive [length][height] > 0) {
            if (massive[length][height] == massive[length - 1][height]) length--;
            else
                if (massive[length][height] == massive[length][height - 1]) height--;
                else {
                    length--;
                    height--;
                    result = first.charAt(height) + result;
                }
        }
        return result;
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     *
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     * Время: O(N^2), N = длина списка
     * Память: O(N) N = длина списка*/
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) throws IOException {
        int size = list.size();
        int[] massive = new int[size];

        if (size == 0 || size == 1) return list;

        List<Integer> result = new ArrayList<>();
        int[] parentInd = new int[list.size()];
        int lastInd = 0;
        int maxSeq = 1;

        for (int i = 0; i < list.size(); i++){
            massive[i] = 1;
            for (int j = 0; j < i; j++){
                if (list.get(j) < list.get(i) && massive[j] >= massive[i]) {
                    massive[i] = massive[j] + 1;
                    parentInd[i] = j;
                    }
                }
                if (massive[i] > maxSeq) {
                    lastInd = i;
                    maxSeq = massive[i];
                }
        }

        while (maxSeq > 0){
            result.add(0,list.get(lastInd));
            lastInd = parentInd[lastInd];
            maxSeq--;
        }
        return result;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     *
     * В файле с именем inputName задано прямоугольное поле:
     *
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     *
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     *
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
