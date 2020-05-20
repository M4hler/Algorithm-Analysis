package Main;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main
{
    private Random random = new Random();

    public static void main(String[] args)
    {
        Main main = new Main();

        //main.Exercise11aPart1();
        //main.Exercise11aPart2();
        main.Exercise11b();
    }

    private void Exercise11aPart1()
    {
        int [] n = new int[]{1, 3, 6, 12, 24, 48};
        double qStep = 0.5 / 100;

        for(int i = 0; i < n.length; i++)
        {
            double q = qStep;
            Map<Double, Double> nakamotoData = new HashMap<>();
            Map<Double, Double> grunspanData = new HashMap<>();

            while(q < 0.5)
            {
                double nakamotoResult = NakamotoFormula(n[i], q);
                double grunspanResult = GrunspanFormula(n[i], q);

                nakamotoData.put(q, nakamotoResult);
                grunspanData.put(q, grunspanResult);

                q += qStep;
            }

            Chart chart = new Chart<>("Exercise 11a part 1, n = " + n[i], nakamotoData, "n = " + n[i], "q", "P(n, q)", "Nakamoto");
            chart.addDataSet(grunspanData, "Grunspan");
            chart.display();
        }
    }

    private void Exercise11aPart2()
    {
        double [] Pnq = new double[]{0.001, 0.01, 0.1};
        double qStep = 0.5 / 100;

        for(int i = 0; i < Pnq.length; i++)
        {
            double q = qStep;
            Map<Double, Integer> nakamotoData = new HashMap<>();
            Map<Double, Integer> grunspanData = new HashMap<>();

            while(q < 0.5)
            {
                double nakamotoResult = 1;
                double grunspanResult = 1;

                int n1;
                for(n1 = 1; nakamotoResult > Pnq[i]; n1++)
                {
                    nakamotoResult = NakamotoFormula(n1, q);
                }

                int n2;
                for(n2 = 1; grunspanResult > Pnq[i]; n2++)
                {
                    grunspanResult = GrunspanFormula(n2, q);
                }

                nakamotoData.put(q, n1);
                grunspanData.put(q, n2);

                q += qStep;
            }

            Chart chart = new Chart<>("Exercise 11a part 2, P(n, q) = " + Pnq[i], nakamotoData, "P(n, q) = " + Pnq[i], "q", "P(n, q)", "Nakamoto");
            chart.addDataSet(grunspanData, "Grunspan");
            chart.display();
        }
    }

    private void Exercise11b()
    {
        int [] n = new int[]{1, 3/*, 6, 12, 24, 48*/};
        double qStep = 0.5 / 100;

        for(int i = 0; i < n.length; i++)
        {
            double q = qStep;
            Map<Double, Double> nakamotoData = new HashMap<>();
            Map<Double, Double> grunspanData = new HashMap<>();
            Map<Double, Double> monteCarloData = new HashMap<>();

            while(q < 0.5)
            {
                double nakamotoResult = NakamotoFormula(n[i], q);
                double grunspanResult = GrunspanFormula(n[i], q);
                double monteCarloResult = MonteCarlo(n[i], q, 10000);

                nakamotoData.put(q, nakamotoResult);
                grunspanData.put(q, grunspanResult);
                monteCarloData.put(q, monteCarloResult);

                q += qStep;
            }

            Chart chart = new Chart<>("Exercise 11b, n = " + n[i], nakamotoData, "n = " + n[i], "q", "P(n, q)", "Nakamoto");
            chart.addDataSet(grunspanData, "Grunspan");
            chart.addDataSet(monteCarloData, "Monte Carlo");
            chart.display();
        }
    }

    private double NakamotoFormula(int n, double q)
    {
        double p = 1 - q;
        double lambda = n * q / p;
        double eToPowerLambda = Math.exp(-lambda);
        double sum = 0;
        double kFactorial = 1;
        double lambdaToPowerK = 1;
        double qn = Math.pow(q / p, n);

        for(int i = 0; i <= n - 1; i++)
        {
            sum += eToPowerLambda * (lambdaToPowerK / kFactorial) * (1 - qn);

            lambdaToPowerK *= lambda;
            kFactorial *= (i + 1);
            qn = Math.pow(q / p, n - (i + 1));
        }

        return 1 - sum;
    }

    private double GrunspanFormula(int n, double q)
    {
        double p = 1 - q;
        double pToPowerN = Math.pow(p, n);
        double qToPowerN = Math.pow(q, n);
        double pToPowerI = 1;
        double qToPowerI = 1;
        double sum = 0;
        double binomialCoefficient = 1;

        for(int i = 0; i <= n - 1; i++)
        {
            sum += (pToPowerN * qToPowerI - qToPowerN * pToPowerI) * binomialCoefficient;
            pToPowerI *= p;
            qToPowerI *= q;
            binomialCoefficient *= (double) (n + i) / (i + 1);
        }

        return 1 - sum;
    }

    private double MonteCarlo(int n, double q, long samples)
    {
        //code here

        return 0;
    }
}
