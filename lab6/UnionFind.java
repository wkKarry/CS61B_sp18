public class UnionFind {
    private int[] vertices;
    int max_index;

    public UnionFind(int n) {
        vertices = new int[n];
        max_index = n - 1;
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = -1;
        }
    }

    public void validate(int v1) {
        if (v1 > max_index) {
            throw new IllegalArgumentException("The given vertex number is out of bounds");
        }
    }

    public int sizeOf(int v1) {
        validate(v1);
        return -vertices[find(v1)];
    }

    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        if (connected(v1, v2)) {
            return;
        }
        if (sizeOf(v1) > sizeOf(v2)) {
            vertices[find(v2)] = find(v1);
            vertices[find(v1)] -= sizeOf(v2);
        } else {
            vertices[find(v1)] = find(v2);
            vertices[find(v2)] -= sizeOf(v1);
        }

    }

    public int parent(int v1) {
        validate(v1);
        return vertices[v1];
    }

    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    public int find(int v1) {
        validate(v1);
        while (vertices[v1] >= 0) {
            int index = v1;
            v1 = parent(v1);
        }
        return v1;
    }

}
