package org.example;


import lombok.SneakyThrows;

import java.io.*;

public class HomeWork {

    private static final String SPASE = " ";

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу https://codeforces.com/contest/356/problem/A
     */
    @SneakyThrows
    public void championship(InputStream in, OutputStream out) {
        var bufferedReader = new BufferedReader(new InputStreamReader(in));
        var numberKnightsAndNumberBattles = bufferedReader.readLine().split(SPASE);
        int numberKnights = Integer.parseInt(numberKnightsAndNumberBattles[0]);
        var activeKnights = new RedBlackTree<Integer>();
        for (int i = 1; i <= numberKnights; i++) //количество рыцарей
            activeKnights.add(i);

        var temp = new int[numberKnights + 1];

        for (int i = 0; i < Integer.parseInt(numberKnightsAndNumberBattles[1]); i++) { //Количество боев
            var descriptionOfBattle = bufferedReader.readLine().split(SPASE);
            var winner = Integer.parseInt(descriptionOfBattle[2]);
            activeKnights.getRange(
                            Integer.parseInt(descriptionOfBattle[0]),
                            Integer.parseInt(descriptionOfBattle[1])
                    ).stream()
                    .filter(knight -> knight != winner)
                    .forEach(knight -> {
                        temp[knight] = winner;
                        activeKnights.remove(knight);
                    });
        }
        write(out, numberKnights, temp);
    }

    private void write(OutputStream out, int numberKnights, int[] temp) {
        var writer = new PrintWriter(out);
        for (int i = 1; i <= numberKnights; i++) {
            var split = i == numberKnights ? "" : SPASE;
            writer.print(temp[i] + split);
        }
        writer.flush();
    }
}
