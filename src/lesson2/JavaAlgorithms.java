package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     * <p>
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     * <p>
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     * <p>
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        throw new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     * <p>
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 5
     * <p>
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 х
     * <p>
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 х 3
     * 8   4
     * 7 6 Х
     * <p>
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     * <p>
     * 1 Х 3
     * х   4
     * 7 6 Х
     * <p>
     * 1 Х 3
     * Х   4
     * х 6 Х
     * <p>
     * х Х 3
     * Х   4
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   х
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   Х
     * Х х Х
     * <p>
     * Общий комментарий: решение из Википедии для этой задачи принимается,
     * но приветствуется попытка решить её самостоятельно.
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        int result = 0;
        for (int i = 1; i <= menNumber; i++) {
            result = (result + choiceInterval) % i;
        }
        return result + 1;
    }
   /* Время: O(N), N = menNumber
     Память: O(1)*/

    /**
     * Наибольшая общая подстрока.
     * Средняя
     * <p>
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строк у.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    static public String longestCommonSubstring(String first, String second) throws IOException {
        int maxLength = 0;
        String maxStr = "";
        int firstLength = first.length();
        int secondLength = second.length();
        for (int i = 0; i < firstLength; i++) {
            int k = i;
            while ((second.contains(first.substring(i, k))) && (k < firstLength)) {
                k++;
            }
            if ((k - i) > maxLength) {
                maxLength = k - i;
                maxStr = first.substring(i, k - 1);
            }
            ;
        }
        return maxStr;
    }

    /* Время: O(N*M), N - количество символов в first, M - количество символов в second*/

    /**
     * Простая
     * <p>
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     * <p>
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        throw new NotImplementedError();
    }

    /**
     * Балда
     * Сложная
     * <p>
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     * <p>
     * И Т Ы Н
     * К Р А Н
     * А К В А
     * <p>
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     * <p>
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     * <p>
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     * <p>
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */

    static private boolean searchWord(ArrayList<String[]> field, int i, int j, String word, int order) {
        if (i < 0 || j < 0 || i >= field.size() || j >= field.get(0).length) return false;
        if (!(field.get(i)[j].equals(word.substring(order, order + 1))))
            return false;

        if (order == word.length()) return true;
        if (searchWord(field, i - 1, j, word, order + 1) ||
                searchWord(field, i + 1, j, word, order + 1) ||
                searchWord(field, i, j - 1, word, order + 1) ||
                searchWord(field, i, j + 1, word, order + 1)) return true;
        return false;
    }


    static public Set<String> baldaSearcher(String inputName, Set<String> words) throws IOException, NullPointerException {


        ArrayList<String[]> field = null;
        int weight = 0;
        int high = 0;
        Set<String> result = new HashSet<>();

        try {
            FileInputStream file = new FileInputStream(inputName);
            InputStreamReader fr = new InputStreamReader(file, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(fr);
            String str = reader.readLine();

            while (str != null) {
                if (!str.matches("([A-ZА-ЯЁ] )+[A-ZА-ЯЁ]")) {
                    throw new IllegalArgumentException();
                }

                field.add(str.split(" "));
                str = reader.readLine();
            }

            weight = field.get(0).length;
            high = field.size();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String word : words) {
            if (!word.matches("[A-ZА-ЯЁ]+")) {
                throw new IllegalArgumentException();
            }
            boolean nowWord = false;
            for (int i = 0; i < weight; i++) {
                for (int j = 0; j < high; j++) {
                    nowWord = searchWord((ArrayList<String[]>) field, i, j, word, 0);
                    if (nowWord) break;
                }
                if (nowWord) break;
            }
            if (nowWord) result.add(word);
        }
        return result;
    }
}
/* Время: O(weight * high * words.size),
     Память: O(weight * high * words.size)*/


