import java.util.*;

public class SuggestFriendsDFS<T> {

    private HashMap<T, ArrayList<T>> adj = new HashMap<>(); //graph
    private List<Set<T>> groups = new ArrayList<>();

    //Time O(1), Space O(1)
    public void addFriendship(T src, T dest) {
        adj.putIfAbsent(src, new ArrayList<T>());
        adj.get(src).add(dest);
        adj.putIfAbsent(dest, new ArrayList<T>());
        adj.get(dest).add(src);
    }

    //DFS wrapper, Time O(V+E), Space O(V)
    //V is total number of people, E is number of connections
    private void findGroups() {
        Map<T, Boolean> visited = new HashMap<>();
        for (T t: adj.keySet())
            visited.put(t, false);
        for (T t:adj.keySet()) {
            if (!visited.get(t)) {
                Set<T> group = new HashSet<>();
                dfs(t, visited, group);
                groups.add(group);
            }
        }
    }

    //DFS + memoization, Time O(V+E), Space O(V)
    private void dfs(T v, Map<T, Boolean> visited, Set<T> group ) {
        visited.put(v,true);
        group.add(v);
        for (T x : adj.get(v)) {
            if (!visited.get(x))
                dfs(x, visited, group);
        }
    }

    //Time O(V+E), Space O(V)
    public Set<T> getSuggestedFriends (T a) {
        if (groups.isEmpty())
            findGroups();
        Set<T> res = new HashSet<>();
        for (Set<T> t : groups) {
            if (t.contains(a)) {
                res = t;
                break;
            }
        }
        if (res.size() > 0)
            res.remove(a);
        return res;
    }

    public static void main(String[] args) {

        SuggestFriendsDFS<String> g = new SuggestFriendsDFS<>();
        g.addFriendship("Ashley", "Christopher");
        g.addFriendship("Ashley", "Emily");
        g.addFriendship("Ashley", "Joshua");
        g.addFriendship("Bart", "Lisa");
        g.addFriendship("Bart", "Matthew");
        g.addFriendship("Christopher", "Andrew");
        g.addFriendship("Emily", "Joshua");
        g.addFriendship("Jacob", "Christopher");
        g.addFriendship("Jessica", "Ashley");
        g.addFriendship("JorEl", "Zod");
        g.addFriendship("KalEl", "JorEl");
        g.addFriendship("Kyle", "Lex");
        g.addFriendship("Kyle", "Zod");
        g.addFriendship("Lisa", "Marge");
        g.addFriendship("Matthew", "Lisa");
        g.addFriendship("Michael", "Christopher");
        g.addFriendship("Michael", "Joshua");
        g.addFriendship("Michael", "Jessica");
        g.addFriendship("Samantha", "Matthew");
        g.addFriendship("Samantha", "Tyler");
        g.addFriendship("Sarah", "Andrew");
        g.addFriendship("Sarah", "Christopher");
        g.addFriendship("Sarah", "Emily");
        g.addFriendship("Tyler", "Kyle");
        g.addFriendship("Stuart", "Jacob");

        g.findGroups();
        System.out.println(g.groups);

        String name = "Stuart";
        System.out.println("Suggestion friends of " + name + ": " + g.getSuggestedFriends(name));
    }
}