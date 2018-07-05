// ConsoleApplication2.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <omp.h>

#define numThreads 4

using namespace std;

struct Range
{
	int range[3] = {};
};

Range mergeRange(Range r1, Range r2)
{
	int val1;
	int val2;

		if (r1.range[0] <= r2.range[0]) {
			val1 = r1.range[0];
		}
		else {
			val1 = r2.range[0];
		}

		if (r1.range[1] <= r2.range[1]) {
			val2 = r2.range[0];
		}
		else {
			val2 = r1.range[0];
		}


	int arr[3] = { val1, val2, r1.range[2] + r2.range[2] };

	Range newR;
	newR.range = arr;

	return newR;
}

int maxSubArraySum(int arr[], int sIndex, int eIndex)
{
	struct Range T[numThreads] = {};
	struct Range M[numThreads] = {};
	struct Range I[numThreads] = {};
	struct Range F[numThreads] = {};

	int val1 = 0;
	int val2 = 0;
	int val3 = 0;
#pragma omp parallel for num_threads(numThreads) private(val1, val2, val3) shared(T, M, I, F)
	for (int i = sIndex; i < eIndex; i += 2)
	{
		val1 = arr[i];
		val2 = arr[i + 1];
		val3 = arr[i + 2];

#pragma omp critical
		int arrT[3] = { i, i + 1, val1 + val2 };
		T[i].range = arrT;

#pragma omp critical
		int arrM[3] = { i, i + 1, val1 + val2 };
		M[i].range = arrM;

#pragma omp critical
		int arrI[3] = { i, i + 1, val1 + val2 };
		I[i].range = arrI;

#pragma omp critical
		int arrF[3] = { i + 1, i + 1, val1 + val2 + val3 };
		F[i].range = arrF;
	}

	struct Range finalT = T[0];
	struct Range finalM = M[0];
	struct Range finalI = I[0];
	struct Range finalF = F[0];

#pragma omp parallel for num_threads(numThreads) private(finalT, finalM, finalI, finalF) shared(T, M, I, F)
	for (int i = 1; i < numThreads; i++)
	{
		finalT = mergeRange(finalT, T[i]);
		finalM = mergeRange(finalM, I[i]);
		finalI = mergeRange(finalI, M[i]);
		finalF = mergeRange(finalF, F[i]);
	}

	return finalM.range[0];
}

int _tmain(int argc, _TCHAR* argv[])
{
	int arr[] = { -2, -5, 6, -2, -3, 1, 5, -6 };
	//int arr[] = { -1, 3, 4, -6, 10 };
	int n = sizeof(arr) / sizeof(arr[0]);

	omp_set_num_threads(sizeof(arr) / 2);

	int max_sum = maxSubArraySum(arr, 0, n - 1);
	cout << max_sum;

	getchar();

	return 0;
}

