## BFS(Breadth First Search)

#### 너비 우선 탐색이란
시작 노드로부터 한 단계식 내려가며 같은 단계에 있는 노드들 부터 먼저 방문

<img src="assets/DFS_BFS.gif">

#### 특징
1. 선입선출(FIFO) 방식
2. 큐(Queue)를 주로 사용

#### 예시 코드
``` java
private static void BFS(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        
        while (!queue.isEmpty()) {
            int q = queue.poll();
            System.out.print(q + " ");
            
            for (int i : A[q]) {
                if (!visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }
```

