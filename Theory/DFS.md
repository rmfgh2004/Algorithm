## DFS(Depth First Search)

#### 깊이 우선 탐색이란
노드의 연결된 루트 중 가장 깊은 곳을 먼저 탐색하는 기법이다.

<img src="assets/DFS_BFS.gif">

#### 특징
1. 재귀호출 구현
2. 목표 노드가 깊은 곳에 있는 경우 BFS보다 빠르다.

#### 예시 코드
``` java
private static void DFS(int v)  {
    System.out.print(v + " ");
    visited[v] = true;
    for (int i : A[v]) {
        if (!visited[i]) DFS(i);
    }
}
```