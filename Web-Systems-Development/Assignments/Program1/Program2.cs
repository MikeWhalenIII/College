/* This program will compute the area and circumference of a circle.
 * 
 * Michael Whalen
 * N01425161
 * COP3855 - Web Systems Development
 */

using System;

namespace Program1
{
    class Program2
    {
        static void Main(string[] args)
        {
            while (true)
            {
                double radius = 0;

                Console.WriteLine("Welcome! Please provide the radius of the circle.\n");
                Console.Write("Radius: ");
                radius = Convert.ToDouble(Console.ReadLine());

                Area(radius);
                Circumference(radius);

                Console.WriteLine("\nPress CTRL + C to exit.\n");
            }
        }

        static void Area(double radius)
        {
            double area = 3.14 * radius * radius;
            Console.WriteLine("Area: " + "3.14" + " * " + radius + " * " + radius + " = " + "{0:0.00}", area);
        }

        static void Circumference(double radius)
        {
            double circumference = 2 * 3.14 * radius;
            Console.WriteLine("Circumference: " + "2" + " * " + "3.14" + " * " + radius + " = " + "{0:0.00}", circumference);
        }
    }
}
