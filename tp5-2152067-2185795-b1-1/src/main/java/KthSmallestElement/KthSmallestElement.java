package KthSmallestElement;

public class KthSmallestElement {
    /*
      Explication de votre complexité temporelle
      Les opérations avant la boucle while sont des opérations à temps constant peu importe la taille de la matrice
      donc la complexité temporelle est O(1). La boucle while de la fonction effectue une recherche binaire pour trouver
      la k-ème plus petite valeur dans la matrice. La recherche binaire divise à chaque itération la plage de valeurs
      possibles par deux. Ainsi, avec m et n le nombre de lignes et de colonnes de la matrice respectivement, le nombre
      d'itérations est m si m >= n et est de n si m < n. Alors, la complexité temporelle est O(log max(m,n)).
      À l'intérieur de la boucle while et ainsi à chaque itération, il y a la boucle for qui parcourt la matrice une fois,
      ce qui prend O(m) temps.
      Par conséquent, le temps total de la fonction est O(1) + O(m*log(max(m,n))) -> O(m log(max (m, n)))
      -
      Explication de votre complexité spatiale
      La complexité spatiale est O(1) dans tous les cas, car le nombre de variables occupant la mémoire est constant
      indépendamment de la taille de la matrice.

     */
    /**
     * TODO Worst case
     *      Time complexity : O ( k log max(m,n) )
     *      Space complexity : O ( log max(m,n) )
     * <p>
     * Returns the `k`th smallest element in `matrix`
     *
     * @param matrix 2D table of shape M x N respecting the following rules
     *               matrix[i][j] <= matrix[i+1][j]
     *               matrix[i][j] <= matrix[i][j + 1]
     * @param k      Index of the smallest element we want
     * @return `K`th smallest element in `matrix`, null if non-existent
     */
    static public <T extends Comparable<T>> T findKthSmallestElement(final T[][] matrix, final int k) {
        if (matrix != null && k >= 0 && k < matrix[0].length * matrix.length) {
            int rows = matrix.length;
            int columns = matrix[0].length;

            Integer start = (Integer) matrix[0][0];
            int end = (Integer) matrix[rows - 1][columns - 1];
            int mid, count, j;

            while (start < end) {
                mid = start + (end - start) / 2;
                count = 0;
                j = columns - 1;

                for (T[] row: matrix) {
                    while (j >= 0 && (Integer) row[j] > mid) {
                        j--;
                    }
                    count += (j + 1);
                }

                if (count <= k) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }

            return (T) start;
        }
        return null;
    }
}
