namespace FibonacciNumbers
{
    class BottomUpFibonacci
    {
        public long f(int n)
        {
            long nMinus1 = 1;
            long nMinus2 = 0;
            long result = 0;

            while (--n > 0)
            {
                result = nMinus1 + nMinus2;

                nMinus2 = nMinus1;
                nMinus1 = result;
            }

            return result;
        }
    }
}