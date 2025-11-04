package leetcode._15_last_minute.courseSchedule;

import java.util.*;

/*
207. Course Schedule
Medium
Topics
conpanies icon
Companies
Hint
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.



Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].

Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
All the pairs prerequisites[i] are unique.
 */
public class CourseSchedule {

    Map<Integer, List<Integer>> depMap;
    boolean cycle;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites.length == 0) {
            return true;
        }

        depMap = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            int dep = prerequisite[1];
            int course = prerequisite[0];

            List<Integer> deps = depMap.getOrDefault(course, new ArrayList<>());
            deps.add(dep);
            depMap.putIfAbsent(course, deps);
        }

        boolean[] visited = new boolean[numCourses];

        for(int i=0; i < numCourses; i++) {
            boolean[] visiting = new boolean[numCourses];
            if(!visited[i]) {
                boolean cycle = dfs(i, visiting, visited);
                if(cycle) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean dfs(Integer course, boolean[] visiting, boolean[] visited) {
        if(!depMap.containsKey(course)) {
            return false;
        }
        if(visiting[course]) {
            // cycle = true;
            return true;
        }

        visiting[course] = true;
        List<Integer> deps = depMap.get(course);
        for(Integer dep: deps) {
            if(!visited[dep]) {
                boolean isCycle = dfs(dep, visiting, visited);
                if(isCycle) {
                    return true;
                }
            }

        }
        visiting[course] = false;;
        visited[course] = true;
        return false;
    }

    public static void main(String[] args ){
        CourseSchedule cs = new CourseSchedule();
        System.out.println(cs.canFinish(4, new int[][]{{1,0},{2,0},{3,1},{3,2}}));
        System.out.println(cs.canFinish(5, new int[][]{{1,4},{2,4},{3,1},{3,2}}));
        System.out.println(cs.canFinish(4, new int[][]{{1,0},{0,1}}));
        System.out.println(cs.canFinish(5, new int[][]{{1,2},{1,3},{0,4}}));
        System.out.println(cs.canFinish(5, new int[][]{{1,2},{1,3},{0,4}}));
    }
}
