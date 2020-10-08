/* This program will divide, multiply and subtract two numbers.
 * 
 * Michael Whalen
 * N01425161
 * COP3855 - Web Systems Development
 */

using System;
using System.Transactions;

namespace Program1
{
    class Program1
    {
        static void Main(string[] args)
        {
            while (true)
            {
                double number1, number2 = 0;

                Console.WriteLine("Welcome! Please provide two numbers that you would like to divide, multiply and subtract.\n");
                Console.Write("First Number: ");
                number1 = Convert.ToDouble(Console.ReadLine());

                Console.Write("Second Number: ");
                number2 = Convert.ToDouble(Console.ReadLine());

                Divide(number1, number2);
                Multiply(number1, number2);
                Subtract(number1, number2);

                Console.WriteLine("\nPress CTRL + C to exit.\n");
            }
        }

        static void Divide(double number1, double number2) 
        {
            double quotient = number1 / number2;
            double remainder = number1 % number2;
            
            Console.WriteLine(number1 + " / " + number2 + " = " + "{0:0.00}", quotient);
            Console.WriteLine(number1 + " % " + number2 + " = " + "{0:0.00}", remainder);
        }

        static void Multiply(double number1, double number2)
        {
            double product = number1 * number2;

            Console.WriteLine(number1 + " * " + number2 + " = " + "{0:0.00}", product);
        }

        static void Subtract(double number1, double number2)
        {
            double difference = number1 - number2;

            Console.WriteLine(number1 + " - " + number2 + " = " + "{0:0.00}", difference);
        }
    }
}
