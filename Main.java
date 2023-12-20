package com.company;
import java.util.*;
public abstract class Main

{
    public static void main(String[] args)
    {
        System.out.println("Part - A\n");
        //Question 1
        int numberOfFaces_A = 6;
        int numberOfFaces_B = 6;
        int totalCombination = numberOfFaces_A * numberOfFaces_B;
        System.out.println("Question - 1");
        System.out.println();
        System.out.println("Total number of combination = Number of Faces in dice A X Number of Faces in dice B");
        System.out.println("Total number of combination = " + numberOfFaces_A + " X " + numberOfFaces_B);
        System.out.println("So," + "\n" + "Total number of combination = " + totalCombination);
        System.out.println("-----------------------------------------------------------------------------------------------------");

        //Question 2
        //Displaying the distribution of all possible combination
        System.out.println("\nQuestion - 2\n");
        int distribution[][] = new int[numberOfFaces_A][numberOfFaces_B];
        System.out.println("The distribution of all possible combinations (36) are :");
        for (int i = 1; i <= numberOfFaces_A; i++) {
            for (int j = 1; j <= numberOfFaces_B; j++) {
                if (i == 6 && j == 6)
                    System.out.print("(" + i + "," + j + ") ");
                else
                    System.out.print("(" + i + "," + j + "), ");
            }
            System.out.println();

        }
        System.out.println("-----------------------------------------------------------------------------------------------------");
        //Question 3
        //Probability of all Possible Sums occurring among the number of combinations from (2)
        System.out.println("\nQuestion - 3\n");
        System.out.println("Probability of all Possible Sums occurring among the number of combinations are :\n");
        for (int i = 0; i < numberOfFaces_A; i++) {
            for (int j = 0; j < numberOfFaces_B; j++) {
                distribution[i][j] = (i + 1) + (j + 1);
            }
        }

        for (int sum = 2; sum <= 12; sum++) {
            int count = 0;
            for (int i = 0; i < numberOfFaces_A; i++) {
                for (int j = 0; j < numberOfFaces_B; j++) {
                    if (distribution[i][j] == sum)
                        count++;
                }
            }
            double probability = (double) count / totalCombination;
            System.out.println("P(Sum = " + sum + ")  = " + "No.of Combinations with sum = " + sum + " /" + " Total no. of Combinations");
            System.out.println("\t\t\t = " + count + "/" + totalCombination);
            System.out.println("\t\t\t = " + probability);
        }
        System.out.println("-----------------------------------------------------------------------------------------------------");

        System.out.println("Part - B\n");
        //Part B
        int dice_A[] = {1,2,3,4,5,6};
        int dice_B[]= {1,2,3,4,5,6};
        undoom_dice(dice_A, dice_B);//Function that takes in old faces and generates new faces.
    }

        public static List<List<Integer>> findCombinationsWithReplacement(int[] arr, int r)
        {
            List<List<Integer>> result = new ArrayList<>();
            generateCombinationsWithReplacement(arr, r, 0, new ArrayList<>(), result);
            return result;
        }

        private static void generateCombinationsWithReplacement(int[] arr, int r, int index, List<Integer> currentCombination, List<List<Integer>> result)
        {
            if (r == 0)
            {
                result.add(new ArrayList<>(currentCombination));
                return;
            }

            for (int i = index; i < arr.length; i++)
            {
                currentCombination.add(arr[i]);
                generateCombinationsWithReplacement(arr, r - 1, i, currentCombination, result);
                currentCombination.remove(currentCombination.size() - 1);
            }
        }
        public static List<List<Integer>> findCombinations(int[] arr, int r)
        {
            List<List<Integer>> result = new ArrayList<>();
            generateCombinations(arr, 0, new ArrayList<>(), result, r);
            return result;
        }

        private static void generateCombinations(int[] arr, int index, List<Integer> currentCombination, List<List<Integer>> result, int r)
        {
            if (currentCombination.size() == r)
            {
                result.add(new ArrayList<>(currentCombination));
                return;
            }

            for (int i = index; i < arr.length; i++)
            {
                currentCombination.add(arr[i]);
                generateCombinations(arr, i + 1, currentCombination, result, r);
                currentCombination.remove(currentCombination.size() - 1);
            }
        }

        public static void undoom_dice(int old_dice_A[], int old_dice_B[])
        {
            System.out.println("Before retrieval :\n");
            System.out.println("Old faces of Die A are : "+ Arrays.toString(old_dice_A));
            System.out.println("\nOld faces of Die B are : "+ Arrays.toString(old_dice_B));
            System.out.println("-----------------------------------------------------------------------------------------------------");

            int sum_array[]={0,0, 1,2,3,4,5,6,5,4,3,2,1,0,0,0};
            int new_sum_array[]= new int[16];
            int sum=0;
            int[] myArray_1 = {1, 2, 3, 4};
            int r = 6;
            List<List<Integer>> combinationsList = findCombinationsWithReplacement(myArray_1, r);

            int[] myArray_2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
            int r_2 = 6;
            List<List<Integer>> combinationsList_2 = findCombinations(myArray_2, r_2);

            System.out.println("\nOh! Eventhough Loki has doomed our dices, we tried retrieving them back successfully.");
            System.out.println("\nAfter retrieval :\n");

            for (List<Integer> combo_die1 : combinationsList)
            {
                for (List<Integer> combo_die2 : combinationsList_2)
                {
                    Arrays.fill(new_sum_array, 0);
                    for (int i = 0; i < 6; i++)
                    {
                        for (int j = 0; j < 6; j++)
                        {
                            sum = combo_die1.get(i) + combo_die2.get(j);
                            new_sum_array[sum]++;
                        }
                    }
                    if (Arrays.equals(sum_array, new_sum_array))
                    {
                        System.out.println("The new faces of the Die A are " + combo_die1);
                        System.out.println("\nThe new faces of the Die B are " + combo_die2);
                    }
                }

            }
        }
    }
