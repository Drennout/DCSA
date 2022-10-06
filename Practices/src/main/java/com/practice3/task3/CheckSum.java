package com.practice3.task3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CheckSum {

    public static void main(String[] args) throws IOException {
        checkSum();
    }

    public static void checkSum() throws IOException {
        String result = "";
        File file = new File("task1.txt");
        if (!file.exists()) {
            file.createNewFile();
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Hello world.");
            bw.flush();
            bw.close();
            fw.close();
        }
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String str = br.readLine(); // Чтение информации о файле
        System.out.println("Содержимое прочитанного файла:" + str);

        byte[] buff = str.getBytes(); // Массив байтов получает значение Ascill для каждого байта
        int byteNum = buff.length; // Количество байтов в тексте

        String choose = (byteNum % 2 == 0 ? "Even" : "Odd"); // Определить, является ли число байтов нечетным или четным
        System.out.println("Длина текста содержит байты:" + choose);
        int num = 0;


        System.out.print("Последовательность шестнадцатеричных целых чисел, соответствующая каждой паре символов в прочитанном файле:");
        int[] ten = new int[100];
        String[] b = new String[100];

        int notnulllength = 0;
        for (String s : b) {// Получить количество непустых элементов сложения
            if (s != null) {
                notnulllength++;

            }
        }
        System.out.println('\t');
        for (int i = 0; i < notnulllength; i++) {
            ten[i] = Integer.parseInt(b[i], 16); // Преобразовать непустые элементы сложения в десятичные числа

        }
        int sum = 0; // Вычисляем десятичную сумму
        int carry = 0; // перенос
        String checkSum = null; // шестнадцатеричная контрольная сумма
        for (int i = 0; i < notnulllength; i++) {
            // Преобразуем непустые элементы сложения в десятичные числа
            //ten[num+1]=0;
            sum += ten[i];
            //System.out.println(sum);
            checkSum = Long.toHexString(sum);
            //System.out.println(checkSum);
            int jud = Integer.parseInt(checkSum, 16); // Преобразовать контрольную сумму в десятичное число
            if (jud > 65535) {// накапливать каждый раз из первого элемента добавления в качестве номера оценки, посмотреть, больше ли шестнадцатеричное значение этого номера оценки, чем FFFF
                carry = 1; // Установить цифру переноса на 1
                sum = jud - 65536 + carry; // итого = исходный номер суждения минус шестнадцатеричное число 10000 плюс перенос 1
            }


        }
        System.out.println("16-битная контрольная сумма вышеуказанных данных: 0x" + checkSum);
    }


}