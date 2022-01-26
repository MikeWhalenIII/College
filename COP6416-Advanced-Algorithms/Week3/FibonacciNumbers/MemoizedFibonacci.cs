namespace FibonacciNumbers
{
    class MemoizedFibonacci
    {
        private List<long> computed = new();

        // Constructor
        public MemoizedFibonacci()
        {
            computed.Add(0L);
            computed.Add(1L);
        }

        public long f(int n)
        {
            /* Base Case */
            if (n < computed.Count())
                return computed[n];

            long l = this.f(n-1) + this.f(n-2);
            // Memoize
            computed.Add(l);

            return l; 
        }
    }
}