package lesson1;

import kotlin.NotImplementedError;

import java.io.*;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     * <p>
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     * <p>
     * Пример:
     * <p>
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     * <p>
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     * <p>
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) throws IOException {
        ArrayList<Integer> time = new ArrayList<Integer>();
        int length = 0;

        try {
            File file = new File(inputName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String str = reader.readLine();

            while (str != null) {
                if (!str.matches("(([0-9]{2}:[0-9]{2}:[0-9]{2}) (AM|PM))"))
                    throw new IllegalArgumentException();

                Integer digit;
                String[] oneTime = str.replace(" ", ":").split(":");
                if (oneTime[3].equals("PM")) digit = 43200;
                else
                    digit = 0;
                if (oneTime[0].equals("12")) digit -= 43200;

                digit = digit + Integer.parseInt(oneTime[0]) * 3600 +
                        Integer.parseInt(oneTime[1]) * 60 + Integer.parseInt(oneTime[2]);
                time.add(digit);

                length++;
                str = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        int[] allTime = new int[length];
        for (int i = 0; i < length; i++) {
            allTime[i] = time.get(i);
        }

        Sorts.mergeSort(allTime);

        try (FileWriter writer = new FileWriter(outputName, false)) {
            for (int i = 0; i < length; i++) {
                int digit = allTime[i];
                String period;
                if (digit >= 43200) {
                    period = "PM";
                    digit -= 43200;
                } else
                    period = "AM";
                int hours = digit / 3600;
                if (hours == 0) hours = 12;
                digit %= 3600;
                int minutes = digit / 60;
                int seconds = digit % 60;

                String zeroHours = "";
                String zeroMinutes = "";
                String zeroSeconds = "";
                if (hours < 10) zeroHours = "0";
                if (minutes < 10) zeroMinutes = "0";
                if (seconds < 10) zeroSeconds = "0";

                writer.write(zeroHours + hours + ":" + zeroMinutes + minutes + ":" + zeroSeconds +
                        seconds + " " + period + "\n");
            }
        }

       /* Время: O(N*logN), N = количество строк в inputName
        Память: O(N)*/


    }

    /**
     * Сортировка адресов
     * <p>
     * Средняя
     * <p>
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     * <p>
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     * <p>
     * Людей в городе может быть до миллиона.
     * <p>
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     * <p>
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     * <p>
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     * <p>
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     * <p>
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     * <p>
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        int length = 0;

        try {

            File file = new File(inputName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String str = reader.readLine();

            while (str != null) {
                Integer digit;
                String part; /*=  str.replace(".", "");*/
                if (str.contains("-0.")) part = str.replace("0.", "");
                else
                    part = str.replace(".", "");
                digit = Integer.parseInt(part);
                temp.add(digit);

                length++;
                str = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        int[] allTemp = new int[length];
        for (int i = 0; i < length; i++) {
            allTemp[i] = temp.get(i);
        }

        Sorts.mergeSort(allTemp);

        try (FileWriter writer = new FileWriter(outputName, false)) {
            for (int i = 0; i < length; i++) {
                int digit = allTemp[i];
                String sign = "";
                if (digit < 0) sign = "-";
                int integerPart = Math.abs(digit / 10);
                int fractionalPart = Math.abs(digit % 10);


                writer.write(sign + integerPart + "." + fractionalPart + "\n");
            }


        }
    }
    /* Время: O(N*logN), N = количество строк в inputName
        Память: O(N)*/

    /**
     * Сортировка последовательности
     * <p>
     * Средняя
     * (Задача взята с сайта acmp.ru)
     * <p>
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     * <p>
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     * <p>
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     * <p>
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) throws IOException {
        ArrayList<Integer> inputList = new ArrayList<Integer>();
        int length = 0;

        try {
            File file = new File(inputName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String str = reader.readLine();

            while (str != null) {
                inputList.add(Integer.parseInt(str));
                length++;
                str = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        int[] listSorted = new int[length];
        for (int i = 0; i < length; i++)
            listSorted[i] = inputList.get(i);

        Sorts.mergeSort(listSorted);

        int maxDigit = listSorted[0];
        int maxRepeat = 1;
        int nowDigit = maxDigit;
        int nowRepeat = 1;

        for (int i = 1; i < length; i++) {
            int digit = listSorted[i];
            if (digit == nowDigit) nowRepeat++;
            else {
                if (nowRepeat > maxRepeat) {
                    maxDigit = nowDigit;
                    maxRepeat = nowRepeat;
                }
                nowDigit = digit;
                nowRepeat = 1;
            }
        }

        if (nowRepeat > maxRepeat) {
            maxRepeat = nowRepeat;
            maxDigit = nowDigit;
        }

        try (FileWriter writer = new FileWriter(outputName, false)) {
            for (int i = 0; i < length; i++) {
                int digit = inputList.get(i);
                if (digit != maxDigit) writer.write(digit + "\n");
            }

            for (int i = 0; i < maxRepeat; i++) {
                writer.write(maxDigit + "\n");
            }
        }

         /* Время: O(N*logN), N = количество строк в inputName
        Память: O(N)*/

    }

    /**
     * Соединить два отсортированных массива в один
     * <p>
     * Простая
     * <p>
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     * <p>
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     * <p>
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
