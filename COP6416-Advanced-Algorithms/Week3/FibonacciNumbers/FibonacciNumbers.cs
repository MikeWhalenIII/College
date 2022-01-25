namespace FibonacciNumbers
{
    class FibonacciNumbers
    {
        static void Main(string[] args)
        {
            /* Naive Fibbonaci */
            NaiveFibonacci nf = new NaiveFibonacci();
            System.Console.WriteLine(nf.f(30));
        }
    }
}