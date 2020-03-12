package org.lider;

import java.util.*;

public class Main
{
    private Random generator;

    public static void main(String[] args)
    {
        Main main = new Main();

        main.scenarioNKnown(100, 1000, "Histogram: n known");

        main.scenarioUKnown(100, 2, 1000, "Histogram: u known, n = 2");
        main.scenarioUKnown(100, 50, 1000, "Histogram: u known, n = 50");
        main.scenarioUKnown(100, 100, 1000, "Histogram: u known, n = 100");

        main.scenarioUKnownEx4(100, 100, 1000);
    }

    public Main()
    {
        generator = new Random();
    }

    public void scenarioNKnown(int n, int number, String title)
    {
        Map<Integer, Integer> data = new HashMap<>();
        int totalSlots = 0;

        for(int i = 0; i < number; i++)
        {
            int result = leaderElection(n);
            totalSlots += result;

            if(data.containsKey(result))
            {
                int oldVal = data.get(result);
                data.put(result, oldVal + 1);
            }
            else
            {
                data.put(result, 1);
            }
        }

        Histogram histogram = new Histogram(title, data);
        histogram.pack();
        histogram.setVisible(true);

        double expectedValue = (double)totalSlots / number;
        double variance = 0;

        for(Map.Entry<Integer, Integer> entry : data.entrySet())
        {
            variance += Math.pow((double)entry.getKey() - expectedValue, 2.0) * entry.getValue();
        }
        variance /= number;

        System.out.println("E[L] = " + expectedValue + " Var[L] = " + variance);
    }

    public void scenarioUKnown(int u, int n, int number, String title)
    {
        Map<Integer, Integer> data = new HashMap<>();

        for(int i = 0; i < number; i++)
        {
            int result = leaderElection(u, n);

            if(data.containsKey(result))
            {
                int oldVal = data.get(result);
                data.put(result, oldVal + 1);
            }
            else
            {
                data.put(result, 1);
            }
        }

        Histogram histogram = new Histogram(title, data);
        histogram.pack();
        histogram.setVisible(true);
    }

    public void scenarioUKnownEx4(int u, int n, int number)
    {
        int firstRounds = 0;
        for(int i = 0; i < number; i++)
        {
            int result = leaderElectionV2(u, n); //in this case result is round number

            if(result == 1)
            {
                firstRounds++;
            }
        }

        double probability = (double)firstRounds / number;

        System.out.println("Pr[S_{L,n}] = " + probability);
    }

    private int leaderElection(int n)
    {
        int i = 0; //number of slot
        int slot = 0; //slot status

        while(slot != 1)
        {
            i++;
            slot = 0;

            for(int j = 0; j < n; j++)
            {
                if(generator.nextDouble() < ((double)1 / n))
                {
                    slot++;
                }

                if(slot > 1)
                {
                    break;
                }
            }
        }

        return i;
    }

    private int leaderElection(int u, int n)
    {
        int i = 0; //number of slot
        int slot = 0; //slot status
        int m = (int)Math.ceil(Math.log(u) / Math.log(2)); //length of single round

        while(slot != 1)
        {
            i++;
            slot = 0;

            for(int j = 0; j < n; j++)
            {
                double probability = 1 / Math.pow(2.0, i % m);

                if(i % m == 0)
                {
                    probability = 1 / Math.pow(2.0, i);
                }

                if(generator.nextDouble() < probability)
                {
                    slot++;
                }

                if(slot > 1)
                {
                    break;
                }
            }
        }

        return i;
    }

    private int leaderElectionV2(int u, int n)
    {
        int i = 0; //number of slot
        int slot = 0; //slot status
        int m = (int)Math.ceil(Math.log(u) / Math.log(2)); //length of single round
        int round = 0; //round number

        while(slot != 1)
        {
            i++;
            slot = 0;

            if(i % m == 1)
            {
                round++;
            }

            for(int j = 0; j < n; j++)
            {
                double probability = 1 / Math.pow(2.0, i % m);

                if(i % m == 0)
                {
                    probability = 1 / Math.pow(2.0, i);
                }

                if(generator.nextDouble() < probability)
                {
                    slot++;
                }

                if(slot > 1)
                {
                    break;
                }
            }
        }

        return round;
    }
}
