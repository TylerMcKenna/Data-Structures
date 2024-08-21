class Eratosthenes {
    public static void main(String[] args) {
        int userPassedValue = (int)Math.pow(2, 30);
        boolean[] primeNumbers = new boolean[userPassedValue];

        for (int i = 0; i < Math.sqrt(userPassedValue) - 1; i++) {
            int currentVal = i+2;
            if (!primeNumbers[i]) {
                for (int n = currentVal*currentVal; n < userPassedValue; n+=currentVal) {
                    primeNumbers[n-2] = true;
                }
            }
        }
        
        
        int i = 0;
        while (i < 5) {
            if(primeNumbers[userPassedValue] = 0)
        }

        /* 
        for (int i = 0; i < userPassedValue; i++) {
            if(!primeNumbers[i])
            System.out.println(i+2);
        }
        */
   /*     
         for (int i = 0; i < Math.sqrt(userPassedValue) - 1.0; i++) {
               int currentVal = i+2
                if (n = currentVal^2; n < userPassedValue; n+=currentVal) {
                   primeNumbers[n-2] = 1;
               }
          }
         
         * so loop goes index 0(2) to 8(10)
         * 
         *  index  |  0  1  2  3  4  5  6  7  8  9  10  11  12  13  14  15  16  17  18  19  20
         *  value  |  0  0  0  0  0  0  0  0  0  0   0   0   0   0   0   0   0   0   0   0   0
         *  number |  2  3  4  5  6  7  8  9 10 11  12  13  14  15  16  17  18  19  20  21  22
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         */
    }
}