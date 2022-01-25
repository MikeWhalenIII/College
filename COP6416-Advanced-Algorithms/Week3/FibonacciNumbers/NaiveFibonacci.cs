namespace FibonacciNumbers
{
    class NaiveFibonacci
    {
        public long f(int n)
        {
            /* Base Cases */
            if (n <= 0)
                return 0L;

            if (n == 1)
                return 1L;

            return this.f(n-1) + this.f(n-2);
        }
    }
}