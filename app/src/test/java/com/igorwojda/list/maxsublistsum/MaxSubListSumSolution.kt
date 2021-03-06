package com.igorwojda.list.maxsublistsum

// Time Complexity: O(n)
// Space Complexity: O(1)
// Use "sliding window" - store sum in single variable and with each iteration add (current item)
// and remove (first item before current sub-list)
private object Solution1 {
    private fun maxSubListSum(list: List<Int>, n: Int): Int? {
        if (list.size < n) {
            return null
        }

        var maxSum = list.take(n).sum()
        var tempSum = maxSum

        (n..list.lastIndex).forEach { index ->
            tempSum = tempSum - list[index - n] + list[index]
            maxSum = Math.max(maxSum, tempSum)
        }

        return maxSum
    }
}

private object Solution2 {
    private fun maxSubListSum(list: List<Int>, n: Int): Int? {
        if (list.size < n) {
            return null
        }

        return list.foldIndexed(0 to 0) { i, (sum, max), next ->
            (sum + next - (list.getOrNull(i - n) ?: 0)).let {
                it to if (it > max) it else max
            }
        }.second
    }
}

// Time Complexity: O(n*m)
// Loop through the list and at each index loop again to calculate sum of sublist (from index to index + n)
private object Solution3 {
    private fun maxSubListSum(list: List<Int>, n: Int): Int? {
        if (list.size < n) {
            return null
        }

        var maxSum: Int? = null

        for (i in 0..list.size - n) {
            var tempSum: Int? = null

            for (j in i until (i + n)) {
                if (tempSum == null) {
                    tempSum = list[j]
                } else {
                    tempSum += list[j]
                }
            }

            maxSum = max(maxSum, tempSum)
        }

        return maxSum
    }

    private fun max(i1: Int?, i2: Int?): Int? {
        return when {
            i1 != null && i2 != null -> Math.max(i1, i2)
            i1 != null && i2 == null -> i1
            i1 == null && i2 != null -> i2
            else -> null
        }
    }
}

private object Solution4 {
    private fun maxSubListSum(list: List<Int>, n: Int): Int? {
        if (list.isEmpty()) return null

        return (0..list.size - n)
            .map { i -> list.subList(i, i + n).sum() }
            .max()
    }
}
