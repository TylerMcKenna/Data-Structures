class ThreeMcKenna {
    public static void main(String[] args) {


        // I should have implemented with false being composite, since there will be a greater number of composite numbers. 
        // This would save a great deal of time by not setting as many booleans to a new value.
        // false = prime
        // true = composite

        if (args.length < 1) {
            System.out.println("Please enter n.");
            return;
        }
        double startTime = System.nanoTime();

        int userPassedValue = Integer.parseInt(args[0]);
        // - 2 because array starts at number 2 (visually shown in docs at bottom)
        boolean[] primeNumbers = new boolean[userPassedValue - 2];
        for (int i = 0; i < Math.sqrt(userPassedValue) - 1; i++) {
            int currentVal = i+2;
            // if current index of primeNumber list is prime
            if (!primeNumbers[i]) {
                for (int j = currentVal*currentVal; j < userPassedValue; j+=currentVal) {
                    primeNumbers[j-2] = true;
                }
            }
        }

        double duration = System.nanoTime() - startTime;
        duration = duration / Math.pow(10, 9);
        System.out.println("Calculating took " + duration + " seconds.");

        //for(int i = 0; i < primeNumbers.length; i++) {
        //    if (!primeNumbers[i]) System.out.print(i + 2 + " ");
        //}

         
        // prints last 5 prime numbers under userPassedValue (n)
        int k = 0;
        for (int i = primeNumbers.length - 1; i > 0; i--) {
            if (!primeNumbers[i]) {
                System.out.println(i + 2);
                k++;
                if (k > 4) break;
            }
        }
        
        /*     
         * THIS IS WHERE I WAS PLANNING OUT MY IMPLEMENTATION OF THE ALGORITHM
         *   
         * for (int i = 0; i < Math.sqrt(userPassedValue) - 1.0; i++) {
         *      int currentVal = i+2
         *          if (n = currentVal^2; n < userPassedValue; n+=currentVal) {
         *              primeNumbers[n-2] = 1;
         *      }
         * }
         *  
         * 
         *  I chose to do this so I could start loop at index 0 still, and save memory
         *  index  |  0  1  2  3  4  5  6  7  8  9  10  11  12  13  14  15  16  17  18  19  20
         *  number |  2  3  4  5  6  7  8  9 10 11  12  13  14  15  16  17  18  19  20  21  22
         * 
         */
    }
}