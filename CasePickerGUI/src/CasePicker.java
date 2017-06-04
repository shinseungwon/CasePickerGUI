

import java.util.ArrayList;

/*
 * 순열, 조합, 중복순열, 중복조합 의 경우의 수와 경우의 수의 갯수를 쉽게 계산하는 모듈입니다.
 */

public class CasePicker {

	ArrayList<int[]> result;

	// 순열 갯수
	public int PermutationNumber(int n, int r) {
		int result = 1;
		for (int i = n; i > n - r; i--)
			result *= i;

		return result;
	}

	// 순열 경우의 수
	public int[][] Permutation(int n, int r) {
		result = new ArrayList<>();

		int[] temp;
		for (int i = 0; i < n; i++) {
			temp = new int[r];
			temp[0] = i;
			PermutationRecursive(n, r, temp, 1);
		}

		int[][] resultarray = new int[result.size()][];
		for (int i = 0; i < result.size(); i++)
			resultarray[i] = result.get(i);

		return resultarray;
	}

	// 순열 계산을 위한 재귀함수
	private void PermutationRecursive(int n, int r, int[] space, int nextpush) {
		boolean flag;
		System.out.println("Recursive");
		for (int i = 0; i < n; i++) {
			flag = false;
			for (int j = 0; j < nextpush; j++)
				if (space[j] == i) {
					flag = true;
					break;
				}

			if (flag)
				continue;

			space[nextpush] = i;

			if (nextpush == r - 1)
				result.add(space.clone());
			else
				PermutationRecursive(n, r, space, nextpush + 1);
		}
	}

	// 조합 갯수
	public int CombinationNumber(int n, int r) {
		int result = 1;
		for (int i = n; i > n - r; i--)
			result *= i;

		for (int i = r; i > 1; i--)
			result /= i;

		return result;
	}

	// 조합 경우의 수
	public int[][] Combination(int n, int r) {
		result = new ArrayList<>();

		int[] temp;
		for (int i = 0; i < n; i++) {
			temp = new int[r];
			temp[0] = i;
			CombinationRecursive(n, r, temp, 1);
		}

		int[][] resultarray = new int[result.size()][];
		for (int i = 0; i < result.size(); i++)
			resultarray[i] = result.get(i);

		return resultarray;
	}

	// 조합 계산을 위한 재귀 함수
	private void CombinationRecursive(int n, int r, int[] space, int nextpush) {

		for (int i = space[nextpush - 1] + 1; i < n; i++) {
			
			space[nextpush] = i;

			if (nextpush == r - 1)
				result.add(space.clone());
			else
				CombinationRecursive(n, r, space, nextpush + 1);
		}
	}

	// 중복순열 갯수
	public int DuplicatedPermutationNumber(int n, int r) {
		return (int) Math.pow((double) n, (double) r);
	}

	// 중복순열 경우의 수
	public int[][] DuplicatedPermutation(int n, int r) {
		result = new ArrayList<>();

		int[] temp;
		for (int i = 0; i < n; i++) {
			temp = new int[r];
			temp[0] = i;
			PermutationDuplicatedRecursive(n, r, temp, 1);
		}

		int[][] resultarray = new int[result.size()][];
		for (int i = 0; i < result.size(); i++)
			resultarray[i] = result.get(i);

		return resultarray;
	}

	// 중복순열 경우의 수 계산을 위한 재귀함수
	private void PermutationDuplicatedRecursive(int n, int r, int[] space, int nextpush) {

		for (int i = 0; i < n; i++) {

			space[nextpush] = i;

			if (nextpush == r - 1)
				result.add(space.clone());
			else
				PermutationDuplicatedRecursive(n, r, space, nextpush + 1);
		}
	}

	// 중복조합 갯수
	public int DuplicatedCombinationNumber(int n, int r) {
		return CombinationNumber(n + r - 1, r);
	}

	// 중복조합 경우의 수
	public int[][] DuplicatedCombination(int n, int r) {
		result = new ArrayList<>();

		int[] temp;
		for (int i = 0; i < n; i++) {
			temp = new int[r];
			temp[0] = i;
			CombinationDuplicatedRecursive(n, r, temp, 1);
		}

		int[][] resultarray = new int[result.size()][];
		for (int i = 0; i < result.size(); i++)
			resultarray[i] = result.get(i);

		return resultarray;
	}

	// 중복조합 경우의 수 계산을 위한 재귀함수
	private void CombinationDuplicatedRecursive(int n, int r, int[] space, int nextpush) {

		for (int i = space[nextpush - 1]; i < n; i++) {

			space[nextpush] = i;

			if (nextpush == r - 1)
				result.add(space.clone());
			else
				CombinationDuplicatedRecursive(n, r, space, nextpush + 1);
		}

	}
}
