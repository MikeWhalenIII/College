namespace FibonacciNumbers
{
    class FibonacciNumbers
    {
        static void Main(string[] args)
        {
            /* Naive Fibonacci */
            NaiveFibonacci nf = new();
            System.Console.WriteLine($"Naive: {nf.f(10)}");

            /* Memoized Fibonacci */
            MemoizedFibonacci mf = new();
            System.Console.WriteLine($"Memoized: {mf.f(92)}"); // Beyond 92, overflows long data type
        }
    }
}